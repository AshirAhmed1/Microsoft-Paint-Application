package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {
    private final ArrayList<Drawable> drawables = new ArrayList<>();
    private Drawable preview;
    private Color currentColor = Color.BLACK;
    private double currentThickness = 2.0;

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

}