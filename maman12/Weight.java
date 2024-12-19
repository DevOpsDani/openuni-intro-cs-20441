/**
 * Matala 12 - Class to create a Weight object
 * @author Daniel BenShushan
 */

public class Weight {
    int _kilos;
    int _grams;

    /**
     * Constructs a Weight object with the specified kilograms and grams.
     * If invalid values are provided, defaults to 1 kilogram and 0 grams.
     * 
     * @param kilos the number of kilograms (must be non-negative)
     * @param grams the number of grams (must be between 0 and 999)
     */
    public Weight(int kilos, int grams) {
        if (kilos < 0) {
            this._kilos = 1;
            this._grams = 0;
        } else if (grams < 0 || grams > 999) {
            this._kilos = kilos;
            this._grams = 0;
        } else {
            this._kilos = kilos;
            this._grams = grams;
        }
    }

    /**
     * Copy constructor that creates a new Weight object identical to the given one.
     * 
     * @param other the Weight object to copy
     */
    public Weight(Weight other) {
        this._kilos = other._kilos;
        this._grams = other._grams;
    }

    /**
     * Constructs a Weight object based on the total number of grams.
     * 
     * @param totalGrams the total weight in grams (must be non-negative)
     */
    public Weight(int totalGrams) {
        if(totalGrams < 0) {
            this._kilos = 1;
            this._grams = 0;
            return;
        }
        
        this._kilos = totalGrams / 1000;
        this._grams = totalGrams % 1000;
    }


    /**
     * Checks if this Weight object is equal to another Weight object.
     * 
     * @param other the other Weight object to compare
     * @return true if both weights are equal, false otherwise
     */
    public boolean equals (Weight other) {
        int sumOtherWeight = other._kilos * 1000 + other._grams;
        int sumThisWegiht = this._kilos * 1000 + this._grams;
        
        return sumOtherWeight == sumThisWegiht;
            
    }


    /**
     * Checks if this Weight object is lighter than another Weight object.
     * 
     * @param other the other Weight object to compare
     * @return true if this weight is lighter, false otherwise
     */
    public boolean lighter (Weight other)  {
        int sumOtherWeight = other._kilos * 1000 + other._grams;
        int sumThisWegiht = this._kilos * 1000 + this._grams;

        return sumThisWegiht < sumOtherWeight;
    }

    /**
     * Checks if this Weight object is heavier than another Weight object.
     * 
     * @param other the other Weight object to compare
     * @return true if this weight is heavier, false otherwise
     */

    public boolean heavier (Weight other) {
        if(!lighter(other))
            return true;
        return false;
    }


    /**
     * Returns the number of kilograms in this Weight object.
     * 
     * @return the kilograms of this Weight
     */
    public int getKilos(){
        return(this._kilos);
    }

    /**
     * Returns the number of grams in this Weight object.
     * 
     * @return the grams component of this Weight
     */

    public int getGrams(){
        return(this._grams);
    }

    /**
     * Returns a string representation of the Weight in the format kg.grams.
     * @return a string representation of the weight
     */

    public String toString() {
        int kilos = this._kilos;
        int grams = this._grams;

        if (grams == 0) {
            return kilos + "." + 0;
        } else if (grams % 10 == 0 && grams < 100) {
            int newGrams = grams / 10;
            return kilos + ".0" + newGrams; 
        } else if (grams <= 10) {
            return kilos + ".00" + grams;
        } else if (grams < 100) {
            return kilos + ".0" + grams; 
        }
        int newGrams = grams / 100;
        return kilos + "." + newGrams;
        
        

    }
    
    /**
     * Adds the specified number of grams to this Weight object and returns a new Weight.
     * 
     * @param grams the number of grams to add
     * @return a new Weight object representing the updated weight
     */

    public Weight add (int grams){
        int sumWegiht = this._kilos * 1000 + this._grams;
        int sumAddGrams = sumWegiht + grams;

        if (sumAddGrams < 1000) {
            return(new Weight(this._kilos, this._grams));
        }

        int newKilos = sumAddGrams / 1000;
        int newGrams = sumAddGrams % 1000;
        return(new Weight(newKilos, newGrams));
    }

}
