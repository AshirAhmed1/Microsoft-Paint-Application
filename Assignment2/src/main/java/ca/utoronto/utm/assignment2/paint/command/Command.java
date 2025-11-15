package ca.utoronto.utm.assignment2.paint.command;

/**
 * Represents a generic command in the paint application following the
 * Command design pattern. Each command must define how it is executed
 * and how it can be undone.
 *
 * @author Ashir / Alex / Abdullah / Ahmed / Arnold
 */
public interface Command {

    /**
     * Execute the command, performing the associated action.
     */
    void execute();

    /**
     * Undo the effects of this command, restoring the previous state.
     */
    void undo();
}
