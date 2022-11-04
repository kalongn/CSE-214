/**
 * This AlreadyAtRootException class allow user to create a
 * AlreadyAtRootException object
 * 
 * @author Ka_Long_Ngai 10/21/2022
 * 
 *         Time used: ~10 mins
 */

public class AlreadyAtRootException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new AlreadyAtRootException instance.
     * 
     */
    public AlreadyAtRootException() {
        super("Already at roots");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new AlreadyAtRootException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public AlreadyAtRootException(String str) {
        super(str);
    }
}
