package functions;

public class AnalyseCommand {

    protected String commandType;
    protected String commandDescription;
    protected String commandTime;

    public AnalyseCommand(String input) {
        setCommandType(input);
        setCommandDescription(input);
        setCommandTime(input);

    }

    public void setCommandType(String input) {
        if (input.contains(" ")) {
            String[] commandType = input.split(" ");
            this.commandType = commandType[0].toLowerCase();
        } else {
            this.commandType = input.toLowerCase();
        }
    }

    public String getCommandType(){
        return this.commandType;
    }

    public void setCommandDescription(String input) {
        if (input.contains(" ")) {
            String[] commandDescription = input.split(" ", 2);
            if (commandDescription[0].equalsIgnoreCase("deadline") || commandDescription[0].equalsIgnoreCase("event")) {
                String[] descriptionWithoutTime = commandDescription[1].split("/");
                this.commandDescription = descriptionWithoutTime[0];
            } else {
                this.commandDescription = commandDescription[1];
            }
        } else {
            this.commandDescription = "No description";
        }
    }

    public String getCommandDescription() {
        return this.commandDescription;
    }

    public void setCommandTime(String input) {
        if (input.contains("/")) {
            String[] commandTime = input.split("/");
            this.commandTime = commandTime[1];
        } else {
            this.commandTime = "No Given time";
        }
    }

    public String getCommandTime() {
        return this.commandTime;
    }

    public boolean isBye() {
        return getCommandType().equals("bye");
    }

    public boolean isList() {
        return getCommandType().equals("list");
    }

    public boolean isDone() {
        return getCommandType().equals("done");
    }
}
