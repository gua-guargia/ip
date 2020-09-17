package duke.main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;



public class Duke {
    //constants
    public static final int MAX_TASK = 100;
    public static final String lineSplit = "    ____________________________________________________________";

    public static void main(String[] args) throws DukeException, IOException {
        boolean canExit = false;
        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];
        int taskAmount = 0;
        String filePath = "data/duke.txt";

        //the logo and greet
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        try {
            taskAmount = printFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        //main chat box
        while(!canExit) {
            line = in.nextLine();
            if(line.toLowerCase().equals("list")) {
                try {
                    listTasks(taskAmount, tasks);
                } catch(DukeException e) {
                    e.printError();
                }
            }
            else if(line.toLowerCase().equals("bye")) {
                try {
                    writeFile(filePath, tasks, taskAmount);
                } catch (IOException e) {
                    System.out.println(lineSplit);
                    System.out.println("    Sorry, I cannot save the file");
                    System.out.println(lineSplit);
                }
                canExit = true;
            }
            else if(line.toLowerCase().contains("done")) {
                try {
                    markAsDone(line, tasks, taskAmount);
                    writeFile(filePath, tasks, taskAmount);
                } catch(DukeException e) {
                    e.printError();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(lineSplit);
                    System.out.println("    it is not a task number in your list, please enter again");
                    System.out.println(lineSplit);
                } catch (IOException e) {
                    System.out.println(lineSplit);
                    System.out.println("    Sorry, I cannot save the file");
                    System.out.println(lineSplit);
                }
            }
            else {
                if(line.toLowerCase().contains("todo")) {
                    try {
                        tasks[taskAmount] = new Todo(line);
                        tasks[taskAmount].doneAddTask();
                        tasks[taskAmount].writeFile();
                        taskAmount++;
                    } catch (DukeException e) {
                        e.printError();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! It is not a proper todo format, please enter again");
                        System.out.println(lineSplit);
                    } catch (IOException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the /data/duke.txt");
                        System.out.println(lineSplit);
                    }
                }
                else if(line.toLowerCase().contains("deadline")) {
                    try {
                        tasks[taskAmount] = new Deadline(line);
                        tasks[taskAmount].doneAddTask();
                        tasks[taskAmount].writeFile();
                        taskAmount++;
                    } catch (DukeException e) {
                        e.printError();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! It is not a proper deadline format, please enter again");
                        System.out.println(lineSplit);
                    } catch (IOException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the /data/duke.txt");
                        System.out.println(lineSplit);
                    }
                }
                else if(line.toLowerCase().contains("event")) {
                    try {
                        tasks[taskAmount] = new Event(line);
                        tasks[taskAmount].doneAddTask();
                        tasks[taskAmount].writeFile();
                        taskAmount++;
                    } catch (DukeException e) {
                        e.printError();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! It is not a proper event format, please enter again");
                        System.out.println(lineSplit);
                    } catch (IOException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the /data/duke.txt");
                        System.out.println(lineSplit);
                    }
                }
                else {
                    System.out.println(lineSplit);
                    System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(lineSplit);
                }
            }
        }
        exit();
    }

    public static void greet() {
        System.out.println(lineSplit);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(lineSplit);
    }

    public static void echo(String message) {
        System.out.println(lineSplit);
        System.out.println("    " + message);
        System.out.println(lineSplit);
    }

    public static void exit() {
        System.out.println(lineSplit);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lineSplit);
    }

    public static void listTasks(int taskAmount, Task[] tasks) throws DukeException {
        String isDone;
        if (taskAmount == 0) {
            throw new DukeException("emptyList");
        }
        else {
            System.out.println(lineSplit);
            for(int i = 0; i< taskAmount; i++) {
                System.out.print("    " + (i+1) +".");
                tasks[i].printTask();
            }
            System.out.println(lineSplit);
        }
    }

    public static void markAsDone(String line, Task[] tasks, int taskAmount) throws DukeException {
        String[] words = line.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        tasks[taskIndex].markAsDone();
    }

    public static int printFileContents(String filePath, Task[] tasks) throws IOException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int taskAmount = 0;
        System.out.println("    You have the following items in your list:");
        while (s.hasNext()) {
            //System.out.println("    " + s.nextLine());
            String line = s.nextLine();
            String[] words = line.split(" \\| ");

            //create todo
            if(words[0].equals("T")) {
                tasks[taskAmount] = new Todo("todo " + words[2]);
                if(words[1].equals("1")) {
                    tasks[taskAmount].markAsDone();
                }
                taskAmount++;
            }
            //create deadline
            else if(words[0].equals("D")) {
                tasks[taskAmount] = new Deadline("deadline " + words[2] + " /by " + words[3]);
                if(words[1].equals("1")) {
                    tasks[taskAmount].markAsDone();
                }
                taskAmount++;
            }
            //create event
            else if(words[0].equals("E")) {
                tasks[taskAmount] = new Event("event " + words[2] + " /at " + words[3]);
                if(words[1].equals("1")) {
                    tasks[taskAmount].markAsDone();
                }
                taskAmount++;

            }
            else {
                System.out.println("File reading error");
            }
        }
        return taskAmount;
    }

    public static void writeFile(String filePath, Task[] tasks, int taskAmount) throws IOException {
        //clear file
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();

        for(int i=0; i<taskAmount; i++) {
            tasks[i].writeFile();
        }
    }
}