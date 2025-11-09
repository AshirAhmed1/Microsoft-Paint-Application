package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Drawable> drawables = new ArrayList<>();
    private Drawable preview;

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
}