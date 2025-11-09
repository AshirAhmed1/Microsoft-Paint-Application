package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractShapeDrawable implements Drawable {
    protected Color color;
    protected double thickness;

    public AbstractShapeDrawable() {}

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public abstract void draw(GraphicsContext gc);
}
