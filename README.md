# Microsoft Paint Application

## Overview

Microsoft Paint Application is a JavaFX-based drawing program built for a university software design assignment. The project recreates many core features of a basic paint application, allowing users to draw shapes, change colors, adjust line thickness, move objects, add text, and use editing actions such as copy, cut, paste, undo, redo, and clear.

The application follows an object-oriented design and uses several design patterns, including MVC, Observer, Factory, and Command, to keep the code organized, scalable, and easier to maintain.

## Features

### Drawing Tools

Users can draw multiple types of shapes and freehand objects on the canvas:

- Circle
- Rectangle
- Square
- Triangle
- Oval
- Squiggle/freehand drawing
- Polyline
- Text

Each shape supports drawing with the currently selected color, line thickness, and fill style.

### Editing Tools

The application includes several editing tools similar to a standard paint program:

- Select and move shapes
- Copy selected shapes
- Cut selected shapes
- Paste copied or cut shapes
- Clear the canvas
- Undo previous actions
- Redo undone actions

### Styling Options

Users can customize how shapes appear:

- Color picker for custom colors
- Preset color buttons
- Fill or outline mode
- Adjustable line thickness
- Paint bucket tool for recoloring shapes
- Eyedropper tool for selecting a shape’s existing color

## Keyboard Shortcuts

### Tool Shortcuts

| Key | Tool |
|---|---|
| C | Circle |
| R | Rectangle |
| S | Square |
| T | Triangle |
| O | Oval |
| Q | Squiggle |
| L | Polyline |
| P | Paint Bucket |
| I | Eyedropper |
| M | Select/Move |
| X | Text |

### Edit Shortcuts

| Shortcut | Action |
|---|---|
| Ctrl + C | Copy |
| Ctrl + X | Cut |
| Ctrl + V | Paste |
| Ctrl + Z | Undo |
| Ctrl + Y | Redo |
| Delete | Clear canvas |

## Technologies Used

