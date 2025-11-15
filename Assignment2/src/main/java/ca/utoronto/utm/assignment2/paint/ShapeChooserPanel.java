package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.assignment2.paint.tools.ToolType;

import java.io.InputStream;

/**
 * A panel that displays buttons for selecting different drawing tools and shapes.
 * Each button is associated with an icon, a keyboard shortcut, and updates the
 * active tool in the view when pressed.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    /**
     * Creates the shape chooser panel and initializes all tool buttons with
     * icons, labels, and click handlers.
     *
     * @param view the main view controlling the canvas and tools
     */
    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = {
                "Circle", "Rectangle", "Square", "Triangle", "Oval",
                "Squiggle", "Polyline", "Eraser", "PaintBucket",
                "SelectMove", "Text", "EyeDropper"
        };

        String[] shortcuts = {"C", "R", "S", "T", "O", "Q", "L", "E", "P", "M", "X", "I"};

        int row = 0;
        for (String label : buttonLabels) {

            String path = "/icons/" + label.toLowerCase() + ".png";
            InputStream is = getClass().getResourceAsStream(path);

            if (is == null) {
                System.out.println("Missing icon for: " + label + " → " + path);
            }

            Image icon = (is != null) ? new Image(is) : null;
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(24);
            iconView.setFitHeight(24);

            Button button = new Button(label + " (" + shortcuts[row] + ")", iconView);

            button.setMinWidth(100);
            this.add(button, 0, row++);

            button.setOnAction(actionEvent -> {
                for (javafx.scene.Node n : this.getChildren()) {
                    n.setStyle("");
                }
                button.setStyle("-fx-background-color: skyblue;");

                try {
                    ToolType type = ToolType.valueOf(label.toUpperCase());
                    view.setTool(type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown tool type: " + label);
                }
            });
        }
    }

    /**
     * Required override for EventHandler; no action performed here because
     * buttons use individual lambda handlers instead.
     *
     * @param event the triggered event
     */
    @Override
    public void handle(ActionEvent event) { }

    /**
     * Highlights the button corresponding to the currently active tool by
     * applying a background style and removing it from all others.
     *
     * @param toolName the name of the tool to highlight
     */
    public void highlightButton(String toolName) {
        for (javafx.scene.Node node : this.getChildren()) {
            Button btn = (Button) node;
            btn.setStyle("");
            if (btn.getText().toUpperCase().startsWith(toolName.toUpperCase())) {
                btn.setStyle("-fx-background-color: skyblue;");
            }
        }
    }

    /**
     * Sets the cursor on the canvas to a custom image associated with the tool.
     *
     * @param tool the image filename for the cursor
     * @param x    hotspot x-coordinate
     * @param y    hotspot y-coordinate
     */
    protected void setCanvasCursor(String tool, double x, double y){
        InputStream cursor = getClass().getResourceAsStream(tool);
        if (cursor != null) {
            Image cursorImage = new Image(cursor);
            view.getPaintPanel().setCursor(new ImageCursor(cursorImage, x, y));
        } else {
            System.out.println("Cursor image not found");
            view.getPaintPanel().setCursor(ImageCursor.DEFAULT);
        }
    }
}
