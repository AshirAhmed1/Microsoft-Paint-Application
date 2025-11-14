package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

/**
 * Drawable text that can be placed on the canvas.
 */
public class Text extends AbstractShapeDrawable {
    private Point position;
    private String text;
    private double fontSize;

    public Text (Point position, String text, double fontSize) {
        super();
        this.position = position;
        this.text = text;
        this.fontSize = fontSize;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setLineWidth(thickness);
        g.setFont(new Font(fontSize));

        if (filled) {
            g.setFill(color);
            g.fillText(text, position.x, position.y);
        } else {
            g.setStroke(color);
            g.strokeText(text, position.x, position.y);
        }
    }

    @Override
    public boolean contains(Point p) {
        // VERY rough bounding box – good enough for selection/move.
        double w = text.length() * fontSize * 0.6;  // approx. width
        double h = fontSize;                        // approx. height

        double x0 = position.x;
        double y0 = position.y - h * 0.8;           // baseline to top

        return p.x >= x0 && p.x <= x0 + w &&
                p.y >= y0 && p.y <= y0 + h;
    }

    @Override
    public void translate(double dx, double dy) {
        position = new Point(position.x + dx, position.y + dy);
    }
}
