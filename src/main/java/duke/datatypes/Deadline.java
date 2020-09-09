package duke.datatypes;

public class Deadline extends Task {

    private final String DEADLINE_SIGN = "[D]";
    protected String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        setDeadlineBy(deadlineBy);
    }

    public void setDeadlineBy(String deadlineBy) {
        String by = deadlineBy.replace("by","by:");
        this.deadlineBy = by;
    }

    public String getDeadlineBy() {
        return deadlineBy;
    }

    @Override
    public String toString() {
        return DEADLINE_SIGN + super.toString() + "(" + getDeadlineBy() + ")";
    }
}
