package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Vec2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * A component representing a collider attached to an entity for collision detection.
 */
public class Collider extends EntityComponent {

    /**
     * The shape defining the collider's boundaries.
     */
    public Shape shape;

    /**
     * {@inheritDoc}
     * <p>
     * This method is currently empty and can be overridden for initialization tasks.
     */
    @Override
    public void start() {
        // Initialization tasks can be added here if needed.
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method currently contains commented-out code for rendering the collider.
     *
     * @param g The graphics context for rendering (may be null if rendering is not needed).
     */
    @Override
    public void update(Graphics g) {
        /*
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(
                this.entity.transform.position.x + shape.getBounds2D().getWidth() / 2,
                this.entity.transform.position.y + shape.getBounds2D().getHeight() / 2.0
        );
        transform.rotate(this.entity.transform.position.angle);
        transform.translate(-shape.getBounds2D().getWidth() / 2, -shape.getBounds2D().getHeight() / 2);
        Shape transformedShape = transform.createTransformedShape(shape);
        g2d.draw(transformedShape);
        */
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is currently empty and can be overridden for cleanup tasks when the collider is disabled.
     */
    @Override
    public void onStop() {
    }

    /**
     * Checks if this collider is colliding with another collider.
     *
     * @param other The other collider to check for collision.
     * @return True if a collision is detected, false otherwise.
     */
    public boolean isColliding(Collider other) {
        Vec2 position1 = entity.transform.getPosition();
        Vec2 position2 = other.entity.transform.getPosition();

        double x1 = position1.x;
        double y1 = position1.y;
        double w1 = shape.getBounds2D().getWidth();
        double h1 = shape.getBounds2D().getHeight();

        double x2 = position2.x;
        double y2 = position2.y;
        double w2 = other.shape.getBounds2D().getWidth();
        double h2 = other.shape.getBounds2D().getHeight();

        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }


    public Vec2[] getSides() {
        Vec2 topLeft = new Vec2(shape.getBounds2D().getMinX(), shape.getBounds2D().getMinY());
        Vec2 topRight = new Vec2(shape.getBounds2D().getMaxX(), shape.getBounds2D().getMinY());
        Vec2 bottomLeft = new Vec2(shape.getBounds2D().getMinX(), shape.getBounds2D().getMaxY());
        Vec2 bottomRight = new Vec2(shape.getBounds2D().getMaxX(), shape.getBounds2D().getMaxY());


        return new Vec2[]{new Vec2(topLeft, topRight), new Vec2(topRight, bottomRight), new Vec2(topLeft, bottomLeft), new Vec2(bottomRight, bottomLeft)};
    }

    /**
     * Checks if two sides (represented by vectors) are colliding.
     *
     * @param side      The vector representing a side of the current collider.
     * @param otherSide The vector representing a side of the other collider.
     * @return True if the sides are colliding, false otherwise.
     */
    private boolean areSidesColliding(Vec2 side, Vec2 otherSide) {
        // Check for a separating axis using the dot product
        double dotProduct = side.dotProduct(perpendicular(otherSide));

        // If the dot product is negative, the sides are not colliding
        return dotProduct >= 0;
    }

    /**
     * Calculates the perpendicular vector of the given vector.
     *
     * @param vector The input vector.
     * @return The perpendicular vector.
     */
    private Vec2 perpendicular(Vec2 vector) {
        // Swap x and y and negate one of them to get a perpendicular vector
        return new Vec2(-vector.y, vector.y);
    }

    /**
     * Calculates the collision normal between this collider and another collider.
     *
     * @param otherCollider The other collider involved in the collision.
     * @return The collision normal vector.
     */
    public Vec2 calculateCollisionNormal(Collider otherCollider) {
        Vec2 position1 = entity.transform.getPosition();
        Vec2 position2 = otherCollider.entity.transform.getPosition();

        double x1 = position1.x;
        double y1 = position1.y;
        double w1 = shape.getBounds2D().getWidth();
        double h1 = shape.getBounds2D().getHeight();

        double x2 = position2.x;
        double y2 = position2.y;
        double w2 = otherCollider.shape.getBounds2D().getWidth();
        double h2 = otherCollider.shape.getBounds2D().getHeight();

        // Calculate the distance between the centers of the two colliders
        double dx = (x1 + w1 / 2) - (x2 + w2 / 2);
        double dy = (y1 + h1 / 2) - (y2 + h2 / 2);

        // Calculate the combined half-widths and half-heights of the colliders
        double combinedHalfWidth = (w1 + w2) / 2;
        double combinedHalfHeight = (h1 + h2) / 2;

        // Calculate the overlap along the x and y axes
        double overlapX = combinedHalfWidth - Math.abs(dx);
        double overlapY = combinedHalfHeight - Math.abs(dy);

        // Determine the direction of the collision (normal vector)
        if (overlapX < overlapY) {
            // Colliding horizontally, set normal vector in the x direction
            return new Vec2(Math.signum(dx), 0);
        } else {
            // Colliding vertically, set normal vector in the y direction
            return new Vec2(0, Math.signum(dy));
        }
    }
}
