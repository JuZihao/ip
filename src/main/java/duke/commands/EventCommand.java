package duke.commands;

import duke.datatypes.Deadline;
import duke.datatypes.Event;
import duke.datatypes.Task;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
        try {
            LocalDate eventAt = LocalDate.parse(userCommands.getCommandTime());
            Task newTask = new Event(userCommands.getCommandDescription(), eventAt);
            taskList.addTask(newTask);
            ui.successAddTaskMessage(newTask, taskList.getNumberOfTasks());
        } catch (DateTimeParseException e) {
            ui.showDateFormatError();
        }
    }
}
