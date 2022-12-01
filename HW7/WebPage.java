
/**
 * This WebPage class allow user to create a
 * WebPage object
 * 
 * @author Ka_Long_Ngai 11/15/2022
 */

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {

    private String url; // the url of this webPage.
    private int index, rank; // the index and rank of this webPage.
    private ArrayList<String> keywords; // the keywords of this webPage.

    /**
     * Return an instance of Webpage with parameter of index, url, rank and
     * keywords. With rank of this object default to 0.
     * 
     * @param index
     *                 The index of this Webpage you want this WebPage object to
     *                 initialize with.
     * @param url
     *                 The url of this Webpage you want this WebPage object to
     *                 initialize with.
     * @param keywords
     *                 The keywords of this Webpage you want this WebPage object to
     *                 initialize with.
     */
    public WebPage(int index, String url, ArrayList<String> keywords) {
        this.index = index;
        this.rank = 0;
        this.url = url;
        this.keywords = keywords;
    }

    /**
     * Return the url of this WebPage object.
     * 
     * @return
     *         The url of this WebPage object.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Return the index of this WebPage object.
     * 
     * @return
     *         The index of this WebPage object.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Alternate the index of this WebPage object.
     * 
     * @param index
     *              The index you want this WebPage object to alternate to.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Return the rank of this WebPage object.
     * 
     * @return
     *         The rank of this WebPage object.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Alternate the rank of this WebPage object.
     * 
     * @param rank
     *             The rank you want this WebPage object to alternate to.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Return the keywords of this WebPage object.
     * 
     * @return
     *         The keywords of this WebPage object.
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    /**
     * Returning neatly String that put every value within the keywords ArrayList
     * into a readable format.
     * 
     * @return
     *         A String value with comma seperating each keyword.
     */
    public String keyWordsIntoString() {
        String keywords = "";
        for (String i : this.getKeywords()) {
            keywords += i + ", ";
        }
        keywords = keywords.substring(0, keywords.length() - 2);
        return keywords;
    }

    /**
     * Returns string of data memebers in a tabular form.
     * 
     * @return
     *         A string value of data members in a tabular form.
     */
    @Override
    public String toString() {
        String keywords = this.keyWordsIntoString();
        return String.format("%-2s%-4s%-2s%-20s%-2s%-4s%-2s%-30s", "", index, "|", url, "|", "***", "|", keywords);
    }

    /**
     * Compare 2 WebPage object and determine which one has a higher priority in
     * sorting.
     * <p>
     * 0 indicate that the Index of these 2 WebPages values are the same.
     * </p>
     * <p>
     * 1 indicate that the index of this WebPage object is greater then the para
     * WebPage.
     * </p>
     * <p>
     * -1 indicate that the Index of this WebPage object is less then the parm
     * WebPage.
     * </p>
     * 
     * @param otherWebPage
     *                     The otherWebPage you want to compare this WebPage to.
     * @return
     *         An integer value to indicate which WebPage object has a higher
     *         priority sorted base on index.
     */
    @Override
    public int compareTo(WebPage otherWebPage) {
        if (this.index == otherWebPage.getIndex()) {
            return 0;
        } else if (this.index > otherWebPage.getIndex()) {
            return 1;
        }
        return -1;
    }
}