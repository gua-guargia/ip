package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand extends Command {


    public FindCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.findTasks(command);
        } catch(DukeException e) {
            e.printError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
