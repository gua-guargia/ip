import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;
    public static final String lineSplit = "    ____________________________________________________________";

    public static void main(String[] args) {
        boolean canExit = false;
        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];
        int taskAmount = 0;

        //the logo and greet
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        //main chat box
        while(!canExit) {
            line = in.nextLine();
            if(line.equals("list")) {
                listTasks(taskAmount, tasks);
            }
            else if(line.equals("bye")) {
                canExit = true;
            }
            else if(line.contains("done")) {
                markAsDone(line, tasks, taskAmount);
            }
            else {
                if(line.contains("todo")) {
                    tasks[taskAmount] = new Todo(line);
                }
                else if(line.contains("deadline")) {
                    tasks[taskAmount] = new Deadline(line);
                }
                else if(line.contains("event")) {
                    tasks[taskAmount] = new Event(line);
                }
                else {
                    tasks[taskAmount] = new Task(line);
                }
                tasks[taskAmount].doneAddTask();
                taskAmount++;
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

    public static void listTasks(int taskAmount, Task[] tasks) {
        String isDone;
        if (taskAmount == 0) {
            System.out.println(lineSplit);
            System.out.println("    There is no list yet. Let's create some todo list!");
            System.out.println(lineSplit);
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

    public static void markAsDone(String line, Task[] tasks, int taskAmount) {
        String[] words = line.split(" ");
        int taskIndex = (Integer.parseInt(words[1])) - 1;
        if(taskIndex < 0 || taskIndex > taskAmount) {
            System.out.println(lineSplit);
            System.out.println("    Task index cannot be identified, please enter again");
            System.out.println(lineSplit);
            return;
        }
        tasks[taskIndex].markAsDone();
    }
}
