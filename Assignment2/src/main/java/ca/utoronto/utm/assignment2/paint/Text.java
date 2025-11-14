package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

public class Text extends AbstractShapeDrawable {
    private Point position;
    private String content;

    public Text(Point position, String content) {
        this.position = position;
        this.content = content;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    // 🔥 ADD THIS
    public String getText() {
        return content;
    }

    // 🔥 And also add a setter (not required, but useful)
    public void setText(String content) {
        this.content = content;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        g.setStroke(color);
        g.fillText(content, position.x, position.y);
    }

    @Override
    public boolean contains(Point p) {
        return false; // simple text: usually not selectable by shape hitbox
    }

    @Override
    public void translate(double dx, double dy) {
        this.position = new Point(position.x + dx, position.y + dy);
    }
}
