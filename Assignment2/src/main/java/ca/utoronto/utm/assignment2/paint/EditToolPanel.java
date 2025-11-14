package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

public class EditToolPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    public EditToolPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Paste", "Cut", "Copy", "Clear", "Undo", "Redo"};

        int row = 0;
        for (String label : buttonLabels) {

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
                System.out.println("No icon found for " + label + " at " + path);
                button = new Button(label);
            }

            button.setMinWidth(100);
            this.add(button, 0, row++);

            button.setOnAction(e -> handleButton(label));
        }
    }

    private void handleButton(String label) {
        switch (label) {
            case "Copy" -> {
                view.copySelection();
            }
            case "Cut" -> {
                view.cutSelection();
                view.getPaintPanel().refresh();
            }
            case "Paste" -> {
                // You can improve with mouse location later.
                view.getPaintModel().pasteAt(150, 150);
                view.getPaintPanel().refresh();
            }
            case "Clear" -> {
                view.getPaintModel().clearCanvas();
                view.getPaintPanel().refresh();
            }
            case "Undo" -> {
                view.getPaintModel().undo();
                view.getPaintPanel().refresh();
            }
            case "Redo" -> {
                view.getPaintModel().redo();
                view.getPaintPanel().refresh();
            }
            default -> System.out.println("Unknown edit action: " + label);
        }
    }

    @Override
    public void handle(ActionEvent event) {

    }

}
