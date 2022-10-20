/**
 * This TrainCarNode class allow user to create a TrainCarNode object
 * 
 * @author Ka_Long_Ngai 09/10/2022
 * 
 * Time used: ~2 hours
*/

public class TrainCarNode {
    
    private TrainCarNode prev, next; //The previous and next node for this TrainCar object.
    private TrainCar car; //The data of the TrainCar in this TrainCarNode.

    /**
     * Initialised a empty TrainNode Object, with the link to the previous and next node also be empty as well.
     * <p>empty == null = true</p>
     */
    public TrainCarNode() {
        prev = null;
        next = null;
        car = null;
    }

    /**
     * Initialised a empty Links TrainNode Object with TrainCar data stored, with the link to the previous and next node also be empty as well.
     * <p>empty == null = true</p>
     * @param car
     *      The TrainCar data that will be associated with this node
     */
    public TrainCarNode(TrainCar car) {
        prev = null;
        next = null;
        this.car = car;
    }

    /**
     * This method return the previous node of the current node.
     * <p>null == the previous node does not exist</p>
     * @return
     *      The previous TrainNode if the link exist, null if not.
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Alter the previous node of the this trainCarNode.
     * @param prev
     *      The node that you want the current node to have as a previous node.
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * This method return the next node of the current node.
     * <p>null == the next node does not exist</p>
     * @return
     *      The next TrainNode if the link exist, null if not.
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Alter the next node of the this trainCarNode.
     * @param next
     *      The node that you want the current node to have as a next node.
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * This method return the trainCar object within this node.
     * <p>null == empty = true</p>
     * @return
     *     The TrainCar object within this current selected node. 
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Alter the data of the variable car for this current selected node.
     * @param car
     *      The TrainCar value you want to change it to for this current node.
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * Printing the Node in a list format by calling the toString from trainCar class.
     * @return 
     *      A list format of all the attribute within this specific train.
     */
    @Override
    public String toString() {
        return this.getCar().toString();
    }

    /* TestCode
    public static void main(String[] args) {
        ProductLoad test = new ProductLoad("Empty", 0.0, 0.00, false);
        TrainCar test2 = new TrainCar(15.0, 10.0);
        test2.setProductLoad(test);
        TrainCarNode test3 = new TrainCarNode(test2);
        System.out.println(test3);
    }
    */
}
