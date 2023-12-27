package org.game.Engine.Systems;

import org.game.Engine.Classes.Vec2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private static final Set<Integer> pressedKeys = new HashSet<>();
    private static final boolean[] mouseButtons = new boolean[MouseInfo.getNumberOfButtons()];
    private static int mouseX, mouseY;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        pressedKeys.add(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        pressedKeys.remove(code);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        mouseButtons[button] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        mouseButtons[button] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public static boolean getMouseButtonPressed(int button) {
        return mouseButtons[button];
    }

    public static Vec2 getMousePosition() {
        return new Vec2(mouseX, mouseY);
    }
}
