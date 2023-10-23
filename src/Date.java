/**
 * This class creates a date object that can be added to the Todoitem class. User will create
 * a date that they want to add to their TodoList, but in this class there are static methods
 * to convert strings to date objects, and a valid date method to confirm if it is valid or not
 * in which exceptions will be thrown and then caught in main
 * @author zaknilsen
 *
 */

public class Date implements Comparable<Date> {
    //grab basic date and change a few things
    
    // instance variables
    /** the month for this date */
    private int month;
    private int day;
    private int year;
    
    // constructors
    
    //can make valid date, and leap year methods
   
    //check validity of month day and year
    public Date(int month, int day, int year) {
        int[] dateCheck;
        
        this.month = month;
        this.day = day;
        this.year = year;
        
        dateCheck = new int[3];
        try {
            dateCheck[0] = month;
            dateCheck[1] = day;
            dateCheck[2] = year;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        
        
        
        if (isValidDate(dateCheck) == false) {
            throw new IllegalArgumentException();
        }
        
    }
    
    // methods
    
    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;    
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean isValidDate(int[] date) {
        if (date[0] > 12 || date[0] <= 0 || date[1] <= 0) {
            return false;
        }
        int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(date[2]) == true) {
            months[2] = 29;
            //if day > months at index month or day < 0
        } 
        
        if (date[1] > months[date[0]]) {
            return false;
        } else if (date[1] > months[date[0]] || date[1] < 0 || date[0] <= 0) {
            return false;
        } else if (date.length != 3) {
            return false;
        } else {
            return true;
        }
    }

    public static Date fromYYYYMMDDString(String date) {
        int thisMonth;
        int thisDay;
        int thisYear;
        
        try {
            thisYear = Integer.parseInt(date.substring(0, 4));
            thisMonth = Integer.parseInt(date.substring(4, 6));
            thisDay = Integer.parseInt(date.substring(6, 8));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException();
        } catch(IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        return new Date(thisMonth, thisDay, thisYear);
    }
    
    public static Date fromYYYYMMDDDashString(String date) {
        String[] parts;
        int thisMonth;
        int thisDay;
        int thisYear;
        parts = date.split("-");
        
        try {
            if (parts.length != 3) {
                throw new IllegalArgumentException();
            }
            
            if (parts[0].length() != 4) {
                throw new IllegalArgumentException();
            }
            
            if (parts[1].length() != 2) {
                throw new IllegalArgumentException();
            }
            
            if (parts[2].length() != 2) {
                throw new IllegalArgumentException();
            }
            thisMonth = Integer.parseInt(parts[1]);
            thisDay = Integer.parseInt(parts[2]);
            thisYear = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        
        return new Date(thisMonth, thisDay, thisYear);
    }
    
    public int getAsYYYYMMDD(String date) {
        int finalDate = 0;
        String holder;
        String[] parts;
        date = date.replaceAll("/", "-");
        parts = date.split("-");
        try {
            if (parts.length == 1) {
                finalDate = Integer.parseInt(date);
            } else {
                holder = parts[2] + parts[0] + parts[1];
                finalDate = Integer.parseInt(holder);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        return finalDate;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        }
        
        Date otherDate = (Date) other;
        if (this.month == otherDate.month && this.day == otherDate.day && otherDate.year == this.year) {
            return true;
        }

        return false;
    }
    
    @Override
    public int compareTo(Date other) {
        //if something goes wrong can use getAsYYYYMMDD method and compare
        if (other.year > this.year) {
            return 1;
        } else if ((other.month < this.month || other.year < this.year)) {
            return -1;
        } else if (other.month <= this.month && other.day < this.day) {
            return -1;
        } else if (other.month >= this.month && other.day > this.day) {
            return 1;
        }
        return 0;
    }
    
    
    
    @Override
    public String toString() {
        String finalString = "";
        String thisMonth = "";
        String thisDay = "";
       
        if (month < 10) {
            thisMonth = thisMonth + "0" + month;
        } else {
            thisMonth = thisMonth + month;
        }
        if (day < 10) {
            thisDay = thisDay + "0" + day;
        } else {
            thisDay = thisDay + day;
        }
        
        finalString = finalString + thisMonth + "/" + thisDay + "/" + year;
        return finalString;
    }

    
    



}
