package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.Vec2;
import org.game.Engine.Systems.JECEngine;

import java.awt.Graphics;

/**
 * Represents the transformation component of a game object.
 */
public class Transform extends EntityComponent {

    /**
     * The position of the game object in 2D space
     */
    private Vec2 position;

    @Override
    public void start() {
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    public void onStop() {
    }

    public void setPosition(Vec2 newPosition) {
        this.position = newPosition;
        for (Object childID : entity.getChildrenIDs()) {
            Entity child = JECEngine.entities.get(childID);
//            child.transform.setPosition(entity.transform.getPosition().subtract(child.transform.getPosition()));
        }
    }

    public void setX(double newX) {
        this.position.x = newX;
    }

    public void setY(double newY) {
        this.position.y = newY;
    }

    public Vec2 getPosition() {
        return position;
    }
}
