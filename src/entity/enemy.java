package entity;

import java.awt.Rectangle;
import javax.imageio.ImageIO;
import main.painelDeJogo;

public class enemy extends entity {

    public int enemyId;
    
    // IA básica de patrulha
    public int limiteEsquerda, limiteDireita;
    public boolean movendoParaDireita = true;

    public enemy(painelDeJogo gp, int enemyId, int xInicial, int yInicial) {
        this.enemyId = enemyId;
        this.x = xInicial;
        this.y = yInicial;

        // Limites de patrulha simples (100px para cada lado)
        this.limiteEsquerda = xInicial - 100;
        this.limiteDireita = xInicial + 100;

        // Configura atributos e sprites de acordo com o ID
        setupEnemyType();
    }

    private void setupEnemyType() {
        try {
            switch (enemyId) {
                case 1: // Exemplo: Inimigo Terrestre Padrão
                    speed = 1;
                    width = 96;  // Tamanho personalizado
                    height = 96;
                    
                    // Hitbox proporcional ao tamanho do sprite
                    solidArea = new Rectangle(20, 20, 56, 76);
                    
                    spriteSheet = ImageIO.read(getClass().getResourceAsStream("/enemy/slime.png"));
                    break;

                case 2: // Exemplo: Inimigo Rápido/Menor
                    speed = 3;
                    width = 64;
                    height = 64;
                    
                    solidArea = new Rectangle(10, 10, 44, 54);
                    
                    spriteSheet = ImageIO.read(getClass().getResourceAsStream("/enemy/Esqueleto.png"));
                    break;
            }

            // Separa os quadros da animação (se o spritesheet foi carregado)
            if (spriteSheet != null) {
                for (int i = 0; i < 8; i++) {
                    walkingFrames[i] = spriteSheet.getSubimage(i * 64, 0, 64, 64);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar os sprites do inimigo ID: " + enemyId);
            e.printStackTrace();
        }
    }

    public void update() {
        // Movimentação de patrulha básica
        if (movendoParaDireita) {
            x += speed;
            andando = true;
            olhandoParaEsquerda = false;
            if (x >= limiteDireita) {
                movendoParaDireita = false;
            }
        } else {
            x -= speed;
            andando = true;
            olhandoParaEsquerda = true;
            if (x <= limiteEsquerda) {
                movendoParaDireita = true;
            }
        }

        // Atualiza animação herdada da classe entity
        updateAnimation();
    }
}