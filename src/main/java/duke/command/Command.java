package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command handler.
 */
public abstract class Command {
    String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
