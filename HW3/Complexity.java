/**
 * This Complexity class allow user to create a Complexity object
 * 
 * @author Ka_Long_Ngai 09/22/2022
 * 
 * Time used: ~20 mins
*/

public class Complexity {

    private int nPower, logPower; //nPower count for the Complexity and logPower count for the Complexity.

    
    /**
     * Return an empty instance of Complexity.
     * Empty as in nPower and logPower both being 0.
     */
    public Complexity() {
        this.nPower = this.logPower = 0;
    }

    /**
     * Return an instance of Complexity. (Assumed that user put numbers that are above 0)
     * @param nPower
     *      The nPower of the Complexity.
     * @param logPower
     *      The logPower of the Complexity.
     */
    public Complexity(int nPower, int logPower) {
        this.nPower = nPower;
        this.logPower = logPower;
    }

    /**
     * This method return an int value of nPower within a Complexity object.
     * @return
     *      The nPower of the Complexity object.
     */
    public int getnPower() {
        return nPower;
    }

    /**
     * This method alter the int value of nPower within a Complexity objet.
     * @param nPower
     *      The nPower you want the Complexity object to be change to.
     */
    public void setnPower(int nPower) {
        this.nPower = nPower;
    }

    /**
     * This method return an int value of logPower within a Complexity object.
     * @return
     *      The logPower of the Complexity object.
     */
    public int getLogPower() {
        return logPower;
    }

    /**
     * This method alter the int value of logPower within a Complexity objet.
     * @param logPower
     *      The logPower you want the Complexity object to be change to.
     */
    public void setLogPower(int logPower) {
        this.logPower = logPower;
    }

    /**
     * This method compare the this Complexity object to another Complexity object. It will return true if the complexity of this is
     * greater then item, false if it is equal or less then the complexity of the item.
     * @param item
     *      The item that will be compare to this.
     * @return
     *      True or false value that indicates which complexity is higher then one another.
     *      <p>True: the nthPower or the logPower of this object is greater then item. Indicating a higher complexity.</p>
     *      <p>False: the nthPower or the logPower of this object is equal or less then item. Indicating an equivelent complexity or lower complexity</p>
     *      
     */
    public boolean compareComplexity(Complexity item) {
        if(this.nPower > item.getnPower()) {
            return true;
        } else if(this.logPower > item.getLogPower() && this.nPower == item.getnPower()) {
            return true;
        }
        return false;
    }

    /**
     * This method will add 2 complexity together, basically merging the number of nPower and logPower of 'this' reference 
     * to the param 'item' nPower and logPower.
     * @param item
     *      The 'item' complexity object that you want to be add to 'this' references.
     * @return
     *      A new Complexity object with the addition of the 2 complexity objects of 'this' and 'item'.
     */
    public Complexity complexityAddition(Complexity item) {
        int totalNPower = this.nPower + item.getnPower();
        int totalLogNPower = this.logPower + item.getLogPower();
        return new Complexity(totalNPower, totalLogNPower);
    }

    
    /**
     * An override toString method to print the Complexity object in a legible format.
     * <p>Ex: "O(1)", "O(n)", "O(log(n))"</p>
     * @return
     *      A String that have all the information of Complexity object in a legible manner.
     */
    @Override
    public String toString() {
        String nPowerStr, logPowerStr;
        boolean nPowerEmpty = false;
        boolean logPowerEmpty = false;
        switch(nPower) {
            case 0:
                nPowerStr = "";
                nPowerEmpty = true;
                break;
            case 1:
                nPowerStr = "n";
                break;
            default:
                nPowerStr = "n^" + nPower;
                break;
        }
        switch(logPower) {
            case 0:
                logPowerStr = "";
                logPowerEmpty = true;
                break;
            case 1:
                logPowerStr = "log(n)";
                break;
            default:
                logPowerStr = "log(n)^" + logPower;
                break;
        }
        if(!(logPowerEmpty && nPowerEmpty)) {
            if(nPowerEmpty) {
                return "O(" + logPowerStr + ")";
            } else if(logPowerEmpty) {
                return "O(" + nPowerStr + ")";
            }
            return "O(" + nPowerStr + " * " + logPowerStr + ")";
        }
        return "O(1)";
    }

    /*testCode
    public static void main(String[] args) {
        Complexity test1 = new Complexity(0,0);
        Complexity test2 = new Complexity(0,1);
        Complexity test3 = new Complexity(1,0);
        Complexity test4 = new Complexity(1,1);
        Complexity test5 = new Complexity(2,2);
        System.out.println("\n" + test1 + "\n" + test2 + "\n" + test3 + "\n" + test4 + "\n" + test5 + "\n");
    }*/
}
