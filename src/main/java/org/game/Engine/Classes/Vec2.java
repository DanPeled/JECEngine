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

    /**
     * Calculates the Euclidean distance between two 2D vectors.
     *
     * @param v1 The first 2D vector.
     * @param v2 The second 2D vector.
     * @return The Euclidean distance between the input vectors.
     */
    public static double dist(Vec2 v1, Vec2 v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates the Euclidean distance between this vector and another.
     *
     * @param other The other 2D vector.
     * @return The Euclidean distance between this vector and the input vector.
     */
    public double dist(Vec2 other) {
        return Vec2.dist(this, other);
    }

    /**
     * Calculates the magnitude (length) of the vector.
     *
     * @return The magnitude of the vector.
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param other The vector to be added.
     * @return This vector after addition.
     */
    public Vec2 add(Vec2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param other The vector to be subtracted.
     * @return This vector after subtraction.
     */
    public Vec2 subtract(Vec2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * Checks if the vector is a zero vector (both components are zero).
     *
     * @return True if the vector is a zero vector, false otherwise.
     */
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    /**
     * Returns a normalized copy of the vector.
     *
     * @return A normalized copy of the vector.
     */
    public Vec2 normalized() {
        double mag = magnitude();
        if (mag != 0) {
            return new Vec2(x / mag, y / mag);
        } else {
            // Handle the case where the vector has zero magnitude
            return new Vec2(0, 0);
        }
    }

    /**
     * Multiplies the vector by a scalar.
     *
     * @param scalar The scalar value.
     * @return The result of the vector multiplication by the scalar.
     */
    public Vec2 multiply(double scalar) {
        return new Vec2(x * scalar, y * scalar);
    }

    /**
     * Calculates the dot product of two 2D vectors.
     * <p>
     * The dot product of vectors v1(x1, y1) and v2(x2, y2) is given by:
     * dotProduct = x1 * x2 + y1 * y2
     *
     * @param v1 The first 2D vector.
     * @param v2 The second 2D vector.
     * @return The dot product of the input vectors.
     */
    public static double dotProduct(Vec2 v1, Vec2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }
}
