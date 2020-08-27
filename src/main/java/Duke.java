import java.util.Scanner;
import Functions.TaskList;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        TaskList currentTaskList = new TaskList();

        greet();

        while (true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                currentTaskList.printAllTasks();
            }
            else if (input.toLowerCase().contains("done")){
                currentTaskList.setTaskAsDone(input);
            }
            else  {
                System.out.println("added: " + input);
                currentTaskList.addTask(input);
            }
        }
        bye();
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }


    public static void bye(){
        System.out.println("Bye! Hope to see you soon!");
    }
}
