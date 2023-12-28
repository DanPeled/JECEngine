package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.JECEngine;
import org.game.Engine.Classes.Vec2;

import java.awt.*;
import java.util.Arrays;

/**
 * The Rigidbody2D class represents a 2D rigid body component that handles physics simulations,
 * including velocity, gravity, collisions, and responses.
 */
public class Rigidbody2D extends EntityComponent {

    private static final double friction = 0.01;
    /**
     * The elasticity of the rigid body, representing the bounciness on collisions.
     * Should be a number between 1 (fully elastic) and 0 (not elastic at all).
     */
    private final double elasticity = 0.7;
    /**
     * The velocity of the rigid body in 2D space.
     */
    public Vec2 velocity = new Vec2(3, 12);
    /**
     * The mass of the rigid body.
     */
    public double mass = 1;
    /**
     * The gravity scale applied to the rigid body.
     */
    public double gravityScale = 1;
    /**
     * Flag indicating whether the rigid body should keep falling due to gravity.
     */
    private boolean keepFalling = true;

    /**
     * Initializes and starts the rigid body component.
     */
    @Override
    public void start() {
    }

    /**
     * Updates the rigid body's position, handles collisions, and applies gravity.
     *
     * @param g The graphics context for rendering.
     * @throws Exception Thrown when a collider is missing on the entity.
     */
    @Override
    public void update(Graphics g) throws Exception {
        updatePosition();
        handleCollisions();
        applyGravity();
    }

    /**
     * Updates the position of the rigid body based on its velocity and gravity.
     */
    private void updatePosition() {
        this.entity.transform.position.x += mass * velocity.x * gravityScale;
        this.entity.transform.position.y += mass * velocity.y * gravityScale;
    }

    /**
     * Handles collisions with other entities' colliders and responds accordingly.
     *
     * @throws Exception Thrown when a collider is missing on the current entity.
     */
    private void handleCollisions() throws Exception {
        for (Object e : JECEngine.entities.toArray()) {
            if (e.equals(this.entity)) {
                continue;
            }
            Collider other = ((Entity) e).getComponent(Collider.class);
            try {
                Collider thisCollider = entity.getComponent(Collider.class);
                if (thisCollider.isColliding(other)) {
                    handleCollisionResponse();
                } else {
                    keepFalling = true;
                }
            } catch (NullPointerException error) {
                throw new Exception(String.format("A collider is missing on: %s: \n%s", this.entity.toString(), Arrays.toString(error.getStackTrace())));
            }
        }
    }

    /**
     * Handles the response when a collision occurs, including bouncing and pushing forces.
     */
    private void handleCollisionResponse() {
        if (keepFalling || !velocity.isZero()) {
            keepFalling = false;

            // Reverse the velocity for a basic bounce with friction
            velocity.x = -velocity.x * elasticity - Math.signum(velocity.x) * friction;
            velocity.y = -velocity.y * elasticity - friction;

            // Apply a pushing force to the colliding object
            Collider thisCollider = entity.getComponent(Collider.class);

            for (Object e : JECEngine.entities.toArray()) {
                if (!e.equals(this.entity)) {
                    Collider otherCollider = ((Entity) e).getComponent(Collider.class);
                    if (thisCollider.isColliding(otherCollider)) {
                        double PUSH_FACTOR = velocity.magnitude() * 0.2;

                        // Apply pushing force to both objects
                        Vec2 pushForce = velocity.normalized().multiply(-elasticity * PUSH_FACTOR);
                        this.entity.transform.position.add(pushForce.multiply(velocity.y)); // No idea why, but this math makes it work
                    }
                }
            }
        }
    }

    /**
     * Resets the velocity of the rigid body.
     */
    private void resetVelocity() {
        // TODO: Implementation for resetting velocity if needed. (idfk if I need)
    }

    /**
     * Applies gravity to the rigid body, adjusting the vertical velocity.
     */
    private void applyGravity() {
        if (keepFalling) {
            velocity.y += 0.5;
        }
        if (velocity.y != 0) {
            keepFalling = true;
        }
    }

    /**
     * Called when the rigid body component is stopped.
     */
    @Override
    public void onStop() {
    }
}
