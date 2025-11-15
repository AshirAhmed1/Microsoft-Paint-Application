package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Map;
import ca.utoronto.utm.assignment2.paint.command.RecolorCommand;

/**
 * A panel that allows users to choose colors for drawing. It provides both
 * a ColorPicker for custom colors and preset color buttons for quick selection.
 * Selected shapes are recolored through the command system to support undo and redo.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ColorChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private ColorPicker picker;

    /**
     * Constructs a new ColorChooserPanel and initializes the color picker
     * and preset color buttons. Selecting a color updates the PaintModel.
     *
     * @param paintModel the model whose current color will be modified
     */
    public ColorChooserPanel(PaintModel paintModel) {
        this.setHgap(10);
        this.setPadding(new Insets(10));

        this.picker = new ColorPicker(Color.BLACK);

        picker.setOnAction(e -> {
            Color newColor = picker.getValue();
            paintModel.setCurrentColor(newColor);

            if (paintModel.getSelected() != null) {
                paintModel.executeCommand(
                        new RecolorCommand(paintModel, paintModel.getSelected(), newColor)
                );
            }
        });

        this.add(picker, 0, 0);

        Map<Color, String> colors = Map.of(
                Color.RED, "RED",
                Color.ORANGE, "ORANGE",
                Color.YELLOW, "YELLOW",
                Color.GREEN, "GREEN",
                Color.BLUE, "BLUE",
                Color.BLACK, "BLACK"
        );

        int col = 1;
        for (Map.Entry<Color, String> entry : colors.entrySet()) {
            Color color = entry.getKey();
            String name = entry.getValue();
            Button button = new Button();

            button.setShape(new Circle(15));
            button.setPrefSize(30, 30);

            BackgroundFill fill = new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY);
            Background background = new Background(fill);
            button.setBackground(background);

            this.add(button, col, 0);
            col++;

            button.setOnAction(e -> {
                System.out.println("selected color " + name);

                paintModel.setCurrentColor(color);
                picker.setValue(color);

                if (paintModel.getSelected() != null) {
                    paintModel.executeCommand(
                            new RecolorCommand(paintModel, paintModel.getSelected(), color)
                    );
                }
            });
        }

        paintModel.addObserver((obs, args) -> {
            Color curr = paintModel.getCurrentColor();
            if (!picker.getValue().equals(curr)) {
                picker.setValue(curr);
            }
        });
    }

    /**
     * Required implementation of EventHandler, but not used
     * for this panel’s behavior.
     *
     * @param event unused
     */
    @Override
    public void handle(ActionEvent event) {}
}
