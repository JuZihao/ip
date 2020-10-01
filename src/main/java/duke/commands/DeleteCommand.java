package duke.commands;

import duke.datatypes.Task;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

/**
 * Delete a task from the task list.
 */
public class DeleteCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        try {
            Task deletedTask = taskList.deleteTask(userCommands.getCommandDescription());
            ui.showDeleteMessage(deletedTask, taskList.getNumberOfTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showNoTaskMessage();
        } catch (NumberFormatException e) {
            ui.showInvalidInput();
        }
    }
}
