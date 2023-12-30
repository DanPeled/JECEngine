package org.game;

import org.game.Engine.Classes.Components.Collider;
import org.game.Engine.Classes.Components.Renderer;
import org.game.Engine.Classes.Components.Rigidbody2D;
import org.game.Engine.Systems.EngineTime;
import org.game.Engine.Systems.JECEngine;
import org.game.Engine.Classes.Vec2;
import org.game.Engine.Systems.Input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Panel extends JPanel implements Runnable {

    Thread panelThread;
    private volatile boolean isRunning = true;
    final int FPS = 60;
    Rect rect = new Rect(new Vec2(100, 100, 0)), rect2 = new Rect(new Vec2(0, 400, 0));
    Graphics graphics;
    Label xvelLabel = new Label("Y Velocity: 0"), yvelLabel = new Label("X Velocity: 0"), fpsLabel = new Label("FPS: 0");

    public Panel() {
        JECEngine.setPanel(this);
        JECEngine.init();
        rect2.getComponent(Rigidbody2D.class).setEnabled(false);
        rect2.getComponent(Collider.class).shape = new Rectangle2D.Double(0, 0, 600, 90);
        rect2.getComponent(Renderer.class).shape = new Rectangle2D.Double(0, 0, 600, 90);
        rect.addComponent(PlayerMovement.class);
        rect.getComponent(Renderer.class).color = Color.red;
        rect2.getComponent(Renderer.class).color = Color.BLACK;
        xvelLabel.setForeground(new Color(0, 0, 0, 0));
        yvelLabel.setForeground(new Color(0, 0, 0, 0));
        this.add(xvelLabel);
        this.add(yvelLabel);
        this.add(fpsLabel);
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
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (isRunning) {
            currentTime = System.currentTimeMillis();
            EngineTime.deltaTime = (currentTime - lastTime) / 1000.0;  // Update deltaTime in seconds
            lastTime = currentTime;
            timer += EngineTime.deltaTime;

            if (timer >= 2) {
                System.out.println("FPS: " + drawCount);
                fpsLabel.setText(String.format("FPS: %.2f", drawCount));
                drawCount = 0;
                timer = 0;
            }

            update();
            repaint();
            drawCount++;

            try {
                // Adjust the sleep time to achieve the desired FPS
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.graphics = graphics;
        JECEngine.updateEntities(graphics);
        Input.resetClickStates();
        yvelLabel.setText(String.format("Y Velocity: %.2f", rect.getComponent(Rigidbody2D.class).velocity.y));
        xvelLabel.setText(String.format("X Velocity: %.2f", rect.getComponent(Rigidbody2D.class).velocity.x));
    }
}
