package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a task list manager. A <code>TaskList</code> object corresponds to a task list manger
 * which allows users to add, delete, modify and check the tasks stored in Task array list
 * e.g., <code>ArrayList<Task></code>
 */
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

    /**
     * Marks task as done by index entered by user.
     * Returns task index that has been marked as done.
     *
     * @param command Command entered by user.
     * @return Index of the task that has been marked as done.
     * @throws DukeException  If command doesn't contains the index to be mark as done.
     */
    public int markAsDone(String command) throws DukeException {
        String[] words = command.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        tasks.get(taskIndex).markAsDone();
        return taskIndex;
    }

    /**
     * Deletes task by index entered by user.
     * Returns task index that has been deleted.
     *
     * @param command Command entered by user.
     * @return Index of the task that has been deleted.
     * @throws DukeException  If command doesn't contains the index to be deleted.
     */
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

    /**
     * Adds new task into the tasks list.
     * Returns task index that has been marked as done.
     *
     * @param task New task to be added into the tasks array list.
     * @throws IOException  If there is a failure during reading, writing and searching file or directory operations.
     */
    public void add(Task task) throws IOException {
        tasks.add(task);
        tasks.get(taskAmount).doneAddTask();
        tasks.get(taskAmount).writeFile();
        taskAmount++;
    }

    /**
     * Deletes task by index entered by user.
     *
     * @param command Command entered by user.
     * @throws DukeException  If command doesn't contains any words for filter.
     */
    public void findTasks(String command) throws DukeException {
        String[] words = command.split(" ");
        ArrayList<Task> taskFilter = new ArrayList<>();
        if(words.length == 1) {
            throw new DukeException("emptyFind");
        }

        for(Task task : tasks) {
            for(int j = 1; j < words.length; j++) {
                if(task.getDescription().contains(words[j])) {
                    taskFilter.add(task);
                    break;
                }
            }
        }
        printTaskFilter(taskFilter);
    }

    /**
     * Prints the task list.
     *
     * @throws DukeException  If the task list doesn't contains any task.
     */
    public void listTasks() throws DukeException {
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

    /**
     * Print filtered task array list based on the filter words.
     *
     * @param taskFilter Task array list to store the tasks that contains the filter words in their task description.
     * @throws DukeException  If the taskFilter is empty which means no task description matches the filter words.
     */
    public void printTaskFilter(ArrayList<Task> taskFilter) throws DukeException {
        int size = taskFilter.size();
        if (size == 0) {
            throw new DukeException("emptyFilter");
        }
        else {
            Ui ui = new Ui();
            ui.findTask();
            for(int i = 0; i< size; i++) {
                System.out.print("    " + (i+1) +".");
                taskFilter.get(i).printTask();
            }
        }
    }
}
