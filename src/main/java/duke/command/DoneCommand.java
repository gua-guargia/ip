package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int index = taskList.markAsDone(command);
            ui.markAsDone(taskList, index);
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
