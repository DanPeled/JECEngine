package org.game;

import org.game.Engine.Classes.Components.Collider;
import org.game.Engine.Classes.Components.Renderer;
import org.game.Engine.Classes.Components.Rigidbody2D;
import org.game.Engine.Classes.Entity;
import org.game.Engine.Classes.Vec2;
import org.game.PlayerMovement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Rect extends Entity {

    public Rect(Vec2 initialPos) {
        super(initialPos);
    }

    @Override
    public void start() {
        Shape shape = new Rectangle2D.Double(0, 0, 80, 80);
        addComponent(Renderer.class);
        addComponent(Rigidbody2D.class);
        addComponent(Collider.class);
        shape = new Rectangle2D.Double(0, 0, 80, 80);
        getComponent(Collider.class).shape = shape;
        shape = new Rectangle2D.Double(0, 0, 80, 80);
        getComponent(Renderer.class).shape = shape;
    }
}
