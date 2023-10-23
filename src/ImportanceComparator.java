import java.util.Comparator;
/**
 * This class compares two Importance objects and determines which one is higher and lower
 * @author zaknilsen
 *
 */
public class ImportanceComparator implements Comparator<TodoItem> {
    
    /**
     * Method compares two importance levels, and determines which one is higher
     * @param level1 importance object in TodoItem that will be compared to other importance object
     * @param level2 importance object in TodoItem that will be compared to other importance object
     * @return int returns 1 if level1 > level2, -1 if opposite, 0 if same
     */
    @Override
    public int compare(TodoItem level1, TodoItem level2) {
        int compare1;
        int compare2;
        compare1 = level1.getImportanceLevel().compareTo(level2.getImportanceLevel());
        compare2 = level2.getImportanceLevel().compareTo(level1.getImportanceLevel());
    
        if (compare1 > compare2) {
            return 1;
        } else if (compare1 < compare2) {
            return -1;
        }
        return 0;
    }

}
