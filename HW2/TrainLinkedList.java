/**
 * This TrainCarNode class allow user to create a TrainCarNode object
 * 
 * @author Ka_Long_Ngai 09/11/2022
 * 
 * Time used: ~9 hours
*/

public class TrainLinkedList {

    private TrainCarNode head,tail,cursor; // the head node, tail node and the cursor node of for this linkedList.
    private int listLength, dangerCount, cursorPosition; //the length of the list, how many danger item is onboard, the location of the cursor.
    private double totalLength, totalValue, totalWeight; /*the total Length of the train, the total value of all the products onboard the train, 
    the total weight of the train inclduing the producsts and trainCar itself.*/

    /**
     * Constructs an instance of the TrainLinkedList with no TrainCar objects in it.
     * <p>Postconditions:</p>
     * <p> - this TrainLinkedList has been initialized to an empty linked list.</p>
     * <p> - head, tial and cursor are all set to null</p>
     */
    public TrainLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        listLength = 0;
        totalLength = 0.0;
        totalValue = 0.0;
        totalWeight = 0.0;
        dangerCount = 0;
        cursorPosition = 0;
    }

    /**
     * Determine whether the cursor is empty or not, if it is empty, A NullPointerException will be thrown be a custom message.
     * @throws NullPointerException
     *      thrown when cursor is null.
     */
    public void cursorEmpty() throws NullPointerException{
        if(this.cursor == null) {
            throw new NullPointerException("Cursor is null, please check your work.");
        }
    }

    /**
     * Determine whether the tail is pointing at same node as the cursor, if it is,
     * A NullPointerException will be thrown be a custom message.
     * @throws NullPointerException
     *      thrown when the cursor is null or if the cursor is referencing the tail.
     *      
     */
    public void tailAndCursorEmpty() throws NullPointerException {
        this.cursorEmpty();
        if(cursor == tail) {
            throw new NullPointerException("Cursor is referencing the tail Node, please check your work.");
        }
    }

    /**
     * Determine whether the head is pointing at same node as the cursor, if it is,
     * A NullPointerException will be thrown be a custom message.
     * @throws NullPointerException
     *      thrown when the cursor is null or if the cursor is referencing the head.
     */
    public void headAndCursorEmpty() throws NullPointerException {
        this.cursorEmpty();
        if(cursor == head) {
            throw new NullPointerException("Cursor is referencing the head node, please check your work.");
        }
    }

    /**
     * Returns a reference to the TrainCar at the node currently referenced by the cursor.
     * <p>Preconditions: This lis not empty (cursor is not null).</p>
     * @return
     *      The reference to the TrainCar at the node currently referenced by the cursor.
     */
    public TrainCar getCursorData() {
        this.cursorEmpty();
        return this.cursor.getCar();
    }

    /**
     * Places car in the node currently referenced by the cursor.
     * <p>Preconditions: The list is not empty (cursor is not null)</p>
     * <p>Postconditions: The cursor node now contains a reference to the car as its data.</p>
     * @param car
     *      The trainCar object you want the cursor node to have.
     */
    public void setCursorData(TrainCar car) {
        this.cursorEmpty();
        this.cursor.setCar(car);
    }

    /**
     * Moves the cursor to point at the next TrainCarNode.
     * <p>Preconditions: The list is not empty (cursor is not null) and cursor does not currently reference the tail of the list.</p>
     * <p>Postconditions: The cursor has been advanced to the next TrainCarNode, or has remained at the tail of the list.</p>
     */
    public void cursorForward() {
        this.tailAndCursorEmpty();
        this.cursor = this.cursor.getNext();
        cursorPosition++;
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     * <p>Preconditions: The list is not empty(cursor is not null) nd cursor does not currently reference the head of the list.</p>
     * <p>Postconditions: The cursor has been moved back to hte previous TrainCarNode, or has remained at the head of the list.</p>
     */
    public void cursorBackward() {
        this.headAndCursorEmpty();
        this.cursor = this.cursor.getPrev();
        cursorPosition--;
    }

    /**
     * Inserts a car into the train after the cursor position.
     * <p>Preconditions: This trainCar object has been instantiated</p>
     * <p>Postconditions:</p>
     * <p> - The new TrainCar has been inserted into the train after position of the cursor.</p>
     * <p> - All TrainCar objects previously on the train are still on the train, and their order has been preserved.</p>
     * <p> - The cursor now points to the inserted car.</p>
     * @param newCar
     *      the new TrainCar to be inserted into the train.
     * @throws IllegalArgumentException
     *      Indicates that newCar is null.
     */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
        if(newCar == null) {
            throw new IllegalArgumentException("The TrainCar you are trying to insert is null");
        }
        TrainCarNode insertNode = new TrainCarNode(newCar);
        if(cursor == null) {
            head = insertNode;
            tail = insertNode;
            cursor = insertNode;
            cursorPosition++;
        } else if(cursor == tail) {
            cursor.setNext(insertNode);
            insertNode.setPrev(cursor);
            tail = insertNode;
            cursorForward();
        } else {
            insertNode.setPrev(cursor);
            insertNode.setNext(cursor.getNext());
            this.cursor.getNext().setPrev(insertNode);
            this.cursor.setNext(insertNode);
            cursorForward();
        }
        totalLength+=newCar.getCarLength();
        totalWeight+=newCar.getCarWeight();
        if(!newCar.isEmpty())
        {
            this.statUpdateInsert(newCar);
        }
        listLength++;
    }

    /**
     * This method update the information and load the trainCar when the user want to add the product to a trainCar 
     * that is within the current linkedList.
     * <p>Postconditions: the stats have been updated and the product is loaded to the current cursor node trainCar.
     * @param load
     *      The load you want to current node trainCar to reference.
     */
    public void loadProductStatUpdate(ProductLoad load) {
        this.getCursorData().setProductLoad(load);
        this.statUpdateInsert(this.getCursorData());
    }

    /**
     * A helper method that update the stats for toString method, the value, weight of the product and is it dangerous.
     * @param car
     *      The trainCar object that is being insert, getting the data from this trainCar.
     */
    private void statUpdateInsert(TrainCar car) {
        totalWeight+=car.getProductLoad().getWeight();
        totalValue+=car.getProductLoad().getValue();
        if(car.getProductLoad().isDangerous()) {
            dangerCount++;
        }
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns the TrainCar contained withinthe node.
     * <p>Preconditions: the cursor is not null</p>
     * <p>Postconditions:</p>
     * <p> - The TrainCarNode referenced by the cursor has been removed from the train.</p>
     * <p> - The cursor now refere ces the next node, or the previous node if no next node exists.</p>
     * @return
     *      The trainCar that is being removes will be return.
     */
    public TrainCar removeCursor() {
        this.cursorEmpty();
        TrainCar returnTrainCar = this.getCursorData();
        if(cursor.getNext() == null && cursor.getPrev() == null) {
            cursor = null;
            head = null;
            tail = null;
            this.statUpdateRemove();
        } else if(cursor == head) {
            cursorForward();
            cursor.setPrev(null);
            this.statUpdateRemove(head);
            head = cursor;
        } else if (cursor == tail) {
            cursorBackward();
            cursor.setNext(null);
            this.statUpdateRemove(tail);
            tail = cursor;
        } else {
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            this.statUpdateRemove(cursor);
            cursorForward();
        }
        return returnTrainCar;
    }
    
    /**
     * Reset all the variable to 0 as the trainLinkedList will be empty.
     */
    private void statUpdateRemove() {
        totalLength = 0.0;
        totalValue = 0.0;
        totalWeight = 0.0;
        dangerCount = 0;
        listLength = 0;
    }

    /**
     * When removing a trainCar, the stats of the trainCar will also need to remove, including the product on it as well. Which is 
     * why the danger count is needed.
     * @param carNode
     *      The node that is being removed, the stat of this node will be remove from the overall total for every attribute.
     */
    private void statUpdateRemove(TrainCarNode carNode) {
        totalLength-=carNode.getCar().getCarLength();
        totalWeight-=carNode.getCar().getCarWeight();
        if(!carNode.getCar().isEmpty())
        {
            totalValue-=carNode.getCar().getProductLoad().getValue();
            totalWeight-=carNode.getCar().getProductLoad().getWeight();
            if(carNode.getCar().getProductLoad().isDangerous() == true) {
                dangerCount--;
            }
        }
        listLength--;
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     * @return
     *      The number of TrainCar objects on this train.
     */
    public int size() {
        return listLength;
    }

    /**
     * return the total length of the train in meteres.
     * @return
     *      The sum of the lengths of each TrainCar in the train.
     */
    public double getLength() {
        return totalLength;
    }

    /**
     * returns the total value of product carried by the train.
     * @return
     *      The sum of the values of each TrainCar in the train.
     */
    public double getValue() {
        return totalValue;
    }

    /**
     * Returns the total weight in tons of the train. Note that the weight of the train is the sum of hte weights of each empty TrainCar, 
     * plus the weight of the ProductLoad carried by that car.
     * @return
     *      the sum of the weight of each TrainCar plus the sum of the ProductLoad carried by that car.
     */
    public double getWeight() {
        return totalWeight;
    }

    /**
     * Whether or not there is a dangerous product on one of the TrainCar objects on the train.
     * @return
     *      returns true if the train ontains at least one TrainCar carrying a dangerous productload, false otherwise.
     */
    public boolean isDangerous() {
        if(dangerCount>0) {
            return true;
        }
        return false;
    }

    /**
     * Searches the list of all ProductLoad objects with the indicated name and sums together their weight and value ( also keeps 
     * track of whether the product is dangerous or not), then prints a single ProductLoad record to the console.
     * @param name
     *      the name of the ProductLoad to find on the train.
     */
    public void findProduct(String name) {
        double findTotalWeight = 0.0;
        double findTotalValue = 0.0;
        int itemCounter = 0;
        boolean findIsDangerous = false;
        TrainCarNode tempCursor = head;
        for(int i = 0; i < size() && tempCursor != null; i++) {
            if(tempCursor.getCar().getProductLoad().getName().equals(name)) {
                if(findTotalValue == 0 || findTotalWeight == 0) {
                    findIsDangerous = tempCursor.getCar().getProductLoad().isDangerous();
                }
                findTotalWeight+=tempCursor.getCar().getProductLoad().getWeight();
                findTotalValue+=tempCursor.getCar().getProductLoad().getValue();
                itemCounter++;
            }
            tempCursor = tempCursor.getNext();
        }
        if(findTotalValue == 0 && findTotalWeight == 0)
        {
            System.out.println("No record of " + name + " on board train.");
        } else {
            String printString = "There following products were found on " + itemCounter + " Cars:\n\n";
            System.out.println(printString + new ProductLoad(name, findTotalWeight, findTotalValue, findIsDangerous));
        }
    }

    /**
     * Print a neartly formatted table of the car number, car length, car weight, load name, load weight, load value, and
     * load dangerousness for all of the car on the train. It also indicate where the cursor location is with -->.
     */
    public void printManifest() {
        TrainCarNode tempCursor = head;
        String printString = String.format("%-3s%-37s%-3s", "", "CAR:", "LOAD:\n");
        printString+=String.format("%-4s%-8s%-14s%-13s%-3s%-10s%-14s%-12s%-9s","", 
         "Num", "Length (m)", "Weight (t)", "|", "Name", "Weight (t)", "Value ($)", "Dangerous");
        printString+="\n   ====================================+================================================\n";
        for(int i = 0; i < size() && tempCursor != null; i++) {
            if(i+1 == cursorPosition) {
                printString+=String.format("%-6s%-3s","-->", (i+1)) + tempCursor.getCar().toStringTrainCarHelper() + "\n";
            } else {
                printString+=String.format("%-6s%-3s","", (i+1)) + tempCursor.getCar().toStringTrainCarHelper() + "\n";
            }
            tempCursor = tempCursor.getNext();
        }
        System.out.println(printString);
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order of the cars in the train.
     * <p>Postconditions:</p>
     * <p> - All dangerous cars have been removed from this train.</p>
     * <p> - The order of all non-dangerous cars must be maintained upon the completion of this method.</p>
     */
    public void removeDangerousCars() {
        if(dangerCount > 0) {
            cursor = head;
            cursorPosition = 0;
            for(int i = 0; i < size(); i++) {
                if(cursor.getCar().getProductLoad().isDangerous()) {
                    this.removeCursor();
                }
                if(cursor.getNext()!= null) {
                    cursorForward();
                } else {
                   break;
                }
            }
            System.out.println("Dangerous cars successfully removed from the train.");
        } else {
            System.out.println("There are no Danbgerous cars on on this train.");
        }
    
    }

    /**
     * Returns a nearly formatted String representation of the train.
     * @return
     *      A nearly formatted string containing information about the train, including its size (number of cars), length in meteres
     *      weight in tons, value in dollars, and whether it is dangerous or not.
     */
    @Override
    public String toString() {
        String temp;
        if(this.isDangerous()) {
            temp = "DANGEROUS.";
        } else {
            temp = "NOT DANGEROUS.";
        }
        String returnString = "Train: " + this.size() + " cars, " + this.getLength() + 
         " meter, " + String.format("%,.1f", this.getWeight()) + 
         " tons, $" + String.format("%,.2f", this.getValue()) + " value, ";
        returnString+=temp;
        return returnString;
    }

    /*Test Code
    public static void main(String[] args) {
        TrainLinkedList pleaseWork = new TrainLinkedList();
        TrainCar testTrainCart1 = new TrainCar(15.0, 10.0);
        ProductLoad testTrainCarProduct1 = new ProductLoad("Corn", 100, 15440, false);
        pleaseWork.insertAfterCursor(testTrainCart1);
        pleaseWork.printManifest();
        System.out.println();
        pleaseWork.loadProductStatUpdate(testTrainCarProduct1);
        pleaseWork.printManifest();
        System.out.println();
        TrainCar testTrainCart2 = new TrainCar(18.5, 8.3);
        pleaseWork.insertAfterCursor(testTrainCart2);
        pleaseWork.printManifest();
        System.out.println();
        ProductLoad testTrainCarProduct2 = new ProductLoad("Corn", 85, 13120, false);
        pleaseWork.loadProductStatUpdate(testTrainCarProduct2);
        pleaseWork.printManifest();
        System.out.println();
        System.out.println(pleaseWork);
        System.out.println();
        pleaseWork.findProduct("Corn");
        System.out.println();
        try {
            pleaseWork.cursorForward();
        } catch (Exception NullPointerException) {
            System.out.println("No next car, cannot move cursor forward");
        }
        System.out.println();
        try {
            pleaseWork.cursorBackward();
            System.out.println("Cursor move backward");
        } catch (Exception NullPointerException) {
            System.out.println("No previous car, cannot move cursor forward");
        }
        System.out.println();
        pleaseWork.printManifest();
        System.out.println();
        TrainCar testTrainCart3 = new TrainCar(32.1, 17.4);
        pleaseWork.insertAfterCursor(testTrainCart3);
        pleaseWork.printManifest();
        System.out.println();
        ProductLoad testTrainCarProduct3 = new ProductLoad("TNT", 25.3, 151500, true);
        pleaseWork.loadProductStatUpdate(testTrainCarProduct3);
        pleaseWork.printManifest();
        System.out.println();
        System.out.println(pleaseWork);
        System.out.println();
        pleaseWork.findProduct("Corn");
        System.out.println();
        pleaseWork.findProduct("Milk");
        System.out.println();
        pleaseWork.removeDangerousCars();
        System.out.println();
        pleaseWork.printManifest();
        System.out.println();
        System.out.println(pleaseWork.removeCursor().getProductLoad());
        System.out.println();
        pleaseWork.printManifest();
    }*/
}
