package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.assignment2.paint.command.Command;
import ca.utoronto.utm.assignment2.paint.command.ClearCanvasCommand;
import ca.utoronto.utm.assignment2.paint.command.PasteCommand;
import javafx.scene.paint.Color;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The core model of the paint application. This class stores all drawable
 * shapes, the current drawing settings, the clipboard, the selected shape,
 * and manages undo/redo operations through the command system. It notifies
 * observers whenever changes occur.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class PaintModel extends Observable {

    private final ArrayList<Drawable> drawables = new ArrayList<>();
    private Drawable preview;

    private Color currentColor = Color.BLACK;
    private double currentThickness = 2.0;
    private boolean fillMode = true;

    private Drawable clipboard;
    private Drawable selected;

    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    /**
     * Returns the list of drawable objects currently on the canvas.
     *
     * @return list of drawables
     */
    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    /**
     * Adds a drawable shape to the canvas and notifies observers.
     *
     * @param d the shape to add
     */
    public void addDrawable(Drawable d) {
        drawables.add(d);
        updateObservers();
    }

    /**
     * Removes a drawable shape from the canvas and notifies observers.
     *
     * @param d the shape to remove
     */
    public void removeDrawable(Drawable d) {
        drawables.remove(d);
        updateObservers();
    }

    /**
     * Sets the current preview shape being drawn.
     *
     * @param d the drawable preview
     */
    public void setPreview(Drawable d) {
        this.preview = d;
        updateObservers();
    }

    /**
     * Returns the preview drawable currently shown.
     *
     * @return preview shape
     */
    public Drawable getPreview() { return preview; }

    /**
     * Clears the preview drawable and updates observers.
     */
    public void clearPreview() {
        this.preview = null;
        updateObservers();
    }

    /**
     * Sets the current drawing color.
     *
     * @param color the chosen color
     */
    public void setCurrentColor(Color color) { this.currentColor = color; }

    /**
     * Returns the currently selected drawing color.
     *
     * @return current color
     */
    public Color getCurrentColor() { return currentColor; }

    /**
     * Returns the current brush thickness.
     *
     * @return thickness value
     */
    public double getCurrentThickness() { return currentThickness; }

    /**
     * Sets the current brush thickness and updates observers.
     *
     * @param thickness new thickness value
     */
    public void setCurrentThickness(double thickness) {
        this.currentThickness = thickness;
        updateObservers();
    }

    /**
     * Returns whether shapes are drawn filled or outlined.
     *
     * @return true if filled mode is active
     */
    public boolean isFillMode() { return fillMode; }

    /**
     * Sets the fill mode for new shapes and notifies observers.
     *
     * @param fillMode true for filled shapes
     */
    public void setFillMode(boolean fillMode) {
        this.fillMode = fillMode;
        updateObservers();
    }

    /**
     * Notifies all observers that the model has changed.
     */
    public void updateObservers() {
        setChanged();
        notifyObservers();
    }

    /**
     * Sets the currently selected drawable and updates observers.
     *
     * @param d selected shape
     */
    public void setSelected(Drawable d) {
        selected = d;
        updateObservers();
    }

    /**
     * Returns the currently selected drawable.
     *
     * @return selected shape
     */
    public Drawable getSelected() { return selected; }

    /**
     * Returns the object currently stored in the clipboard.
     *
     * @return clipboard drawable
     */
    public Drawable getClipboard() { return clipboard; }

    /**
     * Copies the currently selected shape into the clipboard.
     */
    public void copySelected() {
        if (selected != null) {
            clipboard = copyPasteHelper(selected, 0, 0);
        }
    }

    /**
     * Pastes the clipboard drawable onto the canvas at a given position.
     *
     * @param x target x coordinate
     * @param y target y coordinate
     */
    public void pasteAt(double x, double y) {
        if (clipboard == null) return;

        Drawable pasted = createClipboardCopyAt(x, y);
        if (pasted != null) {
            executeCommand(new PasteCommand(this, pasted));
        }
    }

    /**
     * Executes a command and pushes it onto the undo stack.
     *
     * @param cmd the command to run
     */
    public void executeCommand(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
        updateObservers();
    }

    /**
     * Undoes the last executed command, if any.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
            updateObservers();
        }
    }

    /**
     * Redoes the most recently undone command, if any.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
            updateObservers();
        }
    }

    /**
     * Clears the entire canvas using a ClearCanvasCommand.
     */
    public void clearCanvas() {
        if (drawables.isEmpty()) return;
        executeCommand(new ClearCanvasCommand(this));
    }

    /**
     * Creates a deep copy of a drawable shape for clipboard or undo operations.
     *
     * @param d  the shape to copy
     * @param dx horizontal translation
     * @param dy vertical translation
     * @return copied drawable
     */
    private Drawable copyPasteHelper(Drawable d, double dx, double dy) {

        if (d instanceof Triangle t) {
            Triangle cs = new Triangle(
                    new Point(t.getbottom_left().x + dx, t.getbottom_left().y + dy),
                    t.getbase(),
                    t.getside1(),
                    t.getside2()
            );
            cs.setColor(t.getColor());
            cs.setThickness(t.getThickness());
            cs.setFilled(t.isFilled());
            return cs;
        }

        if (d instanceof Square s) {
            Square cs = new Square(
                    new Point(s.getTop_left().x + dx, s.getTop_left().y + dy),
                    s.getSideLength()
            );
            cs.setColor(s.getColor());
            cs.setThickness(s.getThickness());
            cs.setFilled(s.isFilled());
            return cs;
        }

        if (d instanceof Circle c) {
            Circle cs = new Circle(
                    new Point(c.getCentre().x + dx, c.getCentre().y + dy),
                    (int) c.getRadius()
            );
            cs.setColor(c.getColor());
            cs.setThickness(c.getThickness());
            cs.setFilled(c.isFilled());
            return cs;
        }

        if (d instanceof Oval o) {
            Oval cs = new Oval(
                    new Point(o.getTopLeft().x + dx, o.getTopLeft().y + dy),
                    o.getWidth(),
                    o.getHeight()
            );
            cs.setColor(o.getColor());
            cs.setThickness(o.getThickness());
            cs.setFilled(o.isFilled());
            return cs;
        }

        if (d instanceof Rectangle r) {
            Rectangle cs = new Rectangle(
                    new Point(r.getTop_left().x + dx, r.getTop_left().y + dy),
                    r.getWidth(),
                    r.getHeight()
            );
            cs.setColor(r.getColor());
            cs.setThickness(r.getThickness());
            cs.setFilled(r.isFilled());
            return cs;
        }

        if (d instanceof Text t) {
            Text copy = new Text(
                    new Point(t.getPosition().x + dx, t.getPosition().y + dy),
                    t.getText()
            );
            copy.setColor(t.getColor());
            copy.setThickness(t.getThickness());
            copy.setFilled(t.isFilled());
            return copy;
        }

        return d;
    }


    /**
     * Creates a translated copy of the clipboard drawable placed at a given point.
     *
     * @param x target x coordinate
     * @param y target y coordinate
     * @return clipboard copy positioned at (x, y)
     */
    private Drawable createClipboardCopyAt(double x, double y) {
        if (clipboard == null) return null;

        Drawable copy = copyPasteHelper(clipboard, 0, 0);

        double dx = 0, dy = 0;

        if (clipboard instanceof Circle c)
            dx = x - c.getCentre().x;
        else if (clipboard instanceof Rectangle r)
            dx = x - r.getTop_left().x;
        else if (clipboard instanceof Square s)
            dx = x - s.getTop_left().x;
        else if (clipboard instanceof Oval o)
            dx = x - o.getTopLeft().x;
        else if (clipboard instanceof Triangle t)
            dx = x - t.getbottom_left().x;

        dy = dy == 0 ? dx : dy;

        copy.translate(dx, dy);
        return copy;
    }

    /**
     * Returns a deep copy of the given drawable for undo operations.
     *
     * @param d shape to copy
     * @return copied shape
     */
    public Drawable copyForUndo(Drawable d) {
        return copyPasteHelper(d, 0, 0);
    }

    /**
     * Sets the clipboard to the given drawable.
     *
     * @param d drawable to store in clipboard
     */
    public void setClipboard(Drawable d) {
        this.clipboard = d;
    }

}
