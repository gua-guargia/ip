package duke.main;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.IOException;

/**
 * Represents a parser to interpret the command inputted by the user. A <code>Parser</code> object corresponds to
 * a parser that reads in command and interpret the meaning of the command.
 */
public class Parser {

    /**
     * Select the corresponding Command class through interpreting the command entered by user.
     * Returns the correct Command class.
     *
     * @param fullCommand Command entered by user.
     * @return Construct new Command that is corresponding to the command entered by user.
     */
    public static Command parse(String fullCommand) {
        if(fullCommand.toLowerCase().equals("list")) {
            return new ListCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().equals("bye")) {
            return new ExitCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("done")) {
            return new DoneCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("delete")) {
            return new DeleteCommand(fullCommand);
        }
        else if(fullCommand.toLowerCase().contains("find")) {
            return new FindCommand(fullCommand);
        }
        else {
            if(fullCommand.toLowerCase().contains("todo")) {
                return new AddTodoCommand(fullCommand);
            }
            else if(fullCommand.toLowerCase().contains("deadline")) {
                return new AddDeadlineCommand(fullCommand);
            }
            else if(fullCommand.toLowerCase().contains("event")) {
                return new AddEventCommand(fullCommand);
            }
            else {
                return new RandomCommand(fullCommand);
            }
        }
    }
}
