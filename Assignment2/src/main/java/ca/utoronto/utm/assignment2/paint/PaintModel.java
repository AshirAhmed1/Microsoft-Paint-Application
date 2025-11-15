package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.assignment2.paint.command.Command;
import ca.utoronto.utm.assignment2.paint.command.ClearCanvasCommand;
import ca.utoronto.utm.assignment2.paint.command.PasteCommand;
import javafx.scene.paint.Color;
import java.util.ArrayDeque;
import java.util.Deque;

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

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void addDrawable(Drawable d) {
        drawables.add(d);
        updateObservers();
    }

    public void removeDrawable(Drawable d) {
        drawables.remove(d);
        updateObservers();
    }

    public void setPreview(Drawable d) {
        this.preview = d;
        updateObservers();
    }

    public Drawable getPreview() { return preview; }

    public void clearPreview() {
        this.preview = null;
        updateObservers();
    }

    public void setCurrentColor(Color color) { this.currentColor = color; }
    public Color getCurrentColor() { return currentColor; }

    public double getCurrentThickness() { return currentThickness; }

    public void setCurrentThickness(double thickness) {
        this.currentThickness = thickness;
        updateObservers();
    }

    public boolean isFillMode() { return fillMode; }

    public void setFillMode(boolean fillMode) {
        this.fillMode = fillMode;
        updateObservers();
    }

    public void updateObservers() {
        setChanged();
        notifyObservers();
    }

    public void setSelected(Drawable d) {
        selected = d;
        updateObservers();
    }

    public Drawable getSelected() { return selected; }

    public Drawable getClipboard() { return clipboard; }

    public void copySelected() {
        if (selected != null) {
            clipboard = copyPasteHelper(selected, 0, 0);
        }
    }



    public void pasteAt(double x, double y) {
        if (clipboard == null) return;

        Drawable pasted = createClipboardCopyAt(x, y);
        if (pasted != null) {
            executeCommand(new PasteCommand(this, pasted));
        }
    }


    public void executeCommand(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
        updateObservers();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
            updateObservers();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
            updateObservers();
        }
    }


    public void clearCanvas() {
        if (drawables.isEmpty()) return;

        executeCommand(new ClearCanvasCommand(this));
    }

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
            return cs;
        }

        if (d instanceof Square s) {
            Square cs = new Square(
                    new Point(s.getTop_left().x + dx, s.getTop_left().y + dy),
                    s.getSideLength()
            );
            cs.setColor(s.getColor());
            cs.setThickness(s.getThickness());
            return cs;
        }

        if (d instanceof Circle c) {
            Circle cs = new Circle(
                    new Point(c.getCentre().x + dx, c.getCentre().y + dy),
                    (int) c.getRadius()
            );
            cs.setColor(c.getColor());
            cs.setThickness(c.getThickness());
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

    public Drawable copyForUndo(Drawable d) {
        return copyPasteHelper(d, 0, 0);
    }
    public void setClipboard(Drawable d) {
        this.clipboard = d;
    }


}

