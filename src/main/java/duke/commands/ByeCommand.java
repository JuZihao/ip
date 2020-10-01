package duke.commands;

import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import java.io.IOException;

/**
 * Show exit message to user.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Parser userInputs, TextUi ui, FileIO storage) {
       try {
           storage.saveAsTextFile(taskList.getAllTasks());
           ui.showSaveSuccess();
       } catch (IOException e) {
           ui.showSaveError();
       }
       ui.showByeMessage();
    }
}
