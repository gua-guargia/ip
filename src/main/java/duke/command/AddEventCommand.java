package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

import java.io.IOException;

public class AddEventCommand extends Command{
    public AddEventCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(new Event(command));
        } catch (DukeException e) {
            e.printError();
        } catch (StringIndexOutOfBoundsException e) {
            ui.showLine();
            System.out.println("    ☹ OOPS!!! It is not a proper event format, please enter again");
            ui.showLine();
        } catch (IOException e) {
            ui.showLine();
            System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the /data/duke.txt");
            ui.showLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
