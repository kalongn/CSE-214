/**
 * This DirectoryTree class allow user to create a DirectoryTree object
 * Extra credit: section in the end, and code modified to fit the new
 * DirectoryNode class.
 * 
 * @author Ka_Long_Ngai 10/18/2022
 * 
 *         Time used for basic: ~4 hours
 *         Time used for extra credits: ~6 hours
 */

public class DirectoryTree {

    private DirectoryNode root, cursor; // the root node and cursor node reference to this DirectoryTree object.
    private String pwd; // locate where the cursor is at within this DirectoryTree.
    private static String findPath = ""; // a "global" variable that assist when using the method find. (the actual
                                         // path)
    private static boolean found = false; // a "global" variable that assist when using the method find. (determine if a
                                          // target instances exist)

    /**
     * Return a DirectoryTree object with a root node call "root", cursor will
     * reference
     * this node. pwd will be set to root.
     * <p>
     * Postconditions
     * </p>
     * <p>
     * The tree contains a single DirectoryNode named "root", and both cursor and
     * root reference this node.
     * </p>
     * <p>
     * NOTE: Do not confuse the name of the directory with the name of the reference
     * variable. The DirectoryNode member variable of DirectoryTree named root
     * should reference a DirectoryNode who's name is "root", i.e.
     * root.getName().equals("root") is true.
     * </p>
     */
    public DirectoryTree() {
        root = new DirectoryNode("root", false);
        cursor = root;
        pwd = "root";
    }

    /**
     * Move the cursor to the root node of the tree.
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor now references the root node of the tree.
     * </p>
     */
    public void resetCusor() {
        cursor = root;
        pwd = "root";
    }

    /**
     * Moves the cursor to the directory with the name indicated by name. It also
     * keep track of the pwd of cursor when switching directory. (Relative path
     * only)
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * 'name' references a valid directory ('name' cannot reference a file).
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor now references the directory with the name indicated by name. If a
     * child could not be found with the name, then A DirectoryNotFoundException
     * will be thrown. If the name was not a directory name, A
     * NotADirectoryException will be thrown.
     * </p>
     * 
     * @param name
     *             The name of the directory you want to move to.
     * @throws NotADirectoryException
     *                                    Thrown when the name searched exist but it
     *                                    is not a Directory.
     * @throws DirectoryNotFoundException
     *                                    Thrown when the name does not exist within
     *                                    this child node.
     */
    public void changeDirectory(String name) throws NotADirectoryException, DirectoryNotFoundException {
        boolean notExist = true, existButIsAFile = false;
        for (int i = 0; i < cursor.getChildNodes().length; i++) {
            if (cursor.getChildNode(i) != null && cursor.getChildNode(i).getName().equals(name)) {
                notExist = false;
                if (cursor.getChildNode(i).getIsFile() == false) {
                    pwd = pwd + "/" + cursor.getChildNode(i).getName();
                    cursor = cursor.getChildNode(i);
                } else {
                    existButIsAFile = true;
                }
            }
        }
        if (existButIsAFile) {
            throw new NotADirectoryException();
        }
        if (notExist) {
            throw new DirectoryNotFoundException();
        }
    }

    /**
     * Returns a String containing the path of directory names from the root node of
     * the tree to the cursor, with each name separated by a forward slash "/".
     * <p>
     * e.g. root/home/user/Documents if the cursor is at Documents in the example
     * above.
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor remains at the same DirectoryNode
     * </p>
     * 
     * @return
     *         The string value of pwd, aka the current location of cursor within
     *         this tree.
     */
    public String presentWorkingDirectory() {
        return this.pwd;
    }

