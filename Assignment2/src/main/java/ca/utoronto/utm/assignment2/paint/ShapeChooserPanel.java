package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.assignment2.paint.tools.ToolType;
import java.io.InputStream;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Circle", "Rectangle", "Square", "Triangle", "Oval", "Squiggle", "Polyline", "SelectMove","Paste", "cut", "copy"};

        int row = 0;
        for (String label : buttonLabels) {
          Image icon = new Image(getClass().getResourceAsStream("/icons/" + label.toLowerCase() + ".png"));

            Button button;
            if (!icon.isError()) {
                ImageView iconView = new ImageView(icon);
                iconView.setFitWidth(24);
                iconView.setFitHeight(24);

                button = new Button(label, iconView);
            } else {
                button = new Button(label);
            }

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
}
