package duke.task;

import duke.exception.DukeException;
import duke.task.Task;
import duke.main.Duke;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;

public class Event extends Task {
    public static final String EVENT = "E";
    protected String at;

    public Event(String description)  throws DukeException, IOException {
        super(description);
        setBy(description);
    }

    public void setBy(String description) throws DukeException{
        int index = description.indexOf("/at");
        //System.out.println(description);
        index += 4;
        String at = description.substring(index);
        if(at.isEmpty()) {
            throw new DukeException("emptyAt");
        }
        this.at = at;
    }

    public String getAtPrint() {
        String atPrint = "(at: " + at + ")";
        return atPrint;
    }

    @Override
    public void setType() {
        this.type = EVENT;
    }

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

    @Override
    public String getTask() {
        String task = super.getTask() + getAtPrint();
        return task;
    }

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
