import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        infoMenu();

    }
    public static void infoMenu () {

        Scanner menu = new Scanner(System.in);


       while (!menu.hasNext()){System.out.println(ConsoleColors.BLUE+ "Please select ana option: \n" + //popraw...
                ConsoleColors.RESET + "add\n" +
                "remove\n" +
                "list\n" +
                "exit\n ");
        switch (menu.nextLine()){
            case "list":
                listOfTasks();
                break;
            case "add":
                System.out.println("tu bedzie metoda do add");
                    break;
            default: System.out.println("Please select a correct option" ); }


        }




    }

    public static void listOfTasks () {

        File f = new File("/home/qba/Dokumenty/ConsoleProgram/src/main/java/tasks.csv");

        try(Scanner scan1 = new Scanner(f)) {
            while (scan1.hasNextLine()) {
                String singleLine = scan1.nextLine();
                System.out.println(ConsoleColors.PURPLE +singleLine);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
