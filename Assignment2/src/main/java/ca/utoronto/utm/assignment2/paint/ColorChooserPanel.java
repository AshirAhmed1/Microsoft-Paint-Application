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

public class ColorChooserPanel extends GridPane implements EventHandler<ActionEvent>{

    public ColorChooserPanel(PaintModel paintModel) {
        this.setHgap(10);
        this.setPadding(new Insets(10));


        ColorPicker picker = new ColorPicker(Color.BLACK);


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
        Map<Color, String> colors= Map.of(
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
                
                if (paintModel.getSelected() != null) {
                    paintModel.executeCommand(
                            new RecolorCommand(paintModel, paintModel.getSelected(), color)
                    );
                }
            });
        }
    }


    @Override
    public void handle(ActionEvent event) {}
}
