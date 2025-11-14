package ca.utoronto.utm.assignment2.paint.command;

public interface Command {
    void execute();
    void undo();
}
