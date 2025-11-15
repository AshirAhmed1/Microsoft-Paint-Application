package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.command.CutCommand;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ca.utoronto.utm.assignment2.paint.tools.*;

/**
 * The main View class of the paint application. It constructs the UI layout,
 * initializes all tool panels, handles keyboard shortcuts, and delegates tool
 * creation to the ToolFactory. It also routes menu bar actions and forwards
 * selection or editing actions to the PaintModel.
 *
 * This class acts as both the View and (partially) the Controller in the MVC
 * architecture, linking user interaction to model updates and canvas repainting.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public class View implements EventHandler<ActionEvent> {

    private PaintModel paintModel;
    private PaintPanel paintPanel;
    private ShapeChooserPanel shapeChooserPanel;
    private ColorChooserPanel colorChooserPanel;
    private EditToolPanel editToolPanel;

    /**
     * Constructs the main View, initializes all UI components, registers
     * keyboard shortcuts, and sets up the scene layout.
     *
     * @param model the PaintModel used for all drawing state and operations
     * @param stage the main application window
     */
    public View(PaintModel model, Stage stage) {
        this.paintModel = model;

        this.paintPanel = new PaintPanel(this.paintModel);
        this.shapeChooserPanel = new ShapeChooserPanel(this);
        this.colorChooserPanel = new ColorChooserPanel(paintModel);
        ThicknessChooserPanel thicknessChooserPanel = new ThicknessChooserPanel(this.paintModel);
        FillStyleChooserPanel fillStyleChooserPanel = new FillStyleChooserPanel(this.paintModel);

        this.editToolPanel = new EditToolPanel(this);

        BorderPane root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(this.paintPanel);

        VBox leftPanel = new VBox(this.shapeChooserPanel, thicknessChooserPanel, fillStyleChooserPanel);
        root.setLeft(leftPanel);

        root.setRight(this.editToolPanel);
        root.setBottom(this.colorChooserPanel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");

        // ============================
        //      TOOL SHORTCUT KEYS
        // ============================
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case C -> { setTool(ToolType.CIRCLE); shapeChooserPanel.highlightButton("Circle"); }
                case R -> { setTool(ToolType.RECTANGLE); shapeChooserPanel.highlightButton("Rectangle"); }
                case S -> { setTool(ToolType.SQUARE); shapeChooserPanel.highlightButton("Square"); }
                case T -> { setTool(ToolType.TRIANGLE); shapeChooserPanel.highlightButton("Triangle"); }
                case O -> { setTool(ToolType.OVAL); shapeChooserPanel.highlightButton("Oval"); }
                case Q -> { setTool(ToolType.SQUIGGLE); shapeChooserPanel.highlightButton("Squiggle"); }
                case L -> { setTool(ToolType.POLYLINE); shapeChooserPanel.highlightButton("Polyline"); }
                case P -> { setTool(ToolType.PAINTBUCKET); shapeChooserPanel.highlightButton("PaintBucket"); }
                case I -> { setTool(ToolType.EYEDROPPER); shapeChooserPanel.highlightButton("Eyedropper"); }
                case M -> { setTool(ToolType.SELECTMOVE); shapeChooserPanel.highlightButton("SelectMove"); }
                case X -> { setTool(ToolType.TEXT); shapeChooserPanel.highlightButton("Text"); }
                default -> {}
            }
        });

        // ============================
        //      EDIT ACTION SHORTCUTS
        // ============================
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {

            if (e.isControlDown() && e.getCode() == javafx.scene.input.KeyCode.Z) {
                System.out.println("Button pressed: Undo");
                paintModel.undo();
                e.consume();
                return;
            }

            if (e.isControlDown() && e.getCode() == javafx.scene.input.KeyCode.Y) {
                System.out.println("Button pressed: Redo");
                paintModel.redo();
                e.consume();
                return;
            }

            if (e.isControlDown() && e.getCode() == javafx.scene.input.KeyCode.C) {
                System.out.println("Button pressed: Copy");
                paintModel.copySelected();
                e.consume();
                return;
            }

            if (e.isControlDown() && e.getCode() == javafx.scene.input.KeyCode.X) {
                System.out.println("Button pressed: Cut");
                Drawable sel = paintModel.getSelected();
                if (sel != null) {
                    paintModel.executeCommand(new CutCommand(paintModel, sel));
                }
                e.consume();
                return;
            }

            if (e.isControlDown() && e.getCode() == javafx.scene.input.KeyCode.V) {
                System.out.println("Button pressed: Paste");
                paintModel.pasteAt(150, 150);
                e.consume();
                return;
            }

            if (e.getCode() == javafx.scene.input.KeyCode.DELETE) {
                System.out.println("Button pressed: Clear");
                paintModel.clearCanvas();
                e.consume();
            }
        });

        stage.show();
    }

    /**
     * Returns the application's PaintModel.
     *
     * @return the model instance
     */
    public PaintModel getPaintModel() {
        return this.paintModel;
    }

    /**
     * Returns the PaintPanel (canvas area).
     *
     * @return the canvas panel
     */
    public PaintPanel getPaintPanel() {
        return this.paintPanel;
    }

    /**
     * Sets the currently active tool. This method delegates tool creation to the
     * ToolFactory and updates the cursor depending on the tool selected.
     *
     * @param type the tool type selected by the user
     */
    public void setTool(ToolType type) {
        Tool tool = ToolFactory.create(type, this.paintModel, this.paintPanel);
        this.paintPanel.setCurrentTool(tool);

        switch (type) {
            case PAINTBUCKET -> shapeChooserPanel.setCanvasCursor("/icons/paintbucket.png", 100, 100);
            case EYEDROPPER -> shapeChooserPanel.setCanvasCursor("/icons/eyedropper.png", 32, 165);
            case SELECTMOVE, PASTE, CUT, COPY -> paintPanel.setCursor(ImageCursor.DEFAULT);
            default -> paintPanel.setCursor(ImageCursor.CROSSHAIR);
        }
    }

    /**
     * Delegates a copy request to the model.
     */
    public void copySelection() {
        this.paintModel.copySelected();
    }

    /**
     * Builds and returns the menu bar containing File and Edit menus.
     *
     * @return a fully constructed MenuBar
     */
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

    /**
     * Handles menu bar actions such as New, Open, Save, and Exit.
     *
     * @param event the triggered action event
     */
    @Override
    public void handle(ActionEvent event) {
        String command = ((MenuItem) event.getSource()).getText();
        System.out.println("Menu command: " + command);

        if (command.equals("Exit")) {
            Platform.exit();
        }
    }
}
