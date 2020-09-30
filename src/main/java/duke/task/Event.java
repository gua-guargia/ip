package duke.task;

import duke.exception.DukeException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task of event type in task list. A <code>Event</code> object corresponds to a event
 * with event description, complete status and schedule e.g., <code>"event general conference /at 25th Sep"</code>
 */
public class Event extends Task {
    public static final String EVENT = "E";
    protected String at;

    public Event(String description)  throws DukeException, IOException {
        super(description);
        setBy(description);
    }

    /**
     * Sets the date of new 'event' task.
     *
     * @param description Command entered by user.
     * @throws DukeException  If command doesn't contain a date.
     */
    public void setBy(String description) throws DukeException{
        int index = description.indexOf("/at");
        index += 4;
        String at = description.substring(index);
        if(at.isEmpty()) {
            throw new DukeException("emptyAt");
        }
        this.at = at;
    }

    /**
     * Gets the date of the event.
     *
     * @return Event's date.
     */
    public String getAtPrint() {
        String atPrint = "(at: " + at + ")";
        return atPrint;
    }

    /**
     * Sets type of task to event.
     */
    @Override
    public void setType() {
        this.type = EVENT;
    }

    /**
     * Sets the event description for new 'event' task.
     *
     * @param description Command entered by user.
     * @throws DukeException  If command doesn't contain description of the new event.
     */
    @Override
    public void setDescription(String description) throws DukeException{
        String frontFilter = description.replace("event ", "");
        int endIndex = frontFilter.indexOf(" /at");
        String filter = frontFilter.substring(0, endIndex);
        if(filter.isEmpty()) {
            throw new DukeException("description of a event");
        }
        this.description = filter;
    }

    /**
     * Gets data stored in a task of type event.
     *
     * @return Event's complete status, type and date.
     */
    @Override
    public String getTask() {
        String task = super.getTask() + getAtPrint();
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
        textToAdd = "E" + " | " + isDone + " | " + this.description + " | " + this.at;
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator() + textToAdd);
        fw.close();
    }

}
