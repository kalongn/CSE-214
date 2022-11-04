
/**
 * This BashTerminal class allow user to create a BashTerminal object.
 * **NOTED: This Current terminals work with 10 childeNodes by default.
 * 
 * @author Ka_Long_Ngai 10/18/2022
 * 
 *         Time used for basic: ~1 hours
 *         Time used for extra: ~1 hours
 *          
*/
import java.util.Scanner;

public class BashTerminal {

    /**
     * This ask user to input a string value and
     * return it after the scanner picks it up.
     * <p>
     * Precondition: the string user input is a valid double
     * </p>
     * <p>
     * Postcondition: the string value will be return, if something went wrong, a
     * print statement will appear
     * </p>
     * 
     * @param input
     *              the scanner you are using to read user input.
     * @return
     *         the string value (the one word aka no space allow) that user
     *         inputted.
     */
    private static String scannerReadString(Scanner input) {
        String returnStr = "";
        try {
            returnStr = input.nextLine();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was a valid String");
        }
        return returnStr;
    }

    /**
     * Runs a program which takes user input and builds a DirectoryTree using the
     * commands indicated on hw5.
     * 
     * @param args
     *             allow this main method to run.
     */
    public static void main(String[] args) {
        DirectoryTree fileStructure = new DirectoryTree();
        boolean terminateCheck = true;
        Scanner input = new Scanner(System.in);
        String command = "", nameOfNode = "";
        System.out.println("Starting bash terminal.");
        while (terminateCheck) {
            System.out.print("[user@host]: $ ");
            command = scannerReadString(input);
            switch (command) {
                case "pwd":
                    System.out.println(fileStructure.presentWorkingDirectory());
                    break;
                case "ls":
                    System.out.println(fileStructure.listDirectory());
                    break;
                case "ls -R":
                    System.out.println();
                    fileStructure.printDirectoryTree(0);
                    System.out.println();
                    break;
                case "cd /":
                    fileStructure.resetCusor();
                    break;
                case "cd ..":
                    try {
                        fileStructure.changeToPreviousDirectory();
                    } catch (NotADirectoryException ex) {
                        System.out.println("ERROR: Cannot change directory into a file.");
                    } catch (DirectoryNotFoundException ex) {
                        System.out.println("ERROR: No such directory named \'" + nameOfNode + "\'.");
                    } catch (AlreadyAtRootException ex) {
                        System.out.println("ERROR: Already at root directory.");
                    } catch (Exception e) {
                        System.out.println("An unknown Error has occured.");
                    }
                    break;
                case "exit":
                    System.out.println("Program terminating normally");
                    terminateCheck = false;
                    input.close();
                    break;
                default:
                    if (command.length() < 2) {
                        System.out.println("ERROR: Input Command is invalid.");
                        break;
                    } else if (command.substring(0, 2).equals("cd")) {
                        if (command.contains("/")) {
                            try {
                                nameOfNode = command.substring(3);
                                fileStructure.changeToDirectoryAbsolute(nameOfNode);
                            } catch (NotADirectoryException ex) {
                                System.out.println("ERROR: Cannot change directory into a file.");
                            } catch (DirectoryNotFoundException ex) {
                                System.out.println("ERROR: No such directory named \'" + nameOfNode + "\'.");
                            } catch (Exception e) {
                                System.out.println("An unknown Error has occured.");
                            }
                        } else {
                            try {
                                nameOfNode = command.substring(3);
                                fileStructure.changeDirectory(nameOfNode);
                            } catch (NotADirectoryException ex) {
                                System.out.println("ERROR: Cannot change directory into a file.");
                            } catch (DirectoryNotFoundException ex) {
                                System.out.println("ERROR: No such directory named \'" + nameOfNode + "\'.");
                            } catch (Exception e) {
                                System.out.println("An unknown Error has occured.");
                            }
                        }
                    } else if (command.substring(0, 2).equals("mv")) {
                        try {
                            String source = command.substring(3, command.substring(3).indexOf(" ") + 3).trim();
                            String destintion = command.substring(command.substring(3).indexOf(" ") + 4).trim();
                            fileStructure.move(source, destintion);
                        } catch (NotADirectoryException ex) {
                            System.out.println("ERROR: Cannot change directory into a file.");
                        } catch (DirectoryNotFoundException ex) {
                            System.out.println("ERROR: No such directory named \'" + nameOfNode + "\'.");
                        } catch (FullDirectoryException ex) {
                            System.out.println("ERROR: Present directory is full.");
                        } catch (AlreadyAtRootException ex) {
                            System.out.println("ERROR: Already at root directory.");
                        } catch (Exception e) {
                            System.out.println("An unknown Error has occured.");
                        }
                    } else if (command.substring(0, 4).equals("find")) {
                        try {
                            nameOfNode = command.substring(5);
                            fileStructure.find(nameOfNode);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("ERROR: No space is allow in the name of the file.");
                        } catch (NameNotFoundException ex) {
                            System.out.println("ERROR: No such file exists.");
                        } catch (Exception e) {
                            System.out.println("An unknown Error has occured.");
                        }
                    } else if (command.substring(0, 5).equals("mkdir")) {
                        try {
                            nameOfNode = command.substring(6);
                            fileStructure.makeDirectory(nameOfNode);
                        } catch (FullDirectoryException ex) {
                            System.out.println("ERROR: Present directory is full.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("ERROR: No space is allow in the name of the directory.");
                        } catch (Exception e) {
                            System.out.println("An unknown Error has occured.");
                        }
                    } else if (command.substring(0, 5).equals("touch")) {
                        try {
                            nameOfNode = command.substring(6);
                            fileStructure.makeFile(nameOfNode);
                        } catch (FullDirectoryException ex) {
                            System.out.println("ERROR: Present directory is full.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("ERROR: No space is allow in the name of the file.");
                        } catch (Exception e) {
                            System.out.println("An unknown Error has occured.");
                        }
                    } else {
                        System.out.println("ERROR: Input Command is invalid.");
                    }
                    break;
            }
        }
    }
}