package ca.utoronto.utm.assignment2.paint.tools;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;

public class ToolFactory {
    public static Tool create(ToolType type, PaintModel model, PaintPanel view) {
        return switch (type) {
            case CIRCLE -> new CircleTool(model, view);
            case RECTANGLE -> new RectangleTool(model, view);
            case SQUARE -> new SquareTool(model, view);
            case TRIANGLE -> new TriangleTool(model, view);
            case OVAL -> new OvalTool(model, view);
            case SQUIGGLE -> new SquiggleTool(model, view);
            case POLYLINE -> {
                System.out.println("Polyline tool not yet implemented.");
                yield null; // gracefully handle until PolylineTool exists
            }
        };
    }
}