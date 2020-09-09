package duke.datatypes;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    /**
     * Returns the status of the current task
     * The default status is a cross
     * If the task is done it will return a tick
     *
     * @return returns a tick or a cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }
}
