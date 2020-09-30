package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Represents a delete command handler.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int taskIndex = taskList.deleteTask(command);
            storage.writeFile(taskList.getTasks(), taskList.getTaskAmount());
        } catch(DukeException e) {
            e.printError();
        } catch (IndexOutOfBoundsException e) {
            ui.showLine();
            System.out.println("    it is not a task number in your list, please enter again");
            ui.showLine();
        } catch (IOException e) {
            ui.showLine();
            System.out.println("    Sorry, I cannot save the file");
            ui.showLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
