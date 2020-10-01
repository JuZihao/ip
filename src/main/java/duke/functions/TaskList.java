package duke.functions;

import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

import duke.datatypes.Task;
import duke.exceptions.TaskDoneException;

import static duke.functions.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * A list of tasks. Does not allow the description or time of the task to be empty.
 */
public class TaskList {
    private ArrayList<Task> arrayOfTasks;
    private int numberOfTasks;

    /**
     * Constructs empty task list.
     */
    public TaskList() {
        setNumberOfTasks(0);
    }

    /**
     * Constructs task list from saved file.
     */
    public TaskList(ArrayList<Task> arrayOfTasks) {
        setAllTasks(arrayOfTasks);
        setNumberOfTasks(arrayOfTasks.size());
    }

    /**
     * Set up the tasks saved in the file.
     *
     * @param arrayOfTasks tasks to be loaded
     */
    private void setAllTasks(ArrayList<Task> arrayOfTasks) {
        this.arrayOfTasks = arrayOfTasks;
    }

    /**
     * Returns all the tasks in the list.
     *
     * @return all the tasks in the list
     */
    public ArrayList<Task> getAllTasks() {
        return arrayOfTasks;
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
     * Add a new task into the list.
     */
    public void addTask(Task newTask) {
        arrayOfTasks.add(newTask);
        setNumberOfTasks(getNumberOfTasks() + 1);
    }

    /**
     * Remove the task stated by the user's input
     *
     * @param usercommands user's input
     * @return the deleted task
     */
    public Task deleteTask(String usercommands) {
            int taskToBeDeleted = Integer.parseInt(usercommands);
            Task deletedTask= arrayOfTasks.get(taskToBeDeleted - DISPLAYED_INDEX_OFFSET);
            arrayOfTasks.remove(taskToBeDeleted - DISPLAYED_INDEX_OFFSET);
            setNumberOfTasks(getNumberOfTasks() - DISPLAYED_INDEX_OFFSET);
            return deletedTask;
    }

    /**
     * Set the task stated by the user's input to be done.
     *
     * @param input user's input
     * @return the task to be set as done
     */
    public Task setTaskAsDone(String input) throws TaskDoneException {
        int indexTaskToBeDone = Integer.parseInt(input);
        Task taskToBeDone = arrayOfTasks.get(indexTaskToBeDone - DISPLAYED_INDEX_OFFSET);
        if (taskToBeDone.getIsDone()) {
            throw new TaskDoneException();
        } else {
            taskToBeDone.markAsDone();
        }
        return taskToBeDone;
    }

    /**
     * Find and display all the tasks that contains the keyword stated by the user.
     *
     * @param keyword word that user want to find in the task list
     * @return a task list with task that have the keyword
     */
    public ArrayList<Task> findByKeyword(String keyword) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) arrayOfTasks.stream()
                .filter((s) -> s.getDescription().contains(keyword))
                .collect(toList());

        return filteredTaskList;
    }
}
