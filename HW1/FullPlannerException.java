/**
 * This FullPlannerException class allow 
 *  user to create FullPlannerException object
 * 
 * @author Ka_Long_Ngai 08/27/2022
 * 
 * Time used: ~15 mins
*/

public class FullPlannerException extends Exception {
    /**
     * Default Constructor, General use case for this error.
     * Create a new FullPlannerException instance.
     * 
    */  
    public FullPlannerException() {
        super("The Planner is currently at max capacity of 50");
    }

    /**
     * Case by case constructor, use when the error need further 
     *  explanation.
     * Create a new FullPlannerExpcetion instance
     * 
     * @param str
     *      This is the string message that will be print 
     *       instead of the default one.
     */
    public FullPlannerException(String str) {
        super(str);
    }
}
