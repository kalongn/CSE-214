/**
 * This ClosedAuctionException class allow user to create a
 * ClosedAuctionException object
 * 
 * @author Ka_Long_Ngai 11/04/2022
 *         Time used: ~10 mins
 */

public class ClosedAuctionException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new ClosedAuctionException instance.
     * 
     */
    public ClosedAuctionException() {
        super("Auction is closed");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new ClosedAuctionException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public ClosedAuctionException(String str) {
        super(str);
    }
}
