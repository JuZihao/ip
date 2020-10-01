package duke.commands;

import duke.datatypes.Task;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import static duke.functions.DefaultMessages.PRINT_FIND_MESSAGE;

import java.util.ArrayList;

/**
 * Find tasks in the task list that contains the keyword.
 */
public class FindCommand extends Command {
    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        ArrayList<Task> filteredTaskList = taskList.findByKeyword(userCommands.getCommandDescription());
        if (filteredTaskList.isEmpty()) {
            ui.showNoTaskMessage();
        } else {
            ui.showToUser(PRINT_FIND_MESSAGE);
            ui.showList(filteredTaskList);
        }
    }
}
