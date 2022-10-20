import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {


        File f = new File("/home/qba/Dokumenty/ConsoleProgram/src/main/java/tasks.csv");

        try(Scanner scan1 = new Scanner(f)) {
            while (scan1.hasNextLine()) {
                String singleLine = scan1.nextLine();
                System.out.println(ConsoleColors.BLUE +singleLine);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
