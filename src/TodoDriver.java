import java.util.Scanner;
/**
 * Driver method that allows user to interact with file they have already created, or to 
 * create a new TodoList where they can add date, importance level and descriptions until
 * they decide to save and exit. Any exceptions that are thrown should be caught so that
 * the program does not crash and user can still interact with program
 * @author zaknilsen
 *
 */
public class TodoDriver {

    public static void main(String[] args) {
     
        Scanner keyboard;
        TodoList toDo;
        String username;
        String choice;
        String option;
        
        keyboard = new Scanner(System.in);
        System.out.println("Welcome to the TodoList application.");
        System.out.println("Enter your username: ");
        username = keyboard.nextLine();
        toDo = new TodoList(username);
        try {
            toDo = TodoList.buildFromUsername(username);
        } catch (IllegalArgumentException e) {
            System.out.println("An error occured trying to read the file for your username.");
            System.out.println("Create new Todo List? (Y/N) ");
            choice = keyboard.nextLine();
            if (choice.equals("Y")) {
                toDo = new TodoList(username);
            } else {
                return;
            }
                
        }
        
        while (true) {
            System.out.println("Options: ");
            System.out.println("1)  Show tasks sorted by date");
            System.out.println("2)  Show tasks sorted by importance");
            System.out.println("3)  Add new task");
            System.out.println("4)  Save and exit");
            System.out.println("Your choice: ");
            option = keyboard.nextLine();
            if (option.equals("1")) {
                try {
                    System.out.println(toDo.getAsDateSortedString());
                } catch (IllegalArgumentException e) {
                    System.out.println("Corrupted item");
                    return;
                }
        
            } else if (option.equals("2")) {
                System.out.println(toDo.getAsImportanceSortedString());
        
            } else if (option.equals("3")) {
                TodoItem item;
                String dateHolder = null;
                String importance;
                String description = null;
                Date date = null;
                Importance level = null;
                
                //date 
                try {
                    System.out.println("Enter a date (YYYY-MM-DD): ");
                    dateHolder = keyboard.nextLine();
                    date = Date.fromYYYYMMDDDashString(dateHolder);
                } catch (IllegalArgumentException e) {
                    System.out.println("Dates must be entered in YYYY-MM-DD format");
                    continue;
                }
                
                //importance
                System.out.println("Enter an importance (HIGH, MEDIUM, LOW): ");
                importance = keyboard.nextLine();
                if (importance.equals("HIGH")) {
                    level = Importance.HIGH;
                } else if (importance.equals("MEDIUM")) {
                    level = Importance.MEDIUM;
                } else if (importance.equals("LOW")) {
                    level = Importance.LOW;
                } else {
                    System.out.println("Bad importance choice");
                    continue;
                }
                
                //description
                try {
                    System.out.println("Enter a short description (no commas): ");
                    description = keyboard.nextLine();
                    if (description.contains(",")) {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("No commas allowed");
                    continue;
                }
                
                item = new TodoItem(date, description, level);
                toDo.addTask(item);
                
        
            } else if (option.equals("4")) {
                try {
                    toDo.finalize();
                } catch (IllegalArgumentException e) {
                    System.out.println("File could not write");
                }
                return;
            }
        } 

    }

}
