package duke.commands;

import duke.datatypes.Task;
import duke.datatypes.Todo;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

/**
 * Adds a to do task to the task list.
 */
public class TodoCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        Task newTask= new Todo(userCommands.getCommandDescription());
        taskList.addTask(newTask);
        ui.successAddTaskMessage(newTask, taskList.getNumberOfTasks());
    }
}
