package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.command.StyleCommand;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class FillStyleChooserPanel extends HBox {
    private final PaintModel model;

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

        if (model.isFillMode()) filledBtn.setSelected(true);
        else outlineBtn.setSelected(true);

        toggle.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            boolean newFilled = (newToggle == filledBtn);
            boolean oldFilled = model.isFillMode();

            model.setFillMode(newFilled);

            if (model.getSelected() != null) {
                model.executeCommand(
                        new StyleCommand(model, model.getSelected(), oldFilled, newFilled)
                );
            }

            System.out.println("Fill mode: " + (newFilled ? "FILLED" : "OUTLINE"));
        });

        this.getChildren().addAll(filledBtn, outlineBtn);
    }
}
