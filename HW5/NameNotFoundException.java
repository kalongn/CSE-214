/**
 * This NameNotFoundException class allow user to create a
 * NameNotFoundException object
 * 
 * @author Ka_Long_Ngai 10/21/2022
 * 
 *         Time used: ~10 mins
 */

public class NameNotFoundException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new NameNotFoundException instance.
     * 
     */
    public NameNotFoundException() {
        super("Item does not exist given the name.");
    }

    /**
     * Case by case constructor, use when the error need further
     * explanation.
     * Create a new NameNotFoundException instance
     * 
     * @param str
     *            This is the string message that will be print
     *            instead of the default one.
     */
    public NameNotFoundException(String str) {
        super(str);
    }
}
