package org.game.Engine.Classes.Components;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer extends EntityComponent {
    public Shape shape;

    @Override
    public void start() {

    }

    @Override
    public void update(Graphics g) {
        render(g);
    }

    @Override
    public void onStop() {

    }

    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.translate(this.entity.transform.position.x + 20, this.entity.transform.position.y + 20);
        transform.rotate(this.entity.transform.position.angle);
        transform.translate(-20, -20);

        Shape s = transform.createTransformedShape(shape);
        g2d.fill(s);
        g2d.draw(s);
    }
}
