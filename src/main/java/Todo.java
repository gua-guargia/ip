public class Todo extends Task{
    public static final String TODO = "T";

    public Todo(String description) throws DukeException{
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

}
