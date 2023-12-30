package org.game.Engine.Systems;

import org.game.Engine.Classes.Entity;
import org.game.Engine.Systems.Input.Input;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The main engine class for managing entities in the game.
 */
public class JECEngine {
    private static final Input input = new Input();
    private static final int originalTileSize = 16;
    private static final int scale = 3;
    private static final int tileSize = originalTileSize * scale;
    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = tileSize * maxScreenCol;
    private static final int screenHeight = tileSize * maxScreenRow;

    /**
     * Set containing all entities currently active in the game.
     */
    public static HashMap<Integer, Entity> entities;

    public static void setPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        panel.setBackground(Color.gray);
        panel.setDoubleBuffered(true);
        panel.addKeyListener(input);
        panel.addMouseListener(input);
        panel.addMouseMotionListener(input);
        panel.setFocusable(true);
        panel.requestFocus();
    }

    /**
     * Initializes the game engine by creating the set of entities if it does not exist.
     */
    public static void init() {
        if (entities == null) {
            entities = new HashMap<>();
            EngineTime.init();
        }
    }

    /**
     * Instantiates a new entity and adds it to the set of active entities.
     *
     * @param entity The entity to be instantiated and added.
     */
    public static void instantiate(Entity entity) {
        entities.put(entity.getID(), entity);
    }

    /**
     * Updates all active entities in the game.
     *
     * @param g The graphics context for rendering entities.
     * @throws RuntimeException If an exception occurs during the update process.
     */
    public static void updateEntities(Graphics g) {
        for (Entity e : entities.values()) {
            try {
                int parentId = e.getParentID();
                Entity parent = entities.get(parentId);
                boolean isEnabled = e.getEnabled();
                boolean isParentEnabled = parent == null || parent.getEnabled();

                if (isEnabled && isParentEnabled) {
                    e.update(g);
                }
            } catch (Exception ex) {
                throw new RuntimeException(String.format("Error updating entity: %s ID: %d", e.getClass().getSimpleName(), e.getID()), ex);
            }
        }
    }

}
