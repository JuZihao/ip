package duke.functions;

import duke.datatypes.Deadline;
import duke.datatypes.Event;
import duke.datatypes.Task;
import duke.datatypes.Todo;
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
            System.out.println("OOPS!!! No task added yet.");
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
        case "error":
            System.out.println("Please enter your command again!");
            break;
        default:
            System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
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
        try {
            int taskToBeDone = Integer.parseInt(input);
            if(arrayOfTasks.get(taskToBeDone -1).getIsDone()) {
                System.out.println("OOPS!!! Looks like this task is done.");
                System.out.println("Please try another task.");
            } else {
                arrayOfTasks.get(taskToBeDone - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(arrayOfTasks.get(taskToBeDone - 1).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! No such task exists in the list.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Looks like this is an invalid input.");
            System.out.println("Please key in your input in the format:");
            System.out.println("done 1");
        }
    }
}
