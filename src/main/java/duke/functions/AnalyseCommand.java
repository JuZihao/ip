package duke.functions;

public class AnalyseCommand {

    private final String ERROR_COMMAND = "ERROR";
    private final String EVENT_SEPARATOR = "/at";
    private final String DEADLINE_SEPARATOR = "/by";


    protected String commandType;
    protected String commandDescription;
    protected String commandTime;

    public AnalyseCommand(String input) {
        setCommandType(input.trim());
        setCommandDescription(input.trim());
        setCommandTime(input.trim());
    }

    public void setCommandType(String input) {
        if (input.contains(" ")) {
            String[] commandType = input.split(" ");
            this.commandType = commandType[0].toLowerCase();
        } else {
            this.commandType = input.toLowerCase();
        }
    }

    public String getCommandType(){
        return this.commandType;
    }

    public void setCommandDescription(String input) {
        if (isEvent()||isDeadline()) {
            try {
                String[] commandDescription = input.trim().split(" ", 2);
                String[] descriptionWithoutTime = commandDescription[1].split(" /");
                this.commandDescription = descriptionWithoutTime[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                String article = isEvent() ? " an " : " a ";
                System.out.println("OOPS!!! The description of" + article + getCommandType() + " cannot be empty.");
                setCommandType(ERROR_COMMAND);
            }
        } else if (isToDo()||isDone()||isDelete()) {
            try {
                String[] commandDescription = input.trim().split(" ", 2);
                this.commandDescription = commandDescription[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a " + getCommandType() + " cannot be empty.");
                setCommandType(ERROR_COMMAND);
            }
        } else {
            this.commandDescription = "No description!";
        }
    }

    public String getCommandDescription() {
        return this.commandDescription;
    }

    public void setCommandTime(String input) {
        if (isDeadline()||isEvent()) {
            String timeSeparator = isDeadline() ? DEADLINE_SEPARATOR:EVENT_SEPARATOR;
            try {
                String[] commandTime = input.split(timeSeparator);
                this.commandTime = commandTime[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The time of your " + getCommandType() + " is invalid.");
                setCommandType(ERROR_COMMAND);
            }
        } else {
            this.commandTime = "No Given time!";
        }
    }

    public String getCommandTime() {
        return this.commandTime;
    }

    public boolean isBye() {
        return getCommandType().equals("bye");
    }

    public boolean isList() {
        return getCommandType().equals("list");
    }

    public boolean isDone() {
        return getCommandType().equals("done");
    }

    public boolean isToDo() {
        return getCommandType().equals("todo");
    }

    public boolean isEvent() {
        return getCommandType().equals("event");
    }

    public boolean isDeadline() {
        return getCommandType().equals("deadline");
    }

    public boolean isDelete() {
        return getCommandType().equals("delete");
    }



}
