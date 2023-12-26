package org.game;

import org.game.Engine.Classes.Components.Collider;
import org.game.Engine.Classes.Components.Renderer;
import org.game.Engine.Classes.Components.Rigidbody2D;
import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.Vec2;
import org.game.PlayerMovement;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Rect extends Entity {

    public Rect(Vec2 initialPos) {
        super(initialPos);
    }

    @Override
    public void start() {
        addComponent(Renderer.class);
        addComponent(Rigidbody2D.class);
        getComponent(Renderer.class).shape = new Rectangle2D.Double(0, 0, 80, 80);
        addComponent(PlayerMovement.class);
    }
}