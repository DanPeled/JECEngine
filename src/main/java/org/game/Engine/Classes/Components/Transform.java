package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.Vec2;
import org.game.Engine.Systems.JECEngine;

import java.awt.Graphics;
import java.util.Arrays;

/**
 * Represents the transformation component of a game object.
 */
public class Transform extends EntityComponent {

    /**
     * The position of the game object in 2D space
     */
    private Vec2 position;
    private Vec2 parentOffset = new Vec2(0, 0);

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
        entity.getChildrenIDs();
        for (Object childID : entity.getChildrenIDs()) {
            Entity child = JECEngine.entities.get(childID);

            Vec2 parentOffset = child.transform.getParentOffset();
            Vec2 childPosition = child.transform.getPosition();

            // Calculate the new position for the child based on the parent's new position and parent offset
            Vec2 newChildPosition = newPosition.add(parentOffset);
            child.transform.setPosition(newChildPosition);

            // Update the parent offset of the child based on the child's current position
            Vec2 newParentOffset = newPosition.subtract(childPosition);
            child.transform.setParentOffset(newParentOffset);

            System.out.println("Child Position After: " + newChildPosition);
            System.out.println("Parent Position After: " + getPosition());
            System.out.println("offset: " + child.transform.getParentOffset());
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

    public Vec2 getParentOffset() {
        return parentOffset;
    }

    public void setParentOffset(Vec2 newOffset) {
        this.parentOffset = newOffset;
    }

}

