package duke.task;

import duke.exception.DukeException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task of deadline type in task list. A <code>Deadline</code> object corresponds to a deadline
 * with deadline description, complete status and deadline e.g., <code>"deadline homework /by Friday"</code>
 */
public class Deadline extends Task {
    public static final String DEADLINE = "D";
    protected String by;


    public Deadline(String description) throws DukeException, IOException  {
        super(description);
        setBy(description);
    }

    /**
     * Sets the deadline for new 'deadline' task.
     *
     * @param description Command entered by user.
     * @throws DukeException  If command doesn't contain deadline.
     */
    public void setBy(String description) throws DukeException{
        int index = description.indexOf("/by");
        index += 4;
        String by = description.substring(index);
        if(by.isEmpty()) {
            throw new DukeException("emptyBy");
        }
        this.by = by;
    }

    /**
     * Gets the deadline for new 'deadline' task.
     *
     * @return Task's deadline.
     */
    public String getByPrint() {
        String byPrint = "(by: " + by + ")";
        return byPrint;
    }

    /**
     * Sets type of task to deadline.
     */
    @Override
    public void setType() {
        this.type = DEADLINE;
    }

    /**
     * Sets the task description for new 'deadline' task.
     *
     * @param description Command entered by user.
     * @throws DukeException  If command doesn't contain description of deadline.
     */
    @Override
    public void setDescription(String description) throws DukeException{
        String frontFilter = description.replace("deadline ", "");
        int endIndex = frontFilter.indexOf(" /by");
        String filter = frontFilter.substring(0, endIndex);
        if (filter.isEmpty()) {
            throw new DukeException("description of a deadline");
        }
        this.description = filter;
    }

    /**
     * Gets data stored in a task of type deadline.
     *
     * @return Deadline's complete status, type and date.
     */
    @Override
    public String getTask() {
        String task = super.getTask() + getByPrint();
        return task;
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
        textToAdd = "D" + " | " + isDone + " | " + this.description + " | " + this.by;
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator()+ textToAdd);
        fw.close();
    }
}
