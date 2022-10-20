/**
 * This Packet class allow user to create a Packet object
 * 
 * @author Ka_Long_Ngai 10/04/2022
 * 
 *         Time used: ~15 mins
 */

public class Packet {

    public static int packetCount = 0; // value that is used to assign id to a new created packet.
    /*
     * id is the id number of a packet, only goes up by 1
     * packetSize is how large is the packet is.
     * timeArrive is when did the package arrived in each simulation second.
     * timeDeliever is how long will the packet be queueing for, decrement 1 by
     * every simulation second.
     */
    private int id, packetSize, timeArrive, timeToDest;

    /**
     * Return an empty instance of Packet with an id Number.
     * <p>
     * id number will always increment by one.
     * </p>
     * <p>
     * packetSize, timeArrive and timeToDest default to 0.
     * </p>
     */
    public Packet() {
        this.id = ++packetCount;
        this.packetSize = timeArrive = timeToDest = 0;
    }

    /**
     * Return an instance of Packet with an id Number, packetSize, timeArrive and
     * timeToDest.
     * <p>
     * id number will always increment by one.
     * </p>
     * <p>
     * packetSize will be set to the input variable.
     * </p>
     * <p>
     * timeArrive will be set to the input variable.
     * </p>
     * <p>
     * timeToDest will be calculated by using packetSize/10.
     * </p>
     * 
     * @param packetSize
     *                   The packetSize you want this Packet object to default to
     *                   instead of 0.
     * @param timeArrive
     *                   The timeArrive you want this Packet object to default to
     *                   instead of 0.
     */
    public Packet(int packetSize, int timeArrive) {
        this.id = ++packetCount;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        this.timeToDest = packetSize / 100;
    }

    /**
     * Return the static variable packetCount of the Packet class.
     * 
     * @return
     *         The static variable packetCount of the Packet class
     */
    public static int getPacketCount() {
        return packetCount;
    }

    /**
     * Alter the static variable packetCount of the Packet class.
     * 
     * @param pCount
     *               The packetCount you want to change to.
     */
    public static void setPacketCount(int pCount) {
        packetCount = pCount;
    }

    /**
     * Return the id of the Packet object.
     * 
     * @return
     *         The id of the Packet object.
     */
    public int getId() {
        return id;
    }

    /**
     * Alter the id of the Packet object.
     * 
     * @param id
     *           The id that you want to change to within the Packet object.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the packetSize of the Packet object.
     * 
     * @return
     *         The packetSize of the Packet object.
     */
    public int getPacketSize() {
        return packetSize;
    }

    /**
     * Alter the packSize of the Packet object.
     * 
     * @param packetSize
     *                   The packetSize that you want to change to within the Packet
     *                   object.
     */
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    /**
     * Return the timeArrive of the Packet object.
     * 
     * @return
     *         The timeArrive of the Packet object.
     */
    public int getTimeArrive() {
        return timeArrive;
    }

    /**
     * Alter the timeArrive of the Packet object.
     * 
     * @param timeArrive
     *                   The timeArrive that you want to change to within the Pakcet
     *                   object.
     */
    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }

    /**
     * Return the timeToDest of the Packet object.
     * 
     * @return
     *         The timeToDest of the Packet object.
     */
    public int getTimeToDest() {
        return timeToDest;
    }

    /**
     * Alter the timeToDest of the Packet object.
     * 
     * @param timeToDest
     *                   The timeArrive that you want to change to within the Packet
     *                   object.
     */
    public void setTimeToDest(int timeToDest) {
        this.timeToDest = timeToDest;
    }

    /**
     * Decrement the timeToDest of the Packet object by 1.
     */
    public void decrementTimeToDest() {
        this.timeToDest--;
    }

    /**
     * An override toString method that print the Packet in an "array" format
     * containing the id, timeArrive and timeToDest.
     * 
     * @return
     *         A string value that present the Packet in a single [].
     */
    @Override
    public String toString() {
        return "[" + this.id + ", " + this.timeArrive + ", " + this.timeToDest + "]";
    }

}
