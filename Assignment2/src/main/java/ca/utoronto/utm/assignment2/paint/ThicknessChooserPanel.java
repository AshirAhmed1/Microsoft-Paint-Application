package ca.utoronto.utm.assignment2.paint;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * A UI panel that allows the user to adjust the current line thickness used
 * for drawing shapes. It provides a slider that updates the PaintModel's
 * thickness value in real time.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class ThicknessChooserPanel extends HBox {

    private PaintModel model;
    private Slider slider;
    private Label label;

    /**
     * Constructs a new ThicknessChooserPanel bound to the given PaintModel.
     * Displays a label and a slider for adjusting stroke thickness.
     *
     * @param model the PaintModel whose thickness setting is modified
     */
    public ThicknessChooserPanel(PaintModel model)
    {
        this.model = model;
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        label = new Label("Line Thickness");

        slider = new Slider(1, 10, model.getCurrentThickness());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setBlockIncrement(1);
        slider.setPrefWidth(200);

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            model.setCurrentThickness(newVal.doubleValue());
            System.out.println("Line thickness set to: " + newVal.intValue());
        });

        this.getChildren().addAll(label, slider);
    }
}
