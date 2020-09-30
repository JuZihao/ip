package duke.functions;

import static duke.functions.DefaultMessages.GOODBYE_MESSAGE;
import static duke.functions.DefaultMessages.GREET_MESSAGE;

public class TextUi {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    public void showGreetMessage() {
        showToUser(GREET_MESSAGE,DIVIDER);
    }

    public void showByeMessage() {
        showToUser(GOODBYE_MESSAGE,DIVIDER,DIVIDER);
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
