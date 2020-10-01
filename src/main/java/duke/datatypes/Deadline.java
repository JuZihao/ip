package duke.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline in the task list.
 * Guarantees: description and time are present and not null.
 */
public class Deadline extends Task {

    public static final String DEADLINE_SIGN = "[D]";
    protected LocalDate deadlineBy;

    public Deadline(String description, LocalDate deadlineBy) {
        super(description);
        setDeadlineBy(deadlineBy);
    }

    public void setDeadlineBy(LocalDate deadlineBy) {
        this.deadlineBy = deadlineBy;
    }

    public String getDeadlineBy() {
        return deadlineBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getTaskSign() {
        return DEADLINE_SIGN;
    }

    @Override
    public LocalDate getTime() {
        return deadlineBy;
    }

    @Override
    public String toString() {
        return DEADLINE_SIGN + super.toString() + " (by: " + getDeadlineBy() + ")";
    }
}
