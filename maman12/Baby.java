/**
 * Matala 12 - Class to create a Baby object
 * @author Daniel BenShushan
 */

public class Baby {
    String _firstName;
    String _lastName;
    String _id;
    Date _dateOfBirth;
    Weight _birthWeight;
    Weight _currentWeight;

   /**
     * Constructor to initialize a Baby object with the given details.
     * If the ID is not 9 characters long, sets a default value of "000000000".
     * @param fName Baby's first name
     * @param lName Baby's last name
     * @param id Baby's ID
     * @param day Day of birth
     * @param month Month of birth
     * @param year Year of birth
     * @param birthWeightInGrams Birth weight in grams
     */


    public Baby(String fName, String lName, String id,int day, int month, int year, int birthWeightInGrams) {
        this._firstName = fName;
        this._lastName = lName;
        this._dateOfBirth = new Date(day, month, year);
        this._birthWeight = new Weight(birthWeightInGrams);
        this._currentWeight = new Weight(this._birthWeight);

        if (id.length() != 9) {
            this._id = "000000000";
        } else {
            this._id = id;
        }
    }

    /**
     * Copy constructor to create a new Baby object from another Baby object.
     * @param other The Baby object to copy
     */

    public Baby(Baby other){
        this._firstName = other._firstName;
        this._lastName =other._lastName;
        this._dateOfBirth = other._dateOfBirth;
        this._birthWeight = other._birthWeight;
        this._currentWeight = other._currentWeight;
        this._id = other._id;
    }

    /**
     * Gets the baby's first name.
     * @return Baby's first name
     */

    public String getFirstName() {
        return this._firstName;
    }

    /**
     * Gets the baby's last name.
     * @return Baby's last name
     */
    public String getLastName() {
        return this._lastName;
    }

    /**
     * Gets the baby's ID.
     * @return Baby's ID
     */
    public String getId() {
        return this._id;
    } 

    /**
     * Gets the baby's date of birth.
     * @return Baby's date of birth
     */

    public Date getDateOfBirth() {
        return this._dateOfBirth;
    }

    /**
     * Gets the baby's current weight.
     * @return Baby's current weight
     */

    public Weight getBirthWeight() {
        return this._birthWeight;
    }


    /**
     * Gets the baby's current weight.
     * @return Baby's current weight
     */
    public Weight getCurrentWeight() {
        return this._currentWeight;
    }

    /**
     * Sets the baby's current weight using a given Weight object.
     * Ensures weight is calculated in grams.
     * @param weightToSet The new weight to set
     */
    public void setCurrentWeight(Weight weightToSet) {
        int totalGramsToSet = weightToSet._kilos * 1000 + weightToSet._grams;
        this._currentWeight = new Weight(totalGramsToSet);
    }

    /**
     * Generates a string representation of the Baby object.
     * @return A formatted string with the baby's details
     */
    public String toString() {
        return "Name: " + this._firstName + " " + this._lastName + "\n" +
               "Id: " + this._id + "\n" +
               "Date of Birth: " + this._dateOfBirth + "\n" +
               "Birth Weight: " + this._birthWeight + "\n" +
               "Current Weight: " + this._currentWeight;
    }

    /**
     * Compares the current Baby object with another for equality.
     * Two babies are considered equal if they have the same first name,
     * date of birth, and ID.
     * @param other The other Baby object to compare
     * @return True if the babies are equal, false otherwise
     */

    public boolean equals (Baby other) {
        if (this.getFirstName() == other.getFirstName() && 
            this._dateOfBirth.equals(other._dateOfBirth) && 
            this.getId() == other.getId())
            return true;
        return false;
    }

    /**
     * Checks if the current baby and another are twins.
     * Twins share the same last name, different IDs, and similar date of birth.
     * @param other The other Baby object to check
     * @return True if they are twins, false otherwise
     */
    public boolean areTwins (Baby other) {
        if (this._lastName == other._lastName && this._id != other._id) {
            if (this._dateOfBirth.equals(other._dateOfBirth) || (this._dateOfBirth.tomorrow()).equals(other._dateOfBirth)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares the weights of the current baby and another.
     * @param other The other Baby object to compare weight
     * @return True if the current baby is heavier, false otherwise
     */
    public boolean heavier (Baby other) {
        if (this._currentWeight.heavier(other._currentWeight))
            return true;
        return false;
    }

    /**
     * Updates the current weight by adding a specified number of grams.
     * Ensures the total weight is at least 1000 grams before updating.
     * @param grams The number of grams to add
     */
    public void updateCurrentWeight (int grams) {
        Weight testWeight = this._currentWeight.add(grams);
        int totalGrams = testWeight._kilos * 1000 + testWeight._grams;
        if (totalGrams < 1000) 
            return;

        this._currentWeight = this._currentWeight.add(grams);
    }

    /**
     * Compares the age of the current baby with another.
     * @param other The other Baby object to compare age
     * @return True if the current baby is older, false otherwise
     */
    public boolean older (Baby other) {
        if (this._dateOfBirth.before(other._dateOfBirth))
            return true;
        return false;
    }

    /**
     * Checks if the baby's weight is within the expected range based on age in days.
     * Prints the minimum expected weight to the console.
     * @param numOfDays Age in days
     * @return 1 if the number of days is out of range,
     *         2 if the current weight is below the expected range,
     *         3 if the current weight is in the valid range
     */

    public int isWeightInValidRange(int numOfDays) {
        if (numOfDays < 1 || numOfDays > 365) {
            return 1;
        }
    
        int currentWeightInGrams = this._currentWeight._kilos * 1000 + this._currentWeight._grams;
        int birthWeightInGrams = this._birthWeight._kilos * 1000 + this._birthWeight._grams;
        double minExpectedWeight = birthWeightInGrams;
    
        if (numOfDays <= 7) {
            minExpectedWeight = (birthWeightInGrams - (birthWeightInGrams * (0.10 / 7 * numOfDays)));
        } else if (numOfDays >= 8 && numOfDays <= 60) {
            minExpectedWeight = (birthWeightInGrams - (birthWeightInGrams * 0.10)) + (30 * (numOfDays - 7));
        } else if (numOfDays <= 120) {
            minExpectedWeight = (int)(birthWeightInGrams - (birthWeightInGrams * 0.10)) + (30 * 53) + (25 * (numOfDays - 60));
        } else if (numOfDays <= 240) {
            minExpectedWeight = (int)(birthWeightInGrams - (birthWeightInGrams * 0.10)) + (30 * 53) + (25 * 60) + (16 * (numOfDays - 120));
        } else {
            minExpectedWeight = (int)(birthWeightInGrams - (birthWeightInGrams * 0.10)) + (30 * 53) + (25 * 60) + (16 * 120) + (8 * (numOfDays - 240));
        }
        if (currentWeightInGrams < minExpectedWeight) {
            System.out.println(minExpectedWeight);
            return 2;
        }
        System.out.println(minExpectedWeight);
        return 3;
    }
    
}
