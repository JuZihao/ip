package duke.functions;

import duke.datatypes.Deadline;
import duke.datatypes.Event;
import duke.datatypes.Task;
import duke.datatypes.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.datatypes.Deadline.DEADLINE_SIGN;
import static duke.datatypes.Event.EVENT_SIGN;
import static duke.datatypes.Todo.TODO_SIGN;

/**
 * Represents the file used to store task list data.
 */
public class FileIO {
    private String fileName;
    private final String TEXT_DIVIDER = " | ";
    private final String NEW_LINE = "\n";

    /** Default file path used. */
    protected static final String DEFAULT_STORAGE_FILEPATH = "data/savedtasklist.txt";

    public FileIO (String fileName) {
        setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     */
    public void saveAsTextFile (ArrayList<Task> arrayOfTasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        String textToAdd = "";

        for (Task task : arrayOfTasks) {
            textToAdd += changeTaskToText(task);
        }
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Converts each task to text format that can be saved into the text file.
     *
     * @param taskToBeChanged task that has to be converted
     * @return converted task
     */
    public String changeTaskToText (Task taskToBeChanged) {
        String taskAsTXT = "";
        taskAsTXT += taskToBeChanged.getTaskSign();
        taskAsTXT += TEXT_DIVIDER;
        taskAsTXT += (taskToBeChanged.getIsDone()) ? "1" : "0";
        taskAsTXT += TEXT_DIVIDER;
        taskAsTXT += taskToBeChanged.getDescription();
        if (!taskToBeChanged.getTaskSign().equals(TODO_SIGN)) {
            taskAsTXT += TEXT_DIVIDER;
            taskAsTXT += taskToBeChanged.getTime();
        }
        taskAsTXT += NEW_LINE;
        return taskAsTXT;
    }

    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist.
     *
     * @throws FileNotFoundException if there is no saved text file available
     */
    public ArrayList<Task> loadSavedFile() throws FileNotFoundException {
        File savedFile = new File(fileName);
        Scanner in = new Scanner(savedFile);
        ArrayList<Task> arrayOfTasks = new ArrayList<>();
        String taskLine;
        String[] processedElements;
        String taskType;
        String isDone;
        String description;
        String time = "";
        Task savedTaskToAdd = null;

        while (in.hasNextLine()) {
            taskLine = in.nextLine();
            processedElements = taskLine.split("\\| ");
            taskType = processedElements[0];
            isDone = processedElements[1];
            description = processedElements[2].trim();

            if (processedElements.length == 4) {
                time = processedElements[3];
            }

            switch (taskType.trim()) {
            case TODO_SIGN:
                savedTaskToAdd = new Todo(description);
                break;
            case EVENT_SIGN:
                savedTaskToAdd = new Event(description,time);
                break;
            case DEADLINE_SIGN:
                savedTaskToAdd = new Deadline(description,time);
                break;
            default:
                break;
            }
            if (isDone.trim().equals("1")) {
                savedTaskToAdd.markAsDone();
            }
            arrayOfTasks.add(savedTaskToAdd);
        }
        return arrayOfTasks;
    }
}
