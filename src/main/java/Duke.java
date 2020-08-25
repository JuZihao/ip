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

        while (!checkForBye) {
            input = in.nextLine();
            checkForBye = input.equals("bye");
            if(checkForBye){
                break;
            }
            else {
                System.out.println(input);
            }
        }
    }

    public static void bye(){
        System.out.println("Bye! Hope to see you soon!");
    }
}
