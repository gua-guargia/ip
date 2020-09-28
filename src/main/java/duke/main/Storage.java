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

public class Storage {
    protected String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

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
