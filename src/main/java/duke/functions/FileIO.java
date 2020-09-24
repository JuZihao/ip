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

public class FileIO {
    private final String TODO_SIGN = "[T]";
    private final String EVENT_SIGN = "[E]";
    private final String DEADLINE_SIGN = "[D]";
    private final String TEXT_DIVIDER = " | ";
    private final String NEW_LINE = "\n";
    private String fileName;

    public FileIO (String fileName) {
        setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void saveAsTextFile (ArrayList<Task> arrayOfTasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        String textToAdd = "";

        for (Task task : arrayOfTasks) {
            textToAdd += changeTaskToText(task);
        }
        fw.write(textToAdd);
        fw.close();
    }

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
