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

    public static double dist(Vec2 v1, Vec2 v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double dist(Vec2 other) {
        return Vec2.dist(this, other);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 add(Vec2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vec2 subtract(Vec2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public Vec2 normalized() {
        return new Vec2(x / magnitude(), y / magnitude());
    }

    public Vec2 multiply(double scalar) {
        return new Vec2(x * scalar, y * scalar);
    }
}
