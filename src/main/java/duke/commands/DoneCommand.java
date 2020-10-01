package duke.commands;

import duke.datatypes.Task;
import duke.exceptions.TaskDoneException;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

/**
 * Mark a task as done.
 */
public class DoneCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        try {
            Task completedTask = taskList.setTaskAsDone(userCommands.getCommandDescription());
            ui.showDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.showNoTaskMessage();
        } catch (NumberFormatException e) {
            ui.showInvalidInput();
        } catch (TaskDoneException e) {
            ui.showTaskIsCompletedMessage();
        }
    }
}
