package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Map;

public class ColorChooserPanel extends GridPane implements EventHandler<ActionEvent>{

    public ColorChooserPanel(PaintModel paintModel) {
        // Array of colors to display with there string representation
        Map<Color, String> colors= Map.of(
                Color.RED, "RED",
                Color.ORANGE, "ORANGE",
                Color.YELLOW, "YELLOW",
                Color.GREEN, "GREEN",
                Color.BLUE, "BLUE",
                Color.BLACK, "BLACK"
        );

        // Add buttons in a row
        int col = 0;
        for (Map.Entry<Color, String> entry : colors.entrySet()) {
            Color color = entry.getKey();
            String name = entry.getValue();
            Button button = new Button();

            // make button round
            button.setShape(new Circle(15));
            button.setPrefSize(30, 30);

            // add color to button
            BackgroundFill fill = new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY);
            Background background = new Background(fill);
            button.setBackground(background);



            this.add(button, col, 0);
            col++;
            button.setOnAction(e -> {
                System.out.println("selected color " + name);
                paintModel.setCurrentColor(color);

            });
        }
    }

    public void handle(ActionEvent event) {

    }
}
