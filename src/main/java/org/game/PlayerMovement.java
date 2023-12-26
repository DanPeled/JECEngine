package org.game;

import org.game.Engine.Classes.Components.EntityComponent;
import org.game.Engine.Classes.EngineTime;
import org.game.Engine.Systems.Input;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerMovement extends EntityComponent {
    int playerSpeed = 5;
    double rotation = 0;

    @Override
    public void start() {

    }

    @Override
    public void update(Graphics g) {
        double deltaTime = EngineTime.deltaTime * 40;
        entity.transform.position.angle = rotation;
        if (Input.isKeyPressed(KeyEvent.VK_UP)) {
            entity.transform.position.y -= playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_DOWN)) {
            entity.transform.position.y += playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_LEFT)) {
            entity.transform.position.x -= playerSpeed * deltaTime;
        }
        if (Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            entity.transform.position.x += playerSpeed * deltaTime;
        }
        rotation += 0.1 * deltaTime;
    }

    @Override
    public void onStop() {

    }
}
