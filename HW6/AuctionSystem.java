
/**
 * This AuctionSystem class allow user to create a
 * AuctionSystem object
 * 
 * @author Ka_Long_Ngai 11/04/2022
 *         Time used: ~1 hour 
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AuctionSystem {

    private String username; // username of the user that will be using program
    private AuctionTable auctionTable; // the auctionTable that will store the our record of auctions in.

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
            returnStr = input.nextLine();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was a valid String");
        }
        return returnStr;
    }

    /**
     * The method prompt the user for a username, stored the username. The rest of
     * the program will be executed on behalf of this user.
     * 
     * @param args
     *             The thing allowing this program to run.
     * @throws IOException
     *                                Thrown if reading/outputing data from to a
     *                                file gone wrong.
     */
    public static void main(String[] args) throws IOException {
        AuctionSystem system = new AuctionSystem();
        Scanner input = new Scanner(System.in);
        Boolean terminateCheck = true;
        String command = "";
        System.out.println("Starting...");
        try {
            FileInputStream file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            system.auctionTable = (AuctionTable) inStream.readObject();
            inStream.close();
            System.out.println("Loading previous Auction Table...");
        } catch (Exception e) {
            System.out.println("no previous auction table detected.\nCreating new table...");
            system.auctionTable = new AuctionTable();
        }
        System.out.print("Please select a username: ");
        system.username = scannerReadString(input);

        while (terminateCheck) {
            System.out.println(
                    "\nMenu:\n    (D) - Import Data from URL\n    (A) - Create a New Auction\n    (B) - Bid on an Item\n    (I) - Get Info on Auction\n    (P) - Print All Auctions\n    (R) - Remove Expired Auctions\n    (T) - Let Time Pass\n    (Q) - Quit");
            System.out.print("\nPlease select an option: ");
            command = scannerReadString(input);
            switch (command.toUpperCase()) {
                case "D":
                    System.out.print("Please enter a URL: ");
                    command = scannerReadString(input);
                    try {
                        system.auctionTable = AuctionTable.buildFromURL(command);
                        System.out.print("\nLoading...\nAuction data loaded successfully!\n");
                    } catch (Exception ex) {
                        System.out.println("The auction table " + command +" did not load successfully.");
                    }
                    break;
                case "A":
                    System.out.println("Creating new Auction as " + system.username);
                    System.out.print("Please enter an AuctionID: ");
                    String auctionID = scannerReadString(input);
                    System.out.print("Please enter an Auction time (hours): ");
                    int hours = scannerReadInt(input);
                    input.nextLine();
                    System.out.print("Please enter some Item Info: ");
                    String itemInfo = scannerReadString(input);
                    try {
                        system.auctionTable.putAuction(auctionID,
                                new Auction(auctionID, system.username, hours, itemInfo));
                        System.out.println("Auction " + auctionID + " inserted into table.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Auction " + auctionID + " already exist.");
                    }
                    break;
                case "B":
                    System.out.print("Please enter an auction ID: ");
                    command = scannerReadString(input);
                    System.out.println();
                    Auction temp = system.auctionTable.getAuction(command);
                    if (temp == null) {
                        System.out.println("Auction " + command + " does not exist.");
                        break;
                    }
                    String BidPrice = "None";
                    if (temp.getCurrentBid() != -1) {
                        BidPrice = String.format("%,.2f", temp.getCurrentBid());
                        BidPrice = "$ " + BidPrice;
                    }
                    if (temp.isOpen()) {
                        System.out.println("Auction " + command + " is OPEN\n   Current Bid: " + BidPrice);
                    } else {
                        System.out.println("Auction " + command + " is CLOSED\n  Current bid: " + BidPrice
                                + "\n\nYou can no longer bid on this item.");
                        break;
                    }
                    System.out.print("\nWhat would you like to bid?: ");
                    double amount = scannerReadDouble(input);
                    input.nextLine();
                    try {
                        temp.newBid(system.username, amount);
                        system.auctionTable.updateAuction(command, temp);
                        System.out.println("Bid accepted.");
                    } catch (ClosedAuctionException ex) {
                        System.out.println("Bid was not accepted.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Bid was not accepted.");
                    }
                    break;
                case "I":
                    System.out.print("Please enter an Auction ID: ");
                    command = scannerReadString(input);
                    Auction infoAuction = system.auctionTable.getAuction(command);
                    System.out.println(
                            "\nAuction " + command + ":\n   Seller: " + infoAuction.getSellerName() + "\n   Buyer: "
                                    + infoAuction.getBuyerName() + "\n   Time: " + infoAuction.getTimeRemaining()
                                    + " hours\n   Info: " + infoAuction.getItemInfo());
                    break;
                case "P":
                    system.auctionTable.printTable();
                    break;
                case "R":
                    system.auctionTable.removeExpiredAuctions();
                    System.out.println("\nRemoving expired auctions...\nAll expired auctions removed.");
                    break;
                case "T":
                    System.out.print("How many hours should pass: ");
                    int hour = scannerReadInt(input);
                    input.nextLine();
                    try {
                        system.auctionTable.letTimePass(hour);
                        System.out.println("\n\nTime passing...\nAuction times updated.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("\n\nThe input hours is not valid.");
                    } catch (Exception ex) {
                        System.out.println("\n\nAn Error occured.");
                    }
                    break;
                case "Q":
                    System.out.println("Writing Auction Table to file...\nDone!\n\nGoodbye.");
                    terminateCheck = false;
                    break;
                default:
                    System.out.println("Not a Valid selection command, please try again.");
                    break;
            }
        }
        FileOutputStream file = new FileOutputStream("auction.obj");
        ObjectOutputStream outStream = new ObjectOutputStream(file);
        outStream.writeObject(system.auctionTable);
        outStream.close();
    }
}