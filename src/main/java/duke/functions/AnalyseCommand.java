package duke.functions;

public class AnalyseCommand {

    protected String commandType;
    protected String commandDescription;
    protected String commandTime;

    private static final String BYE_COMMAND = "BYE";
    private static final String LIST_COMMAND = "LIST";
    private final String DONE_COMMAND = "DONE";
    private final String TODO_COMMAND = "TODO";
    private final String EVENT_COMMAND = "EVENT";
    private final String DEADLINE_COMMAND = "DEADLINE";
    private final String DELETE_COMMAND = "DELETE";
    private final String FIND_COMMAND = "FIND";

    /** Set any command that is not valid to ERROR */
    private final String ERROR_COMMAND = "ERROR";
    /** String to separate the time from the whole event command */
    private final String EVENT_SEPARATOR = "/at";
    /** String to separate the time from the whole deadline command */
    private final String DEADLINE_SEPARATOR = "/by";

    /**
     * User's input is being analysed and split into different part.
     *
     * @param input User's input
     */
    public AnalyseCommand(String input) {
        setCommandType(input.trim());
        setCommandDescription(input.trim());
        setCommandTime(input.trim());
    }

    /**
     * Split user's input and get the command type.
     *
     * @param input User's input
     */
    public void setCommandType(String input) {
        if (input.contains(" ")) {
            String[] commandType = input.split(" ");
            this.commandType = commandType[0].toUpperCase();
        } else {
            this.commandType = input.toUpperCase();
        }
    }

    /**
     * Returns the command type given by the user.
     *
     * @return User's input command type
     */
    public String getCommandType(){
        return this.commandType;
    }

    public void setCommandDescription(String input) {
        if (isEvent()||isDeadline()) {
            try {
                String[] commandDescription = input.trim().split(" ", 2);
                String[] descriptionWithoutTime = commandDescription[1].split(" /");
                this.commandDescription = descriptionWithoutTime[0].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                String article = isEvent() ? " an " : " a ";
                System.out.println("OOPS!!! The description of" + article + getCommandType() + " cannot be empty.");
                setCommandType(ERROR_COMMAND);
            }
        } else if (isToDo()||isDone()||isDelete()||isFind()) {
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
                this.commandTime = commandTime[1].trim();
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

    public static boolean isBye(String input) {
        return input.trim().toUpperCase().equals(BYE_COMMAND);
    }

    public static boolean isList(String input) {
        return input.trim().toUpperCase().equals(LIST_COMMAND);
    }

    public boolean isDone() {
        return getCommandType().equals(DONE_COMMAND);
    }

    public boolean isToDo() {
        return getCommandType().equals(TODO_COMMAND);
    }

    public boolean isEvent() {
        return getCommandType().equals(EVENT_COMMAND);
    }

    public boolean isDeadline() {
        return getCommandType().equals(DEADLINE_COMMAND);
    }

    public boolean isDelete() {
        return getCommandType().equals(DELETE_COMMAND);
    }

    public boolean isFind() {
        return getCommandType().equals(FIND_COMMAND);
    }
}
