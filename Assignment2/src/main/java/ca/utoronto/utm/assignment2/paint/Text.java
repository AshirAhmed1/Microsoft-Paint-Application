package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a text element that can be drawn on the canvas. The text is
 * defined by its position and content, and supports movement and basic
 * hit detection within an estimated bounding box.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class Text extends AbstractShapeDrawable {
    private Point position;
    private String content;

    /**
     * Constructs a new Text object with the given position and content string.
     *
     * @param position the position where the text begins
     * @param content  the string to be rendered
     */
    public Text(Point position, String content) {
        this.position = position;
        this.content = content;
    }

    /**
     * Returns the position of this text.
     *
     * @return the text’s anchor point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets a new position for the text.
     *
     * @param p the new position
     */
    public void setPosition(Point p) {
        this.position = p;
    }

    /**
     * Returns the content of the text.
     *
     * @return the text string
     */
    public String getText() {
        return content;
    }

    /**
     * Updates the content of the text object.
     *
     * @param content the new text string to display
     */
    public void setText(String content) {
        this.content = content;
    }

    /**
     * Draws the text on the canvas using the current color and position.
     *
     * @param g the graphics context used for drawing
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        g.setStroke(color);
        g.fillText(content, position.x, position.y);
    }

    /**
     * Checks whether the specified point lies within the approximate bounding
     * box of the text. The width and height are estimated based on thickness
     * and character count.
     *
     * @param p the point to test
     * @return true if the point is inside the estimated text bounds
     */
    @Override
    public boolean contains(Point p) {
        double width = content.length() * (getThickness() + 6);
        double height = getThickness() * 10;

        return p.x >= position.x &&
                p.x <= position.x + width &&
                p.y >= position.y - height &&
                p.y <= position.y;
    }

    /**
     * Moves the text by the specified horizontal and vertical offsets.
     *
     * @param dx horizontal movement
     * @param dy vertical movement
     */
    @Override
    public void translate(double dx, double dy) {
        this.position = new Point(position.x + dx, position.y + dy);
    }
}
