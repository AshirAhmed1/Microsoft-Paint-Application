package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {
    private final ArrayList<Drawable> drawables = new ArrayList<>();
    private Drawable preview;
    private Color currentColor = Color.BLACK;
    private double currentThickness = 2.0;
    private boolean fillMode = true;
    private Drawable clipboard;
    private Drawable selected;

    public void addDrawable(Drawable d) {
        drawables.add(d);
        System.out.println("Shape Added: " + d.getClass().getSimpleName());
        setChanged();
        notifyObservers();
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void setPreview(Drawable d) {
        this.preview = d;
        setChanged();
        notifyObservers();
    }

    public Drawable getPreview() {
        return this.preview;
    }

    public void clearPreview() {
        this.preview = null;
        setChanged();
        notifyObservers();
    }

    public void setCurrentColor(Color color) { this.currentColor = color;}

    public Color getCurrentColor() { return this.currentColor;}

    public double getCurrentThickness()
    {
        return this.currentThickness;
    }

    public void setCurrentThickness(double thickness)
    {
        this.currentThickness = thickness;
        setChanged();
        notifyObservers();
    }

    public boolean isFillMode()
    {
        return fillMode;
    }

    public void setFillMode(boolean fillMode)
    {
        this.fillMode = fillMode;
        setChanged();
        notifyObservers();
    }

    public void updateObservers() {
        setChanged();
        notifyObservers();
    }

    private Drawable copyPasteHelper(Drawable d, double dx, double dy) {
        // Circle
        if (d instanceof Circle c) {
            Point centre = c.getCentre();
            // Circle(Point centre, int radius)
            return new Circle(
                    new Point(centre.x + dx, centre.y + dy),
                    (int) c.getRadius()
            );
        }

        // Rectangle
        if (d instanceof Rectangle r) {
            Point tl = r.getTop_left();
            return new Rectangle(
                    new Point(tl.x + dx, tl.y + dy),
                    r.getWidth(),
                    r.getHeight()
            );
        }

        // Square  (if this gives warnings, you can delete this whole if-block)
        if (d instanceof Square s) {
            Point tl = s.getTop_left();
            return new Square(
                    new Point(tl.x + dx, tl.y + dy),
                    (int) s.getSideLength()
            );
        }

        // Oval
        if (d instanceof Oval o) {
            Point tl = o.getTopLeft();
            return new Oval(
                    new Point(tl.x + dx, tl.y + dy),
                    o.getWidth(),
                    o.getHeight()
            );
        }

        // Triangle  (if getter names differ, we can fix, or comment this out)
        if (d instanceof Triangle t) {
            Point bl = t.getbottom_left();
            return new Triangle(
                    new Point(bl.x + dx, bl.y + dy),
                    t.getbase(),
                    t.getside1(),
                    t.getside2()
            );
        }

        // If some other Drawable we didn't handle, just return it
        return d;
    }

    public void setSelected(Drawable d) {
        this.selected = d;
        setChanged();
        notifyObservers();
    }

    public Drawable getSelected() {
        return this.selected;
    }
    public Drawable getClipboard() {
        return clipboard;
    }
    public void copySelected() {
        if (selected != null) {
            clipboard = copyPasteHelper(selected, 0, 0);
        }
    }

    public void cutSelected() {
        if (selected != null) {
            drawables.remove(selected);
            clipboard = copyPasteHelper(selected, 0, 0);
            selected = null;   // nothing selected now
            setChanged();
            notifyObservers();
        }
    }

    private Drawable createClipboardCopyAt(double x, double y) {
        if (clipboard == null) return null;

        // First make a copy at the same place as original
        Drawable copy = copyPasteHelper(clipboard, 0, 0);

        double dx = 0;
        double dy = 0;

        if (clipboard instanceof Circle c && copy instanceof Circle cc) {
            dx = x - c.getCentre().x;
            dy = y - c.getCentre().y;
        } else if (clipboard instanceof Rectangle r && copy instanceof Rectangle rr) {
            dx = x - r.getTop_left().x;
            dy = y - r.getTop_left().y;
        } else if (clipboard instanceof Square s && copy instanceof Square ss) {
            dx = x - s.getTop_left().x;
            dy = y - s.getTop_left().y;
        } else if (clipboard instanceof Oval o && copy instanceof Oval oo) {
            dx = x - o.getTopLeft().x;
            dy = y - o.getTopLeft().y;
        } else if (clipboard instanceof Triangle t && copy instanceof Triangle tt) {
            dx = x - t.getbottom_left().x;
            dy = y - t.getbottom_left().y;
        } else if (clipboard instanceof Polyline p && copy instanceof Polyline pp) {
            // For polyline/squiggle, anchor on first point
            Point p0 = p.getPoints().get(0);
            dx = x - p0.x;
            dy = y - p0.y;
        } else if (clipboard instanceof Squiggle s && copy instanceof Squiggle ss) {
            Point p0 = s.getPoints().get(0);
            dx = x - p0.x;
            dy = y - p0.y;
        }

        // All drawables support translate(dx, dy)
        copy.translate(dx, dy);
        return copy;
    }
    public void pasteAt(double x, double y) {
        Drawable pasted = createClipboardCopyAt(x, y);
        if (pasted != null) {
            addDrawable(pasted);  // your existing addDrawable, which notifies observers
        }
    }
}