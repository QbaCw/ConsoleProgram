import org.apache.commons.lang3.math.NumberUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static String[][]tasks;

    public static void main(String[] args) {
        infoMenu();  //wywołanie menu

    }
    public static void infoMenu () {  // menu z opcjami

        Scanner menu = new Scanner(System.in);
        tasks = listOfTasks();
        String str = "";


        while (!str.equals("exit")) {
            System.out.println(ConsoleColors.BLUE + "Please select ana option: \n"   +
                    ConsoleColors.RESET +
                    "add\n" +
                    "remove\n" +
                    "list\n" +
                    "exit\n ");
            switch (menu.nextLine()) {
                case "list":
                    list();
                    break;
                case "add":
                    add(menu);
                    break;
                case "remove":
                    remove(menu);
                    break;
                case "exit":
                    System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "PA PA" + ConsoleColors.RESET);
                    exit(tasks);
                    System.exit(0);
                default:
                    System.out.println("Please select a correct option");
                    break;
            }
        }

    }
    public static String[][] listOfTasks () {  //wczytywanie listy z pliku

        File f = new File("/home/qba/Dokumenty/ConsoleProgram/src/main/java/tasks.csv");
        tasks = new String[0][];
        try(Scanner scan1 = new Scanner(f)) {
            while (scan1.hasNextLine()) {
                String singleLine = scan1.nextLine();
                String[] date = singleLine.split(",");
                tasks = newRow(tasks, date);

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;

    }

    public static void list(){  //metoda wypisująca aktualną listę zadań
        System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "LIST:" + ConsoleColors.RESET);

        if(tasks.length == 0 ){
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Sorry! Your list is empty." + ConsoleColors.RESET);
        }else {
            for (int i =0; i< tasks.length; i++){
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + i + " : " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT +  tasks[i][0] + "\t" +tasks[i][1] + "\t" + tasks[i][2] + ConsoleColors.RESET);
            }
        }
    }

    public static void add (Scanner adding){        // metoda dodawania wpisów do planera
        String[] newTasks = new String[3];
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT+ "Please add task description");
        newTasks[0] = adding.nextLine();
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Please add task due date");

        while (!NumberUtils.isParsable(adding.nextLine())){
            System.out.println(ConsoleColors.RED_BOLD + " Invalid date format! \n" + ConsoleColors.RESET+ ConsoleColors.RED + "Please try again and use format yayyy-mm-dd" + ConsoleColors.RESET); // tu cały czas do poprawy ..
              } newTasks[1] = adding.nextLine();

        System.out.println("Is your task important: true/false");
        while (!adding.hasNextBoolean()){
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid value!"+ ConsoleColors.RESET + ConsoleColors.RED + "Please type :  "
            + ConsoleColors.RED_UNDERLINED + "true" + ConsoleColors.RESET+ ConsoleColors.RED + " or " + ConsoleColors.RESET +ConsoleColors.RED_UNDERLINED + "false" + ConsoleColors.RESET );
            adding.nextLine();
        } newTasks[2] = adding.nextLine();
        tasks = newRow(tasks, newTasks);
    }

    public static void remove (Scanner erase){
        System.out.println("Please select number to remove");
        int number =  erase.nextInt();
        erase.nextLine();
        while (number < 0 || number >tasks.length -1 ) {
            System.out.println("INVALID Value \n" +"Please select number between 0 and " + (tasks.length-1));
            erase.nextLine();
            number= erase.nextInt();
            erase.nextLine();
        }
        String [][] eraseTask = new String[0][];
        for (int i = 0; i < tasks.length; i++){
            if(i != number) {
                eraseTask = newRow(eraseTask,tasks[i]);

            }
        }
        tasks = eraseTask;
        System.out.println("Value was successfully deleted");





    }

    public static void exit ( String[][]task){
        Path dir =  Paths.get("/home/qba/Dokumenty/ConsoleProgram/src/main/java/tasks.csv");  //metoda wyjscia i zapisu do pliku
        String [] lines = new String[tasks.length];
        for (int i =0; i < task.length; i++){
            lines[i]= String.join(",",task[i]);
        }
        try{
            Files.write(dir, Arrays.asList(lines));

        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static String[][] newRow (String[][] mArray, String[] row) {  //Nowa tablcica
        mArray = Arrays.copyOf(mArray, mArray.length +1);
        mArray[mArray.length-1] = row;
        return mArray;

    }

}
