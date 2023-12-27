package org.game.Engine.Classes;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class JECEngine {
    public static Set<Entity> entities;

    public static void init() {
        if (entities == null) {
            entities = new HashSet<Entity>();
        }
    }

    public static void instantiate(Entity entity) {
        entities.add(entity);
    }

    public static void updateEntities(Graphics g) {
        for (Entity e : entities) {
            try {
                e.update(g);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
