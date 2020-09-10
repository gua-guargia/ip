package duke.exception;
public class DukeException extends Exception{
    public static final String lineSplit = "    ____________________________________________________________";
    protected String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public void printError() {
        switch (message) {
        case "description of a todo":
        case "description of a event":
        case "description of a deadline":
            System.out.println(lineSplit);
            System.out.println("    ☹ OOPS!!! The " +message +" cannot be empty.");
            System.out.println(lineSplit);
            break;
        //case "randomMessage":
        //    System.out.println(lineSplit);
        //    System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        //    System.out.println(lineSplit);
        //    break;
        case "emptyList":
            System.out.println(lineSplit);
            System.out.println("    ☹ OOPS!!! The list is still empty.");
            System.out.println(lineSplit);
            break;
        case "emptyDone":
            System.out.println(lineSplit);
            System.out.println("    ☹ OOPS!!! I don't know which task you have done.");
            System.out.println(lineSplit);
            break;
        case "emptyBy":
            System.out.println(lineSplit);
            System.out.println("    ☹ OOPS!!! The by cannot be empty.");
            System.out.println(lineSplit);
            break;
        case "emptyAt":
            System.out.println(lineSplit);
            System.out.println("    ☹ OOPS!!! The at cannot be empty.");
            System.out.println(lineSplit);
            break;
        }
    }
}
