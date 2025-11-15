package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.command.CutCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.InputStream;

/**
 * A panel providing editing tools, including cut, copy, paste, clear,
 * undo, and redo. Each button triggers its corresponding action on the
 * PaintModel and refreshes the canvas.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class EditToolPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;

    /**
     * Constructs a new EditToolPanel and creates the set of edit buttons.
     *
     * @param view the application view used to access model and panel updates
     */
    public EditToolPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Paste (Ctrl+V)", "Cut (Ctrl+X)", "Copy (Ctrl+C)", "Clear (DEL)", "Undo (Ctrl+Z)", "Redo (Ctrl+Y)"};

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
                button = new Button(label);
            }

            button.setMinWidth(100);
            this.add(button, 0, row++);

            button.setOnAction(e -> handleButton(label));
        }
    }

    /**
     * Handles the action of a button based on its label.
     * Executes the appropriate edit command and refreshes the canvas.
     *
     * @param label the label of the button clicked
     */
    private void handleButton(String label) {
        System.out.println("Button pressed: " + label);
        switch (label) {
            case "Copy (Ctrl+C)" -> {
                view.copySelection();
            }
            case "Cut (Ctrl+X)" -> {
                Drawable sel = view.getPaintModel().getSelected();
                if (sel != null) {
                    view.getPaintModel().executeCommand(new CutCommand(view.getPaintModel(), sel));
                }
                view.getPaintPanel().refresh();
            }
            case "Paste (Ctrl+V)" -> {
                view.getPaintModel().pasteAt(150, 150);
                view.getPaintPanel().refresh();
            }
            case "Clear (DEL)" -> {
                view.getPaintModel().clearCanvas();
                view.getPaintPanel().refresh();
            }
            case "Undo (Ctrl+Z)" -> {
                view.getPaintModel().undo();
                view.getPaintPanel().refresh();
            }
            case "Redo (Ctrl+Y)" -> {
                view.getPaintModel().redo();
                view.getPaintPanel().refresh();
            }
            default -> System.out.println("Unknown edit action: " + label);
        }
    }

    /**
     * Required event handler implementation, unused by this panel.
     *
     * @param event ignored
     */
    @Override
    public void handle(ActionEvent event) {

    }

}
