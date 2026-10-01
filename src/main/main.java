package main;

import javax.swing.*;

public class main {

    public static void main(String[] args) {
        // É uma boa prática inicializar a interface Swing usando SwingUtilities.invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame janela = new JFrame("Aventura 2D");
                janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janela.setResizable(false);

                painelDeJogo painel = new painelDeJogo();
                janela.add(painel);

                janela.pack(); // Ajusta o tamanho da janela de acordo com o painel
                janela.setLocationRelativeTo(null); // Centraliza na tela
                janela.setVisible(true);

                painel.startGameThread(); // Inicia o loop do jogo
            }
        });
    }
}