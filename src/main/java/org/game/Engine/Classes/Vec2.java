package org.game.Engine.Classes;

/**
 * Represents a 2D vector with x and y components, and an angle.
 */
public class Vec2 {
    public double x;
    public double y;
    public double angle;

    /**
     * Constructs a Vec2 with the specified x and y components.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     */
    public Vec2(double x, double y) {
        this(x, y, 0);
    }

    /**
     * Constructs a Vec2 with the specified x, y components, and angle.
     *
     * @param x     The x-component of the vector.
     * @param y     The y-component of the vector.
     * @param angle The angle of the vector.
     */
    public Vec2(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}
