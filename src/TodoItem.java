
/**
 * This class are items that will be added to the Todolist in which user will decide
 * what they want to add to this item when it is added, but exceptions should be caught
 * if user puts in wrong input
 * @author zaknilsen
 *
 */
public class TodoItem {
    /** Date object that user will add in TodoItem */
    private Date date;
    /** String description that will user will add in their TodoItem */
    private String description;
    /** Determines if Importance is high, medium or low, based off users choice */
    private Importance importanceLevel;
    
    public TodoItem(Date date, String description, Importance level) {
        this.date = date;
        this.description = description;
        importanceLevel = level;
           
    }
    
    /**
     * Class takes in line and separates the objects that are separated by commas and uses
     * this to create a new TodoItem object
     * @param line this is line that is read from file
     * @return TodoItem is item that will be added to TodoList
     */
    // detect errors here
    public static TodoItem buildFromCSV(String line) {
        Date date;
        Importance level;
        String description;
        String[] holder;
        
        holder = line.split(",");
        if (holder.length != 3) {
            throw new IllegalArgumentException();
        }
        date = Date.fromYYYYMMDDString(holder[0]);
        description = holder[1];
        if (holder[2].equals("HIGH")) {
            level = Importance.HIGH;
        } else if (holder[2].equals("MEDIUM")) {
            level = Importance.MEDIUM;
        } else {
            level = Importance.LOW;
        }
        return new TodoItem(date, description, level);
    }
    
    
    /**
     * Will take TodoItem and separate each object with commas
     * @return String that returns TodoItem back into line separated by commas
     */
    // make sure to write correct date format
    public String getAsCSV() {
        String dateHolder;
        int date;
        String description;
        Importance importance;
        String stringAsCSV = "";
        
        dateHolder = getDate().toString();
        date = getDate().getAsYYYYMMDD(dateHolder);
        description = getDescription();
        importance = getImportanceLevel();
        
        stringAsCSV = date + "," + description + "," + importance;
        
        return stringAsCSV;
    }
    
    /**
     * Is getter that returns instance variable date
     * @return Date returns instance variable date
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Is getter that returns instance variable importanceLevel
     * @return Importance returns instance variable importanceLevel
     */
    public Importance getImportanceLevel() {
        return importanceLevel;
    }
    
    /**
     * Is getter that returns instance variable description
     * @return String returns instance variable description
     */
    public String getDescription() {
        return description;
    }


    
    
    
    
    
    
    
    
}
