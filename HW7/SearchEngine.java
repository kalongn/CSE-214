
/**
 * This SearchEngine class allow user to create a
 * SearchEngine object
 * 
 * 
 * @author Ka_Long_Ngai 11/17/2022
 */

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {

    public static final String PAGES_FILE = "pages.txt"; // the File for PAGES import.
    public static final String LINKS_FILE = "links.txt"; // the File for LINKS import.

    private WebGraph web; // The Web of this Search Engine.

    /**
     * Return a new instance of SearchEngine with an empty WebGraph.
     */
    public SearchEngine() {
        web = new WebGraph();
    }

    /**
     * Return the Web of this SearchEngine Object.
     * 
     * @return
     *         The Web of this SearchEngine Object.
     */
    public WebGraph getWeb() {
        return this.web;
    }

    /**
     * Alter this SearchEngine object's Web to the param web.
     * 
     * @param web
     *            The web we want this SearchEngine to change to.
     */
    public void setWeb(WebGraph web) {
        this.web = web;
    }

    /**
     * This ask user to input a string value (one word aka no space allow) and
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
     * Provide a menu prompt and implement the following menu options:
     * (AP) - Add a new page to the graph.
     * (RP) - Remove a page from the graph.
     * (AL) - Add a link between pages in the graph.
     * (RL) - Remove a link between pages in the graph.
     * (P) - Print the graph.
     * (I) Sort based on index (ASC)
     * (U) Sort based on URL (ASC)
     * (R) Sort based on rank (DSC)
     * (S) - Search for pages with a keyword.
     * (GUI) - A GUI with the current table.
     * (Q) - Quit.
     * 
     * @param args
     *             The main args allow this method to run on startup.
     */
    public static void main(String[] args) {
        boolean terminateCheck = true;
        SearchEngine alphabet = new SearchEngine();
        Scanner input = new Scanner(System.in);
        String command = "", src = "", des = "";

        try {
            System.out.println("Loading WebGraph data...");
            alphabet.setWeb(WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE));
            System.out.println("Success!\n");
        } catch (IllegalArgumentException ex) {
            System.out.println("\nError: PAGES_FILE or LINKS_FILE is not correct.\n");
        } catch (Exception ex) {
            System.out.println("\nError: an unknown error occcured.\n");
        }

        while (terminateCheck) {
            System.out.println(
                    "Menu:\n   (AP) - Add a new page to the graph.\n   (RP) - Remove a page from the graph.\n   (AL) - Add a link between pages in the graph.\n   (RL) - Remove a link between pages in the graph.\n   (P)  - Print the graph.\n   (S)  - Search for pages with a keyword.\n   (GUI) - A GUI with the current table.\n   (Q)  - Quit.");
            System.out.print("\nPlease select an option: ");
            command = scannerReadString(input);
            switch (command.toUpperCase()) {
                case "AP":
                    System.out.print("Enter a URL: ");
                    String Url = scannerReadString(input);
                    System.out.print("Enter keywords (space-separated): ");
                    command = scannerReadString(input);
                    ArrayList<String> keywords = WebGraph.keywordsSplit(command);
                    try {
                        alphabet.getWeb().addPage(Url, keywords);
                        System.out.println("\n" + Url + " successfully added to the WebGraph!\n");
                    } catch (IllegalArgumentException ex) {
                        System.out.println(
                                "\nError: " + Url + " already exists in the WebGraph. Could not add new WebPage.\n");
                    } catch (Exception ex) {
                        System.out.println("\nError: an unknown error occcured.\n");
                    }
                    break;
                case "RP":
                    System.out.print("Enter a URL: ");
                    command = scannerReadString(input);
                    alphabet.getWeb().removePages(command);
                    System.out.println("\n" + command + " has been removed from the graph!\n");
                    break;
                case "AL":
                    System.out.print("Enter a source URL: ");
                    src = scannerReadString(input);
                    System.out.print("Enter a destination URL: ");
                    des = scannerReadString(input);
                    try {
                        alphabet.getWeb().addLink(src, des);
                        System.out.println("\nLink successfully added from " + src + " to " + des + "!\n");
                    } catch (IllegalArgumentException ex) {
                        if (ex.getMessage().equals("The url for destination does not exist.")) {
                            System.out.println("\nError: " + des + " could not be found in the WebGraph.\n");
                        } else if ((ex.getMessage().equals("The url for source does not exist."))) {
                            System.out.println("\nError: " + src + " could not be found in the WebGraph.\n");
                        } else {
                            System.out.println("\nError: link was already established");
                        }
                    } catch (Exception ex) {
                        System.out.println("\nError: an unknown error occcured.\n");
                    }
                    break;
                case "RL":
                    System.out.print("Enter a source URL: ");
                    src = scannerReadString(input);
                    System.out.print("Enter a destination URL: ");
                    des = scannerReadString(input);
                    alphabet.getWeb().removeLink(src, des);
                    System.out.println("\nLink removed from " + src + " to " + des + "!\n");
                    break;
                case "P":
                    System.out.print(
                            "\n   (I) Sort based on index (ASC)\n   (U) Sort based on URL (ASC)\n   (R) Sort based on rank (DSC)");
                    System.out.print("\n\nPlease select an option: ");
                    command = scannerReadString(input);
                    try {
                        alphabet.getWeb().printTable(command);
                    } catch (Exception ex) {
                        System.out.println("\nError: an unknown error occcured.\n");
                    }
                    break;
                case "S":
                    System.out.print("Search keyword: ");
                    command = scannerReadString(input);
                    System.out.println();
                    try {
                        alphabet.getWeb().Search(command);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("\nNo search results found for the keyword " + command + ".\n");
                    } catch (Exception ex) {
                        System.out.println("\nError: an unknown error occcured.\n");
                    }
                    break;
                case "GUI":
                    System.out.println("\nTable appeared.\n");
                    alphabet.getWeb().GuiTable();
                    System.out.println("Table Closed.\n");
                    break;
                case "Q":
                    terminateCheck = false;
                    System.out.println("\nGoodbye.\n");
                    break;
                default:
                    System.out.println("\nError: The input was not an option, please try again.\n");
                    break;
            }
        }
    }
}
