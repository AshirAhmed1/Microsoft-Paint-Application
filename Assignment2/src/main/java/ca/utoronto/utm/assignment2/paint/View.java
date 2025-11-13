package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ca.utoronto.utm.assignment2.paint.tools.*; // import for Tool, ToolType, ToolFactory

public class View implements EventHandler<ActionEvent> {

    private PaintModel paintModel;
    private PaintPanel paintPanel;
    private ShapeChooserPanel shapeChooserPanel;
    private ColorChooserPanel colorChooserPanel;

    public View(PaintModel model, Stage stage) {
        this.paintModel = model;

        this.paintPanel = new PaintPanel(this.paintModel);
        this.shapeChooserPanel = new ShapeChooserPanel(this);
        this.colorChooserPanel = new ColorChooserPanel(paintModel);
        ThicknessChooserPanel thicknessChooserPanel = new ThicknessChooserPanel(this.paintModel);
        FillStyleChooserPanel fillStyleChooserPanel = new FillStyleChooserPanel(this.paintModel);

        BorderPane root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(this.paintPanel);
        VBox leftPanel = new VBox(this.shapeChooserPanel, thicknessChooserPanel, fillStyleChooserPanel);
        root.setLeft(leftPanel);
        root.setBottom(this.colorChooserPanel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();
    }

    public PaintModel getPaintModel() {
        return this.paintModel;
    }

    public PaintPanel getPaintPanel() {
        return this.paintPanel;
    }

    /**
     * Old: setMode(String mode)
     * New: setTool(ToolType type)
     * Called by ShapeChooserPanel when a button is clicked.
     */
    public void setTool(ToolType type) {
        Tool tool = ToolFactory.create(type, this.paintModel, this.paintPanel);
        this.paintPanel.setCurrentTool(tool);

    }
    public void copySelection() {
        this.paintModel.copySelected();
    }

    public void cutSelection() {
        this.paintModel.cutSelected();
    }


    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        // === File Menu ===
        menu = new Menu("File");
        menuItem = new MenuItem("New");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Open");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Save");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());

        menuItem = new MenuItem("Exit");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        // === Edit Menu ===
        menu = new Menu("Edit");
        menuItem = new MenuItem("Cut");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Copy");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Paste");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());
        menuItem = new MenuItem("Undo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Redo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        return menuBar;
    }

    @Override
    public void handle(ActionEvent event) {
        String command = ((MenuItem) event.getSource()).getText();
        System.out.println("Menu command: " + command);
        if (command.equals("Exit")) {
            Platform.exit();
        }
    }
}
