public class Todo extends Task {
    public static final String TODO = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public void setType() {
        this.type = TODO;
    }

    @Override
    public void setDescription(String description) {
        String filter = description.replace("todo ", "");
        this.description = filter;
    }

}
