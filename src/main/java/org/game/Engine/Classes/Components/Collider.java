package org.game.Engine.Classes.Components;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Collider extends EntityComponent {
    public Shape shape;

    @Override
    public void start() {

    }

    @Override
    public void update(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        AffineTransform transform = new AffineTransform();
//        transform.translate(this.entity.transform.position.x + shape.getBounds2D().getWidth() / 2, this.entity.transform.position.y + shape.getBounds2D().getHeight() / 2.0);
//        transform.rotate(this.entity.transform.position.angle);
//        transform.translate(-shape.getBounds2D().getWidth() / 2, -shape.getBounds2D().getHeight() / 2);
//        Shape s = transform.createTransformedShape(shape);
//        g2d.draw(s);
    }

    @Override
    public void onStop() {

    }

    public boolean isColliding(Collider other) {
        double x1 = entity.transform.position.x;
        double y1 = entity.transform.position.y;
        double w1 = shape.getBounds2D().getWidth();
        double h1 = shape.getBounds2D().getHeight();

        double x2 = other.entity.transform.position.x;
        double y2 = other.entity.transform.position.y;
        double w2 = other.shape.getBounds2D().getWidth();
        double h2 = other.shape.getBounds2D().getHeight();

        // Check for no overlap along the x-axis
        if (x1 + w1 < x2 || x2 + w2 < x1) {
            return false;
        }

        // Check for no overlap along the y-axis
        return !(y1 + h1 < y2) && !(y2 + h2 < y1);
    }
}
