package duke.main;


import java.io.FileNotFoundException;
import java.io.IOException;
import duke.exception.DukeException;
import duke.command.Command;


/**
 * Represents a task manager called the Duke. A <code>Duke</code> object corresponds to
 * a functional task manger which users can add, delete, modify and store tasks. The tasks information is stored in
 * a file under a file path e.g., <code>"data/duke.txt"</code>
 */
public class Duke {
    //constants
    //public static final int MAX_TASK = 100;
    public static final String lineSplit = "    ____________________________________________________________";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse("list");
            c.execute(taskList, ui, storage);
            ui.showLine();
        } catch (FileNotFoundException e) {
            ui.showFileNoteFoundError();
            storage.create();
            taskList = new TaskList();
        } catch (DukeException e) {
            e.printError();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
                ui.showLine();
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
