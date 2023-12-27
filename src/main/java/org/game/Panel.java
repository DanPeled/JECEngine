package org.game;

import org.game.Engine.Classes.Components.Collider;
import org.game.Engine.Classes.Components.Renderer;
import org.game.Engine.Classes.Components.Rigidbody2D;
import org.game.Engine.Classes.EngineTime;
import org.game.Engine.Classes.JECEngine;
import org.game.Engine.Classes.Vec2;
import org.game.Engine.Systems.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Panel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    Input input = new Input();
    Thread panelThread;
    private volatile boolean isRunning = true;
    final int FPS = 60;
    Rect rect = new Rect(new Vec2(100, 100, 0)), rect2 = new Rect(new Vec2(0, 400, 0));
    Graphics graphics;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.setFocusable(true);
        this.requestFocus();
        JECEngine.init();
        rect2.getComponent(Rigidbody2D.class).setEnabled(false);
        rect2.getComponent(Collider.class).shape = new Rectangle2D.Double(0, 0, 900, 90);
        rect2.getComponent(Renderer.class).shape = new Rectangle2D.Double(0, 0, 900, 90);
        rect.addComponent(PlayerMovement.class);
    }

    public void startPanelThread() {
        panelThread = new Thread(this);
        panelThread.start();
    }

    public void stopPanelThread() {
        isRunning = false;
        try {
            panelThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (isRunning) {
            currentTime = System.nanoTime();
            EngineTime.deltaTime += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (EngineTime.deltaTime >= 1) {
                update();
                repaint();
                EngineTime.deltaTime--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.graphics = graphics;
        JECEngine.updateEntities(graphics);
    }
}
