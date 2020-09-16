package duke.datatypes;

public class Deadline extends Task {

    private final String DEADLINE_SIGN = "[D]";
    protected String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        setDeadlineBy(deadlineBy);
    }

    public void setDeadlineBy(String deadlineBy) {
        String by = deadlineBy.replace("by ","");
        this.deadlineBy = by;
    }

    public String getDeadlineBy() {
        return deadlineBy;
    }

    @Override
    public String getTaskSign() {
        return DEADLINE_SIGN;
    }

    @Override
    public String getTime() {
        return getDeadlineBy();
    }

    @Override
    public String toString() {
        return DEADLINE_SIGN + super.toString() + " (by: " + getDeadlineBy() + ")";
    }
}
