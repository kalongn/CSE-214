/**
 * This BlockStack class allow user to create a BlockStack object
 * 
 * @author Ka_Long_Ngai 09/22/2022
 * 
 * Time used: ~15 mins
*/

import java.util.EmptyStackException;
import java.util.Stack;

public class BlockStack {
    private Stack<CodeBlock> blockStack; //A stack of CodeBlock object named blockStack.

    /**
     * Return an empty instance of BlockStack, which is a new Stack of CodeBlock.
     */
    public BlockStack() {
        this.blockStack = new Stack<CodeBlock>();
    }

    /**
     * Add a CodeBlock object to the top of the blockStack.
     * @param block
     *      The CodeBlock that you want to be added to the top of blockStack.
     */
    public void push(CodeBlock block) {
        blockStack.push(block);
    }

    /**
     * Remove the top element within the blockStack, return the value that was removed.
     * @return
     *      The value that the stack removed.
     * @throws EmptyStackException
     *      Thrown if the stack is empty.
     */
    public CodeBlock pop() throws EmptyStackException{
        if(this.isEmpty()) {
            throw new EmptyStackException();
        }
        return (CodeBlock)blockStack.pop();
    }

    /**
     * Return the value of the top element within the stack.
     * @return
     *      The value of the top element within the stack.
     * @throws EmptyStackException
     *      Thrown if the stack is empty.
     */
    public CodeBlock peek() throws EmptyStackException{
        if(this.isEmpty()) {
            throw new EmptyStackException();
        }
        return (CodeBlock)blockStack.peek();
    }

    /**
     * Return the size of the blockStack object, aka how many elements are there within the stack.
     * @return
     *      The size of the blockStack object.
     */
    public int size() {
        return blockStack.size();
    }

    /**
     * Check if the blockStack object is empty.
     * @return
     *      <p>True: blockStack is empty</p>
     *      <p>False : blockStack is not empty and contain value within </p>
     */
    public boolean isEmpty() {
        return blockStack.empty();
    }

    /**
     * Print out the entire blockStack utilizing a enhanced for loop.
     * @return
     *      A string that contain all CodeBlock within the blockStack, with a line break seperating each CodeBlock.
     */
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        String str = "";
        Stack<CodeBlock> cloneBlockStack = (Stack<CodeBlock>) this.blockStack.clone();
        for( CodeBlock x : cloneBlockStack) {
            str = x.toString() + "\n" + str;
        }
        return str;
    }

}
