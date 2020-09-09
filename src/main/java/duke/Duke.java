package duke;

import java.util.Scanner;
import duke.functions.AnalyseCommand;
import duke.functions.TaskList;
import static duke.functions.DefaultMessages.greet;
import static duke.functions.DefaultMessages.bye;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        TaskList currentTaskList = new TaskList();

        greet();
        while (true) {
            input = in.nextLine();
            AnalyseCommand userCommands = new AnalyseCommand(input);
            if (userCommands.isBye()) {
                break;
            } else if (userCommands.isList()) {
                currentTaskList.printAllTasks();
            } else if (userCommands.isDone()) {
                currentTaskList.setTaskAsDone(userCommands.getCommandDescription());
            } else {
                currentTaskList.addTask(userCommands);
            }
        }
        bye();
    }
}
