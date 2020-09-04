package functions;

import datatypes.Deadline;
import datatypes.Event;
import datatypes.Task;
import datatypes.Todo;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> arrayOfTasks = new ArrayList<>();
    private int numberOfTasks;

    public TaskList() {
        setNumberOfTasks(0);
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks=numberOfTasks;
    }

    public void printAllTasks() {
        if(arrayOfTasks.isEmpty()) {
            System.out.println("No tasks added yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i<arrayOfTasks.size();i++){
                Task currentTask = arrayOfTasks.get(i);
                System.out.println(i+1 + "." + currentTask.toString());
            }
        }
    }

    public void addTask(AnalyseCommand usercommands) {

        Task newTask;

        switch (usercommands.getCommandType()) {
        case "todo":
            newTask= new Todo(usercommands.getCommandDescription());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks()+1);
            printAddTaskMessage(newTask);
            break;
        case "deadline":
            newTask= new Deadline(usercommands.getCommandDescription(), usercommands.getCommandTime());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks()+1);
            printAddTaskMessage(newTask);
            break;
        case "event":
            newTask= new Event(usercommands.getCommandDescription(), usercommands.getCommandTime());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks()+1);
            printAddTaskMessage(newTask);
            break;
        default:
            System.out.println("No such commands!");
            break;
        }

    }

    private void printAddTaskMessage(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        if (getNumberOfTasks() < 2) {
            System.out.println("Now you have " + getNumberOfTasks() + " task in the list.");
        } else {
            System.out.println("Now you have " + getNumberOfTasks() + " tasks in the list.");
        }
    }

    public void setTaskAsDone(String input) {
        int taskToBeDone = Integer.parseInt(input);

        if (taskToBeDone  <= getNumberOfTasks()) {
            arrayOfTasks.get(taskToBeDone - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(arrayOfTasks.get(taskToBeDone - 1).toString());
        } else if (taskToBeDone  > getNumberOfTasks()) {
            System.out.println("No such task exists in the list!");
        } else {
            System.out.println("Invalid input!");
            System.out.println("Please key in your input in the format:");
            System.out.println("done 1");
        }
    }
}
