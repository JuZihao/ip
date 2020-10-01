package duke.datatypes;

import java.time.LocalDate;

/**
 * Represents a to do in the task list.
 * Guarantees: description is present and not null.
 */
public class Todo extends Task {

    public static final String TODO_SIGN = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskSign() {
        return TODO_SIGN;
    }

    @Override
    public LocalDate getTime() {
        return null;
    }

    @Override
    public String toString() {
        return TODO_SIGN + super.toString();
    }
}
