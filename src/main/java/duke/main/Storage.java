package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage which stores all the data of the tasks in a Duke program. A <code>Storage</code> object
 * corresponds to a storage manager of a file in a given file path e.g., <code>"data/duke.txt"</code>
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Creates new TXT file under the pre-defined file path to store data.
     */
    public void create() {
        File f = new File(filePath);
        boolean success = f.getParentFile().mkdir();
        if(success) {
            System.out.println("    Successfully created new directory");
        }
        else {
            System.out.println("    Failed to create new directory");
        }
    }

    /**
     * Loads tasks from the file in the given file path.
     * Returns a task array list which contains all the tasks added from the file.
     *
     * @return Task array list which contains all the tasks added from the file.
     * @throws DukeException  If the task description from the file is incomplete.
     * @throws IOException  If there is a failure during reading, writing and searching file or directory operations.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(f);
        int taskAmount = 0;
        while (s.hasNext()) {

            String line = s.nextLine();
            String[] words = line.split(" \\| ");

            //create todo
            switch (words[0]) {
            case "T":
                tasks.add(new Todo("todo " + words[2]));
                if (words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;
                break;
            //create deadline
            case "D":
                tasks.add(new Deadline("deadline " + words[2] + " /by " + words[3]));
                if (words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;
                break;
            //create event
            case "E":
                tasks.add(new Event("event " + words[2] + " /at " + words[3]));
                if (words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;

                break;
            default:
                System.out.println("    You have the following items in your list:");
                break;
            }
        }
        return tasks;

    }

    /**
     * Writes and saves all the information in taskList to the file in the pre-defined file path.
     *
     * @param taskList Task array list which contains all the information of the tasks that user have entered.
     * @param taskAmount Amount of tasks that stored in the taskList.
     * @throws IOException  If there is a failure during writing and searching file or directory operations.
     */
    public void writeFile(ArrayList<Task> taskList, int taskAmount) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();

        for(int i=0; i<taskAmount; i++) {
            taskList.get(i).writeFile();
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
