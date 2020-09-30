package duke;

import java.util.Scanner;
import duke.functions.Command;
import duke.functions.TaskList;
import duke.functions.TextUi;

import static duke.functions.Command.isBye;
import static duke.functions.Command.isList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TextUi ui;
    private TaskList currentTaskList;

    public Duke() {
        this.ui = new TextUi();
        this.currentTaskList = new TaskList();
        currentTaskList.loadSavedTasks();
        ui.showGreetMessage();
    }

    /** Runs the program until user inputs bye. */
    public void run() {
        String input;
        Scanner in = new Scanner(System.in);
        TaskList currentTaskList = new TaskList();

        boolean isBye = false;
        while(!isBye) {
            input = in.nextLine();
            Command userCommands = new Command(input);
            if (isBye(input)) {
                isBye = isBye(input);
            } else if (isList(input)) {
                currentTaskList.printAllTasks();
            } else if (userCommands.isDone()) {
                currentTaskList.setTaskAsDone(userCommands.getCommandDescription());
            } else if (userCommands.isDelete()) {
                currentTaskList.deleteTask(userCommands.getCommandDescription());
            } else if(userCommands.isFind()) {
                currentTaskList.findByKeyword(userCommands.getCommandDescription());
            } else {
                currentTaskList.addTask(userCommands);
            }
        }
        currentTaskList.saveTasksAsText();
        ui.showByeMessage();
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
