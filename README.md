# Microsoft Paint Application

A desktop drawing application built with **Java and JavaFX**, featuring multiple drawing tools, object manipulation, customizable styling, clipboard operations, keyboard shortcuts, and command-based undo/redo.

The application was designed using object-oriented programming principles and incorporates several software design patterns, including **MVC, Observer, Command, and Factory**, to separate application state, rendering, user interaction, and tool creation.

## Demo

▶️ **[Watch the Full Application Demo](https://drive.google.com/file/d/1RLx8O17vyPEO2Zy77sGc-EETGuH5pP5Q/view?usp=sharing)**

The demo showcases drawing tools, styling controls, object manipulation, clipboard operations, text insertion, and undo/redo functionality.

---

## Features

### Drawing Tools

The application supports several drawable object types:

- Circle
- Rectangle
- Square
- Triangle
- Oval
- Squiggle / freehand drawing
- Polyline
- Text

Shapes can be created using the currently selected:

- Color
- Line thickness
- Fill style

### Editing Tools

Users can interact with objects already placed on the canvas through several editing operations:

- Select and move shapes
- Copy selected objects
- Cut selected objects
- Paste copied or cut objects
- Clear the canvas
- Undo previous actions
- Redo undone actions

### Styling Tools

The application includes several options for customizing drawn objects:

- Custom color picker
- Preset color palette
- Filled and outlined shape modes
- Adjustable line thickness
- Paint Bucket for recoloring existing shapes
- Eyedropper for selecting colors from existing objects

### Text

The Text tool allows users to select a location on the canvas and insert custom text through a dialog.

---

## Application Architecture

The application is centered around a `PaintModel` that stores the current state of the drawing application.

The model maintains information such as:

- Drawn objects
- Preview shapes
- Current color
- Current line thickness
- Fill mode
- Selected object
- Clipboard contents
- Undo stack
- Redo stack

`PaintPanel` is responsible for rendering the canvas and responding to changes in the model.

User interaction is delegated to specialized tool classes. For example:

```text
CircleTool
RectangleTool
TriangleTool
SquiggleTool
PolylineTool
SelectMoveTool
PaintBucketTool
EyeDropperTool
TextTool
```

Rather than placing all interaction logic inside a single controller, each tool handles the behavior associated with a specific interaction mode.

---

## Design Patterns

### MVC Architecture

The application separates state, interface construction, rendering, and interaction responsibilities.

- `PaintModel` stores and manages application state
- `View` constructs the main application interface
- `PaintPanel` displays the canvas and handles interaction with the currently active tool

This separation helps keep the application easier to maintain and extend.

### Observer Pattern

`PaintModel` extends Java's `Observable` class, while `PaintPanel` observes the model.

When the model changes, the canvas is notified and redrawn.

This keeps the visual state of the application synchronized with the underlying application data.

### Command Pattern

Actions that require undo and redo support are represented using command objects.

Examples include:

```text
AddShapeCommand
MoveCommand
CutCommand
PasteCommand
ClearCanvasCommand
RecolorCommand
StyleCommand
TextCommand
```

Each command implements the `Command` interface and provides behavior for executing and reversing an action.

This allows editing operations to be stored as objects and later undone or reapplied.

### Factory Pattern

`ToolFactory` creates the appropriate tool implementation based on the selected `ToolType`.

Centralizing tool creation reduces coupling between the interface and individual tool implementations and makes new tools easier to introduce.

---

## Undo and Redo System

Undo and redo are implemented using two stacks maintained inside `PaintModel`.

### Undo Stack

Stores commands that have already been executed.

### Redo Stack

Stores commands that were previously undone.

When a new command executes:

1. The command is performed.
2. It is pushed onto the undo stack.
3. The redo stack is cleared.

When Undo is triggered:

1. The most recent command is removed from the undo stack.
2. Its `undo()` behavior is executed.
3. The command is placed on the redo stack.

When Redo is triggered:

1. The command is removed from the redo stack.
2. The command is executed again.
3. It is returned to the undo stack.

This provides consistent reversible behavior across supported editing actions.

---

## Clipboard System

The application contains an internal clipboard for copying, cutting, and pasting drawable objects.

Supported clipboard objects include:

- Circle
- Rectangle
- Square
- Triangle
- Oval
- Text
- Polyline
- Squiggle

Copied objects are duplicated before being stored in the clipboard.

Pasted objects are repositioned and inserted into the application through `PasteCommand`, which also allows paste operations to participate in the undo/redo system.

---

## Tool Architecture

Each interaction mode is implemented as a dedicated tool class.

### Shape Tools

Shape tools use click-and-drag interaction.

While the user is dragging, the application displays a preview of the shape before committing the final object to the model.

Examples:

```text
CircleTool
RectangleTool
SquareTool
TriangleTool
OvalTool
```

### Freehand Tools

`SquiggleTool` creates a continuous freehand path from multiple mouse coordinates.

`PolylineTool` creates a multi-segment line using a sequence of user-selected points.

### Editing Tools

Editing operations are separated into dedicated classes:

```text
SelectMoveTool
CopyTool
CutTool
PasteTool
ClearCanvasTool
UndoTool
```

### Color and Style Tools

```text
PaintBucketTool
EyeDropperTool
TextTool
```

---

## Keyboard Shortcuts

### Drawing Tools

| Key | Tool |
|---|---|
| `C` | Circle |
| `R` | Rectangle |
| `S` | Square |
| `T` | Triangle |
| `O` | Oval |
| `Q` | Squiggle |
| `L` | Polyline |
| `P` | Paint Bucket |
| `I` | Eyedropper |
| `M` | Select / Move |
| `X` | Text |

### Editing

| Shortcut | Action |
|---|---|
| `Ctrl + C` | Copy |
| `Ctrl + X` | Cut |
| `Ctrl + V` | Paste |
| `Ctrl + Z` | Undo |
| `Ctrl + Y` | Redo |
| `Delete` | Clear Canvas |

---

## Technologies

- **Java**
- **JavaFX**
- Object-Oriented Programming
- MVC Architecture
- Observer Pattern
- Command Pattern
- Factory Pattern
- Event-Driven Programming
- Java GUI Development

---

## Project Structure

```text
src/main/java/ca/utoronto/utm/assignment2/
│
├── paint/
│   ├── Paint.java
│   ├── View.java
│   ├── PaintModel.java
│   ├── PaintPanel.java
│   ├── Drawable.java
│   ├── AbstractShapeDrawable.java
│   │
│   ├── Circle.java
│   ├── Rectangle.java
│   ├── Square.java
│   ├── Triangle.java
│   ├── Oval.java
│   ├── Squiggle.java
│   ├── Polyline.java
│   ├── Text.java
│   │
│   ├── command/
│   │   ├── Command.java
│   │   ├── AddShapeCommand.java
│   │   ├── ClearCanvasCommand.java
│   │   ├── CutCommand.java
│   │   ├── MoveCommand.java
│   │   ├── PasteCommand.java
│   │   ├── RecolorCommand.java
│   │   ├── StyleCommand.java
│   │   └── TextCommand.java
│   │
│   └── tools/
│       ├── Tool.java
│       ├── ToolType.java
│       ├── ToolFactory.java
│       ├── AbstractShapeTool.java
│       ├── CircleTool.java
│       ├── RectangleTool.java
│       ├── SquareTool.java
│       ├── TriangleTool.java
│       ├── OvalTool.java
│       ├── SquiggleTool.java
│       ├── PolylineTool.java
│       ├── SelectMoveTool.java
│       ├── PaintBucketTool.java
│       ├── EyeDropperTool.java
│       ├── CopyTool.java
│       ├── CutTool.java
│       ├── PasteTool.java
│       ├── ClearCanvasTool.java
│       ├── UndoTool.java
│       └── TextTool.java
│
├── scribble/
│   ├── Scribble.java
│   └── ScribblePanel.java
│
├── HelloApplication.java
└── HelloController.java
```

---

# Running the Project

## Requirements

Before running the application, install:

- **Java Development Kit (JDK) 21**
- **JavaFX SDK**
- An IDE with Java support

The project can be run using:

- Visual Studio Code
- IntelliJ IDEA
- Eclipse

The instructions below use **Visual Studio Code**.

---

## 1. Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/AshirAhmed1/Microsoft-Paint-Application.git
```

Move into the repository:

```bash
cd Microsoft-Paint-Application
```

You can also download the repository directly from GitHub using:

```text
Code → Download ZIP
```

Extract the ZIP before opening the project.

---

## 2. Install Java

Install **JDK 21**.

Verify that Java is installed by opening a terminal and running:

```bash
java -version
```

You should see Java 21 listed.

You can also verify the Java compiler:

```bash
javac -version
```

---

## 3. Install Visual Studio Code Java Support

Open Visual Studio Code.

Go to the **Extensions** tab and install:

```text
Extension Pack for Java
```

Publisher:

```text
Microsoft
```

This provides Java language support, project management, debugging, and execution inside VS Code.

---

## 4. Install JavaFX

Download the **JavaFX SDK** for your operating system.

Extract it somewhere permanent on your computer.

For example, on Windows:

```text
C:\javafx-sdk-21
```

The important folder is:

```text
C:\javafx-sdk-21\lib
```

Your exact folder name may differ depending on the JavaFX version you downloaded.

---

## 5. Open the Entire Project

In VS Code, select:

```text
File → Open Folder
```

Open the root folder:

```text
Microsoft-Paint-Application
```

Do not open only `Paint.java`.

The application depends on `PaintModel`, `View`, the drawing classes, tool classes, and command classes contained throughout the project.

---

## 6. Configure JavaFX in VS Code

Create a folder named:

```text
.vscode
```

inside the project root if one does not already exist.

Inside `.vscode`, create:

```text
launch.json
```

Add:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Run Paint",
            "request": "launch",
            "mainClass": "ca.utoronto.utm.assignment2.paint.Paint",
            "vmArgs": "--module-path \"C:/javafx-sdk-21/lib\" --add-modules javafx.controls,javafx.fxml"
        }
    ]
}
```

Replace:

```text
C:/javafx-sdk-21/lib
```

with the actual location of your JavaFX `lib` directory.

For example:

```text
C:/Users/YourName/Downloads/javafx-sdk-21/lib
```

---

## 7. Run the Application

The main application class is:

```text
src/main/java/ca/utoronto/utm/assignment2/paint/Paint.java
```

The fully qualified class name is:

```text
ca.utoronto.utm.assignment2.paint.Paint
```

Open `Paint.java`.

Run the application using either:

```text
Run → Start Debugging
```

or select the **Run Paint** configuration from VS Code.

The application will initialize the `PaintModel`, construct the `View`, and open the JavaFX Paint interface.

---

## Main Entry Point

```java
public class Paint extends Application {

    PaintModel model;
    View view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.model = new PaintModel();
        this.view = new View(model, stage);
    }
}
```

JavaFX calls `start(Stage stage)` after the application launches.

The method creates the central `PaintModel` and passes it into the application's `View`.

---

## Contributors

- Ashir
- Alex
- Ahmed

---

## About

This project was developed as part of a university software design course.

It demonstrates the use of **object-oriented programming, GUI development, event-driven programming, application architecture, and software design patterns** within a larger Java application.

The project goes beyond basic shape rendering by implementing a structured tool system, command-based undo and redo, clipboard operations, object manipulation, styling controls, keyboard shortcuts, and multiple interacting design patterns.
