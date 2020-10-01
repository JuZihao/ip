package duke.commands;

import duke.datatypes.Deadline;
import duke.datatypes.Task;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userCommands, TextUi ui, FileIO storage) {
       try {
           LocalDate deadlineBy = LocalDate.parse(userCommands.getCommandTime());
           Task newTask = new Deadline(userCommands.getCommandDescription(), deadlineBy);
           taskList.addTask(newTask);
           ui.successAddTaskMessage(newTask, taskList.getNumberOfTasks());
       } catch (DateTimeParseException e) {
           ui.showDateFormatError();
       }
    }
}
