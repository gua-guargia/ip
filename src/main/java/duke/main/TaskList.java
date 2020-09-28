package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int taskAmount = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.taskAmount = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskAmount = 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskAmount() {
        return taskAmount;
    }

    public int markAsDone( String line) throws DukeException {
        String[] words = line.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        tasks.get(taskIndex).markAsDone();
        return taskIndex;
    }

    public int deleteTask(String command) throws DukeException {
        String[] words = command.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("    " + tasks.get(taskIndex).getTask());
        System.out.println("    Now you have " + (taskAmount - 1) + " tasks in the list.");
        tasks.get(taskIndex).removeTask();
        tasks.remove(taskIndex);
        taskAmount--;
        return taskIndex;
    }

    public void add(Task task) throws IOException {
        tasks.add(task);
        tasks.get(taskAmount).doneAddTask();
        tasks.get(taskAmount).writeFile();
        taskAmount++;
    }
}
