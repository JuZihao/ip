package duke.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the task list.
 * Guarantees: description and time are present and not null.
 */
public class Event extends Task {

    public static final String EVENT_SIGN = "[E]";
    protected LocalDate eventAt;

    public Event(String description, LocalDate eventAt) {
        super(description);
        setEventAt(eventAt);
    }

    public void setEventAt(LocalDate eventAt) {
        this.eventAt = eventAt;
    }

    public String getEventAt() {
        return eventAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getTaskSign() {
        return EVENT_SIGN;
    }

    @Override
    public LocalDate getTime() {
        return eventAt;
    }

    @Override
    public String toString() {
        return EVENT_SIGN + super.toString() + " (at: " + getEventAt() + ")";
    }
}
