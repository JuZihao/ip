package duke.commands;

import duke.datatypes.Task;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import static duke.functions.DefaultMessages.PRINT_LIST_MESSAGE;

import java.util.ArrayList;

/**
 * Show the current task list to the user.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        ArrayList<Task> arrayOfTasks = taskList.getAllTasks();
        if(arrayOfTasks.isEmpty()) {
            ui.showEmptyListMessage();
        } else {
            ui.showToUser(PRINT_LIST_MESSAGE);
            ui.showList(arrayOfTasks);
        }
    }
}
