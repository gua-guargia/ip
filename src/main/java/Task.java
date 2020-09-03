public class Task {
    /** Number of connections to this database */
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static int totalTasks = 0;
    public static final String NO_TYPE = "NT";
    public static final String lineSplit = "    ____________________________________________________________";


    public Task(String description) {
        setDescription(description);
        setType();
        this.isDone = false;
        totalTasks++;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType() {
        this.type = NO_TYPE;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println(lineSplit);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + getTask());
        System.out.println(lineSplit);
    }

    public void doneAddTask() {
        System.out.println(lineSplit);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + getTask());
        System.out.println("    Now you have " + totalTasks +" tasks in the list.");
        System.out.println(lineSplit);
    }

    public Task() {
        //set empty task
        this("");
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
}
