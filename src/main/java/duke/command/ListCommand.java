package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            listTasks(taskList.getTaskAmount(), taskList.getTasks());
        } catch(DukeException e) {
            e.printError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void listTasks(int taskAmount, ArrayList<Task> tasks) throws DukeException {
        if (taskAmount == 0) {
            throw new DukeException("emptyList");
        }
        else {
            for(int i = 0; i< taskAmount; i++) {
                System.out.print("    " + (i+1) +".");
                tasks.get(i).printTask();
            }
        }
    }
}
