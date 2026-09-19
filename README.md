# Microsoft Paint Application

A JavaFX desktop drawing application featuring shape tools, freehand drawing, object selection and movement, customizable styling, clipboard operations, and command-based undo/redo.

The application is built using object-oriented design principles and applies **MVC, Observer, Command, and Factory design patterns** to separate application state, rendering, user interaction, and tool creation.

## Features

### Drawing Tools

Create and edit multiple types of drawable objects:

* Circle
* Rectangle
* Square
* Triangle
* Oval
* Squiggle / freehand drawing
* Polyline
* Text

Shapes support configurable colors, line thickness, and fill styles.

### Editing Tools

* Select and move shapes
* Copy selected shapes
* Cut selected shapes
* Paste copied or cut shapes
* Clear the canvas
* Undo previous actions
* Redo undone actions

### Styling Tools

* Custom color picker
* Preset colors
* Fill or outline mode
* Adjustable line thickness
* Paint bucket for recoloring shapes
* Eyedropper for selecting an existing shape color

## Architecture

The application is centered around `PaintModel`, which stores the drawing state, selected objects, current styling settings, clipboard contents, and undo/redo history.

`PaintPanel` renders the canvas and observes changes to the model. User interaction is handled through dedicated tool classes such as `CircleTool`, `RectangleTool`, `SquiggleTool`, and `SelectMoveTool`.

Actions that require undo and redo support are implemented as command objects, including:

* `AddShapeCommand`
* `MoveCommand`
* `CutCommand`
* `PasteCommand`
* `ClearCanvasCommand`
* `RecolorCommand`
* `StyleCommand`
* `TextCommand`

This separates user actions from application state and allows editing operations to be reversed and reapplied consistently.

## Design Patterns

### MVC

The project separates application state, interface construction, rendering, and user interaction.

* `PaintModel` manages application state
* `View` builds the main interface and connects controls to application actions
* `PaintPanel` renders the canvas and delegates mouse input to the active tool

### Observer

`PaintModel` extends `Observable`, while `PaintPanel` observes the model.

Whenever the application's state changes, the canvas is notified and redrawn automatically.

### Command

Editing operations that support undo and redo are represented as command objects.

Each command implements the `Command` interface and provides `execute()` and `undo()` behavior.

This pattern is used for actions such as:

* Adding shapes
* Moving shapes
* Cutting and pasting
* Clearing the canvas
* Recoloring objects
* Changing styles
* Adding text

### Factory

`ToolFactory` creates the appropriate tool implementation based on the selected `ToolType`.

Centralizing tool creation makes the application easier to extend without tightly coupling the interface to individual tool classes.

## Undo and Redo

Undo and redo are implemented using two stacks maintained by `PaintModel`.

* The **undo stack** stores executed commands
* The **redo stack** stores commands that have been undone

When a command executes, it is added to the undo stack and the redo stack is cleared.

When undo is triggered, the most recent command is reversed and transferred to the redo stack. Redo executes the command again and returns it to the undo stack.

This allows drawing and editing operations to maintain consistent reversible behavior.

## Clipboard System

The application includes an internal clipboard for copying, cutting, and pasting drawable objects.

Supported objects include:

* Circle
* Rectangle
* Square
* Triangle
* Oval
* Text
* Polyline
* Squiggle

Copied objects are duplicated before being stored in the clipboard. Pasted shapes are translated to their new position and inserted using `PasteCommand`, allowing paste operations to also be undone and redone.

## Tool System

Each interaction mode is implemented as a dedicated tool class.

### Shape Tools

Shape tools use click-and-drag interaction and display a preview while the shape is being created.

Examples include:

* `CircleTool`
* `RectangleTool`
* `SquareTool`
* `TriangleTool`
* `OvalTool`

### Freehand Tools

* `SquiggleTool` creates continuous freehand drawings
* `PolylineTool` creates connected line segments using multiple clicks

### Editing Tools

* `SelectMoveTool`
* `CopyTool`
* `CutTool`
* `PasteTool`
* `ClearCanvasTool`
* `UndoTool`

### Color and Style Tools

* `PaintBucketTool`
* `EyeDropperTool`
* `TextTool`

## Keyboard Shortcuts

### Tool Shortcuts

| Key | Tool          |
| --- | ------------- |
| `C` | Circle        |
| `R` | Rectangle     |
| `S` | Square        |
| `T` | Triangle      |
| `O` | Oval          |
| `Q` | Squiggle      |
| `L` | Polyline      |
| `P` | Paint Bucket  |
| `I` | Eyedropper    |
| `M` | Select / Move |
| `X` | Text          |

### Editing Shortcuts

| Shortcut   | Action       |
| ---------- | ------------ |
| `Ctrl + C` | Copy         |
| `Ctrl + X` | Cut          |
| `Ctrl + V` | Paste        |
| `Ctrl + Z` | Undo         |
| `Ctrl + Y` | Redo         |
| `Delete`   | Clear canvas |

## Technologies

* Java
* JavaFX
* Object-Oriented Programming
* MVC Architecture
* Observer Pattern
* Command Pattern
* Factory Pattern

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

## Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/AshirAhmed1/Microsoft-Paint-Application.git
```

### 2. Open the project

Open the repository in a Java IDE with JavaFX support, such as:

* IntelliJ IDEA
* Eclipse

### 3. Configure JavaFX

Make sure JavaFX is installed and properly configured in the project's runtime settings.

### 4. Run the application

Run:

```text
src/main/java/ca/utoronto/utm/assignment2/paint/Paint.java
```

or the corresponding package:

```text
ca.utoronto.utm.assignment2.paint.Paint
```

## Contributors

* Ashir
* Alex
* Ahmed

## About

This project was developed as part of a university software design course and demonstrates practical use of object-oriented programming, GUI development, event-driven programming, application architecture, and software design patterns in a larger Java application.
