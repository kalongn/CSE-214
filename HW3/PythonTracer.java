/**
 * This PythonTracer class allow calculate the Big O Notation of a specific python file.
 * 
 * @author Ka_Long_Ngai 09/24/2022
 * 
 * Time used: ~10 hours
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PythonTracer {

    public static final int SPACE_COUNT = 4; //A constant value for how many spaces there are for each indentation.

    /**
     * This static allow us to remove duplicate code to check when the indentation is less then the size of the stack in the
     * begining of the loop and when the size of the stack is greater then one at the end of the loop. It basically pop 1 element
     * off of codeBlockStack parameter and compare the complexity to the new stack Top to the oldComplexity. Therefore updating
     * the highestSubComplexity by that and returning a String of the previous element name to check for the name of the next block.
     * @param codeBlockStack
     *      The Blockstack object you want this operation to be performed on.
     * @return
     *      The String 'name' value of the CodeBlock oldTop.
     */
    public static String updateHighestSubPriority(BlockStack codeBlockStack) {
        CodeBlock oldTop = codeBlockStack.pop();
        String tempName = oldTop.getName();
        Complexity oldTopComplexity = oldTop.getTotalComplexity();
        if(oldTopComplexity.compareComplexity(codeBlockStack.peek().getHighestSubComplexity())) {
            System.out.println("   Leaving block " + oldTop.getName() + ", updating block " + codeBlockStack.peek().getName() + ":");
            codeBlockStack.peek().setHighestSubComplexity(oldTopComplexity);
            System.out.println(codeBlockStack.peek() + "\n");
            } else {
                System.out.println("   Leaving block " + oldTop.getName() + ", nothing to update.");
                System.out.println(codeBlockStack.peek() + "\n");
            }
        return tempName;
    }

    /**
     * Opens the indicated file and traces through the code of the Python function containted within the file, returning the Big-Oh order
     * of complexity of the function. During operation, the stack trace should be printed to the console as code blocks are pushed to/popped from the stack.
     * <p>Preconditions: filename is not null and the file it names contains a single pythong function with valid syntax</p>
     * @param fileName
     *      The name of the python code file.
     * @return
     *      A complexity object representing the total order of complexity of the Python code contained within the file.
     * @throws FileNotFoundException
     *      Thrown if user's input python code file were not found.
     */
    public static Complexity traceFile(String fileName) throws FileNotFoundException{
        BlockStack codeBlockStack = new BlockStack(); //the Stack
        int prevIndents = 0, indents = 0, indentationCount = 1; 
        /*prevIndents track the previous line indentation, 
        indents track current indentation, indentationCount is for the name of the block*/
        boolean firstIteration = true; //allow a specific code if it is the first iteration
        String line, tempName = ""; //line is each line of the file, tempName is the last character of the name String of the block.
        File file = new File(fileName); //read a file
        Scanner inputFile = new Scanner(file);
        while(inputFile.hasNextLine()) {
            line = inputFile.nextLine();
            if( (!(line.trim().isEmpty())) && line.trim().charAt(0) != '#' && line.trim().contains("import") == false)
            {
                indents = (line.length() - line.replaceAll("    ", "").length())/ SPACE_COUNT;
                while(indents < codeBlockStack.size()) {
                    if(indents == 0) {
                        System.out.println("   Leaving block " + codeBlockStack.peek().getName() + ".\n");
                        inputFile.close();
                        return codeBlockStack.peek().getHighestSubComplexity();
                    } else {
                        tempName = updateHighestSubPriority(codeBlockStack);
                    }
                }
                String potentialKeywordStr = CodeBlock.containsKeywords(line.toUpperCase());
                if(!potentialKeywordStr.equals("")) {
                    String keyword = potentialKeywordStr;
                    switch(keyword) {
                        case "FOR":
                            if(line.indexOf("log_N:") != -1)
                            {
                                codeBlockStack.push(new CodeBlock(new Complexity(0,1)));
                            } else {
                                codeBlockStack.push(new CodeBlock(new Complexity(1,0)));
                            }
                            break;
                        case "WHILE":
                            CodeBlock temp = new CodeBlock(new Complexity(0,0));
                            temp.setLoopVariable(line.replaceAll(" ", "").substring(5, line.replaceAll(" ", "").indexOf(">")));
                            codeBlockStack.push(temp);
                            break;
                        default:
                            codeBlockStack.push(new CodeBlock(new Complexity(0,0)));
                            break;
                    }
                    if(firstIteration) {
                        codeBlockStack.peek().setName("" + indentationCount);
                        firstIteration = false;
                    } else {
                        CodeBlock temp = codeBlockStack.pop();
                        if(indents > prevIndents) {
                            indentationCount = 1;
                            temp.setName(codeBlockStack.peek().getName() + "." + indentationCount);
                        } else {
                            indentationCount = Character.getNumericValue(tempName.charAt(tempName.length()-1));
                            indentationCount++;
                            temp.setName(codeBlockStack.peek().getName() + "." + indentationCount);
                        }
                        codeBlockStack.push(temp);
                    }
                    prevIndents = indents;
                    System.out.println("   Entering block " + codeBlockStack.peek().getName() + " '" + keyword.toLowerCase() + "':");
                    System.out.println(codeBlockStack.peek() + "\n");
                } else if(codeBlockStack.peek().getLoopVariable()!= null && line.indexOf(codeBlockStack.peek().getLoopVariable()) != -1 
                 && line.trim().indexOf(codeBlockStack.peek().getLoopVariable()) == 0) {
                    System.out.println("   Found update statement, updating block " + codeBlockStack.peek().getName() + ":");
                    if(line.indexOf(" -= 1") != -1) {
                        codeBlockStack.peek().setBlockComplexity(new Complexity(1,0));
                        System.out.println(codeBlockStack.peek() + "\n");
                    } else {
                        codeBlockStack.peek().setBlockComplexity(new Complexity(0,1));
                        System.out.println(codeBlockStack.peek() + "\n");
                    }
                }
            }
        }
        while(codeBlockStack.size() > 1) {
            tempName = updateHighestSubPriority(codeBlockStack);
        }
        System.out.println("   Leaving block " + codeBlockStack.peek().getName() + ".\n");
        inputFile.close();
        return codeBlockStack.pop().getHighestSubComplexity();
    }

    /**
     * Prompts the user for the name of a file containing a single python function, 
     * determines its order of complexity, and prints the result
     * to the console.
     * @param args
     *      The main args allow the code the run.
     * @throws FileNotFoundException
     *      Thrown if user's input file were not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String option;
        boolean terminateCheck = true;
        while (terminateCheck) {
            System.out.print("Please enter a file name (or 'quit' to quit): ");
            option = input.next();
            System.out.println();
            switch(option) {
                case "quit":
                    System.out.println("Program terminating successfully...\n");
                    terminateCheck = false;
                    break;
                default:
                    System.out.println("Overall complexity of " + option.substring(0,option.lastIndexOf(".")) + ": " + traceFile(option) + "\n");
            }
        }
        input.close();
    }
}
