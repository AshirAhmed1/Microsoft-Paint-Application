package ca.utoronto.utm.assignment2.paint.tools;

import javafx.scene.input.MouseEvent;

public interface Tool {
    void onPress(MouseEvent e);
    void onDrag(MouseEvent e);
    void onRelease(MouseEvent e);
}
