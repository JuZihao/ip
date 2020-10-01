package duke.commands;

import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;
import duke.functions.FileIO;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Default execute function for all child classes
     *
     * @param taskList Current task list containing all added tasks
     * @param userCommands The user interface to interact with the user
     * @param ui The user interface to interact with the user
     * @param storage The Storage for file IO
     * */
    public abstract void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage);
}