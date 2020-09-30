package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list command handler.
 */
public class ListCommand extends Command{
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.listTasks();
        } catch(DukeException e) {
            e.printError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
