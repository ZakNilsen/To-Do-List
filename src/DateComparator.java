import java.util.Comparator;
/**
 * This class compares dates and determines which dates are before or after others so that
 * when user wants their dates ordered, they are in the proper order
 * @author zaknilsen
 *
 */
public class DateComparator implements Comparator<TodoItem> {
    
    /**
     * This method compares date objects and determines which one was before, and which one was after
     * @param date1 is date object that will be compared to other date object
     * @param date2 is date object that will be compared to other date object
     * @return int returns 1 if date1 > date2, -1 if opposite and 0 if they are the same
     */
    @Override
    public int compare(TodoItem date1, TodoItem date2) {
        int compare1;
        int compare2;
        compare2 = date1.getDate().compareTo(date2.getDate());
        compare1 = date2.getDate().compareTo(date1.getDate());
      
        if (compare1 > compare2) {
            return 1;
        } else if (compare1 < compare2) {
            return -1;
        }
        return 0;
    }

}
