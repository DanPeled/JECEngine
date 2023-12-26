package org.game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Game");

            Panel panel = new Panel();
            window.add(panel);
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            panel.startPanelThread(); // Start the game loop thread
        });
    }
}