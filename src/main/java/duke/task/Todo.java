package duke.task;

import duke.exception.DukeException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task of todo type in task list. A <code>Todo</code> object corresponds to a todo
 * with todo description, complete status e.g., <code>"todo read a book"</code>
 */
public class Todo extends Task{
    public static final String TODO = "T";

    public Todo(String description) throws DukeException, IOException {
        super(description);
    }

    /**
     * Sets type of task to Todo.
     */
    @Override
    public void setType() {
        this.type = TODO;
    }

    /**
     * Sets the todo description for new 'todo' task.
     *
     * @param description Command entered by user.
     * @throws DukeException  If command doesn't contain description of the new event.
     */
    @Override
    public void setDescription(String description) throws DukeException {
        String filter = description.replace("todo ", "");
        String errorChecker = description.replace("todo", "");
        if (filter.isEmpty() || errorChecker.isEmpty()) {
            throw new DukeException("description of a todo");
        }
        this.description = filter;
    }

    /**
     * Writes and stores new task into the file.
     *
     * @throws IOException  If there is a failure during reading, writing and searching file or directory operations.
     */
    @Override
    public void writeFile() throws IOException {
        String textToAdd;
        String isDone = (this.isDone)? "1" : "0";
        textToAdd = "T" + " | " + isDone + " | " + getDescription();
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator()+ textToAdd);
        fw.close();
    }

}
