package org.game.Engine.Classes.Components;

import java.awt.*;
import java.awt.geom.AffineTransform;

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
        // Cleanup tasks can be added here if needed.
    }

    /**
     * Checks if this collider is colliding with another collider.
     *
     * @param other The other collider to check for collision.
     * @return True if a collision is detected, false otherwise.
     */
    public boolean isColliding(Collider other) {
        double x1 = entity.transform.getPosition().x;
        double y1 = entity.transform.getPosition().y;
        double w1 = shape.getBounds2D().getWidth();
        double h1 = shape.getBounds2D().getHeight();

        double x2 = other.entity.transform.getPosition().x;
        double y2 = other.entity.transform.getPosition().y;
        double w2 = other.shape.getBounds2D().getWidth();
        double h2 = other.shape.getBounds2D().getHeight();

        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
}
