package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Todo;

import java.io.IOException;

/**
 * Represents a add todo command handler.
 */
public class AddTodoCommand extends Command{
    public AddTodoCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(new Todo(command));
        } catch (DukeException e) {
            e.printError();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! It is not a proper todo format, please enter again");
        } catch (IOException e) {
            System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the data/duke.txt");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
