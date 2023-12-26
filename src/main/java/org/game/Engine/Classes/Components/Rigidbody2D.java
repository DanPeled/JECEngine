package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.EngineTime;
import org.game.Engine.Classes.Vec2;

import java.awt.*;

public class Rigidbody2D extends EntityComponent {
    Vec2 velocity = new Vec2(3, 0.1);
    @Override
    public void start() {

    }

    @Override
    public void update(Graphics g) {
        System.out.println(entity.transform.position.y);
        this.entity.transform.position.y += velocity.y;
        this.entity.transform.position.x += velocity.x;
        velocity.y += 0.5;
    }

    @Override
    public void onStop() {

    }
}
