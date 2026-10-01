package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entity {
    // Posição e velocidade
    public int x, y;
    public int speed;

    // Tamanho visual
    public int width = 144;
    public int height = 144;

    // Hitbox
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisao = false;
    
    // Imagens e Sprites
    public BufferedImage defaultFrameEsquerda;
    public BufferedImage defaultFrameDireita;
    public BufferedImage spriteSheet;
    public BufferedImage[] walkingFrames = new BufferedImage[8];
    public BufferedImage[] jumpingFrames = new BufferedImage[8];

    // Controle da Animação
    public int currentFrame = 0;
    public int animationCounter = 0;
    public int animationSpeed = 8;

    // Estados
    public boolean andando = false;
    public boolean pulando = false; 
    public boolean noChao = true; 
    public boolean olhandoParaEsquerda = false;

    // Física básica
    public double velocidadeY = 0;
    public double gravidade = 0.23;

    // Método de animação comum para qualquer entidade
    public void updateAnimation() {
        if (andando || pulando) {
            animationCounter++;
            if (animationCounter >= animationSpeed) {
                currentFrame = (currentFrame + 1) % 8;
                animationCounter = 0;
            }
        } else {
            currentFrame = 0;
        }
    }

    // Método genérico para desenhar a entidade
    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;

        if (pulando && jumpingFrames[0] != null) {
            imagem = jumpingFrames[currentFrame];
        } 
        else if (andando && walkingFrames[0] != null) {
            imagem = walkingFrames[currentFrame];
        }
        else if (olhandoParaEsquerda){
            imagem = defaultFrameEsquerda;
        }
        else {
            imagem = defaultFrameDireita;
        }

        if (imagem != null) {
            if (olhandoParaEsquerda && (andando || pulando)) {
                g2.drawImage(imagem, x + width, y, -width, height, null);
            } else {
                g2.drawImage(imagem, x, y, width, height, null);
            }
        }

        // Hitbox de debug
        if (solidArea != null) {
            g2.setColor(Color.RED);
            g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public Rectangle getHitBox(){
        return new Rectangle(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }
}