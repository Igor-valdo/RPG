package entity;

import java.awt.*;
import javax.imageio.ImageIO;
import main.controleDeComandos;
import main.painelDeJogo;

public class player extends entity {

    painelDeJogo gp;
    controleDeComandos controle;

    // Define gravidade e velocidade de pulo específicas do player
    int alturaPulo = 160;
    double velocidadePulo = -8.30;

    public player(painelDeJogo gp, controleDeComandos controle){
        this.gp = gp;
        this.controle = controle;

        // Criando a Hitbox
        solidArea = new Rectangle();
        solidArea.x = 50;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 40;
        solidArea.height = 110;

        setDeaultValue();
        loadFrame();
    }

    private void loadFrame() {
        try {
            defaultFrameDireita = ImageIO.read(getClass().getResourceAsStream("/player/Aveel_direita.png"));
            defaultFrameEsquerda = ImageIO.read(getClass().getResourceAsStream("/player/Aveel_esquerda.png"));
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/personagem_sprites_64x64.png"));

            for (int i = 0; i < 8; i++) {
                walkingFrames[i] = spriteSheet.getSubimage(i * 64, 0, 64, 64);
                jumpingFrames[i] = spriteSheet.getSubimage(i * 64, 64, 64, 64);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDeaultValue(){
        x = 100;
        y = gp.yChao - height;
        speed = 3;
    }

    public void update() {
        andando = false;

        // Movimentação por teclado
        if (controle.cimaPressionado && noChao) {
            velocidadeY = velocidadePulo;
            noChao = false;
            pulando = true;
        }

        if (!noChao) {
            velocidadeY += gravidade;
            y += velocidadeY;
        }

        if (y + height >= gp.yChao) {
            y = gp.yChao - height;
            velocidadeY = 0;
            noChao = true;
            pulando = false;
        }

        if (controle.esquerdaPressionado) {
            x -= speed;
            andando = true;
            olhandoParaEsquerda = true;
        }

        if (controle.direitaPressionado) {
            x += speed;
            andando = true;
            olhandoParaEsquerda = false;
        }

        // Atualiza os quadros da animação (método herdado de entity)
        updateAnimation();
    }
}