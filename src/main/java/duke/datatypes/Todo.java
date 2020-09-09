package duke.datatypes;

public class Todo extends Task {

    private final String TODO_SIGN = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_SIGN + super.toString();
    }
}
