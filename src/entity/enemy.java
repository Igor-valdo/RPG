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


    // CONSTRUTOR
    public enemy(
        painelDeJogo gp,
        int enemyId,
        int xInicial,
        int yInicial
    ) {

        this.enemyId = enemyId;

        this.x = xInicial;
        this.y = yInicial;

        // Limite da patrulha
        this.limiteEsquerda = xInicial - 100;
        this.limiteDireita = xInicial + 100;

        // Configura o inimigo
        setupEnemyType();
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
                        20,
                        20,
                        56,
                        76
                    );

                    solidAreaDefaultX = solidArea.x;
                    solidAreaDefaultY = solidArea.y;


                    // --------------------------
                    // SLIME PARADO
                    // --------------------------

                    File slimeParado = new File(
                        "src/enemy/slime/slime.png"
                    );

                    System.out.println(
                        "Slime parado: "
                        + slimeParado.getAbsolutePath()
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
                            "src/enemy/slime/slime_andando_"
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

                    speed = 3;

                    width = 64;
                    height = 64;

                    // Hitbox
                    solidArea = new Rectangle(
                        12,
                        8,
                        40,
                        56
                    );

                    solidAreaDefaultX = solidArea.x;
                    solidAreaDefaultY = solidArea.y;


                    // --------------------------
                    // ESQUELETO
                    // --------------------------

                    File esqueleto = new File(
                        "src/enemy/Esqueleto.png"
                    );

                    System.out.println(
                        "Esqueleto: "
                        + esqueleto.getAbsolutePath()
                    );

                    System.out.println(
                        "Existe? "
                        + esqueleto.exists()
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
                            defaultFrameDireita;
                    }

                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ==========================================
    // UPDATE
    // ==========================================
    public void update() {

        // Anda para a direita
        if (movendoParaDireita) {

            x += speed;
            andando = true;
            olhandoParaEsquerda = false;

            // Chegou no limite direito
            if (x >= limiteDireita) {
                movendoParaDireita = false;
            }
        }

        // Anda para a esquerda
        else {

            x -= speed;
            andando = true;
            olhandoParaEsquerda = true;

            // Chegou no limite esquerdo
            if (x <= limiteEsquerda) {
                movendoParaDireita = true;
            }
        }

        // Atualiza a animação
        updateAnimation();
    }
}