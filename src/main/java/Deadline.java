public class Deadline extends Task {
    public static final String DEADLINE = "D";
    protected String by;


    public Deadline(String description) throws DukeException {
        super(description);
        setBy(description);
    }

    public void setBy(String description) {
        int index = description.indexOf("/by");
        //System.out.println(description);
        index += 4;
        String by = description.substring(index);
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
    public void setDescription(String description){
        String frontFilter = description.replace("deadline ", "");
        int endIndex = frontFilter.indexOf("/by");
        String filter = frontFilter.substring(0, endIndex);
        this.description = filter;
    }

    @Override
    public String getTask() {
        String task = super.getTask() + getByPrint();
        return task;
    }
}
