package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Entity;
import org.game.Engine.Systems.EngineTime;
import org.game.Engine.Systems.JECEngine;
import org.game.Engine.Classes.Vec2;

import java.awt.*;

/**
 * The Rigidbody2D class represents a 2D rigid body component that handles physics simulations,
 * including velocity, gravity, collisions, and responses.
 */
public class Rigidbody2D extends EntityComponent {

    private static final double elasticity = -0.8;
    private static final double friction = 1;

    public Vec2 velocity = new Vec2(0, 0);
    public double mass = 1;
    public double gravityScale = 1;

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
        applyForces();
        updatePosition();
        handleCollisions();
    }

    /**
     * Applies external forces to the rigid body, such as gravity and friction.
     */
    private void applyForces() {
        Vec2 gravityForce = new Vec2(0, mass * 980 * gravityScale); // Acceleration due to gravity
        Vec2 netForce = gravityForce; // Add other forces as needed
        Vec2 acceleration = netForce.divide(mass);
        velocity = velocity.add(acceleration.multiply(EngineTime.deltaTime));

        // Apply friction (drag force) with dampening over time
        double dampingFactor = 0.05; // Adjust this value based on desired damping strength
        velocity = velocity.multiply(1 - dampingFactor * EngineTime.deltaTime);
    }

    /**
     * Updates the position of the rigid body based on its velocity.
     */
    private void updatePosition() {
        Vec2 position = entity.transform.getPosition();
        position = position.add(velocity.multiply(EngineTime.deltaTime));
        entity.transform.setPosition(position);
    }

    /**
     * Handles collisions with other entities' colliders and responds accordingly.
     *
     * @throws Exception Thrown when a collider is missing on the current entity.
     */
    private void handleCollisions() throws Exception {
        Collider thisCollider = entity.getComponent(Collider.class);
        if (thisCollider == null) {
            throw new Exception("A collider is missing on the entity.");
        }

        for (Object e : JECEngine.entities.values().toArray()) {
            if (e.equals(this.entity)) {
                continue;
            }

            Collider otherCollider = ((Entity) e).getComponent(Collider.class);
            if (otherCollider != null && thisCollider.isColliding(otherCollider)) {
                resolveCollision(thisCollider, otherCollider);
            }
        }
    }

    /**
     * Handles the response when a collision occurs, including bouncing and pushing forces.
     *
     * @param thisCollider  The collider of the current rigid body.
     * @param otherCollider The collider of the colliding entity.
     */
    private void resolveCollision(Collider thisCollider, Collider otherCollider) {
        Vec2 relativeVelocity = velocity.subtract(otherCollider.entity.getComponent(Rigidbody2D.class).velocity);
        Vec2 normal = thisCollider.calculateCollisionNormal(otherCollider);
        double relativeSpeed = relativeVelocity.dotProduct(normal);

        if (relativeSpeed > 0) {
            // Objects are moving apart; no action needed
            return;
        }

        double impulse = -(1 + elasticity) * relativeSpeed / (1 / mass + 1 / otherCollider.entity.getComponent(Rigidbody2D.class).mass);

        // Update velocities
        velocity = velocity.add(normal.multiply(impulse / mass));
        otherCollider.entity.getComponent(Rigidbody2D.class).velocity = otherCollider.entity.getComponent(Rigidbody2D.class).velocity.subtract(normal.multiply(impulse / otherCollider.entity.getComponent(Rigidbody2D.class).mass));

        // Apply friction
        Vec2 tangent = relativeVelocity.subtract(normal.multiply(relativeVelocity.dotProduct(normal)));
        tangent = tangent.normalized();

        double frictionImpulse = -relativeVelocity.dotProduct(tangent) * friction;

        // Update velocities with friction
        velocity = velocity.add(tangent.multiply(frictionImpulse / mass));
        otherCollider.entity.getComponent(Rigidbody2D.class).velocity = otherCollider.entity.getComponent(Rigidbody2D.class).velocity.subtract(tangent.multiply(frictionImpulse / otherCollider.entity.getComponent(Rigidbody2D.class).mass));
    }

    /**
     * Called when the rigid body component is stopped.
     */
    @Override
    public void onStop() {
    }
}