- Java
- JavaFX
- Object-Oriented Programming
- MVC architecture
- Observer pattern
- Command pattern
- Factory pattern

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-username/Microsoft-Paint-Application.git
```

2. Open the project in an IDE that supports JavaFX, such as IntelliJ IDEA or Eclipse.

3. Make sure JavaFX is properly configured in your project settings.

4. Run the `Paint.java` file to start the application.

The main file is located at:

```text
src/main/java/ca/utoronto/utm/assignment2/paint/Paint.java
```

or by package path:

```text
ca.utoronto.utm.assignment2.paint.Paint
```

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
│   ├── Point.java
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
│   ├── ShapeChooserPanel.java
│   ├── ColorChooserPanel.java
│   ├── ThicknessChooserPanel.java
│   ├── FillStyleChooserPanel.java
│   ├── EditToolPanel.java
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

## How It Works

The application is built around a central `PaintModel`, which stores all drawable objects, current drawing settings, selected shapes, clipboard contents, and undo/redo stacks.

The `PaintPanel` observes the model and redraws the canvas whenever the model changes. User input is handled through different tool classes, such as `CircleTool`, `RectangleTool`, `SquiggleTool`, and `SelectMoveTool`.

When a user performs an action, the selected tool updates the model. For actions that should support undo and redo, the application uses command objects such as `AddShapeCommand`, `MoveCommand`, `CutCommand`, and `RecolorCommand`.

## Design Patterns Used

### MVC Architecture

The project separates the application into model, view, and controller-style responsibilities.

- `PaintModel` stores the application state.
- `View` builds the main interface and connects UI controls to model actions.
- `PaintPanel` displays the canvas and delegates mouse input to the active tool.

### Observer Pattern

`PaintModel` extends `Observable`, and `PaintPanel` observes it. Whenever the model changes, the panel is notified and the canvas is redrawn.

This keeps the visual display synchronized with the application state.

### Command Pattern

The command pattern is used for actions that need undo and redo functionality.

Examples include:

- Adding a shape
- Moving a shape
- Cutting a shape
- Pasting a shape
- Clearing the canvas
- Recoloring a shape
- Changing fill style
- Adding text

Each command implements the `Command` interface and provides both `execute()` and `undo()` methods.

### Factory Pattern

`ToolFactory` creates the correct tool object based on the selected `ToolType`. This keeps tool creation centralized and makes it easier to add new tools later.

## Main Classes

### `Paint`

The main entry point of the paint application. It launches JavaFX, creates the model, and initializes the main view.

### `View`

Builds the full application interface, including the canvas, tool panels, color panel, edit panel, menu bar, and keyboard shortcuts.

### `PaintModel`

Stores the core application data, including:

- Drawn shapes
- Preview shape
- Current color
- Current line thickness
- Fill mode
- Clipboard
- Selected shape
- Undo stack
- Redo stack

### `PaintPanel`

The canvas where all drawings appear. It listens for mouse events and forwards them to the currently selected tool.

### `Drawable`

An interface implemented by all drawable objects. It requires shapes to support drawing, color changes, thickness changes, movement, and hit detection.

### `AbstractShapeDrawable`

A base class for drawable shapes. It stores shared properties such as color, line thickness, and fill state.

## Supported Shapes

### Circle

Drawn from a center point and radius. Supports filled and outlined modes, hit detection, and movement.

### Rectangle

Drawn using a top-left point, width, and height. Supports fill mode, outline mode, selection, and translation.

### Square

Extends `Rectangle` while keeping width and height equal.

### Triangle

Drawn from a bottom-left point, base, side lengths, and calculated height.

### Oval

Drawn from a top-left point, width, and height. Uses oval-based hit detection.

### Squiggle

A freehand drawing made from a list of connected points.

### Polyline

A multi-segment line created from multiple user clicks. The polyline is finalized with a right-click.

### Text

Allows the user to click on the canvas and enter text through a dialog box.

## Tools

The application uses a separate class for each tool. Each tool handles mouse events differently depending on its purpose.

### Shape Tools

Shape tools use click-and-drag behavior. They show a preview while dragging and commit the final shape when the mouse is released.

Examples:

- `CircleTool`
- `RectangleTool`
- `SquareTool`
- `TriangleTool`
- `OvalTool`

### Freehand Tools

Freehand tools build drawings from many points.

- `SquiggleTool` creates a continuous freehand line.
- `PolylineTool` creates connected line segments from multiple clicks.

### Editing Tools

Editing tools interact with existing objects on the canvas.

- `SelectMoveTool` selects and moves shapes.
- `CopyTool` copies the selected shape.
- `CutTool` removes a shape and stores it in the clipboard.
- `PasteTool` places the clipboard shape onto the canvas.
- `ClearCanvasTool` clears all shapes from the canvas.
- `UndoTool` undoes the most recent command.

### Color and Style Tools

- `PaintBucketTool` recolors a clicked shape.
- `EyeDropperTool` selects the color of a clicked shape.
- `TextTool` adds text at the clicked location.

## Undo and Redo System

Undo and redo are handled using two stacks in `PaintModel`.

- The undo stack stores executed commands.
- The redo stack stores commands that were undone.

When a command is executed, it is pushed onto the undo stack and the redo stack is cleared. When undo is triggered, the command is removed from the undo stack, reversed, and pushed onto the redo stack. Redo executes the command again and returns it to the undo stack.

This system makes the application more reliable and keeps editing actions consistent.

## Clipboard System

The clipboard stores a copied version of a selected drawable object. The model includes helper methods to create copies of supported shape types, including:

- Circle
- Rectangle
- Square
- Triangle
- Oval
- Text
- Polyline
- Squiggle

When pasted, the copied shape is translated to the requested location and added through a `PasteCommand`, which means paste actions can also be undone.

## Possible Future Improvements

- Add file saving and loading for drawings
- Add image export support
- Add resize handles for selected shapes
- Improve triangle hit detection using exact geometric bounds
- Add shape layering controls such as bring forward and send backward
- Add redo as a canvas tool in addition to the edit panel shortcut
- Add more advanced text editing options
- Add eraser support
- Add custom canvas sizes

## Contributors

- Ashir
- Alex
- Abdullah
- Ahmed
- Arnold

## Notes

This project was created as part of a JavaFX paint application assignment. It demonstrates object-oriented programming, GUI development, event handling, and the use of software design patterns in a larger application.
