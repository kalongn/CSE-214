/**
 * This DirectoryNotFoundException class allow user to create a
 * DirectoryNotFoundException object
 * 
 * @author Ka_Long_Ngai 10/18/2022
 * 
 *         Time used: ~10 mins
 */

public class DirectoryNotFoundException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new DirectoryNotFoundException instance.
     * 
     */
    public DirectoryNotFoundException() {
        super("Directory not found.");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new DirectoryNotFoundException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public DirectoryNotFoundException(String str) {
        super(str);
    }
}
