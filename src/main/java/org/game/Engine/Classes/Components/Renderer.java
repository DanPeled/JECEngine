package org.game.Engine.Classes.Components;

import org.game.Engine.Classes.Components.EntityComponent;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer extends EntityComponent {
    public Shape shape;
    public Color color;
    public boolean fill = true;

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

        // Set the color before rendering
        if (color != null) {
            g2d.setColor(color);
        }

        AffineTransform transform = new AffineTransform();
        double centerX = this.entity.transform.getPosition().x + shape.getBounds2D().getCenterX();
        double centerY = this.entity.transform.getPosition().y + shape.getBounds2D().getCenterY();

        transform.translate(centerX, centerY);
        transform.rotate(this.entity.transform.getPosition().angle);
        transform.translate(-shape.getBounds2D().getWidth() / 2, -shape.getBounds2D().getHeight() / 2);
        Shape transformedShape = transform.createTransformedShape(shape);

        // Fill the shape with the specified color
        if (color != null && fill) {
            g2d.fill(transformedShape);
        }

        // Draw the shape outline
        g2d.draw(transformedShape);
        g2d.setColor(Color.black);
    }
}
