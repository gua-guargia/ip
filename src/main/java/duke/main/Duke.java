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
import duke.command.Command;
import java.util.ArrayList;



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
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
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



/*
    public static void main(String[] args) throws DukeException, IOException {
        boolean canExit = false;
        String line;
        Scanner in = new Scanner(System.in);
        //Task[] tasks = new Task[MAX_TASK];
        ArrayList<Task> tasks = new ArrayList<>();
        int taskAmount = 0;
        String filePath = "data/duke.txt";


        try {
            taskAmount = printFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("    You're still a new user! I have created a new duke.txt for you!");
            File f = new File(filePath);
            boolean success = f.getParentFile().mkdir();
            if(success) {
                System.out.println("    Successfully created new directory");
            }
            else {
                System.out.println("    Failed to create new directory");
            }
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
            else if(line.toLowerCase().contains("delete")) {
                try {
                    int taskIndex = deleteTask(line, tasks, taskAmount);
                    tasks.get(taskIndex).removeTask();
                    tasks.remove(taskIndex);
                    taskAmount--;
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
                        tasks.add(new Todo(line));
                        tasks.get(taskAmount).doneAddTask();
                        tasks.get(taskAmount).writeFile();
                        taskAmount++;
                    } catch (DukeException e) {
                        e.printError();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! It is not a proper todo format, please enter again");
                        System.out.println(lineSplit);
                    } catch (IOException e) {
                        System.out.println(lineSplit);
                        System.out.println("    ☹ OOPS!!! Something wrong with the file, please check the data/duke.txt");
                        System.out.println(lineSplit);
                    }
                }
                else if(line.toLowerCase().contains("deadline")) {
                    try {
                        tasks.add(new Deadline(line));
                        tasks.get(taskAmount).doneAddTask();
                        tasks.get(taskAmount).writeFile();
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
                        tasks.add(new Event(line));
                        tasks.get(taskAmount).doneAddTask();
                        tasks.get(taskAmount).writeFile();
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


    public static void listTasks(int taskAmount, ArrayList<Task> tasks) throws DukeException {
        String isDone;
        if (taskAmount == 0) {
            throw new DukeException("emptyList");
        }
        else {
            System.out.println(lineSplit);
            for(int i = 0; i< taskAmount; i++) {
                System.out.print("    " + (i+1) +".");
                tasks.get(i).printTask();
                //tasks[i].printTask();
            }
            System.out.println(lineSplit);
        }
    }

    public static void markAsDone(String line, ArrayList<Task> tasks, int taskAmount) throws DukeException {
        String[] words = line.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        tasks.get(taskIndex).markAsDone();
        //tasks[taskIndex].markAsDone();
    }

    public static int deleteTask(String line, ArrayList<Task> tasks, int taskAmount) throws DukeException {
        String[] words = line.split(" ");
        if(words.length == 1) {
            throw new DukeException("emptyDone");
        }
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        System.out.println(lineSplit);
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("    " + tasks.get(taskIndex).getTask());
        System.out.println("    Now you have " + (taskAmount - 1) + " tasks in the list.");
        System.out.println(lineSplit);
        //tasks[taskIndex].markAsDone();
        return taskIndex;
    }

    public static int printFileContents(String filePath, ArrayList<Task> tasks) throws IOException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int taskAmount = 0;
        while (s.hasNext()) {
            //System.out.println("    " + s.nextLine());
            String line = s.nextLine();
            String[] words = line.split(" \\| ");

            //create todo
            if(words[0].equals("T")) {
                tasks.add(new Todo("todo " + words[2]));
                if(words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;
            }
            //create deadline
            else if(words[0].equals("D")) {
                tasks.add(new Deadline("deadline " + words[2] + " /by " + words[3]));
                if(words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;
            }
            //create event
            else if(words[0].equals("E")) {
                tasks.add(new Event("event " + words[2] + " /at " + words[3]));
                if(words[1].equals("1")) {
                    tasks.get(taskAmount).markAsDone();
                }
                taskAmount++;

            }
            else {
                System.out.println("    You have the following items in your list:");
            }
        }
        return taskAmount;
    }

    public static void writeFile(String filePath, ArrayList<Task> tasks, int taskAmount) throws IOException {
        //clear file
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();

        for(int i=0; i<taskAmount; i++) {
            tasks.get(i).writeFile();
        }
    }
    */
}
