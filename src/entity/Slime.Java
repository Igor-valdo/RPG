package entity;

import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;
import main.painelDeJogo;

public class Slime extends enemy {

    public Slime(painelDeJogo gp, int xInicial, int yInicial) {
        super(gp, 1, xInicial, yInicial);
    }

    @Override
    protected void setupEnemyType() {
        try {
            // ==========================================
            // SLIME
            // ==========================================
            speed = 1;

            // Tamanho que aparecerá na tela
            width = 96;
            height = 96;

            // Hitbox
            solidArea = new Rectangle(
                12,
                8,
                40,
                56
            );

            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;

            File slime = new File(
                "RPG/src/enemy/Slime/Slime.png"
            );

            defaultFrameDireita =
                ImageIO.read(slime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}