package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class controleDeComandos implements KeyListener {

    public boolean cimaPressionado, baixoPressionado, esquerdaPressionado, direitaPressionado, spacePressionado, xPressionado, zPressionado;

    @Override
    public void keyTyped(KeyEvent e) {
        // Não costuma ser usado para movimentação em jogos
    }

    @Override 
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            cimaPressionado = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            baixoPressionado = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            esquerdaPressionado = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            direitaPressionado = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressionado = true;
        }
        if (code == KeyEvent.VK_X) {
            xPressionado = true;
        }
        if (code == KeyEvent.VK_Z) {
            zPressionado = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            cimaPressionado = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            baixoPressionado = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            esquerdaPressionado = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            direitaPressionado = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressionado = false;
        }
        if (code == KeyEvent.VK_X) {
            xPressionado = false;
        }
        if (code == KeyEvent.VK_Z) {
            zPressionado = false;
        }
    }
}