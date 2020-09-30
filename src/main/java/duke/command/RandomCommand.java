package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a random command handler.
 */
public class RandomCommand extends Command {
    public RandomCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.randomCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
