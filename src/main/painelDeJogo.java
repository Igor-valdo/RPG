package main;
import entity.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class painelDeJogo extends JPanel implements Runnable{
    public final int originalTileSize = 24; // 24x24 pixels
    public final int scale = 3; // define a escala que o tamanho do tile será multiplicado

    public final int tileSize = originalTileSize * scale; // 64x764 pixels
    public final int maxScreenCol = 16; // 16 tiles por linha
    public final int maxScreenRow = 9; // 18 tiles por coluna
    public final int screenWidth = tileSize * maxScreenCol; // define a largura da tela em 1024 pixels
    public final int screenHeight = tileSize * maxScreenRow; // define a altura da tela em 576 pixels

    public final int yChao = screenHeight - tileSize; //

    //FPS
    int FPS = 120;
    int mls = 1000000; //milissegundos em nanosegundos
    int nanos = 1000000000; // nanosegundos em segundos

    //Posição do jogador
    // Posição X e Y do jogador no meio da tela:
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3; // Velocidade do jogador

    controleDeComandos controle = new controleDeComandos();
    Thread gameThread;
    public player player = new player(this, controle);
    public ArrayList<enemy> inimigos = new ArrayList();

    public void setupGame(){
        enemy slime = new enemy(this, 1, 500, yChao - 96);
        inimigos.add(slime);
        enemy esqueleto = new enemy(this, 2, 800, yChao - 96);
        inimigos.add(esqueleto);
    }

    public painelDeJogo(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(controle);
        this.setFocusable(true);
        setupGame();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) nanos / FPS;
        double delta = 0;
        double timer = 0.00;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime(); // Corrigido o ponto e vírgula
            
            // Calcula a fração de tempo passada desde o último loop
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            // Quando completar 1 ciclo de quadro (1/60s), atualiza e redesenha
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount +=1;
            }
            if (timer >= nanos){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        // Aqui você pode atualizar a lógica do jogo
        player.update();
        for (int i=0;i<inimigos.size();i++){
            if (inimigos.get(i) != null){
                inimigos.get(i).update();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //converte para 2D para ter mais controle sobre o desenho
        // Aqui você pode desenhar os elementos do jogo
        //Chão
        g2.setColor(Color.GREEN);
        for (int col = 0; col < maxScreenCol; col++){
            g2.fillRect(col*tileSize,yChao,tileSize,tileSize);
        }
        player.draw(g2);
        for (int i = 0; i < inimigos.size(); i++) {
            if (inimigos.get(i) != null) {
                inimigos.get(i).draw(g2);
            }
        }
        g2.dispose();
    }

}