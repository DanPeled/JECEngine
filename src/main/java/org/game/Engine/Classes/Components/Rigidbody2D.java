package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.EngineTime;
import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.JECEngine;
import org.game.Engine.Classes.Vec2;

import java.awt.*;

public class Rigidbody2D extends EntityComponent {
    public Vec2 velocity = new Vec2(3, 0.1);
    public double mass = 1;
    private boolean keepFalling = true;

    @Override
    public void start() {

    }

    @Override
    public void update(Graphics g) throws Exception {
        this.entity.transform.position.y += mass * velocity.y;
        this.entity.transform.position.x += mass * velocity.x;
        for (Object e : JECEngine.entities.toArray()) {
            if (e.equals(this.entity)) {
                continue;
            }
            Collider other = ((Entity) e).getComponent(Collider.class);
            try {
                Collider thisCollider = entity.getComponent(Collider.class);
                thisCollider.isColliding(other);
                if (thisCollider.isColliding(other)) {
                    keepFalling = false;
                    velocity = new Vec2(0, 0);
                } else {
                    keepFalling = true;
                    if (velocity.y == 0) velocity = new Vec2(0, 0.1);
                }
            } catch (NullPointerException error) {
                throw new Exception(String.format("A collider is missing on: %s", this.entity.toString()));
            }
        }
        if (keepFalling) {
            velocity.y += 0.5;
        }
        if (velocity.y != 0) {
            keepFalling = true;
        }
    }

    @Override
    public void onStop() {

    }
}
