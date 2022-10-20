/**
 * This TrainCar class allow user to create a TrainCar object
 * 
 * @author Ka_Long_Ngai 09/09/2022
 * 
 * Time used: ~25 mins
*/

public class TrainCar {
    private double carLength, carWeight; //The length and weight of the traincar object.
    private ProductLoad load; //The load on the traincar.

    /**
     * <p>The constructor for TrainCar object, require the length of the trainCar and weight of the trainCar.
     * The load of the TrainCar will be null when initialised.<p/>
     * <p>Precondition: carLenght and carWeight must be greater then 0.</p>
     * <p>Postcondition: a TrainCar object initialised.<p/>
     * @param carLength
     *      The length of the trainCar you want the trainCar object to have.
     * @param carWeight
     *      The weight of the trainCar you want the trainCar object to have.
     * @throws IllegalArgumentException
     *      When the carLength or carWeight is below or equal to 0.
     */
    public TrainCar(double carLength, double carWeight) throws IllegalArgumentException{
        if(carLength >= 0 && carWeight >= 0) {
            this.carLength = carLength;
            this.carWeight = carWeight;
            this.load = null;
        } else {
            throw new IllegalArgumentException("Your input value for this TrainCar object is not valid.");
        }
    }

    /**
     * This method return the double value of carLength within a TrainCar object.
     * @return
     *      The length of the traincar on its own.
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * This method return the double value of carWeight within a TrainCar object.
     * @return
     *      The weight of the traincar on its own.
     */
    public double getCarWeight() {
        return carWeight;
    }

    /**
     * This method return the product loaded on the trainCar object.
     * @return
     *      what product is loaded on the traincar.
     *      <p>null == empty.<p/>
     */
    public ProductLoad getProductLoad() {
        return load;
    }

    /**
     * This method alter the productLoad value of load within a TrainCar object.
     * @param load
     *      The product you want the traincar to hold.
     */
    public void setProductLoad(ProductLoad load) {
        this.load = load;
    }

    /**
     * Determine whether the trainCar object has an empty load or has a load already.
     * @return
     *      True: The traincar is empty, False: The traincar is not empty.
     */
    public boolean isEmpty() {
        return load == null || load.getName().equals("Empty");
    }

    /**
     * <p>Assiting toString method as we cannot print null in the product section.</p>
     * It will createa new ProductLoad with the name Empty, weight and value 0 and not dangerous. 
     * This object will be inserted into the null spot. Making the toString() method easier to understand.
     */
    private void emptyLoadString() {
        ProductLoad load = new ProductLoad("Empty", 0.0, 0.00, false);
        if(this.isEmpty()) {
            this.setProductLoad(load);
        }
    }

    /**
     * This method return a String that is in a line format instead of a list format, can be added on with further toString method,
     * basically another toString method that is in another format.
     * @return
     *      All the attribute in a line format instead of a list format including the product toStringHelper method.
     *      <p>Ex:"15.0,10.0 Corn, 10.0, 10000.00, NO"<p/>
     */
    public String toStringTrainCarHelper() {
        this.emptyLoadString();
        String returnString = "";
        String tempCarLength = String.format("%,.1f", this.getCarLength());
        String tempCarWeight = String.format("%,.1f", this.getCarWeight());
        returnString+=String.format("%-9s%-14s%-7s%-4s%-20s","", tempCarLength, tempCarWeight,"|", this.getProductLoad().toStringProductHelper());
        return returnString;
    }

    /**
     * An override toString method to print the information of the product in a clean list format.
     * @return
     *      A list format of all the attribute within this specific train.
     */
    @Override
    public String toString() {
        this.emptyLoadString();
        String returnString = "";
        returnString+=String.format("%-4s%-14s%-12s%-3s%-10s%-14s%-12s%-9s","", "Length (m)", "Weight (t)", "|", "Name", "Weight (t)", "Value ($)", "Dangerous");
        returnString+="\n   ===========================+===================================================\n";
        returnString+=toStringTrainCarHelper();        
        return returnString;
    }
    

    /*Test Code
    public static void main(String[] args) {
        ProductLoad test = new ProductLoad("Empty", 0.0, 0.00, false);
        TrainCar test2 = new TrainCar(15.0, 10.0);
        test2.setProductLoad(test);
        System.out.println(test2);
    }*/
}
