/**
 * This DirectoryNode class allow user to create a DirectoryNode object
 * Extra credit: Modified to fit a dynamic amount of nodes, can be 3, can be 10,
 * can be whatever amount of childNodes. Default to 10 nodes.
 * 
 * @author Ka_Long_Ngai 10/18/2022
 * 
 *         Time used for basic: ~15 mins
 *         Time used for extra credits: ~45 mins
 */

public class DirectoryNode {

    private String name; // The relative name of this DirectoyNode.
    private DirectoryNode[] childNodes; // Array of ChildNodes
    private boolean isFile; // Determines whether this node is actually a Directory or a file.

    /**
     * Return an empty instance of DirectoyNode with a empty name.
     * <p>
     * All link to child nodes are null by default. And Capacity of a single node is
     * 10 childs.
     * </p>
     * <p>
     * isFile indicate whether this node is a file or directory. True -> is a File,
     * False -> is a Directory.
     * </p>
     */
    public DirectoryNode() {
        this.name = "";
        this.childNodes = new DirectoryNode[10];
        this.isFile = false;
    }

    /**
     * Return an instance of DirectoryNode with input name and boolean isFile.
     * <p>
     * All link to child nodes are null by default. And Capacity of a single node is
     * 10 childs.
     * </p>
     * 
     * @param name
     *               The string value name you want this DirectoryNode to have.
     * @param isFile
     *               The boolean value determine whether this node is a file or
     *               directory, True -> is a File,
     *               False -> is a Directory.
     */
    public DirectoryNode(String name, boolean isFile) {
        this.name = name;
        this.childNodes = new DirectoryNode[10];
        this.isFile = isFile;
    }

    /**
     * Return an instance of DirectoryNode with input name and boolean isFile.
     * <p>
     * All link to child nodes are null by default and the capacity of a node could
     * be adjust.
     * </p>
     * 
     * @param name
     *                   The string value name you want this DirectoryNode to have.
     * @param isFile
     *                   The boolean value determine whether this node is a file or
     *                   directory, True -> is a File,
     *                   False -> is a Directory.
     * @param numOfChild
     *                   The capacity of child node you want a single node to have.
     */
    public DirectoryNode(String name, boolean isFile, int numOfChild) {
        this.name = name;
        this.childNodes = new DirectoryNode[numOfChild];
        this.isFile = isFile;
    }

    /**
     * Return the name of the DirectoryNode object.
     * 
     * @return
     *         The name of the DirectoryNode object.
     */
    public String getName() {
        return name;
    }

    /**
     * Alter the name of the DirectoryNode object.
     * 
     * @param name
     *             The name that you want to change to within the DirectoryNode
     *             object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the isFile of the DirectoryNode object.
     * 
     * @return
     *         The boolean value of the isFile of the DirectoryNode object.
     */
    public boolean getIsFile() {
        return isFile;
    }

    /**
     * Alter the isFile of the DirectoryNode object, but negating the boolean value
     * itself.
     */
    public void setIsFile() {
        this.isFile = !(this.isFile);
    }

    /**
     * Return a childNode base on the index (0 indices).
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * The index is a within the valid range.
     * </p>
     * 
     * @param index
     *              The index of the child node you want to get from this parent
     *              node.
     * @return
     *         The child node of the index.
     */
    public DirectoryNode getChildNode(int index) {
        if (index > childNodes.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        return childNodes[index];
    }

    /**
     * Alter the childNode with the specific index to a the input newChild.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * The index is a within the valid range.
     * </p>
     * 
     * @param newChild
     *                 The newChild node you want this childNode at index to be
     *                 replace with.
     * @param index
     *                 the index of the childNode to be replace.
     */
    public void setChildNode(DirectoryNode newChild, int index) {
        if (index > childNodes.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        this.childNodes[index] = newChild;
    }

    /**
     * Return the entire array of childNodes from this parent node.
     * 
     * @return
     *         The array of childNodes.
     */
    public DirectoryNode[] getChildNodes() {
        return childNodes;
    }

    /**
     * Add newChild to any of the open child position of this node (One of the empty
     * position in array childNodes) regardless of the node being
     * a file or a directory.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * There is at least one empty position in the children of this node (aka empty
     * within the childNodes array).
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * newChild has been added as a child of this node if there is no room for this
     * new node, throw a FullDirectoryException.
     * </p>
     * 
     * @param newChild
     *                 The child node that you want to add to this reference
     *                 DirectoryNode as a children.
     * @throws FullDirectoryException
     *                                When all child position are taken (The array
     *                                childNodes are full).
     */
    public void addAnyChild(DirectoryNode newChild) throws FullDirectoryException {
        for (int i = 0; i < this.getChildNodes().length; i++) {
            if (this.getChildNode(i) == null) {
                this.setChildNode(newChild, i);
                return;
            }
        }
        throw new FullDirectoryException();
    }

    /**
     * Add newChild to any of the open child position of this node ((One of the
     * empty position in array childNodes)
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * There is at least one empty position in the children of this node (aka empty
     * within the childNodes array).
     * </p>
     * <p>
     * The node is not a File
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * newChild has been added as a child of this node if there is no room for this
     * new node, throw a FullDirectoryException.
     * </p>
     * 
     * @param newChild
     *                 The child node that you want to add to this reference
     *                 DirectoryNode as a children.
     * @throws FullDirectoryException
     *                                Thrown when all child position are taken
     *                                (The array childNodes are full).
     * @throws NotADirectoryException
     *                                Thrown when the newChild is a file and not a
     *                                Directory.
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException {
        if (this.getIsFile() == true) {
            throw new NotADirectoryException();
        }
        this.addAnyChild(newChild);
    }

    /**
     * Ensure that there isn't a empty node between 2 node that have actual
     * contents. This method is to
     * assist move method in DirectoryTree.
     */
    public void shiftNode() {
        for (int i = 0; i < this.getChildNodes().length - 1; i++) {
            if (this.getChildNode(i) == null && this.getChildNode(i + 1) == null) {
                return;
            } else if (this.getChildNode(i) == null && this.getChildNode(i + 1) != null) {
                this.setChildNode(this.getChildNode(i + 1), i);
                this.setChildNode(null, i + 1);
            }
        }
    }
}