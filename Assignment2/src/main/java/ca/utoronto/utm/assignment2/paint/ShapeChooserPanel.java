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
 * ShapeChooserPanel is a UI component that allows the user to select different tools for painting.
 * Selecting a tool highlights its button and sets the current tool in the PaintPanel.
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Circle", "Rectangle", "Square", "Triangle", "Oval", "Squiggle", "Polyline", "Eraser", "PaintBucket", "SelectMove","Paste", "Cut", "Copy", "Text"};
        String[] shortcuts = {"C", "R", "S", "T", "O", "Q", "L", "E","P","M", "", "", "", ""};

        int row = 0;
        for (String label : buttonLabels) {
//          Image icon = new Image(getClass().getResourceAsStream("/icons/" + label.toLowerCase() + ".png"));
            String path = "/icons/" + label.toLowerCase() + ".png";
            InputStream is = getClass().getResourceAsStream(path);

            Button button;
            if (is != null) {
                Image icon = new Image(is);
                ImageView iconView = new ImageView(icon);
                iconView.setFitWidth(24);
                iconView.setFitHeight(24);

                button = new Button(label, iconView);
            } else {
                // No icon? Just make a text-only button
                System.out.println("No icon found for " + label + " at " + path);
                button = new Button(label);
            }

            button.setMinWidth(100);
            this.add(button, 0, row++);

            // Highlight selected button and set active Tool
            button.setOnAction(actionEvent -> {
                for (javafx.scene.Node n : this.getChildren()) {
                    n.setStyle("");
                }
                button.setStyle("-fx-background-color: skyblue;");

                try {
                    ToolType type = ToolType.valueOf(label.toUpperCase());
                    view.setTool(type);
                    System.out.println("Tool selected: " + type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown tool type: " + label);
                }
            });
        }
    }

    @Override
    public void handle(ActionEvent event) {
        // Not needed anymore since we use lambdas directly,
        // but kept for interface compliance.
        String command = ((Button) event.getSource()).getText();
        try {
            ToolType type = ToolType.valueOf(command.toUpperCase());
            view.setTool(type);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown tool: " + command);
        }
    }

    /**
     * Provide visual feedback by highlighting a selected shape
     * by giving the button a blue tint.
     * @param toolName
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
     * Set the cursor of the canvas to represent the tool currently selected.
     * Icons for Eraser, PaintBucket, Eyedropper.
     * Crosshair for Shapes.
     * Default otherwise.
     * @param tool
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
