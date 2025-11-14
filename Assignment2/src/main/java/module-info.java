module ca.utoronto.utm.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires ca.utoronto.utm.assignment2;


    opens ca.utoronto.utm.assignment2 to javafx.fxml;
    exports ca.utoronto.utm.assignment2.scribble;
    exports ca.utoronto.utm.assignment2.paint;
    exports ca.utoronto.utm.assignment2.paint.tools;


}