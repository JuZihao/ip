import java.util.Scanner;

import functions.AnalyseCommand;
import functions.TaskList;
import static functions.DefaultMessages.greet;
import static functions.DefaultMessages.bye;

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
                currentTaskList.addTask(userCommands.getCommandType(),userCommands.getCommandDescription(),userCommands.getCommandTime());
            }
        }

        bye();
    }

}
