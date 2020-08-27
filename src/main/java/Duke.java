import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
        bye();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(){
        boolean checkForBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        String[] todoList = new String[1000];
        int numberOfItems = 0;

        while (!checkForBye) {
            input = in.nextLine();
            checkForBye = input.equals("bye");
            switch (input) {
            case "bye":
                break;
            case "list":
                for(int i=0;i<numberOfItems;i++){
                    System.out.println( i+1 +". " + todoList[i]);
                }
                break;
            default:
                System.out.println("added: " + input);
                todoList[numberOfItems] = input;
                numberOfItems++;
                break;
            }
        }
    }

    public static void bye(){
        System.out.println("Bye! Hope to see you soon!");
    }
}
