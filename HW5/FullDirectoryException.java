/**
 * This FullDirectoryException class allow user to create a
 * FullDirectoryException object
 * 
 * @author Ka_Long_Ngai 10/18/2022
 * 
 *         Time used: ~10 mins
 */

public class FullDirectoryException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new FullDirectoryException instance.
     * 
     */
    public FullDirectoryException() {
        super("All Child Directories are full");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new FullDirectoryException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public FullDirectoryException(String str) {
        super(str);
    }
}
