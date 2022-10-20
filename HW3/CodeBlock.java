/**
 * This CodeBlock class allow user to create a CodeBlock object
 * 
 * @author Ka_Long_Ngai 09/22/2022
 * 
 * Time used: ~20 mins
*/

public class CodeBlock {

    //Enum declaration of all static keywords. Allow for switches and comparison much eaiser.
    public static enum BlockType {
        DEF,
        FOR,
        WHILE,
        IF,
        ELIF,
        ELSE;
    }

    private Complexity blockComplexity; //The block complexity that this CodeBlock object has.
    private Complexity highestSubComplexity; //The highest sub complexity that this codeBlock object has.
    private String name, loopVariable; //The name(amount of indentation will determine this) and the loopVariable(While loop determination.)

    /**
     * Return an empty instance of CodeBlock object.
     * <p>blockComplexity default to null.</p>
     * <p>highestSubComplexity default to a complexity object with 0 nPower and 0 logPower.</p>
     * <p>name is default to an empty String.</p>
     * <p>loopVariable is default to null.</p>
     */
    public CodeBlock() {
        this.blockComplexity = null;
        this.highestSubComplexity = new Complexity(0,0);
        this.name = "";
        this.loopVariable = null;
    }

    /**
     * Return a mostly empty instance of CodeBlock object.
     * <p>blockComplexity set to the param blockCompexity.</p>
     * <p>highestSubComplexity default to a complexity object with 0 nPower and 0 logPower.</p>
     * <p>name is default to an empty String.</p>
     * <p>loopVariable is default to null.</p>
     * @param blockComplexity
     *      The blockComplexity you want this CodeBlock object to default to instead of null.
     */
    public CodeBlock(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
        this.highestSubComplexity = new Complexity(0,0);
        this.name = "";
        this.loopVariable = null;
    }

    /**
     * Return the blockComplexity of the CodeBlock object.
     * @return
     *      The blockComplexity of the CodeBlock object.
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * Alter the blockComplexity of the CodeBlock object.
     * @param blockComplexity
     *      The blockComplexity that you want to change to within the CodeBlock object.
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /**
     * Return the highestSubComplexity of the CodeBlock object.
     * @return
     *      The highestSubComplexity of the CodeBlock object.
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     * Alter the highestSubComplexity of the CodeBlock object.
     * @param highestSubComplexity
     *      The highestSubComplexity that you want to change to within the CodeBlock object.
     */
    public void setHighestSubComplexity(Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }

    /**
     * Return the name of the CodeBlock object.
     * @return
     *      The name of the CodeBlock object.
     */
    public String getName() {
        return name;
    }

    /**
     * Alter the name of the CodeBlock object.
     * @param name
     *      The name that you want to change to within the CodeBlock object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the loopVariable of the CodeBlock object.
     * @return
     *      The loopVariable of the CodeBlock object.
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     * Alter the loopVariable of the CodeBlock object.
     * @param loopVariable
     *      The loopVariable that you want to change to within the CodeBlock object.
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    /**
     * Check if a the param String line contain a keyword which will match to each one of the enum. If it does, it will return the
     * respective keyword. If nothing is found, it will return an empty string.
     * @param line
     *      A String value, in this context, it is the specific line of the python file.
     * @return
     *      The respective keyword that match the enum, if nothing is found, return an empty string.
     */
    public static String containsKeywords(String line) {
        line = line.replaceAll(" ", "");
        for(BlockType i : BlockType.values()) {
                if(line.contains(i.name()) && line.indexOf(i.name()) == 0 && line.endsWith(":")) {
                    return i.name();
                }
        }
        return "";
    }

    /**
     * Add the blockComplexity and highestSubComexplity of this reference of CodeBlock object.
     * @return
     *      The totalComplexity of this refernce of CodeBlock object.
     */
    public Complexity getTotalComplexity() {
        return this.blockComplexity.complexityAddition(this.highestSubComplexity);
    }

    /**
     * An override toString method that print the CodeBlock object in an organize way.
     * @return
     *      A string value that present the CodeBlock in a single line.
     */
    @Override
    public String toString() {
        String returnStr = "";
        returnStr+=String.format("%-9s%-16s%-30s%-30s", "", "BLOCK " + name + ":", "block complexity = " + this.blockComplexity, " highest sub-complexity = " + this.highestSubComplexity);
        return returnStr;
    }

}
