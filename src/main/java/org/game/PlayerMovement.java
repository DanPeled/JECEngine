package org.game;

import org.game.Engine.Classes.Components.EntityComponent;
import org.game.Engine.Classes.Components.Rigidbody2D;
import org.game.Engine.Systems.EngineTime;
import org.game.Engine.Systems.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayerMovement extends EntityComponent {
    int playerSpeed = 300;
    double rotation = 0;
    Rigidbody2D rigidbody2D;

    @Override
    public void start() {
        this.rigidbody2D = this.entity.getComponent(Rigidbody2D.class);
    }

    @Override
    public void update(Graphics g) {
        double deltaTime = EngineTime.deltaTime ;
        if (Input.isKeyPressed(KeyEvent.VK_UP)) {
            rigidbody2D.velocity.y -= playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_DOWN)) {
            rigidbody2D.velocity.y += playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_LEFT)) {
            rigidbody2D.velocity.x -= playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            rigidbody2D.velocity.x += playerSpeed * deltaTime;
        }
        if (Input.isMouseButtonPressed(MouseEvent.BUTTON1)) {
            entity.transform.position = Input.getMousePosition();
            entity.getComponent(Rigidbody2D.class).velocity.y = 0;
        }
        rotation += 0.1 * deltaTime;
    }

    @Override
    public void onStop() {

    }
}
