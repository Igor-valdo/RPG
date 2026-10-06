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

    private boolean encostouInimigo(int movimentoX, int movimentoY){
        //Calcula onde a hitbox do player vai depois de se mover
        Rectangle proximaHitbox = new Rectangle(
            x + solidArea.x + movimentoX,
            y + solidArea.y + movimentoY,
            solidArea.width,
            solidArea.height
        );
        // Verifica se a próxima posição da hitbox colide com algum obstáculo
        // (Implementação da verificação de colisão)
        for (enemy inimigo : gp.inimigos){
            if (inimigo != null) {
                if (proximaHitbox.intersects(inimigo.getHitBox())){
                    return false;
                }
            }
        }
        return true;
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
            olhandoParaEsquerda = true;
            if (encostouInimigo(-speed, 0)) {
                x -= speed;
                andando = true;
            }
        }

        if (controle.direitaPressionado) {
            olhandoParaEsquerda = false;
            if (encostouInimigo(speed, 0)) {
                x += speed;
                andando = true;
            }
        }

        // Atualiza os quadros da animação (método herdado de entity)
        updateAnimation();
    }
}