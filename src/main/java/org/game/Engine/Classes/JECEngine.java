package org.game.Engine.Classes;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The main engine class for managing entities in the game.
 */
public class JECEngine {

    /**
     * Set containing all entities currently active in the game.
     */
    public static Set<Entity> entities;

    /**
     * Initializes the game engine by creating the set of entities if it does not exist.
     */
    public static void init() {
        if (entities == null) {
            entities = new HashSet<>();
        }
    }

    /**
     * Instantiates a new entity and adds it to the set of active entities.
     *
     * @param entity The entity to be instantiated and added.
     */
    public static void instantiate(Entity entity) {
        entities.add(entity);
    }

    /**
     * Updates all active entities in the game.
     *
     * @param g The graphics context for rendering entities.
     * @throws RuntimeException If an exception occurs during the update process.
     */
    public static void updateEntities(Graphics g) {
        for (Entity e : entities) {
            try {
                if (e.getEnabled()) {
                    e.update(g);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Error updating entity: " + e.getClass().getSimpleName(), ex);
            }
        }
    }
}
