package duke.task;

import duke.exception.DukeException;
import java.io.IOException;

/**
 * Represents a task in task list. A <code>Task</code> object corresponds to a task with task description,
 * complete status and time limit e.g., <code>"event general conference /at 25th Sep"</code>
 */
public abstract class Task {
    /** Number of connections to this database */
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static int totalTasks = 0;
    public static final String NO_TYPE = "NT";
    String filePath = "data/duke.txt";

    public Task(String description) throws DukeException, IOException {
        setDescription(description);
        setType();
        this.isDone = false;
        totalTasks++;
    }

    public abstract void setDescription(String description) throws DukeException;

    public abstract void setType();

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void doneAddTask() {
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + getTask());
        System.out.println("    Now you have " + totalTasks +" tasks in the list.");
    }


    public static int getTotalTasks() {
        return totalTasks;
    }

    public String getTypeIcon() {
        String typeIcon = "[" + type + "]";
        return typeIcon;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void printTask() {
        System.out.println(getTask());
    }

    public String getTask() {
        String task = getTypeIcon() + getStatusIcon() + " " + getDescription();
        return task;
    }

    public void removeTask() {
        totalTasks--;
    }

    public abstract void writeFile() throws IOException;
}
