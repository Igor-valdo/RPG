package entity;

import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import main.painelDeJogo;

public class enemy extends entity {

    public int enemyId;

    // IA básica de patrulha
    public int limiteEsquerda;
    public int limiteDireita;
    public boolean movendoParaDireita = true;
    public painelDeJogo gp;


    // CONSTRUTOR
    public enemy(
        painelDeJogo gp,
        int enemyId,
        int xInicial,
        int yInicial
    ) {
        this.gp = gp;
        this.enemyId = enemyId;

        this.x = xInicial;
        this.y = yInicial;

        // Limite da patrulha
        this.limiteEsquerda = xInicial - 200;
        this.limiteDireita = xInicial + 200;

        // Configura o inimigo
        setupEnemyType();

        // Corrige posição inicial
        normalizaHitbox();
    }


    // CONFIGURAÇÃO DOS TIPOS DE INIMIGO
    private void setupEnemyType() {

        try {

            switch (enemyId) {

                // ==========================================
                // SLIME
                // ==========================================
                case 1:

                    speed = 1;

                    // Tamanho que aparecerá na tela
                    width = 96;
                    height = 96;

                    // Hitbox
                    solidArea = new Rectangle(
                        26,
                        30,
                        48,
                        64
                    );

                    solidAreaDefaultX = solidArea.x;
                    solidAreaDefaultY = solidArea.y;


                    // --------------------------
                    // SLIME PARADO
                    // --------------------------

                    File slimeParado = new File(
                        "RPG/src/enemy/slime/slime.png"
                    );

                    defaultFrameDireita =
                        ImageIO.read(slimeParado);

                    defaultFrameEsquerda =
                        defaultFrameDireita;

                    // --------------------------
                    // SLIME ANDANDO
                    // --------------------------

                    for (int i = 0; i < 8; i++) {

                        File frame = new File(
                            "RPG/src/enemy/slime/slime_andando_"
                            + (i + 1)
                            + ".png"
                        );

                        walkingFrames[i] =
                            ImageIO.read(frame);
                    }

                    break;


                // ==========================================
                // ESQUELETO
                // ==========================================
                case 2:

                    speed = 2;

                    width = 128;
                    height = 128;

                    // Hitbox
                    solidArea = new Rectangle(
                        50,
                        20,
                        30,
                        105
                    );

                    solidAreaDefaultX = solidArea.x;
                    solidAreaDefaultY = solidArea.y;


                    // --------------------------
                    // ESQUELETO
                    // --------------------------

                    File esqueleto = new File(
                        "RPG/src/enemy/Esqueleto/Esqueleto.png"
                    );

                    File esqueletoAndando = new File(
                        "RPG/src/enemy/Esqueleto/Esqueleto_andando_1.png"
                    );

                    defaultFrameDireita =
                        ImageIO.read(esqueleto);

                    defaultFrameEsquerda =
                        defaultFrameDireita;


                    // Por enquanto o esqueleto não
                    // possui animação separada.
                    // Então repetimos a mesma imagem.
                    for (int i = 0; i < 8; i++) {
                        walkingFrames[i] =
                            ImageIO.read(esqueletoAndando);
                    }

                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean encostouPlayer(int movimentoX, int movimentoY) {
        // Calcula onde a hitbox do inimigo ficará
        Rectangle proximaHitbox = new Rectangle(
            x + solidArea.x + movimentoX,
            y + solidArea.y + movimentoY,
            solidArea.width,
            solidArea.height
        );

        // Verifica colisão com o player
        if (proximaHitbox.intersects(gp.player.getHitBox())) {
            return true;
        }

        return false;
    }

    private void normalizaHitbox() {
        // Parte mais baixa da hitbox do inimigo
        int baseHitbox = y + solidArea.y + solidArea.height;

        // Se a hitbox entrou no chão, sobe o inimigo
        if (baseHitbox > gp.yChao) {
            y = gp.yChao - solidArea.y - solidArea.height;
        }
    }

    // ==========================================
    // UPDATE
    // ==========================================
    public void update() {

        // Anda para a direita
        if (movendoParaDireita) {
            olhandoParaEsquerda = false;
            if (!encostouPlayer(speed, 0)) {
                x += speed;
                andando = true;
            }
            // Chegou no limite direito
            if (x >= limiteDireita) {
                movendoParaDireita = false;
            }
        }

        // Anda para a esquerda
        else {
            
            olhandoParaEsquerda = true;
            if (!encostouPlayer(-speed, 0)) {
                x -= speed;
                andando = true;
            }

            // Chegou no limite esquerdo
            if (x <= limiteEsquerda) {
                movendoParaDireita = true;
            }
        }

        // Atualiza a animação
        updateAnimation();
    }
}