/**
 * Matala 12 - Class to create a Date object
 * @author Daniel BenShushan
 */

public class Date {
    private int _day; 
    private int _month; 
    private int _year;

    private final int january = 1;
    private final int february = 2;
    private final int march = 3;
    private final int april = 4;
    private final int may = 5;
    private final int june = 6;
    private final int july = 7;
    private final int august = 8;
    private final int september = 9;
    private final int october = 10;
    private final int november = 11;
    private final int december = 12;

    //constructors:
    /**
    * Creates a new Date object
    * @param _day the day in the month(1-31)
    * @param _month the month in the year
    * @param _year the year (in 4 digits)
    * Validates that the date is valid 
    */

    public Date(int day, int month, int year) {
        this(); 
        if (validateDate(day, month, year)) {
            this._day = day;
            this._month = month;
            this._year = year;
        }
    }

    /** 
     * Sets the Date to 1.1.2024 
     */
    public Date() {
        this._day = 1;
        this._month = january;
        this._year = 2024;
    }

    /**
    * Copy Constructor
    * @param Date to be copied
    */
    public Date(Date other){
        this._day = other._day;
        this._month = other._month;
        this._year = other._year;
    }


    /* 
    Getters 
    */

    /** Gets the Day */
    public int getDay() {
        return(_day);
    }

    /** Gets the month */
    public int getMonth() {
        return(_month);
    }

    /** Gets the year */
    public int getYear() {
        return(_year);
    }

    /* Setters With Validation 
     * if the value is incurrect - object will not be changed 
     */


    /**  Sets the day 
     *  @param dayToSet the value to be set
     */
    public void setDay(int dayToSet) {
        if (dayToSet <= 0 || dayToSet > 31)
            return;

        if (_month == april || _month == june || _month == september || _month == november) {
            if (dayToSet > 30)
                return;
        } else if (_month == february) {
            if ((isLeapYear(_year) && dayToSet > 29) || (!isLeapYear(_year) && dayToSet > 28)) {
                return;
            }
        }
        this._day = dayToSet;
    }

    /** Set the month
     * @param monthToSet the value to be set
     */
    public void setMonth(int monthToSet) {
        if (monthToSet <= 0 || monthToSet > 12) {
            return; 
        }
       
        if (monthToSet == april || monthToSet == june || monthToSet == september || monthToSet == november) { 
            if (_day > 30) {
                return; 
            }
        } else if (monthToSet == february) {
            if ((isLeapYear(_year) && _day > 29) || (!isLeapYear(_year) && _day > 28)) {
                return; 
            }
        }
        this._month = monthToSet;
    }

    /** Sets the year
     * @param yearToSet the value to be set
     */
    public void setYear(int yearToSet) {
        if (yearToSet < 1000 || yearToSet > 9999) {
            return;
        }

        if (_month == february) {
            if ((isLeapYear(yearToSet) && _day > 29) || (!isLeapYear(yearToSet) && _day > 28)) {
                return; 
            }
        }
        this._year = yearToSet;
    }

    /** 
     * Compares between Date Objects to validate if equals 
     * @param other another Date object
     * */
    public boolean equals(Date other) {
        if (other._day == _day && other._month == _month && other._year == _year) {
            return true;
        }
        return false;
    }

    /**
    * Checks if this date comes before a given date
    * @param other another Date object
    * @return true if this date comes before other
    */
    public boolean before(Date other) {
        if (_year < other._year)
            return true;
        if (_year == other._year) {
            if (_month < other._month)
                return true;
            if (_month == other._month && _day < other._day)
                return true;
        }
        return false;
    }

    /**
    * Compares between Date Objects to validate if given Date is after
    * @param other another Date object
    * @return true if this date comes before other
    */
    public boolean after(Date other) {
        if (before(other))
            return false;
        return true;   
    }

    /**
     * Returns Date as follows - dd/mm/yyyy 
     * @return dd/mm/yyyy 
     * */
    public String toString() {
        return (_day < 10 ? "0" : "") + _day + "/" +
               (_month < 10 ? "0" : "") + _month + "/" +_year;
    }
    /**
     * Difference method accepts as a parameter a certain date, 
     * and calculates and returns the difference 
     * in days between the date represented by 
     * the object on which the method is invoked
     * @return returns the difference in days
     */
    public int difference (Date other) {
        int thisDatevalue = calculateDate(_day, _month, _year);
        int otherDateValue = calculateDate(other._day, other._month, other._year);
        return Math.abs(thisDatevalue - otherDateValue);
    }

    /**
     *  Returns a date of one day after the date represented by the object 
     *  The method was invoked on.
     *  @return Tomorrow
     * */
    public Date tomorrow() {
        int nextDay = _day;
        int nextMonth = _month;
        int nextYear = _year;

        nextDay++;

        if ((_month == april || _month == june || _month == september || _month == november) && _day == 30) {
            nextDay = 1;
            nextMonth++;
        }

        if ((_month == january || _month == march || _month == may || _month == july || _month == august || _month == october || _month == december) && _day == 31) {
            nextDay = 1;
            if (_month == december) {
                nextMonth = 1;
                nextYear++;
            } else {
                nextMonth++;
            }
        }

        if (_month == february) {
            if (isLeapYear(_year)) {
                if (_day == 29) {
                    nextDay = 1;
                    nextMonth++;
                }
            } else if (_day == 28) {
                nextDay = 1;
                nextMonth++;
            }
        }
         return (new Date(nextDay, nextMonth, nextYear));
    }

    /* Private methods */
    
    private int calculateDate ( int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }

    /* Checks if date is valid */
    private boolean validateDate(int day, int month, int year) {
        if (day <= 0 || day > 31 || month <= 0 || month > 12 || year < 1000 || year > 9999) {
            return false;
        }

        // Determine the number of days in the given month
        int daysInMonth = 31; 
        if (month == april || month == june || month == september || month == november) {
            daysInMonth = 30; 
        } else if (month == february) { 
            daysInMonth = isLeapYear(year) ? 29 : 28;
        }

        if (day > daysInMonth) {
            return false; 
        }

        return true;
    }

    /* Calculates if y is leap year */
    private boolean isLeapYear (int y) {
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    }
}
