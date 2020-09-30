package duke.main;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a user interface to read user's command and communicate the computed
 * outputs with the user. A <code>Ui</code> object corresponds to user interface.
 */
public class Ui {
    public static final String lineSplit = "    ____________________________________________________________";
    public static final String  logo = " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Prints leave message.
     */
    public void showExit() {
        showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints message to inform the user that the program will create an new file to store data.
     */
    public void showFileNoteFoundError() {
        System.out.println("    You're still a new user! I have created a new duke.txt for you!");
    }

    /**
     * Prints line for splitting contents.
     */
    public void showLine() {
        System.out.println(lineSplit);
    }

    /**
     * Read the command entered by the user.
     * Returns the command entered by the user.
     *
     * @return String of command entered by the user.
     */
    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    /**
     * Prints message to inform the user that a task has been marked as done.
     *
     * @param taskList Task array list which stores all the tasks entered by user.
     * @param index Index of the task that has been marked as done.
     */
    public void markAsDone(TaskList taskList, int index) {
        String task = taskList.getTasks().get(index).getTask();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    /**
     * Prints message to inform the user that a new task has been added.
     *
     * @param taskList Task array list which stores all the tasks entered by user.
     */
    public void doneAddTask(TaskList taskList) {
        int taskAmount = taskList.getTaskAmount();
        String task = taskList.getTasks().get(taskAmount - 1).getTask();
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskAmount +" tasks in the list.");
    }

    /**
     * Prints a line of message to inform users that a filtered list of tasks will be printed.
     */
    public void findTask() {
        System.out.println("    Here are the matching tasks in your list:");
    }

    /**
     * Prints message to alert user that the command is invalid and the program Duke cannot understand it.
     */
    public void randomCommand() {
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
