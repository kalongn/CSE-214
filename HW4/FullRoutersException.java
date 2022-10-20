/**
 * This FullRoutersException class allow user to create a FullRoutersException
 * object
 * 
 * @author Ka_Long_Ngai 10/04/2022
 * 
 *         Time used: ~10 mins
 */

public class FullRoutersException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new FullRoutersException instance.
     * 
     */
    public FullRoutersException() {
        super("All routers are full, please wait.");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new FullRoutersException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public FullRoutersException(String str) {
        super(str);
    }
}
