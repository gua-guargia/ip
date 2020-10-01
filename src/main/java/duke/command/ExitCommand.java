package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Represents a exit command handler.
 */
public class ExitCommand extends Command{
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.writeFile(taskList.getTasks(), taskList.getTaskAmount());
        } catch (IOException e) {
            System.out.println("    Sorry, I cannot save the file");
        } finally {
            System.out.println("    Your file has been saved in " + storage.getFilePath());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
