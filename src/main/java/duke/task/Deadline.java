package duke.task;

import duke.exception.DukeException;
import duke.task.Task;
import duke.main.Duke;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;

public class Deadline extends Task {
    public static final String DEADLINE = "D";
    protected String by;


    public Deadline(String description) throws DukeException, IOException  {
        super(description);
        setBy(description);
    }

    public void setBy(String description) throws DukeException{
        int index = description.indexOf("/by");
        //System.out.println(description);
        index += 4;
        String by = description.substring(index);
        if(by.isEmpty()) {
            throw new DukeException("emptyBy");
        }
        this.by = by;
    }

    public String getByPrint() {
        String byPrint = "(by: " + by + ")";
        return byPrint;
    }

    @Override
    public void setType() {
        this.type = DEADLINE;
    }

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

    @Override
    public String getTask() {
        String task = super.getTask() + getByPrint();
        return task;
    }

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