    /**
     * Returns a String containing a space-separated list of names of all the child
     * directories or files of the cursor.
     * <p>
     * e.g. dev home bin if the cursor is at root in the example above.
     * </p>
     * <p>
     * Postconditions
     * </p>
     * <p>
     * The cursor remains at the same DirectoryNode.
     * </p>
     * 
     * @return
     *         A formatted String of DirectoryNode names.
     */
    public String listDirectory() {
        String returnString = "";
        for (int i = 0; i < cursor.getChildNodes().length; i++) {
            if (cursor.getChildNode(i) != null) {
                if (i == 0) {
                    returnString = cursor.getChildNode(i).getName();
                } else {
                    returnString = returnString + "    " + cursor.getChildNode(i).getName();
                }
            }
        }
        return returnString;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory
     * tree, starting from the cursor.
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor remains at the same DirectoryNode.
     * </p>
     * 
     * @param amountOfIndents
     *                        Use for recursion to determins how many indentation is
     *                        required for the specific DirectNode within this tree
     *                        starting from cursor.
     */
    public void printDirectoryTree(int amountOfIndents) {
        DirectoryNode relativeRoot = cursor;
        String indents = "";
        for (int i = 0; i < amountOfIndents; i++) {
            indents += "    ";
        }
        if (cursor.getIsFile()) {
            System.out.println(indents + "- " + cursor.getName());
        } else {
            System.out.println(indents + "|- " + cursor.getName());
        }
        for (int i = 0; i < cursor.getChildNodes().length; i++) {
            if (cursor.getChildNode(i) != null) {
                cursor = cursor.getChildNode(i);
                this.printDirectoryTree(amountOfIndents + 1);
                cursor = relativeRoot;
            }
        }
    }

    /**
     * Creates a directory with the indicated name and adds it to the children of
     * the cursor node. Remember that children of a node are added with preorder
     * method.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * 'name' is a legal argument (does not contain spaces " " or forward slashes
     * "/").
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * A new DirectoryNode has been added to the children of the cursor, or an
     * exception has been thrown.
     * </p>
     * 
     * @param name
     *             The name of the directory to add.
     * @throws IllegalArgumentException
     *                                  Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     *                                  Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException {
        if ((name.contains(" ") || name.contains("/"))) {
            throw new IllegalArgumentException("Your file name has space in it.");
        }
        DirectoryNode newChild = new DirectoryNode(name, false);
        cursor.addAnyChild(newChild);
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the
     * cursor node. Remember that children of a node are added with preorder
     * method.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * 'name' is a legal argument (does not contain spaces " " or forward slashes
     * "/").
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * A new DirectoryNode has been added to the children of the cursor, or an
     * exception has been thrown.
     * </p>
     * 
     * @param name
     *             The name of the file to add.
     * @throws IllegalArgumentException
     *                                  Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     *                                  Thrown if all child references of this
     *                                  directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException {
        if ((name.contains(" ") || name.contains("/"))) {
            throw new IllegalArgumentException("Your file name has space in it.");
        }
        DirectoryNode newChild = new DirectoryNode(name, true);
        cursor.addAnyChild(newChild);
    }

    // Extra credits code sections

    /**
     * A recursive method that traverse the entire tree to find all the node with a
     * name that is target.
     * If found, this will print out the path to that node.
     * 
     * @param target
     *                 The name of the node you want to find within this tree.
     * @param currNode
     *                 The node that act as a cursor without disturbing cursor
     *                 actual location within this recursion method.
     * @return
     */
    private static boolean findHelper(String target, DirectoryNode currNode) {
        if (currNode == null) {
            return false;
        }
        if (currNode.getName().equals("root")) {
            findPath += currNode.getName();
        } else {
            findPath += "/" + currNode.getName();
        }
        if (currNode.getName().equals(target)) {
            System.out.println(findPath);
            found = true;
        }
        for (int i = 0; i < currNode.getChildNodes().length; i++) {
            if (findHelper(target, currNode.getChildNode(i))) {
                return true;
            }
        }
        if (!findPath.equals("root")) {
            findPath = findPath.substring(0, findPath.lastIndexOf("/"));
        }
        return false;
    }

    /**
     * The actual find method that will call its helper method to start a recursion.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * 'name' is a legal argument (does not contain spaces " " or forward slashes
     * "/").
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The path of all the node that is with the target name will be print, if none
     * present, and exception is thrown.
     * </p>
     * 
     * @param name
     *             The name of node you want to find within this tree.
     * @throws IllegalArgumentException
     *                                  Thrown if the name is not valid.
     * @throws NameNotFoundException
     *                                  Thrown if the target doest not exist.
     */
    public void find(String name) throws IllegalArgumentException, NameNotFoundException {
        if ((name.contains(" ") || name.contains("/"))) {
            throw new IllegalArgumentException("Your file name has space in it.");
        }
        DirectoryNode rootNode = root;
        findPath = "";
        found = false;
        findHelper(name, rootNode);
        if (!found) {
            throw new NameNotFoundException();
        }
    }

    /**
     * An assist method for both cd method, determine the occurance of a target
     * character within a String.
     * 
     * @param input
     *               The input String to be check.
     * @param target
     *               The target character to check for occurance.
     * @return
     *         The amount of time the target character appear within the input
     *         String.
     */
    private static int charOccuranceInString(String input, char target) {
        if (input.equals("root")) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * Moves the cursor up to its parent directory( does nothing at root).
     * Was achieved by string manupilation with current working directory string
     * value.
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor will be referencing the parent node. if not an exception is
     * thrown.
     * </p>
     * 
     * @throws NotADirectoryException
     *                                    Thrown when the name searched exist but it
     *                                    is not a Directory.
     * @throws DirectoryNotFoundException
     *                                    Thrown when the name does not exist within
     *                                    this child node.
     * @throws AlreadyAtRootException
     *                                    Thrown if the current cursor is already at
     *                                    the root.
     */
    public void changeToPreviousDirectory()
            throws NotADirectoryException, DirectoryNotFoundException, AlreadyAtRootException {
        if (cursor == root) {
            throw new AlreadyAtRootException();
        }
        String pathToCurrentNode = pwd;
        String nextDirectory = "";
        pathToCurrentNode = pathToCurrentNode.substring(4, pathToCurrentNode.lastIndexOf("/"));
        this.resetCusor();
        int loopAmount = charOccuranceInString(pathToCurrentNode, '/');
        for (int i = 0; i < loopAmount; i++) {
            pathToCurrentNode = pathToCurrentNode.substring(1);
            if (pathToCurrentNode.contains("/")) {
                nextDirectory = pathToCurrentNode.substring(0, pathToCurrentNode.indexOf("/"));
                pathToCurrentNode = pathToCurrentNode.substring(pathToCurrentNode.indexOf("/"));
            } else {
                nextDirectory = pathToCurrentNode;
            }
            this.changeDirectory(nextDirectory);
        }
    }

    /**
     * Moves the cursor to the directory with the absolute path. Was able to
     * achieved
     * with string splicing with the String pwd.
     * <p>
     * 'path' must contain all valid 'name' for each node references a valid
     * directory ('name' cannot reference a file).
     * </p>
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * The cursor now references the directory with the absolute path. If a
     * child could not be found with the name, then A DirectoryNotFoundException
     * will be thrown. If the name that is apart of the path was not a directory
     * name, A
     * NotADirectoryException will be thrown.
     * </p>
     * 
     * @param path
     *             the absolute path to a certain file/directory.
     * @throws NotADirectoryException
     *                                    Thrown when the name searched exist but it
     *                                    is not a Directory.
     * @throws DirectoryNotFoundException
     *                                    Thrown when the name does not exist within
     *                                    this child node.
     */
    public void changeToDirectoryAbsolute(String path) throws NotADirectoryException, DirectoryNotFoundException {
        String nextDirectory = "";
        this.resetCusor();
        if (path.indexOf("root") == 0) {
            path = path.substring(5);
        }
        int loopAmount = charOccuranceInString(path, '/');
        for (int i = 0; i < loopAmount; i++) {
            if (path.contains("/")) {
                nextDirectory = path.substring(0, path.indexOf("/"));
                this.changeDirectory(nextDirectory);
                path = path.substring(path.indexOf("/") + 1);
            }
        }
        this.changeDirectory(path);
    }

    /**
     * Moves a file or directory specified by src to dst, including all children.
     * (Note that src and dst are absolute paths). Cursor should not change
     * location,
     * it should remain the same before and after this code. But it does move within
     * the code.
     * 
     * @param src
     *            The source path aka the node you want to move.
     * @param dst
     *            The destination path so that the source path node can move to.
     * @throws NotADirectoryException
     *                                    Thrown when the name in either path
     *                                    searched exist but it
     *                                    is not a Directory.
     * @throws DirectoryNotFoundException
     *                                    Thrown when the name in either path does
     *                                    not exist within
     *                                    this child node.
     * @throws AlreadyAtRootException
     *                                    Thrown if the current cursor is already at
     *                                    the root.
     * @throws FullDirectoryException
     *                                    Thrown if the destination path already
     *                                    have full childNodes and cannot add more
     *                                    to it.
     */
    public void move(String src, String dst)
            throws NotADirectoryException, DirectoryNotFoundException, AlreadyAtRootException, FullDirectoryException {
        DirectoryNode cursorLocation = cursor, nodeTobeMove = null;
        String itemToBeMovedName = "";
        this.changeToDirectoryAbsolute(src);
        this.changeToPreviousDirectory();
        itemToBeMovedName = src.substring(src.lastIndexOf("/") + 1);
        for (int i = 0; i < cursor.getChildNodes().length; i++) {
            if (cursor.getChildNode(i).getName().equals(itemToBeMovedName)) {
                nodeTobeMove = cursor.getChildNode(i);
                cursor.setChildNode(null, i);
                cursor.shiftNode();
                break;
            }
        }
        this.changeToDirectoryAbsolute(dst);
        cursor.addAnyChild(nodeTobeMove);
        cursor = cursorLocation;
    }
}