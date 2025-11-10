package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;

public class Circle extends AbstractShapeDrawable {
        private Point centre;
        private double radius;

        public Circle(Point centre, int radius){
            super();
            this.centre = centre;
            this.radius = radius;
        }

        public Point getCentre() {
                return centre;
        }

        public void setCentre(Point centre) {
                this.centre = centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        @Override
        public void draw(GraphicsContext g)
        {
            double x = centre.x - radius;
            double y = centre.y - radius;
            g.setFill(color);
            g.fillOval(x, y, radius * 2, radius * 2);

            g.setStroke(color);
            g.setLineWidth(thickness);
            g.strokeOval(x, y, radius * 2, radius * 2);

        }

}
