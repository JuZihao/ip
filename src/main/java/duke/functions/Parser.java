package duke.functions;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.UnknownCommandException;

/**
 * Analyse the user's input and set it as different parts that will be passed to other functions
 */
public class Parser {

    protected String commandType;
    protected String commandDescription;
    protected String commandTime;

    protected static final String BYE_COMMAND = "BYE";
    protected static final String LIST_COMMAND = "LIST";
    protected static final String DONE_COMMAND = "DONE";
    protected static final String TODO_COMMAND = "TODO";
    protected static final String EVENT_COMMAND = "EVENT";
    protected static final String DEADLINE_COMMAND = "DEADLINE";
    protected static final String DELETE_COMMAND = "DELETE";
    protected static final String FIND_COMMAND = "FIND";

    /** Set any command that is not valid to ERROR */
    protected static final String ERROR_COMMAND = "ERROR";
    /** String to separate the time from the whole event command */
    private final String EVENT_SEPARATOR = "/at";
    /** String to separate the time from the whole deadline command */
    private final String DEADLINE_SEPARATOR = "/by";

    /**
     * User's input is being analysed and split into different part.
     * Parses user inputs into commands.
     *
     * @param input user's full input
     * @throws InvalidCommandException if the command entered is not valid
     */
    public Parser(String input) throws InvalidCommandException {
        setCommandType(input.trim());
        setCommandDescription(input.trim());
        setCommandTime(input.trim());
    }

    /**
     * Split user's input and get the command type.
     *
     * @param input user's full input
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
     * @return user's input command type
     */
    public String getCommandType(){
        return this.commandType;
    }

    /**
     * Spilt user's input and get the description of the task if no description
     * is specified it will be set to "No description!"
     *
     * @param input user's full input
     * @throws InvalidCommandException if the command entered is not valid
     */
    public void setCommandDescription(String input) throws InvalidCommandException {
        if (isEvent()||isDeadline()) {
            try {
                String[] commandDescription = input.trim().split(" ", 2);
                String[] descriptionWithoutTime = commandDescription[1].split("/");
                this.commandDescription = descriptionWithoutTime[0].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandException();
            }
        } else if (isToDo()||isDone()||isDelete()||isFind()) {
            try {
                String[] commandDescription = input.trim().split(" ", 2);
                this.commandDescription = commandDescription[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandException();
            }
        } else {
            this.commandDescription = "No description!";
        }
    }

    /**
     * Returns the description given by the user.
     *
     * @return user's input description
     */
    public String getCommandDescription() {
        return this.commandDescription;
    }

    /**
     * Spilt user's input and get the time of the task if no task is specified it will be set to "No given time!"
     *
     * @param input user's full input
     * @throws InvalidCommandException if the command entered is not valid
     */
    public void setCommandTime(String input) throws InvalidCommandException{
        if (isDeadline()||isEvent()) {
            String timeSeparator = isDeadline() ? DEADLINE_SEPARATOR:EVENT_SEPARATOR;
            try {
                String[] commandTime = input.split(timeSeparator);
                this.commandTime = commandTime[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandException();
            }
        } else {
            this.commandTime = "No given time!";
        }
    }

    /**
     * Returns the time given by the user.
     *
     * @return user's input description
     */
    public String getCommandTime() {
        return this.commandTime;
    }

    public Command parseCommand(String input) throws UnknownCommandException {
        String CommandType = getCommandType();

        if (isBye(input)) {
            return new ByeCommand();
        } else if (isList(input)) {
            return new ListCommand();
        }

        switch (CommandType) {
        case DONE_COMMAND:
            return new DoneCommand();
        case EVENT_COMMAND:
            return new EventCommand();
        case TODO_COMMAND:
            return new TodoCommand();
        case DEADLINE_COMMAND:
            return new DeadlineCommand();
        case DELETE_COMMAND:
            return new DeleteCommand();
        case FIND_COMMAND:
            return new FindCommand();
        default:
            throw  new UnknownCommandException();
        }
    }

    /**
     * Returns true if the user input line is a bye command.
     *
     * @param input user's full input
     * @return true if the entire user input line is bye
     */
    public static boolean isBye(String input) {
        return input.trim().toUpperCase().equals(BYE_COMMAND);
    }

    /**
     * Returns true if the user input line is a bye command.
     *
     * @param input user's full input
     * @return true if the entire user input line is list
     */
    public static boolean isList(String input) {
        return input.trim().toUpperCase().equals(LIST_COMMAND);
    }

    /**
     * Returns true if the user's command type is a done command.
     */
    public boolean isDone() {
        return getCommandType().equals(DONE_COMMAND);
    }

    /**
     * Returns true if the user's command type is a to do command.
     */
    public boolean isToDo() {
        return getCommandType().equals(TODO_COMMAND);
    }

    /**
     * Returns true if the user's command type is an event command.
     */
    public boolean isEvent() {
        return getCommandType().equals(EVENT_COMMAND);
    }

    /**
     * Returns true if the user's command type is a deadline command.
     */
    public boolean isDeadline() {
        return getCommandType().equals(DEADLINE_COMMAND);
    }

    /**
     * Returns true if the user's command type is a delete command.
     */
    public boolean isDelete() {
        return getCommandType().equals(DELETE_COMMAND);
    }

    /**
     * Returns true if the user's command type is a find command.
     */
    public boolean isFind() {
        return getCommandType().equals(FIND_COMMAND);
    }
}
