
/**
 * This Router class allow user to create a Router object
 * 
 * @author Ka_Long_Ngai 10/04/2022
 * 
 *         Time used: ~40 mins
*/
import java.util.ArrayList;

public class Router extends ArrayList<Packet> {

    // Contructor for Router, size() and isEmpty() method are embeded within
    // ArrayList since we are extending it.

    /**
     * Add a packet at the back of the queue (ArrayList pretending to be a queue).
     * 
     * @param packet
     *               The packet that you want to add to the back of the queue.
     */
    public void enqueue(Packet packet) {
        super.add(packet);
    }

    /**
     * Remove the packet that is at the front of the queue (ArrayList pretending to
     * be a queue).
     * 
     * @return
     *         The packet is being removed from the queue.
     */
    public Packet dequeue() {
        return super.remove(0);
    }

    /**
     * Return the value that is at the front of the queue.
     * 
     * @return
     *         The packet that is at the front of the queue.
     */
    public Packet peek() {
        return super.get(0);
    }

    /**
     * An override toString method that print the queue in an "arrayList" format.
     * 
     * @return
     *         A string containing all the packet within the queue in a single line.
     */
    @Override
    public String toString() {
        if (super.size() == 0) {
            return "{}";
        }
        String returnStr = "{";
        for (int i = 0; i < super.size(); i++) {
            returnStr += super.get(i) + ", ";
        }
        returnStr = returnStr.substring(0, returnStr.length() - 2) + "}";
        return returnStr;
    }

    /**
     * This metod loop through an ArrayList of Intermediate routers. Find the router
     * with the most free buffer space (contain the least Packets),
     * and return the index of the router. If there are mutiple routers, and
     * corresponding indices will be acceptable. If all router buffers are full,
     * throw
     * an FullRouterException.
     * 
     * @param routers
     *                      An ArrayList of Router object that we could loop
     *                      through.
     * @param maxBufferSize
     *                      The maximum size of each Router can have, since this
     *                      queue is made off ArrayList, we need this extra
     *                      parameter.
     * @return
     *         The index (starting from 1) of the router that has the least Packets.
     * @throws FullRoutersException
     *                              Thrown when all routers are full.
     */
    public static int sendPacketTo(ArrayList<Router> routers, int maxBufferSize) throws FullRoutersException {
        int minIndex = 0;
        int fullRouterCount = 0;
        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(minIndex).size() > routers.get(i).size() && routers.get(i).size() <= maxBufferSize) {
                minIndex = i;
            }
            if (routers.get(i).size() == maxBufferSize) {
                fullRouterCount++;
            }
        }
        if (fullRouterCount == routers.size()) {
            throw new FullRoutersException();
        }
        return minIndex + 1;
    }

}
