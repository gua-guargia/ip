package duke.exception;

/**
 * Represents a exception handler unique to the Duke program. A <code>DukeException</code> object corresponds to
 * a unique exception while running the Duke program e.g., <code>"emptyBy"</code>
 */
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
            System.out.println("    ☹ OOPS!!! The " + message + " cannot be empty.");
            break;
        case "emptyList":
            System.out.println("    ☹ OOPS!!! The list is still empty.");
            break;
        case "emptyDone":
            System.out.println("    ☹ OOPS!!! I don't know which task you have done.");
            break;
        case "emptyBy":
            System.out.println("    ☹ OOPS!!! The by cannot be empty.");
            break;
        case "emptyAt":
            System.out.println("    ☹ OOPS!!! The at cannot be empty.");
            break;
        case "emptyFind":
            System.out.println("    ☹ OOPS!!! The filter word cannot be empty.");
            break;
        case "emptyFilter":
            System.out.println("    ☹ OOPS!!! I cannot find the words from current list.");
            break;
        }

    }
}
