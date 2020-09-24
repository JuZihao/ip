package duke;

import java.util.Scanner;
import duke.functions.AnalyseCommand;
import duke.functions.TaskList;

import static duke.functions.AnalyseCommand.isBye;
import static duke.functions.AnalyseCommand.isList;
import static duke.functions.DefaultMessages.showGreet;
import static duke.functions.DefaultMessages.showBye;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        TaskList currentTaskList = new TaskList();

        showGreet();
        currentTaskList.loadSavedTasks();

        while (true) {
            input = in.nextLine();
            AnalyseCommand userCommands = new AnalyseCommand(input);
            if (isBye(input)) {
                break;
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
        showBye();
    }
}
