package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.UnknownCommandException;
import duke.functions.FileIO;
import duke.functions.Parser;
import duke.functions.TaskList;
import duke.functions.TextUi;

import static duke.functions.FileIO.DEFAULT_STORAGE_FILEPATH;
import static duke.functions.Parser.isBye;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private FileIO storage;
    private TextUi ui;
    private TaskList currentTaskList;

    public Duke(String fileName) {
        this.ui = new TextUi();
        this.storage = new FileIO(fileName);
        try {
            currentTaskList = new TaskList(storage.loadSavedFile());
            ui.showLoadSuccess();
        } catch (FileNotFoundException e) {
            ui.showLoadError();
            currentTaskList = new TaskList();
        }
        ui.showGreetMessage();
    }

    /** Runs the program until user inputs bye. */
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        while(!isBye) {
            String input = in.nextLine();
            try {
                Parser parser = new Parser(input);
                Command c = parser.parseCommand(input);
                c.execute(currentTaskList, parser, ui, storage);
            } catch (UnknownCommandException e) {
                ui.showUnknownCommandError();
            } catch (InvalidCommandException e) {
                ui.showInvalidInput();
            }
            isBye = isBye(input);
        }
    }
    public static void main(String[] args) {
        new Duke(DEFAULT_STORAGE_FILEPATH).run();
    }
}
