import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class is where all the reading, writing and sorting happens, in which will be sent
 * to driver so that user can interact with their Todolist or create a new one
 * @author zaknilsen
 *
 */
public class TodoList {
    /** ArrayList of users TodoItems */
    private ArrayList<TodoItem> theTasks;
    /** String that will be the users name */
    private String username;
    
    // for creating new TodoList use constructor
    public TodoList(String user) {
        theTasks = new ArrayList<TodoItem>();
        this.username = user;
    }
    
    /**
     * Static method that takes in users name and reads file, and if file cannot be read
     * exception is thrown to main
     * @param user is String user name that user will put in to find their TodoList or create new one
     * @return TodoList returns new TodoList where users TodoItems will be added under their file name
     */
    //if already existing TodoList use this method
    // file reading here, should throw illegal exception to driver
    public static TodoList buildFromUsername(String user) {
        List<String> list;
        TodoList existingList;
        existingList = new TodoList(user);
        
        try {
            list = Files.readAllLines(Paths.get(user + ".txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        
        for (String currentLine : list) {
            existingList.addTask(TodoItem.buildFromCSV(currentLine));
        }
        
        return existingList;
    }
    
    /**
     * This method adds items to the ArrayList
     * @param item TodoItem that will be added to TodoList
     */
    public void addTask(TodoItem item) {
        theTasks.add(item);
    }
    
    /**
     * Sorts the TodoItems based off date by using DateComparator class
     * @return String of TodoItems in easy to understand style for user
     */
    public String getAsDateSortedString() {
        DateComparator comparator = new DateComparator();
        String sorted = "";
        String getter;
        String[] holder;
        Date date;
        Collections.sort(theTasks, comparator);
        
        if (theTasks.size() == 0) {
            return "No tasks in list";
        }
        
        for (int i = 0; i < theTasks.size(); i++) {
            
            
            getter = theTasks.get(i).getAsCSV();
            holder = getter.split(",");
            try {
                date = Date.fromYYYYMMDDString(holder[0]);
                sorted = sorted + "*" + "\n" + "  " + "Date: " + date + "\n";
                sorted = sorted + "  " + "Importance:" + holder[2] + "\n";
                sorted = sorted + "  " + holder[1] + "\n" + "\n";
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException();
            }
            
        }
        return sorted;
    }
    
    /**
     * Sorts TodoItems based off Importance levels by using ImportanceComparator class
     * @return String of TodoItems in easy to understand style for user
     */
    public String getAsImportanceSortedString() {
        ImportanceComparator comparator = new ImportanceComparator();
        String sorted = "";
        String getter;
        String[] holder;
        Date date;
        Collections.sort(theTasks, comparator);
        
        if (theTasks.size() == 0) {
            return "No tasks in list";
        }
        
        for (int i = 0; i < theTasks.size(); i++) {
            
            getter = theTasks.get(i).getAsCSV();
            holder = getter.split(",");
            try {
                date = Date.fromYYYYMMDDString(holder[0]);
                sorted = sorted + "*" + "\n" + "  " + "Date: " + date + "\n";
                sorted = sorted + "  " + "Importance:" + holder[2] + "\n";
                sorted = sorted + "  " + holder[1] + "\n" + "\n";
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException();
            }
            
        }
        return sorted;
    }
    
    /**
     * This method writes the TodoItems to the users file
     */
    // only write to file when user exits, so use getAsCSV method, use single write
    public void finalize() {
        String toWrite = "";
        for (int i = 0; i < theTasks.size(); i++) {
            // will write TodoItem 's to file
            toWrite = toWrite + theTasks.get(i).getAsCSV() + "\n";
        }
        
        try {
            Files.write(Paths.get(username + ".txt"), toWrite.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        
        
    }

}
