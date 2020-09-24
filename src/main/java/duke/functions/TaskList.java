package duke.functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static duke.functions.Command.*;
import static java.util.stream.Collectors.toList;

import duke.datatypes.Deadline;
import duke.datatypes.Event;
import duke.datatypes.Task;
import duke.datatypes.Todo;
import static duke.functions.FileIO.DEFAULT_STORAGE_FILEPATH;


/**
 * A list of tasks. Does not allow the description or time of the task to be empty.
 */
public class TaskList {
    private ArrayList<Task> arrayOfTasks = new ArrayList<>();
    private final FileIO savedTextFile = new FileIO(DEFAULT_STORAGE_FILEPATH);
    private int numberOfTasks;

    /**
     * Constructs empty task list.
     */
    public TaskList() {
        setNumberOfTasks(0);
    }

    /**
     * Retrieve the number of tasks in the list currently.
     *
     * @return number of tasks in the list
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Set the number of task in the list.
     *
     * @param numberOfTasks current number of tasks
     */
    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks=numberOfTasks;
    }

    /**
     * Display all current tasks to the user.
     */
    public void printAllTasks() {
        if(arrayOfTasks.isEmpty()) {
            System.out.println("OOPS!!! No task added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < arrayOfTasks.size(); i++){
                Task currentTask = arrayOfTasks.get(i);
                System.out.println(i+1 + "." + currentTask.toString());
            }
        }
    }

    /**
     * Add tasks into the list if it is one of the task commands.
     * Return error message if the user command is not any of the command words.
     *
     * @param usercommands user's input
     */
    public void addTask(Command usercommands) {

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

    /**
     * Display what task was added into the list and the number of tasks in the list currently.
     *
     * @param newTask new task added
     */
    private void printAddTaskMessage(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        if (getNumberOfTasks() < 2) {
            System.out.println("Now you have " + getNumberOfTasks() + " task in the list.");
        } else {
            System.out.println("Now you have " + getNumberOfTasks() + " tasks in the list.");
        }
    }

    /**
     * Remove the task stated by the user's input
     *
     * @param usercommands user's input
     */
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

    /**
     * Display what task is removed and the number of tasks left in the list currently
     *
     * @param taskToBeDeleted task to be deleted
     */
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

    /**
     * Set the task stated by the user's input to be done.
     *
     * @param input user's input
     */
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

    /**
     * Save the current task list as a text file.
     */
    public void saveTasksAsText() {
        try {
            savedTextFile.saveAsTextFile(arrayOfTasks);
            System.out.println("Done! All tasks saved!");
        } catch (IOException e) {
            System.out.println("OOPS!!! Looks like there is an error saving tasks.");
        }
    }

    /**
     * Load the saved text file as the current task list.
     */
    public void loadSavedTasks() {
        try {
            arrayOfTasks = savedTextFile.loadSavedFile();
            setNumberOfTasks(arrayOfTasks.size());
            System.out.println("Done! All tasks loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("The file you try to load does not exist!");
        }
    }

    /**
     * Find and display all the tasks that contains the keyword stated by the user.
     *
     * @param keyword word that user want to find in the task list
     */
    public void findByKeyword(String keyword) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) arrayOfTasks.stream()
                .filter((s) -> s.getDescription().contains(keyword))
                .collect(toList());
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTaskList.size(); i++){
            Task currentTask = filteredTaskList.get(i);
            System.out.println(i+1 + "." + currentTask.toString());
        }
    }
}
