
/**
 * This Simulator class allow user to create a Simulator object
 * 
 * @author Ka_Long_Ngai 10/08/2022
 * 
 *         Time used: ~ 6 hours
*/
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    private Router dispatcher; // Level 1 router
    private ArrayList<Router> routers; // Level 2 routers
    /*
     * totalServiceTime - contains the running sum of the total time each packet is
     * in the network.
     * The service time per packet is simply the time it has arrived to the
     * Destination minus the
     * time when the packet was created. When a packet counter reaches 0, dequeue it
     * from the router
     * queue and add the time to the total time. Ignore the leftover Packets in the
     * network when simulation time is up.
     * totalPacketsArrived - contains the total number of packets that has been
     * successfully forwarded
     * to the destination. When a packet counter reaches 0, dequeue it from the
     * router queue and increase this count by 1.
     * packetsDropped - records the number of packets that have been dropped due to
     * a congested network.
     * Note: this can only happen when sendPacketTo(Collection routers) throws an
     * exception.
     * arrivalProb - the probability of a new packet arriving at the Dispatcher.
     * numIntRouters - the number of Intermediate routers in the network.
     * maxBufferSize - the maximum number of Packets a Router can accommodate for.
     * minPacketSize - the minimum size of a Packet.
     * maxPacketSize - the maximum size of a Packet.
     * bandwidth - the maximum number of Packets the Destination router can accept
     * at a given simulation unit.
     * duration - the number of simulation units.
     */
    private int totalServiceTime, totalPakcetsArrived, packetsDropped, numIntRouters,
            maxBufferSize, minPacketSize, maxPacketSize, bandWidth, duration;
    private double arrivalProb;

    public final static int MAX_PACKETS = 3; // ???

    /**
     * Return a new instance of Simulation object.
     * 
     * @param arrivalProb
     *                      The probability of a new packet arriving at the
     *                      Dispatcher.
     * @param numIntRouters
     *                      The number of Intermediate routers in the network.
     * @param maxBufferSize
     *                      The maximum number of Packets a Router can accommodate
     *                      for.
     * @param minPacketSize
     *                      The minimum size of a Packet.
     * @param maxPacketSize
     *                      The maximum size of a Packet.
     * @param bandWidth
     *                      The maximum number of Packets the Destination router can
     *                      accept at a given simulation unit.
     * @param duration
     *                      The number of simulation units.
     */
    public Simulator(double arrivalProb, int numIntRouters, int maxBufferSize, int minPacketSize, int maxPacketSize,
            int bandWidth, int duration) {
        dispatcher = new Router();
        routers = new ArrayList<Router>();
        totalServiceTime = 0;
        totalPakcetsArrived = 0;
        packetsDropped = 0;
        this.arrivalProb = arrivalProb;
        this.numIntRouters = numIntRouters;
        this.maxBufferSize = maxBufferSize;
        this.minPacketSize = minPacketSize;
        this.maxPacketSize = maxPacketSize;
        this.bandWidth = bandWidth;
        this.duration = duration;
    }

    /**
     * Return the totalPacketArrived of the Simulation object.
     * 
     * @return
     *         The totalPacketArrived of the Simulation object.
     */
    public int getTotalPacketArrived() {
        return this.totalPakcetsArrived;
    }

    /**
     * Inrease totalPacketArrived by 1.
     */
    public void incrementTotalPacketArrvied() {
        this.totalPakcetsArrived++;
    }

    /**
     * Return the totalServiceTime of the Simulation object.
     * 
     * @return
     *         the totalServiceTime of the Simulation object.
     */
    public int getTotalServiceTime() {
        return this.totalServiceTime;
    }

    /**
     * Increase totalServiceTime by param amount.
     * 
     * @param amount
     *               The amount of increment you want totalServiceTime to be
     *               increase by.
     */
    public void incrementTotalServiceTime(int amount) {
        this.totalServiceTime = this.totalServiceTime + amount;
    }

    /**
     * Return the packetsDropped of the Simulation object.
     * 
     * @return
     *         The packetsDropped of the Simulation object.
     */
    public int getPacketsDropped() {
        return this.packetsDropped;
    }

    /**
     * Alter the packetsDropped of the Simulation object.
     * 
     * @param packetsDropped
     *                       The packetsDropped that you want to change to within
     *                       the simulation object.
     */
    public void setPacketsDropped(int packetsDropped) {
        this.packetsDropped = packetsDropped;
    }

    /**
     * Return the bandWidth of the Simulation object.
     * 
     * @return
     *         The bandWidth of the Simulation object.
     */
    public int getBandWidth() {
        return this.bandWidth;
    }

    /**
     * Return the Dispatcher of the Simulation object.
     * 
     * @return
     *         The dispatcher of the Simulation object.
     */
    public Router getDispatcher() {
        return this.dispatcher;
    }

    /**
     * Return the Routers of the Simulation object.
     * 
     * @return
     *         The routers of the the Simulation object.
     */
    public ArrayList<Router> getRouters() {
        return this.routers;
    }

    /**
     * Return the Duration of the Simulation object.
     * 
     * @return
     *         The duration of the Simulation object.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Decrement the duration by 1.
     */
    public void durationDecrease() {
        this.duration--;
    }

    /**
     * Return the arrivalProb of the Simulation object.
     * 
     * @return
     *         The arrivalProb of the Simulation object.
     */
    public double getArrivalProb() {
        return this.arrivalProb;
    }

    /**
     * Return the minPacketSize of the Simulation object.
     * 
     * @return
     *         The minPacketSize of the Simulation object.
     */
    public int getMinPacketSize() {
        return this.minPacketSize;
    }

    /**
     * Return the maxPacketSize of the Simulation object.
     * 
     * @return
     *         The maxPacketSize of the Simulation object.
     */
    public int getMaxPacketSize() {
        return this.maxPacketSize;
    }

    /**
     * Return the maxBufferSize of the Simulation object.
     * 
     * @return
     *         The maxBufferSize of the Simulation object.
     */
    public int getMaxBufferSize() {
        return this.maxBufferSize;
    }

    /**
     * Return the numIntRouters of the Simulation object.
     * 
     * @return
     *         the numIntRouters of the Simulation object.
     */
    public int getNumIntRouters() {
        return this.numIntRouters;
    }

    /**
     * Return a randomNumber within param minVal and maxVal inclusively.
     * 
     * @param minVal
     *               The minimum value you want this random method to generate.
     * @param maxVal
     *               The maximum value you want this random method to generate.
     * @return
     *         [minVal, maxVal] as range, a random number between these 2 values
     *         inclusively.
     */
    private static int randomInt(int minVal, int maxVal) {
        return minVal + (int) (Math.random() * ((maxVal - minVal) + 1));
    }

    /**
     * Runs the simulator with the specification which stated in the HW assignment,
     * The code utilize a while loop
     * to decrement time. It first get the preLastIndex (fairness implementation)
     * and remove any possible package.
     * Then it will add package base on chance at the dispatcher then the dispatcher
     * send the package to the router
     * that contain the least amount of package. Then time decrement and it start
     * all over again.
     * 
     * @param simulation
     *                   The simulation object that have every single variable
     *                   initialized to an appropriate value.
     * @return
     *         The average package handling time (only counting packages that
     *         sucessfully reach the destination).
     */
    public double simulate(Simulator simulation) {
        int realTime = 1, bufferCheck = 0, lastIndex = 0, prevLastIndex = 0;
        boolean prevLastIndexAltered = false;
        String printLater = "";
        for (int i = 0; i < simulation.getNumIntRouters(); i++) {
            simulation.getRouters().add(new Router());
        }
        while (simulation.getDuration() > 0) {
            decrementFrontloop: for (int i = 0; i < simulation.getRouters().size(); i++) {
                if (simulation.getRouters().get(lastIndex).size() == 0) {
                    break decrementFrontloop;
                }
                if (simulation.getRouters().get(lastIndex).peek().getTimeToDest() > 0) {
                    simulation.getRouters().get(lastIndex).peek().decrementTimeToDest();
                }
                if (simulation.getRouters().get(lastIndex).peek().getTimeToDest() == 0
                        && simulation.getBandWidth() > bufferCheck) {
                    Packet tempPacket = simulation.getRouters().get(lastIndex).dequeue();
                    simulation.incrementTotalServiceTime(realTime - tempPacket.getTimeArrive());
                    simulation.incrementTotalPacketArrvied();
                    bufferCheck++;
                    printLater += "Packet " + tempPacket.getId()
                            + " has successfully reached its destination: +"
                            + (realTime - tempPacket.getTimeArrive() + "\n");
                } else if (!prevLastIndexAltered) {
                    prevLastIndex = lastIndex;
                    prevLastIndexAltered = true;
                }
                lastIndex++;
                if (lastIndex == simulation.getRouters().size()) {
                    lastIndex = 0;
                }
            }
            System.out.println("\nTime: " + realTime);
            for (int i = 0; i < MAX_PACKETS; i++) {
                if (Math.random() < simulation.getArrivalProb()) {
                    int size = randomInt(simulation.getMinPacketSize(), simulation.getMaxPacketSize());
                    Packet packet = new Packet(size, realTime);
                    System.out.println("Packet " + packet.getId() + " arrives at dispatcher with size "
                            + packet.getPacketSize() + ".");
                    simulation.getDispatcher().enqueue(packet);
                }
            }
            for (int i = 0; i < simulation.getDispatcher().size(); i++) {
                try {
                    int routerId = Router.sendPacketTo(simulation.getRouters(), simulation.getMaxBufferSize());
                    Packet dequeuedFromDispatcher = simulation.getDispatcher().dequeue();
                    System.out
                            .println("Packet " + dequeuedFromDispatcher.getId() + " sent to Router " + routerId + ".");
                    simulation.getRouters().get(routerId - 1).enqueue(dequeuedFromDispatcher);
                } catch (Exception FullRoutersException) {
                    System.out.println("Network is congested. Packet " + simulation.getDispatcher().dequeue().getId()
                            + " is dropped.");
                    simulation.setPacketsDropped(simulation.getPacketsDropped() + 1);
                }
                i--;
            }
            if (!printLater.equals("")) {
                System.out.println(printLater);
            }
            for (int i = 0; i < simulation.getNumIntRouters(); i++) {
                System.out.println("R" + (i + 1) + " " + simulation.getRouters().get(i));
            }
            bufferCheck = 0;
            printLater = "";
            prevLastIndexAltered = false;
            lastIndex = prevLastIndex;
            realTime++;
            simulation.durationDecrease();
        }
        double returnDouble = (double) simulation.getTotalServiceTime() / (double) simulation
                .getTotalPacketArrived();
        System.out.println("\nSimulation ending...\nTotal service time: " + simulation.getTotalServiceTime()
                + "\nTotal packets served: " + simulation.getTotalPacketArrived()
                + "\nAverage service time per packets: " + returnDouble + "\nTotal packets dropped: "
                + simulation.getPacketsDropped() + "\n");
        return returnDouble;
    }

    /**
     * This make sure the scanner is empty, so that it scanns the next item
     * correctly.
     * 
     * @param input
     *              the Scanner object you want to reset.
     * @return
     *         an empty Scanner object.
     */
    private static Scanner scannerReset(Scanner input) {
        input.nextLine();
        return input;
    }

    /**
     * This ask user to input a int value and return it after the scanner picks it
     * up.
     * <p>
     * Precondition: the int user input is a valid int
     * </p>
     * <p>
     * Postcondition: the int value will be return, if something went wrong, a print
     * statement will appear
     * </p>
     * 
     * @param input
     *              the scanner you are using to read user input.
     * @return
     *         the double value that user inputted.
     */
    private static int scannerReadInt(Scanner input) {
        int returnResult = 0;
        try {
            returnResult = input.nextInt();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was not a valid int");
            return -1;
        }
        return returnResult;
    }

    /**
     * This ask user to input a double value and return it after the scanner picks
     * it up.
     * <p>
     * Precondition: the double user input is a valid double
     * </p>
     * <p>
     * Postcondition: the double value will be return, if something went wrong, a
     * print statement will appear
     * </p>
     * 
     * @param input
     *              the scanner you are using to read user input.
     * @return
     *         the double value that user inputted.
     */
    private static double scannerReadDouble(Scanner input) {
        double returnResult = 0;
        try {
            returnResult = input.nextDouble();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was not a valid double");
            return -1;
        }
        return returnResult;
    }

    /**
     * This ask user to input a string value (one word aka no space allow) and
     * return it after the scanner picks it up.
     * <p>
     * Precondition: the string user input is a valid double
     * </p>
     * <p>
     * Postcondition: the string value will be return, if something went wrong, a
     * print statement will appear
     * </p>
     * 
     * @param input
     *              the scanner you are using to read user input.
     * @return
     *         the string value (the one word aka no space allow) that user
     *         inputted.
     */
    private static String scannerReadString(Scanner input) {
        String returnStr = "";
        try {
            returnStr = input.next();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was a valid String");
        }
        return returnStr;
    }

    /**
     * Prompts the user for all the attributes required to create a simulation
     * object, and
     * allow user to have control over the simulation to formulate a trend line.
     * 
     * @param args
     *             The main args allow the code to run.
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String terminateOrRepeat = "";
        boolean terminateCheck = true;
        int numIntRouters, maxBufferSize, minPacketSize, maxPacketSize, bandWidth, duration;
        double arrivalProb;

        while (terminateCheck) {
            System.out.println("Starting simulator...");
            System.out.print("\nEnter the number of Intermediate routers: ");
            numIntRouters = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the Arrival probability of Packets: ");
            arrivalProb = scannerReadDouble(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the maximum buffer size of a router: ");
            maxBufferSize = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the minimum size of packet: ");
            minPacketSize = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the maximum size of packet: ");
            maxPacketSize = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the bandwith size: ");
            bandWidth = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            System.out.print("\nEnter the simulation duration: ");
            duration = scannerReadInt(userInput);
            userInput = scannerReset(userInput);
            Simulator simulation = new Simulator(arrivalProb, numIntRouters, maxBufferSize, minPacketSize,
                    maxPacketSize, bandWidth, duration);
            simulation.simulate(simulation);
            Packet.setPacketCount(0);
            System.out.print("\nDo you want another simulation (y/n)");
            terminateOrRepeat = scannerReadString(userInput);
            userInput = scannerReset(userInput);
            switch (terminateOrRepeat.toLowerCase()) {
                case "y":
                    break;
                case "n":
                    terminateCheck = false;
                    System.out.println("Program terminating succesfully...");
                    break;
                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }
    }
}
