/**
 * This ProductLoad class allow user to create a ProductLoad object
 * 
 * @author Ka_Long_Ngai 09/09/2022
 * 
 * Time used: ~30 mins
*/

public class ProductLoad {
    private String name; //name of the product.
    private double weight, value; // the weight of the item in tons, the value of the item in dollars.
    private boolean isDangerous; // is the product dangerous.

    /**
     * Return an instance Of ProductLoad
     * 
     * @param name
     *      The name of the Product.
     * @param weight
     *      The weight of the Product.
     * @param value
     *      The value of the Product.
     * @param isDangerous
     *      True if product is dangers, false if it is not dangerous.
     * Precondition:
     *      Weight, value must be the correct type of value and cannot be negative.
     * @exception IllegalArgumentException
     *      Appears when the precondition is violated.
     */
    public ProductLoad(String name, double weight, double value, boolean isDangerous) throws IllegalArgumentException{
        this.name = name;
        this.setWeight(weight);
        this.value = value;
        this.isDangerous = isDangerous;
    }

    /**
     * This method return the String value of name within a ProductLoad object.
     * @return
     *      The name of the object.
     */
    public String getName() {
        return name;
    }

    /**
     * This method alter the String value of name within a ProductLoad object.
     * @param name
     *      The name your want the object to be change to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method return the double value of weight within a ProductLoad object.
     * @return
     *      The weight in tons of the object.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This method alter the double value of weight within a ProductLoad object.
     * @param weight
     *      The weight you want to object to be change to.
     * @throws IllegalArgumentException
     *      When the weight is negative aka not within the valid range.
     */
    public void setWeight(double weight) throws IllegalArgumentException {
        if(weight >= 0 ) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Your input for weight is not valid");
        }
    }

    /**
     * This method return the double value of value within a ProductLoad object.
     * @return
     *      The value of the object.
     */
    public double getValue() {
        return value;
    }

    /**
     * This method alter the double value of value within a ProductLoad object.
     * @param value
     *      The value you want to object to be change to. 
     * @throws IllegalArgumentException
     *      When the value is negative aka not within the valid range.
     */
    public void setValue(double value) throws IllegalArgumentException {
        if(value >= 0) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Your input for value is not valid");
        }
    }

    /**
     * Determine whether a product is dangerous or not base on the isDangerous boolean.
     * @return
     *      True : product is dangerous.
     *      false : product is not dangerous.
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Alter the isDangerous boolean to true or false depends base on scenario. 
     * @param isDangerous
     *      The status of danger you want to object to be change to.
     */
    public void setDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    /**
     * This method return a String that is in a line format instead of a list format, can be added on with further toString method,
     * basically another toString method that is in another format.
     * @return
     *      All the attribute in a line format instead of a list format.
     *      <p>Ex:" Corn, 10.0, 10000.00, NO"<p/>
     */
    public String toStringProductHelper() {
        String returnString = "";
        String tempDangerous = "NO";
        if(this.isDangerous() == true) {
            tempDangerous = "YES";
        }
        String tempValue = String.format("%,.2f", this.getValue());
        String tempWeight = String.format("%,.1f", this.getWeight());
        returnString+=String.format("%-14s%-10s%-18s%-3s",this.getName(), tempWeight, tempValue, tempDangerous);
        return returnString;
    }

    /**
     * An override toString Method to print the information of the product in a clean list format.
     * @return
     *      A String that have the value within this ProductLoad object in a legible manner.
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString+=String.format("%-4s%-10s%-14s%-12s%-9s", "", "Name", "Weight (t)", "Value ($)", "Dangerous");
        returnString+="\n===================================================\n     ";
        returnString+=toStringProductHelper();
        return returnString;
    }
    /* Test code
    public static void main(String[] args) {
        ProductLoad testTrainCarProduct1 = new ProductLoad("Corn", 100, 15440, false);
        System.out.println(testTrainCarProduct1);
    }
    */
}
