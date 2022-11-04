/**
 * This NotADirectoryException class allow user to create a
 * NotADirectoryException object
 * 
 * @author Ka_Long_Ngai 10/20/2022
 * 
 *         Time used: ~10 mins
 */

public class NotADirectoryException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new NotADirectoryException instance.
     * 
     */
    public NotADirectoryException() {
        super("The selected child is not a directory.");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new NotADirectoryException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public NotADirectoryException(String str) {
        super(str);
    }
}
