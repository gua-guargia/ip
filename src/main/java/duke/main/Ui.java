package duke.main;

import java.util.Scanner;

public class Ui {
    public static final String lineSplit = "    ____________________________________________________________";
    public static final String  logo = " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println(lineSplit);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(lineSplit);
    }

    public void showExit() {
        System.out.println(lineSplit);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lineSplit);
    }

    public void showFileNoteFoundError() {
        System.out.println("    You're still a new user! I have created a new duke.txt for you!");
    }

    public void showLoadingError() {

    }

    public void showCreatingFileError() {

    }

    public void showCreatingFileSuccessful() {

    }

    public void showLine() {
        System.out.println(lineSplit);
    }

    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public void showError(String message) {

    }

    public void markAsDone(TaskList taskList, int index) {
        String task = taskList.getTasks().get(index).getTask();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void doneAddTask(TaskList taskList) {
        int taskAmount = taskList.getTaskAmount();
        String task = taskList.getTasks().get(taskAmount - 1).getTask();
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskAmount +" tasks in the list.");
    }
}
