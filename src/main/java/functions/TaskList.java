package functions;

import datatypes.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrayOfTasks = new ArrayList<>();
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
                System.out.println(i+1 + "." + "[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
            }
        }
    }

    public void addTask(String input) {
        Task newTask= new Task(input);
        arrayOfTasks.add(newTask);
        setNumberOfTasks(getNumberOfTasks()+1);
    }

    public void setTaskAsDone(String input) {
        String[] in = input.split(" ");
        int taskToBeDone = Integer.parseInt(in[1]);

        if (taskToBeDone  <= getNumberOfTasks()) {
            arrayOfTasks.get(taskToBeDone - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" [\u2713]"
                    + arrayOfTasks.get(taskToBeDone - 1).getDescription());
        } else if (taskToBeDone  > getNumberOfTasks()){
            System.out.println("No such task exists in the list!");
        } else {
            System.out.println("Invalid input!");
            System.out.println("Please key in your input in the format:");
            System.out.println("done 1");
        }
    }
}
