package duke.task;
import duke.main.Duke;
import duke.exception.DukeException;
import duke.task.Task;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;

public class Todo extends Task{
    public static final String TODO = "T";

    public Todo(String description) throws DukeException, IOException {
        super(description);
    }

    @Override
    public void setType() {
        this.type = TODO;
    }

    @Override
    public void setDescription(String description) throws DukeException {
        String filter = description.replace("todo ", "");
        String errorChecker = description.replace("todo", "");
        if (filter.isEmpty() || errorChecker.isEmpty()) {
            throw new DukeException("description of a todo");
        }
        this.description = filter;
    }

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
