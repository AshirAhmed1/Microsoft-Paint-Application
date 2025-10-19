package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;

    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    public Rectangle rectangle;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline"
        switch(this.mode){
            case "Circle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Circle");
                     Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
                        this.circle=new Circle(centre, 0);
                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    double dx = this.circle.getCentre().x-mouseEvent.getX();
                    double dy = this.circle.getCentre().y-mouseEvent.getY();
                    double radius = Math.sqrt(dx*dx+dy*dy);
                    this.circle.setRadius(radius);

                    // Shows the circle while dragging
                    this.update(this.model, null);
                    GraphicsContext g2d = this.getGraphicsContext2D();
                    g2d.setFill(Color.GREEN);
                    double x = this.circle.getCentre().x - radius;
                    double y = this.circle.getCentre().y - radius;
                    double diameter = radius * 2;
                    g2d.fillOval(x, y, diameter, diameter);



                } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.circle!=null){
                                double dx = this.circle.getCentre().x-mouseEvent.getX();
                                double dy = this.circle.getCentre().y-mouseEvent.getY();
                                double radius = Math.sqrt(dx*dx+dy*dy);
                                this.circle.setRadius(radius);
                                this.model.addCircle(this.circle);
                                System.out.println("Added Circle");
                                this.circle=null;
                        }
                }

                break;
            case "Rectangle":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Rectangle");
                    Point top_left = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.rectangle=new Rectangle(top_left, 0, 0);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED))
                {
                    double dx = mouseEvent.getX() - this.rectangle.getTop_left().x;
                    double dy = mouseEvent.getY() - this.rectangle.getTop_left().y;
                    this.rectangle.setWidth(dx);
                    this.rectangle.setHeight(dy);

                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (this.rectangle != null) {
                        double dx = mouseEvent.getX() - this.rectangle.getTop_left().x;
                        double dy = mouseEvent.getY() - this.rectangle.getTop_left().y;
                        ;
                        this.rectangle.setWidth(dx);
                        this.rectangle.setHeight(dy);
                        this.model.addRectangle(this.rectangle);
                        System.out.println("Added Rectangle");
                        this.rectangle = null;
                    }
                }
                break;




            case "Square": break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    this.model.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));
                }
                break;
            case "Polyline": break;
            default: break;
        }
    }
    @Override
    public void update(Observable o, Object arg) {

                GraphicsContext g2d = this.getGraphicsContext2D();
                g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
                // Draw Lines
                ArrayList<Point> points = this.model.getPoints();

                g2d.setFill(Color.RED);
                for(int i=0;i<points.size()-1; i++){
                        Point p1=points.get(i);
                        Point p2=points.get(i+1);
                        g2d.strokeLine(p1.x,p1.y,p2.x,p2.y);
                }

                // Draw Circles
                ArrayList<Circle> circles = this.model.getCircles();

                g2d.setFill(Color.GREEN);
                for(Circle c: this.model.getCircles()){
                        double radius = c.getRadius();
                        double x = c.getCentre().x - radius;
                        double y = c.getCentre().y - radius;
                        double diameter = radius * 2;
                        g2d.fillOval(x, y, diameter, diameter);
                }

                //Draw Rectangles
                ArrayList<Rectangle> rectangles = this.model.getRectangles();
                g2d.setFill(Color.GREEN);

                for (Rectangle x : this.model.getRectangles())
                {
                    g2d.fillRect(x.getTop_left().x, x.getTop_left().y, x.getWidth(), x.getHeight());
                }
    }
}
