package duke.functions;

import duke.datatypes.Task;
import java.util.ArrayList;
import static duke.functions.DefaultMessages.ADD_TASK_MESSAGE;
import static duke.functions.DefaultMessages.DATE_FORMAT_ERROR;
import static duke.functions.DefaultMessages.DELETE_TASK_MESSAGE;
import static duke.functions.DefaultMessages.DONE_TASK_MESSAGE;
import static duke.functions.DefaultMessages.EMPTY_LIST_MESSAGE;
import static duke.functions.DefaultMessages.GOODBYE_MESSAGE;
import static duke.functions.DefaultMessages.GREET_MESSAGE;
import static duke.functions.DefaultMessages.INVALID_FORMAT_MESSAGE;
import static duke.functions.DefaultMessages.INVALID_TASK_MESSAGE;
import static duke.functions.DefaultMessages.LOAD_ERROR_MESSAGE;
import static duke.functions.DefaultMessages.LOAD_SUCCESS_MESSAGE;
import static duke.functions.DefaultMessages.SAVE_ERROR_MESSAGE;
import static duke.functions.DefaultMessages.UNKNOWN_COMMAND_ERROR;
import static duke.functions.DefaultMessages.SAVE_SUCCESS_MESSAGE;
import static duke.functions.DefaultMessages.TASK_IS_COMPLETED_MESSAGE;

/**
 * Text Ui that deals with interaction with the user.
 */
public class TextUi {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String DIVIDER = "===================================================";

    public void showGreetMessage() {
        showToUser(GREET_MESSAGE,DIVIDER);
    }

    public void showByeMessage() {
        showToUser(GOODBYE_MESSAGE,DIVIDER,DIVIDER);
    }

    public void showEmptyListMessage() {
        showToUser(EMPTY_LIST_MESSAGE,DIVIDER);
    }

    /**
     * Display the task list to the user.
     * @param arrayOfTasks the task list to be displayed
     */
    public void showList(ArrayList<Task> arrayOfTasks) {
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (Task task : arrayOfTasks) {
            showToUser(displayIndex + "." + task.toString());
            displayIndex++;
        }
        showToUser(DIVIDER);
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Shows add task message to user.
     *
     * @param newTask task to be added
     * @param numberOfTasks total number of tasks
     */
    public void successAddTaskMessage(Task newTask, int numberOfTasks) {
        showToUser(ADD_TASK_MESSAGE);
        showToUser("  " + newTask.toString());
        showNumberOfTasks(numberOfTasks);
    }

    /**
     * Show load error message when the saved file does not exist.
     */
    public void showLoadError() {
        showToUser(LOAD_ERROR_MESSAGE,DIVIDER);
    }

    /**
     * Show load success message when the saved file is loaded.
     */
    public void showLoadSuccess() {
        showToUser(LOAD_SUCCESS_MESSAGE,DIVIDER);
    }

    /**
     * Show save error message when the task list did not save successfully.
     */
    public void showSaveError() {
        showToUser(SAVE_ERROR_MESSAGE,DIVIDER);
    }

    /**
     * Show save success message when the task list is saved.
     */
    public void showSaveSuccess() {
        showToUser(SAVE_SUCCESS_MESSAGE,DIVIDER);
    }

    /**
     * Show invalid task message when the task selected is not valid.
     */
    public void showNoTaskMessage() {
        showToUser(INVALID_TASK_MESSAGE,DIVIDER);
    }

    /**
     * Show invalid input message when the input format is invalid.
     */
    public void showInvalidInput() {
        showToUser(INVALID_FORMAT_MESSAGE,DIVIDER);
    }

    /**
     * Show mark as done message.
     */
    public void showDoneMessage(Task completedTask) {
        showToUser(DONE_TASK_MESSAGE);
        showToUser("  " + completedTask.toString(), DIVIDER);
    }

    /**
     * Show number of tasks in the list to the user.
     */
    public void showNumberOfTasks(int numberOfTasks) {
        if (numberOfTasks < 2) {
            showToUser("Now you have " + numberOfTasks + " task in the list.", DIVIDER);
        } else {
            showToUser("Now you have " + numberOfTasks + " tasks in the list.", DIVIDER);
        }
    }

    /**
     * Show task deleted message
     *
     * @param deletedTask task to be deleted
     */
    public void showDeleteMessage(Task deletedTask, int numberOfTasks) {
        showToUser(DELETE_TASK_MESSAGE);
        showToUser("  " + deletedTask.toString());
        showNumberOfTasks(numberOfTasks);
    }

    /**
     * Show to user that the task he is trying ot set as done is already completed.
     */
    public void showTaskIsCompletedMessage() {
        showToUser(TASK_IS_COMPLETED_MESSAGE,DIVIDER);
    }

    /**
     * Show unknown command message when the user input does not match any commands.
     */
    public void showUnknownCommandError() {
        showToUser(UNKNOWN_COMMAND_ERROR, DIVIDER);
    }

    /**
     * Show invalid date message when the date format is invalid.
     */
    public void showDateFormatError() {
        showToUser(DATE_FORMAT_ERROR,DIVIDER);
    }
}
