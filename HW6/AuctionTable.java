
/**
 * This AuctionTable class allow user to create a
 * AuctionTable object
 * 
 * @author Ka_Long_Ngai 11/04/2022
 *         Time used: ~1 hour 
 */

import java.util.Hashtable;
import big.data.*;

public class AuctionTable extends Hashtable<String, Auction> {

    /**
     * Uses the BigData library to construct an AuctionTable from a remote data
     * source.
     * <p>
     * Precondition:
     * </p>
     * <p>
     * URL represents a data source which can be connected to using the BigData
     * library.
     * </p>
     * <p>
     * The data source has proper syntax.
     * </p>
     * 
     * @param URL
     *            String representing the URL fo the remote data source.
     * @return
     *         The AuctionTable constructed from the remote data source.
     * @throws IllegalArgumentException
     *                                  Thrown if the AuctionTable constructed from
     *                                  the remote
     *                                  data source.
     */
    public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException {
        AuctionTable returnTable = new AuctionTable();
        DataSource ds;
        try {
            ds = DataSource.connect(URL).load();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        String[] sellerNameList = ds.fetchStringArray("listing/seller_info/seller_name");
        String[] currentBidStringList = ds.fetchStringArray("listing/auction_info/current_bid");
        double[] currentBidDoubleList = new double[currentBidStringList.length];
        String[] timeLeftStringList = ds.fetchStringArray("listing/auction_info/time_left");
        int[] timeLeftIntList = new int[timeLeftStringList.length];
        String[] idNumList = ds.fetchStringArray("listing/auction_info/id_num");
        String[] buyerNameList = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
        String[] cpuNameList = ds.fetchStringArray("listing/item_info/cpu");
        String[] memeoryList = ds.fetchStringArray("listing/item_info/memory");
        String[] hardDriveList = ds.fetchStringArray("listing/item_info/hard_drive");
        String[] itemInfoList = new String[cpuNameList.length];
        for (int i = 0; i < currentBidStringList.length; i++) {
            // userName Section
            sellerNameList[i] = sellerNameList[i].replace(" ", "").replace("\n", "").replace("\r", "");
            // currentBid Section.
            if (currentBidStringList[i].indexOf("$") == 0) {
                currentBidStringList[i] = currentBidStringList[i].substring(1);
            }
            currentBidDoubleList[i] = Double.parseDouble(currentBidStringList[i].replace(",", ""));
            // timeLetf Section.
            int hours = 0;
            if (timeLeftStringList[i].contains("days")) {
                hours = 24 * Integer.parseInt(
                        timeLeftStringList[i].substring(0, timeLeftStringList[i].indexOf("days")).replace(" ", ""));
            }
            if (timeLeftStringList[i].contains("hours")) {
                hours += Integer.parseInt(timeLeftStringList[i]
                        .substring(timeLeftStringList[i].indexOf("hours") - 3, timeLeftStringList[i].indexOf("hours"))
                        .replace(" ", ""));
            }
            timeLeftIntList[i] = hours;
            // id_num Section
            idNumList[i] = idNumList[i].trim();
            // buyer_name Section
            buyerNameList[i] = buyerNameList[i].trim();
            // item_info Section
            itemInfoList[i] = cpuNameList[i].trim() + " - " + memeoryList[i].trim() + " - " + hardDriveList[i].trim();
            // creating Auction
            Auction toBeAddedAuction = new Auction(idNumList[i], currentBidDoubleList[i], sellerNameList[i],
                    buyerNameList[i], timeLeftIntList[i], itemInfoList[i]);
            returnTable.putAuction(idNumList[i], toBeAddedAuction);
        }
        return returnTable;
    }

    /**
     * Update an auction entry of an Auction given the key value in the hashtable.
     * 
     * @param auctionID
     *                  The key of the value(should be a key that exist in the
     *                  hashtable prior).
     * @param auction
     *                  The auction you want to update to.
     */
    public void updateAuction(String auctionID, Auction auction) {
        this.put(auctionID, auction);
    }

    /**
     * Manually posts an auction, and add it into the table.
     * <p>
     * Postcondition: The item will be added to the table if all given parameters
     * are correct.
     * </p>
     * 
     * @param auctionID
     *                  The unique key for this object.
     * @param auction
     *                  The auction to insert into the table with the corresponding
     *                  auctionID.
     * @throws IllegalArgumentException
     *                                  Thrown if the given auctionID is already
     *                                  stored in the table.
     */
    public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException {
        if (this.containsKey(auctionID)) {
            throw new IllegalArgumentException();
        }
        this.put(auctionID, auction);
    }

    /**
     * Get the information of an Auction that contains the given ID as key
     * 
     * @param auctionID
     *                  The unique key for this object.
     * @return
     *         An Auction object with the given key, null otherwise.
     * 
     */
    public Auction getAuction(String auctionID) {
        return this.get(auctionID);
    }

    /**
     * Simulates the passing of time. Decrease the timeRemaining of all Auction
     * objects by the amount specified. The value cannot go below 0.
     * <p>
     * Postcondition: All Auctions in the table have their timeRemaining timer
     * decreased. If the original value is less than the decreased value, set the
     * value to 0.
     * </p>
     * 
     * @param numHours
     *                 The number of hours to decrease the timeRemaining value by.
     * @throws IllegalArgumentException
     *                                  Thrown if the given numHours is non positive
     */
    public void letTimePass(int numHours) throws IllegalArgumentException {
        if (!(numHours > 0)) {
            throw new IllegalArgumentException();
        }
        for (String key : this.keySet()) {
            Auction temp = this.get(key);
            temp.decretmentTimeRemaining(numHours);
            this.put(key, temp);
        }
    }

    /**
     * Iterates over all Auction objects in the table and removes them if they are
     * expired (timeRemaining == 0).
     * <p>
     * Postcondition: Only open Auction remain in the table.
     * </p>
     */
    public void removeExpiredAuctions() {
        String[] arrOfKeys = new String[this.size()];
        int i = 0;
        for (String key : this.keySet()) {
            Auction temp = this.get(key);
            if (temp.getTimeRemaining() == 0) {
                arrOfKeys[i] = temp.getAuctionID();
                i++;
            }
        }
        for (int y = 0; y < i; y++) {
            this.remove(arrOfKeys[y]);
        }
    }

    /**
     * Return the index of the Maximum element within an int array
     * 
     * @param arr
     *            The array of int elements
     * @return
     *         The index of the max element within the array.
     */
    private static int maxIndex(int[] arr) {
        int max = arr[0];
        int indexOfMax = 0;
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    /**
     * Prints the AuctionTable in tabular form. Order by their ID Number (Larger on
     * top, smaller on bottom)
     */
    public void printTable() {
        String returnStr = String.format("%-1s%-11s%-5s%-7s%-7s%-13s%-10s%-16s%-5s%-7s%-10s%-2s", "", "Auction ID", "|",
                "Bid", "|", "Seller", "|", "Buyer", "|", "Time", "|", "Item Info");
        returnStr += "\n===================================================================================================================================\n";
        Auction[] forSorting = new Auction[this.size()];
        int[] timeLeftList = new int[this.size()];
        int i = 0;
        for (String key : this.keySet()) {
            forSorting[i] = this.get(key);
            timeLeftList[i] = forSorting[i].getTimeRemaining();
            i++;
        }

        for (i = 0; i < this.size(); i++) {
            int index = maxIndex(timeLeftList);
            returnStr += forSorting[index].toString() + "\n";
            timeLeftList[index] = -1;
        }
        System.out.println(returnStr);
    }
}