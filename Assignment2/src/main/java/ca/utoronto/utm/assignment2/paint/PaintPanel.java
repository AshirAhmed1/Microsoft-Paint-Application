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
    // Used while the user is drawing a squiggle
    private Squiggle currentSquiggle;


    public Circle circle; // This is VERY UGLY, should somehow fix this!!
    public Rectangle rectangle;
    public Square square;
    public Oval oval;

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


                    // Shows the rectangle while dragging
                    this.update(this.model, null);
                    GraphicsContext g2d = this.getGraphicsContext2D();
                    g2d.setFill(Color.GREEN);
                    double x = this.rectangle.getWidth();
                    double y = this.rectangle.getHeight();
                    Point topleft = this.rectangle.getTop_left();
                    g2d.fillRect(topleft.x, topleft.y, x, y);

                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (this.rectangle != null) {
                        double dx = mouseEvent.getX() - this.rectangle.getTop_left().x;
                        double dy = mouseEvent.getY() - this.rectangle.getTop_left().y;
                        this.rectangle.setWidth(dx);
                        this.rectangle.setHeight(dy);
                        this.model.addRectangle(this.rectangle);
                        System.out.println("Added Rectangle");
                        this.rectangle = null;
                    }
                }
                break;




            case "Square":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Square");
                    Point top_left = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.square = new Square(top_left, 0);
                }
                else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    double dx = mouseEvent.getX() - this.square.getTop_left().x;
                    double dy = mouseEvent.getY() - this.square.getTop_left().y;
                    double side = Math.max(Math.abs(dx), Math.abs(dy));
                    this.square.setSideLength(side);

                    // Shows the square while dragging
                    this.update(this.model, null);
                    GraphicsContext g2d = this.getGraphicsContext2D();
                    g2d.setFill(Color.GREEN);
                    Point topleft = this.square.getTop_left();
                    g2d.fillRect(topleft.x, topleft.y, side, side);
                }

                else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if (this.square != null) {
                        double dx = mouseEvent.getX() - this.square.getTop_left().x;
                        double dy = mouseEvent.getY() - this.square.getTop_left().y;
                        double side = Math.max(Math.abs(dx), Math.abs(dy));
                        this.square.setSideLength(side);
                        this.model.addSquare(this.square);
                        System.out.println("Added Square");
                        this.square = null;
                    }
                }
                break;

            case "Triangle": break;
            case "Oval":
                if(mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    System.out.println("Started Oval");
                    Point topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
                    this.oval=new ca.utoronto.utm.assignment2.paint.Oval(topLeft, 0, 0);

                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    double dx = this.oval.getTopLeft().x-mouseEvent.getX();
                    double dy = this.oval.getTopLeft().y-mouseEvent.getY();
                    this.oval.setWidth(Math.abs(dx));
                    this.oval.setHeight(Math.abs(dy));

                    // Shows the oval while dragging
                    this.update(this.model, null);
                    GraphicsContext g2d = this.getGraphicsContext2D();
                    g2d.setFill(Color.GREEN);
                    double x = this.oval.getTopLeft().x;
                    double y = this.oval.getTopLeft().y;
                    double width =  this.oval.getWidth();
                    double height = this.oval.getHeight();
                    g2d.fillOval(x, y, width, height);

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    if(this.oval!=null){
                        double dx = this.oval.getTopLeft().x-mouseEvent.getX();
                        double dy = this.oval.getTopLeft().y-mouseEvent.getY();
                        this.oval.setWidth(Math.abs(dx));
                        this.oval.setHeight(Math.abs(dy));
                        this.model.addOval(this.oval);
                        System.out.println("Added Oval");
                        this.oval=null;
                    }
                }
                break;
            case "Squiggle":
                if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                    // Start a new squiggle stroke
                    currentSquiggle = new Squiggle();
                    currentSquiggle.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));

                } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                    // Add points while dragging and show live drawing
                    if (currentSquiggle != null) {
                        currentSquiggle.addPoint(new Point(mouseEvent.getX(), mouseEvent.getY()));

                        // Redraw all completed squiggles + other shapes
                        this.update(this.model, null);

                        // Draw the in-progress squiggle on top
                        GraphicsContext g2d = this.getGraphicsContext2D();
                        g2d.setStroke(Color.RED);
                        ArrayList<Point> pts = currentSquiggle.getPoints();
                        for (int i = 0; i < pts.size() - 1; i++) {
                            Point p1 = pts.get(i);
                            Point p2 = pts.get(i + 1);
                            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
                        }
                    }

                } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                    // Finalize and store squiggle
                    if (currentSquiggle != null) {
                        this.model.addSquiggle(currentSquiggle);
                        currentSquiggle = null;
                    }
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
                // Draw Squiggles
                g2d.setStroke(Color.RED);
                for (Squiggle s : this.model.getSquiggles()) {
                    ArrayList<Point> pts = s.getPoints();
                    for (int i = 0; i < pts.size() - 1; i++) {
                        Point p1 = pts.get(i);
                        Point p2 = pts.get(i + 1);
                        g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
                    }
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

                //Draw Squares
                ArrayList<Square> squares = this.model.getSquares();
                g2d.setFill(Color.GREEN);

                for (Square x : squares)
                {
                    g2d.fillRect(x.getTop_left().x, x.getTop_left().y, x.getSideLength(), x.getSideLength());
                }

                // Draw Ovals
                ArrayList<ca.utoronto.utm.assignment2.paint.Oval> ovals = this.model.getOvals();

                g2d.setFill(Color.GREEN);
                for(ca.utoronto.utm.assignment2.paint.Oval ov: this.model.getOvals()){
                    double dx = ov.getTopLeft().x;
                    double dy = ov.getTopLeft().y;
                    double width =  ov.getWidth(); // diameter
                    double height = ov.getHeight();
                    g2d.fillOval(dx, dy, width, height);
                }
    }
}
