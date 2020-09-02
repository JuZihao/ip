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
        String[] commandType = input.split(" ");
        this.commandType = commandType[0].toLowerCase();
    }

    public String getCommandType(){
        return this.commandType;
    }

    public void setCommandDescription(String input) {
        String[] commandDescription = input.split(" ",2);

        if (commandDescription[0].equalsIgnoreCase("deadline") || commandDescription[0].equalsIgnoreCase("event")) {
            String[] deadlineDescription = commandDescription[1].split("/");
            this.commandDescription = deadlineDescription[0];
        }
        else {
            this.commandDescription = commandDescription[1];
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
            this.commandTime = "0";
        }
    }

    public String getCommandTime() {
        return this.commandTime;
    }
}
