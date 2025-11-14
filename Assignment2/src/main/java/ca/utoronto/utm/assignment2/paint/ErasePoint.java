package ca.utoronto.utm.assignment2.paint;

import java.time.chrono.Era;

public class ErasePoint extends Point{

    public double thickness;

    public ErasePoint(double x, double y, double thickness) {
        super(x, y);
        this.thickness = thickness;
    }
}
