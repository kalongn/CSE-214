
/**
 * This Auction class allow user to create a
 * Auction object
 * 
 * @author Ka_Long_Ngai 11/04/2022
 *         Time used: ~1 hour 
 */

import java.io.Serializable;

public class Auction implements Serializable {
    private int timeRemaining; // int value to indicate time in an auction.
    private double currentBid; // double value to indicate the currentBid, -1 = no Bid.
    /*
     * auctionID is the identificaion and key of the auction.
     * sellerName is the seller of the auction.
     * buyerName is the current highest bidder of the auction, empty = no one bid on
     * it.
     * itemInfo is the describe what is being bid on on this auction.
     */
    private String auctionID, sellerName, buyerName, itemInfo;

    /**
     * Return an instance of Auction with an auctionID, currentBid, sellerName,
     * buyerName, timeRemaining, itemInfo.
     * 
     * @param auctionID
     *                      The auctionID that you want to set to this reference of
     *                      Auction.
     * @param currentBid
     *                      The currentBid that you want to set to this reference of
     *                      Auction.
     * @param sellerName
     *                      The sellerName that you want to set to this reference of
     *                      Auction.
     * @param buyerName
     *                      The buyerName that you want to set to this reference of
     *                      Auction.
     * @param timeRemaining
     *                      The timeRemaining that you want to set to this reference
     *                      of Auction.
     * @param itemInfo
     *                      The itemInfo that you want to set to this reference of
     *                      Auction.
     */
    public Auction(String auctionID, double currentBid, String sellerName, String buyerName, int timeRemaining,
            String itemInfo) {
        this.timeRemaining = timeRemaining;
        decretmentTimeRemaining(0);
        this.currentBid = currentBid;
        this.auctionID = auctionID;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
        this.itemInfo = itemInfo;
    }

    /**
     * Return an instance of Auction with an auctionID, currentBid, sellerName,
     * buyerName, timeRemaining, itemInfo. With
     * currentBid set to -1 and buyerName set to "" to indicate no one bid on this
     * auction yet.
     * 
     * @param auctionID
     *                      The auctionID that you want to set to this reference of
     *                      Auction.
     * @param sellerName
     *                      The sellerName that you want to set to this reference of
     *                      Auction.
     * @param timeRemaining
     *                      The timeRemaining that you want to set to this reference
     *                      of Auction.
     * @param itemInfo
     *                      The itemInfo that you want to set to this reference of
     *                      Auction.
     */
    public Auction(String auctionID, String sellerName, int timeRemaining, String itemInfo) {
        this.timeRemaining = timeRemaining;
        decretmentTimeRemaining(0);
        this.currentBid = -1;
        this.auctionID = auctionID;
        this.sellerName = sellerName;
        this.buyerName = "";
        this.itemInfo = itemInfo;
    }

    /**
     * Return the timeRemaining of the Auction object.
     * 
     * @return
     *         The timeRemaining of the Auction object.
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Return the currentBid of the Auction object.
     * 
     * @return
     *         The currentBid of the Auction object.
     */
    public double getCurrentBid() {
        return currentBid;
    }

    /**
     * Return the auctionID of the Auction object.
     * 
     * @return
     *         The auctionID of the Auction object.
     */
    public String getAuctionID() {
        return auctionID;
    }

    /**
     * Return the sellerName of the Auction object.
     * 
     * @return
     *         The sellerName of the Auction object.
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Return the buyerName of the Auction object.
     * 
     * @return
     *         The buyerName of the Auction object.
     * 
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Return itemInfo of the Auction object.
     * 
     * @return
     *         The itemInfo of the Auction object.
     */
    public String getItemInfo() {
        return itemInfo;
    }

    /**
     * Decreases the time remaining for this auction by the specified amount. If
     * time is greater than the current remaining time for the auction, then the
     * time remaining is set to 0 (i.e. no negative times).
     * <p>
     * Postconditions: timeRemaining has been decremented by the indicated amount
     * and is greater than or equal to 0.
     * </p>
     * 
     * @param time
     *             The amount of time you want the timeRemaining of the Auction
     *             object to subtract by.
     */
    public void decretmentTimeRemaining(int time) {
        timeRemaining -= time;
        if (timeRemaining < 0) {
            timeRemaining = 0;
        }
    }

    /**
     * Determine whether the auction is Open or Not.
     * <p>
     * True: Auction is open
     * </p>
     * <p>
     * False: Auction is not open
     * </p>
     * 
     * @return
     *         A boolean value indicated if the Auction object is open or not.
     */
    public boolean isOpen() {
        if (timeRemaining > 0) {
            return true;
        }
        return false;
    }

    /**
     * Makes a new bid on this auction. If bidAmt is larger than currentBid, then
     * the value of currentBid is replaced by bidAmt and buyerName is is replaced by
     * bidderName.
     * <p>
     * Precondition: The auction is not closed (i.e. timeRemaining > 0).
     * </p>
     * <p>
     * Postcondition: currentBid Reflects the largest bid placed on this object. If
     * the auction is closed, throw a ClosedAuctionException.
     * </p>
     * 
     * @param bidderName
     *                   The person who place this new Bid down.
     * @param bidAmt
     *                   The amount of money this person Bid.
     * @throws ClosedAuctionException
     *                                Thrown if the auction is closed and no more
     *                                bids can be placed (i.e. timeRemaining == 0).
     */
    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException {
        if (!(timeRemaining > 0)) {
            throw new ClosedAuctionException();
        }
        if (bidAmt > currentBid) {
            currentBid = bidAmt;
            buyerName = bidderName;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * An override method of default toString, return string of data members in
     * tabular form.
     * 
     * @return
     *         A string of data members in tabular form.
     */
    @Override
    public String toString() {
        decretmentTimeRemaining(0);
        String tempPrice = "", returnStr = "";
        if (currentBid == -1) {
            tempPrice = "";
        } else {
            tempPrice = "$ " + String.format("%,.2f", currentBid);
        }
        returnStr = String.format("%-2s%-10s%-2s%-10s%-2s%-18s%-2s%-24s%-2s%-4s%-6s%-2s%-44s", "", auctionID, "|", tempPrice, "|", sellerName, "|",
                buyerName, "|", timeRemaining, "hours", "|",
                itemInfo.substring(0, Math.min(itemInfo.length(), 43)));
        return returnStr;

        /*
         * return " " + auctionID + " | " + tempPrice + " | " + sellerName + " | " +
         * buyerName
         * + " | " + timeRemaining + " hours | " + itemInfo.substring(0,
         * Math.min(itemInfo.length(), 43));
         */
    }

}
