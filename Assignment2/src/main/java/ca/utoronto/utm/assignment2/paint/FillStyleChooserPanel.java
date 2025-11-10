package ca.utoronto.utm.assignment2.paint;

import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class FillStyleChooserPanel extends HBox {
    private PaintModel model;

    public FillStyleChooserPanel(PaintModel model)
    {
        this.model = model;
        this.setSpacing(10);
        this.setPadding(new Insets(10));

        ToggleGroup toggle = new ToggleGroup();

        ToggleButton filledBtn = new ToggleButton("Filled");
        ToggleButton outlineBtn = new ToggleButton("Outline");

        filledBtn.setToggleGroup(toggle);
        outlineBtn.setToggleGroup(toggle);

        filledBtn.setSelected(model.isFillMode());

        toggle.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == filledBtn) {
                model.setFillMode(true);
                System.out.println("Fill mode: FILLED");
            } else if (newToggle == outlineBtn) {
                model.setFillMode(false);
                System.out.println("Fill mode: OUTLINE");
            }
        });

        this.getChildren().addAll(filledBtn, outlineBtn);
    }
}
