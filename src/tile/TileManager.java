package tile;

import main.painelDeJogo;

public class TileManager {

    painelDeJogo gp;
    Tile[] tile;

    public TileManager(painelDeJogo gp) {
        this.gp = gp;
        tile = new Tile[10]; // Supondo que você tenha 10 tipos de tiles
        getTileImage();
    }

    public void getTileImage() {
        // Aqui você carregaria as imagens dos tiles
        // Exemplo:
        // tile[0] = new Tile();
        // tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        // tile[0].collision = false; // Se o tile não tiver colisão
        try {
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Ceu.png"));
            tile[0].collision = true; // Exemplo de tile sem colisão

            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Chao_1.png"));
            tile[1].collision = true; // Exemplo de tile com colisão

            tile[2] = new Tile();
            tile[2].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Chao_2.png"));
            tile[2].collision = true; // Exemplo de tile com colisão

            tile[3] = new Tile();
            tile[3].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Parede_1.png"));
            tile[3].collision = true; // Exemplo de tile com colisão

            tile[4] = new Tile();
            tile[4].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Parede_2.png"));
            tile[4].collision = true; // Exemplo de tile com colisão

            tile[5] = new Tile();
            tile[5].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Chao_Buraco_1.png"));
            tile[5].collision = true; // Exemplo de tile com colisão

            tile[6] = new Tile();
            tile[6].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Chao_Buraco_2.png"));
            tile[6].collision = true; // Exemplo de tile com colisão

            tile[7] = new Tile();
            tile[7].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Parede_Buraco_1.png"));
            tile[7].collision = true; // Exemplo de tile com colisão

            tile[8] = new Tile();
            tile[8].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Parede_Buraco_2.png"));
            tile[8].collision = true; // Exemplo de tile com colisão

            tile[9] = new Tile();
            tile[9].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/TileSet/Fundo.png"));
            tile[9].collision = true; // Exemplo de tile com colisão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g2) {
        int col = 0;
        int row = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = gp.tileM.mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
            col++;

            if (col == gp.maxScreenCol) {
                col = 0;
                row++;
            }
        }
    }
}