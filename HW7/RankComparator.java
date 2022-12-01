
/**
 * This RankComparator class allow user to create a
 * RankComparator object
 * 
 * @author Ka_Long_Ngai 11/16/2022
 */

import java.util.Comparator;

public class RankComparator implements Comparator<WebPage> {

    /**
     * Compare 2 WebPage object and determine which one has a higher priority in
     * sorting.
     * <p>
     * 0 indicate that the Rank of these 2 WebPages values are the same.
     * </p>
     * <p>
     * 1 indicate that the Rank of WebPage pageOne object is less then the WebPage
     * pageTwo.
     * </p>
     * <p>
     * -1 indicate that the Rank of WebPage pageOne object is greater then the
     * WebPage
     * pageTwo.
     * </p>
     * 
     * @param pageOne
     *                The first WebPage being compared to the other WebPage object.
     * @param pageTwo
     *                The second WebPage being compared to the other WebPage object.
     * @return
     *         An int to indicate whether pageOne or pageTwo Rank value is
     *         greater/lesser/equal to each other.
     */
    @Override
    public int compare(WebPage pageOne, WebPage pageTwo) {
        if (pageOne.getRank() == pageTwo.getRank()) {
            return 0;
        } else if (pageOne.getRank() > pageTwo.getRank()) {
            return -1;
        }
        return 1;
    }
}
