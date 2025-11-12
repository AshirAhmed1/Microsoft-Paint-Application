package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.assignment2.paint.tools.ToolType;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Circle", "Rectangle", "Square", "Triangle", "Oval", "Squiggle", "Polyline", "SelectMove"};
        String[] shortcuts = {"C", "R", "S", "T", "O", "Q", "L", "M"};

        int row = 0;
        for (int i = 0; i < buttonLabels.length; i++) {
            String label = buttonLabels[i];
            String shortcut = shortcuts[i];
            Image icon = new Image(getClass().getResourceAsStream("/icons/" + label.toLowerCase() + ".png"));
            ImageView iconView = new ImageView(icon);
            iconView.setFitWidth(24);
            iconView.setFitHeight(24);

            Button button = new Button(label + " (" + shortcut + ")", iconView);
            button.setMinWidth(100);
            button.setTooltip(new javafx.scene.control.Tooltip("Press " + shortcut + " to select " + label));
            this.add(button, 0, row);
            row++;

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

    public void highlightButton(String toolName) {
        for (javafx.scene.Node node : this.getChildren()) {
            Button btn = (Button) node;
            btn.setStyle("");
            if (btn.getText().toUpperCase().startsWith(toolName.toUpperCase())) {
                btn.setStyle("-fx-background-color: skyblue;");
            }
        }
    }
}
