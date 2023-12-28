package org.game.Engine.Systems;

import org.game.Engine.Classes.Vec2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Input class handles user input including keyboard and mouse events.
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private static final Set<Integer> pressedKeys = new HashSet<>();
    private static final Set<Integer> clickedKeys = new HashSet<>();
    private static final boolean[] mouseButtons = new boolean[MouseInfo.getNumberOfButtons()];
    private static final boolean[] clickedMouseButtons = new boolean[MouseInfo.getNumberOfButtons()];
    private static int mouseX, mouseY;

    /**
     * Invoked when a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.add(keyCode);
        clickedKeys.add(keyCode);
    }

    /**
     * Invoked when a key is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        pressedKeys.remove(keyCode);
    }

    /**
     * Invoked when the mouse is clicked (pressed and released).
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button is pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        mouseButtons[button] = true;
        clickedMouseButtons[button] = true;
    }

    /**
     * Invoked when a mouse button is released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        mouseButtons[button] = false;
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Invoked when the mouse is moved.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * Invoked when the mouse is dragged.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e); // Delegate to mouseMoved for consistency
    }

    /**
     * Checks if a specific key is currently pressed.
     *
     * @param keyCode The code of the key to check.
     * @return True if the key is pressed, false otherwise.
     */
    public static boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    /**
     * Checks if a specific key was clicked (pressed and released) in the current frame.
     *
     * @param keyCode The code of the key to check.
     * @return True if the key was clicked, false otherwise.
     */
    public static boolean isKeyClicked(int keyCode) {
        return clickedKeys.contains(keyCode);
    }

    /**
     * Checks if a specific mouse button is currently pressed.
     *
     * @param button The code of the mouse button to check.
     * @return True if the button is pressed, false otherwise.
     */
    public static boolean isMouseButtonPressed(int button) {
        return mouseButtons[button];
    }

    /**
     * Checks if a specific mouse button was clicked (pressed and released) in the current frame.
     *
     * @param button The code of the mouse button to check.
     * @return True if the button was clicked, false otherwise.
     */
    public static boolean isMouseButtonClicked(int button) {
        return clickedMouseButtons[button];
    }

    /**
     * Gets the current mouse position.
     *
     * @return A Vec2 object representing the mouse position.
     */
    public static Vec2 getMousePosition() {
        return new Vec2(mouseX, mouseY);
    }

    /**
     * Resets the clicked state of keys and mouse buttons at the end of each frame.
     */
    public static void resetClickStates() {
        clickedKeys.clear();
        Arrays.fill(clickedMouseButtons, false);
    }
}
