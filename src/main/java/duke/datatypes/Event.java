package duke.datatypes;

public class Event extends Task {

    private final String EVENT_SIGN = "[E]";
    protected String eventAt;

    public Event(String description, String eventAt) {
        super(description);
        setEventAt(eventAt);
    }

    public void setEventAt(String eventAt) {
        String at = eventAt.replace("at ","");
        this.eventAt = at;
    }

    public String getEventAt() {
        return eventAt;
    }

    @Override
    public String getTaskSign() {
        return EVENT_SIGN;
    }

    @Override
    public String getTime() {
        return getEventAt();
    }

    @Override
    public String toString() {
        return EVENT_SIGN + super.toString() + " (at: " + getEventAt() + ")";
    }
}
