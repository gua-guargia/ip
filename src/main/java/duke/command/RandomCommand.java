package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class RandomCommand extends Command {
    public RandomCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
