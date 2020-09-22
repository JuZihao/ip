package duke.functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import duke.datatypes.Deadline;
import duke.datatypes.Event;
import duke.datatypes.Task;
import duke.datatypes.Todo;

public class TaskList {
    private ArrayList<Task> arrayOfTasks = new ArrayList<>();
    private final FileIO savedTextFile = new FileIO("data/savedtasklist.txt");
    private int numberOfTasks;

    private final String DONE_COMMAND = "DONE";
    private final String TODO_COMMAND = "TODO";
    private final String EVENT_COMMAND = "EVENT";
    private final String DEADLINE_COMMAND = "DEADLINE";
    private final String DELETE_COMMAND = "DELETE";
    private final String ERROR_COMMAND = "ERROR";

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
        case TODO_COMMAND:
            newTask= new Todo(usercommands.getCommandDescription());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks() + 1);
            printAddTaskMessage(newTask);
            break;
        case DEADLINE_COMMAND:
            newTask= new Deadline(usercommands.getCommandDescription(), usercommands.getCommandTime());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks() + 1);
            printAddTaskMessage(newTask);
            break;
        case EVENT_COMMAND:
            newTask= new Event(usercommands.getCommandDescription(), usercommands.getCommandTime());
            arrayOfTasks.add(newTask);
            setNumberOfTasks(getNumberOfTasks() + 1);
            printAddTaskMessage(newTask);
            break;
        case ERROR_COMMAND:
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

    public void deleteTask(String usercommands) {
        try {
            int taskToBeDeleted = Integer.parseInt(usercommands);
            printDeleteTaskMessage(arrayOfTasks.get(taskToBeDeleted - 1));
            arrayOfTasks.remove(taskToBeDeleted - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! No such task exists in the list.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Looks like this is an invalid input.");
            System.out.println("Please key in your input in the format:");
            System.out.println("delete 1");
        }
    }

    private void printDeleteTaskMessage(Task taskToBeDeleted) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToBeDeleted.toString());
        setNumberOfTasks(getNumberOfTasks() - 1);
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

    public void saveTasksAsText() {
        try {
            savedTextFile.saveAsTextFile(arrayOfTasks);
            System.out.println("Done! All tasks saved!");
        } catch (IOException e) {
            System.out.println("OOPS!!! Looks like there is an error saving tasks.");
        }
    }

    public void loadSavedTasks() {
        try {
            arrayOfTasks = savedTextFile.loadSavedFile();
            setNumberOfTasks(arrayOfTasks.size());
            System.out.println("Done! All tasks loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("The file you try to load does not exist!");
        }
    }
}
