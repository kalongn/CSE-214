
/**
 * This WebGraph class allow user to create a
 * WebGraph object
 * 
 * 
 * @author Ka_Long_Ngai 11/17/2022
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

public class WebGraph {

    public static final int MAX_PAGES = 40; // maximum size for x and y dimension of the 2D Array/Matrix.
    private ArrayList<WebPage> pages; // ArrayList of pages within this Web.
    private int[][] edges; // 2D Matrix to indicate directional edges.
    private int ids = 0; // Ids to create new WebPage for index input.

    /**
     * Return an instance of WebGraph with an empty pages as an ArrayList of pages
     * and a 2D matrix with a dimension of 40x40 maximum.
     */
    public WebGraph() {
        this.pages = new ArrayList<>();
        this.edges = new int[MAX_PAGES][MAX_PAGES];
    }

    /**
     * Constructs a WebGraph object using the indicated files as the source for
     * pages and edges.
     * <p>
     * Preconditions:
     * <p/>
     * <p>
     * - Both parameters reference text files which exist.
     * </p>
     * <p>
     * - The files follow proper format as outlined in the "Reading Graph from File"
     * section below.
     * </p>
     * <p>
     * Postcondition:
     * <p/>
     * <p>
     * - A WebGraph has been constructed and initialized based on the text files.
     * </p>
     * 
     * @param pagesFile
     *                  String of the relative path to the file containin the page
     *                  information.
     * @param linksFile
     *                  String of the relative path to the file containing the link
     *                  information.
     * @return
     *         The WebGraph constructed from the text files.
     * @throws IllegalArgumentException
     *                                  Thrown if either of the files does not
     *                                  reference a valid text file, or if the files
     *                                  are not formatted coretly.
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException {
        Scanner pagesFileScanner, linksFileScanner;
        WebGraph web = new WebGraph();
        // loading files
        try {
            pagesFileScanner = new Scanner(new File(pagesFile));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("pagesFile path does not exist relatively in this directory.");
        }
        try {
            linksFileScanner = new Scanner(new File(linksFile));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("linksFile path does not exist relatively in this directory.");
        }
        // retrieveing data line by line for pagesFile
        while (pagesFileScanner.hasNextLine()) {
            String info = pagesFileScanner.nextLine().trim();
            String name = info.substring(0, info.indexOf(" "));
            info = info.substring(info.indexOf(" ") + 1);
            ArrayList<String> keywords = keywordsSplit(info);
            web.addPage(name, keywords);
        }
        // retrieving data line by line for linksFile
        while (linksFileScanner.hasNextLine()) {
            String info = linksFileScanner.nextLine().trim();
            String[] infoSplited = info.split(" ");
            web.addLink(infoSplited[0], infoSplited[1]);
        }
        return web;
    }

    /**
     * Splitting keywords up when given a String of keywords into ArrayList.
     * 
     * @param keywords
     *                 A string containing all keywords seperate by a space.
     * @return
     *         An ArrayList of the keywords which each keyword being a value within
     *         the ArrayList.
     */
    public static ArrayList<String> keywordsSplit(String keywords) {
        String[] info = keywords.split(" ");
        ArrayList<String> returnList = new ArrayList<>();
        for (String i : info) {
            returnList.add(i);
        }
        return returnList;
    }

    /**
     * Adds a page to the WebGraph.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * - url is unique and does not exist as the URL of a WebPage already in the
     * graph.
     * </p>
     * <p>
     * - url and keywords are not null.
     * </p>
     * <p>
     * Postcondition:
     * </p>
     * <p>
     * - The page has been added to pages at index 'i' and links has been logically
     * extended to include the new row and column indexed by 'i'.
     * 
     * @param url
     *                 The URL of the webpage(must not already exist in the
     *                 WebGraph).
     * @param keywords
     *                 The keywords associated with the WebPage.
     * @throws IllegalArgumentException
     *                                  Thrown if the url is not unique and already
     *                                  exists in the graph, or if either argument
     *                                  is null.
     */
    public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException {
        if (url == null) {
            throw new IllegalArgumentException("The url is null.");
        }
        if (keywords == null) {
            throw new IllegalArgumentException("The keywords are null.");
        }
        for (WebPage page : this.pages) {
            if (page.getUrl().equals(url)) {
                throw new IllegalArgumentException("This url is already in the graph.");
            }
        }
        this.pages.add(new WebPage(ids, url, keywords));
        ids++;
        updatePageRanks();
    }

    /**
     * Adds a link from the WebPage with the URL indicated by source to the WebPage
     * with the URL indicated by destination.
     * <p>
     * Preconditions:
     * </p>
     * <p>
     * - Both parameters reference WebPages which exist in the graph.
     * </p>
     * 
     * @param source
     *                    the URL of the page which contains the hyperlink to
     *                    destination.
     * @param destination
     *                    the URL of the page which hyperlink points to.
     * @throws IllegalArgumentException
     *                                  Thrown if either of the URLs are null or
     *                                  could not be found in pages.
     */
    public void addLink(String source, String destination) throws IllegalArgumentException {
        boolean sourceExist = false, destinationExist = false;
        WebPage sourceWebPage = null, destinationWebPage = null;
        for (WebPage page : this.pages) {
            if (page.getUrl().equals(source)) {
                sourceExist = true;
                sourceWebPage = page;
            }
            if (page.getUrl().equals(destination)) {
                destinationExist = true;
                destinationWebPage = page;
            }
            if (destinationExist && sourceExist) {
                break;
            }
        }
        if (!(destinationExist)) {
            throw new IllegalArgumentException("The url for desintation does not exist.");
        }
        if (!(sourceExist)) {
            throw new IllegalArgumentException("The url for source does not exist.");
        }
        if (this.edges[sourceWebPage.getIndex()][destinationWebPage.getIndex()] == 1) {
            throw new IllegalArgumentException("Link already exist.");
        }
        this.edges[sourceWebPage.getIndex()][destinationWebPage.getIndex()] = 1;
        updatePageRanks();
    }

    /**
     * Decrease the index of every WebPage starting from the specific index.
     * 
     * @param startingIndex
     *                      The index that indicate where to start decrementing the
     *                      index of each WebPage by 1.
     */
    public void decreasingIndex(int startingIndex) {
        for (int i = startingIndex; i < this.pages.size(); i++) {
            this.pages.get(i).setIndex(this.pages.get(i).getIndex() - 1);
        }
    }

    /**
     * Removes the WebPage from the graph with the given URL.
     * <p>
     * Postconditions
     * </p>
     * <p>
     * - The WebPage with the indicated URL has been removed from the graph, and
     * it's corresponding row and column has been removed from the adjacency matrix.
     * </p>
     * <p>
     * - All pages that has an index greater than the index that was removed should
     * decrease their index value by 1.
     * </p>
     * <p>
     * - If url is null or could not be found in pages, the method ignores the input
     * and does nothing.
     * </p>
     * 
     * @param url
     *            The URL of the page to remove from the graph.
     */
    public void removePages(String url) {
        boolean urlExist = false;
        WebPage toBeRemoved = null;
        int index = 0;

        for (WebPage page : this.pages) {
            if (page.getUrl().equals(url)) {
                urlExist = true;
                toBeRemoved = page;
                break;
            }
            index++;
        }
        if (!urlExist) {
            return;
        }
        pages.remove(toBeRemoved);
        this.decreasingIndex(index);
        // 2D array row/column all need to shifted by 1
        for (int i = index; i < pages.size(); i++) {
            edges[i] = edges[i + 1];
        }
        for (int i = 0; i < pages.size(); i++) {
            for (int y = index; y < pages.size(); y++) {
                edges[i][y] = edges[i][y + 1];
            }
        }

        ids--;
        updatePageRanks();
    }

    /**
     * Removes the link from WebPage with the URL indicated by source to the WebPage
     * with the URL indicated by destination.
     * <p>
     * Postconditions:
     * </p>
     * <p>
     * - The entry in links for the specified hyperlink has been set to 0 (no link).
     * </p>
     * <p>
     * - If either of the URLs could not be found, the input is ignored and the
     * method does nothing.
     * </p>
     * 
     * @param source
     *                    The URL of the WebPage to remove the link.
     * @param destination
     *                    The URL of the link to be removed.
     */
    public void removeLink(String source, String destination) {
        int sourceIndex = -1, destinationIndex = -1;
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(source)) {
                sourceIndex = i;
            }
            if (pages.get(i).getUrl().equals(destination)) {
                destinationIndex = i;
            }
        }
        if (sourceIndex == destinationIndex) {
            return;
        }
        this.edges[sourceIndex][destinationIndex] = 0;
        updatePageRanks();
    }

    /**
     * Calculates and assigns the PageRank for every page in the WebGraph.
     * <p>
     * Postcondition:
     * </p>
     * <p>
     * - All WebPages in the graph have been assigned their proper PageRank.
     * </p>
     */
    public void updatePageRanks() {
        for (int i = 0; i < pages.size(); i++) {
            int pageConnection = 0;
            for (int y = 0; y < pages.size(); y++) {
                pageConnection += this.edges[y][i];
            }
            pages.get(i).setRank(pageConnection);
        }
    }

    /**
     * Determine all the edges for a specific Page that is direct outward given an
     * index of the Page.
     * 
     * @param index
     *              The index of the WebPage that is required to be check.
     * @return
     *         A String value put every single link neatly.
     */
    private String allEdgesForPage(int index) {
        String returnStr = "";
        for (int i = 0; i < pages.size(); i++) {
            if (edges[index][i] == 1) {
                returnStr += i + ", ";
            }
        }
        if (returnStr.equals("")) {
            return returnStr;
        }
        return returnStr.substring(0, returnStr.length() - 2);
    }

    /**
     * Determine which WebPage within the pages ArrayList contain the keywords, if
     * it contains the keyword, it will be added to a newly created ArrayList and
     * the function return the ArrayList at the end.
     * 
     * @param keyword
     *                The target keyword we are looking for in each WebPage.
     * @return
     *         An ArrayList containing all the WebPages that contain the keyword
     *         inside their keywords.
     */
    private ArrayList<WebPage> containKeyWords(String keyword) {
        ArrayList<WebPage> webPagesContainKeywods = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++) {
            for (String j : pages.get(i).getKeywords()) {
                if (j.equals(keyword)) {
                    webPagesContainKeywods.add(pages.get(i));
                }
            }
        }
        return webPagesContainKeywods;
    }

    /**
     * Print out every single WebPage that contain the keywords in a neatly tabular
     * format. Indexed by their PageRank.
     * 
     * @param keyword
     *                The target keyword we are looking for in each WebPage.
     * @throws IllegalArgumentException
     *                                  Thrown if the keyword is not found in any
     *                                  WebPages within the Pages ArrayList.
     */
    public void Search(String keyword) throws IllegalArgumentException {
        String prtString = String.format("%-6s%-11s%-20s", "Rank", "PageRank", "URL") + "\n";
        prtString += "--------------------------------------------------------------------------------------\n";
        ArrayList<WebPage> webPagesContainKeywods = containKeyWords(keyword);
        if (webPagesContainKeywods.size() == 0) {
            throw new IllegalArgumentException("Keyword does not exist in any webpage.");
        }
        Collections.sort(webPagesContainKeywods, new RankComparator());
        for (int i = 1; i <= webPagesContainKeywods.size(); i++) {
            prtString += String.format("%-2s%-3s%-5s%-5s%-2s%-20s", "", i, "|",
                    webPagesContainKeywods.get(i - 1).getRank(), "|",
                    webPagesContainKeywods.get(i - 1).getUrl()) + "\n";
        }
        System.out.println(prtString);
    }

    /**
     * Prints the WebGraph in a tabular Form with 3 option, I : sorted by index, U :
     * sorted by URL, R : sorted by Rank. If user input other wrong value, function
     * terminate.
     * 
     * @param options
     *                The options the user want this tabular to sorted to which
     *                format.
     */
    public void printTable(String options) {
        String returnStr = String.format("%-8s%-22s%-11s%-18s%-40s", "Index", "URL", "PageRank", "Links", "Keywords") + "\n";
        returnStr += "--------------------------------------------------------------------------------------\n";

        switch (options.toUpperCase()) {
            case "I":
                Collections.sort(pages);
                break;
            case "U":
                Collections.sort(pages, new UrlComparator());
                break;
            case "R":
                Collections.sort(pages, new RankComparator());
                break;
            default:
                System.out.println("Error: Not a Valid Print Options, please try again.");
                return;
        }

        for (int i = 0; i < pages.size(); i++) {
            String replaceString = String.format("%-4s%-5s%-2s%-16s", "", pages.get(i).getRank(), "|",
                    allEdgesForPage(pages.get(i).getIndex()));
            returnStr += pages.get(i).toString().replace("***", replaceString) + "\n";
        }
        System.out.println("\n" + returnStr);

        Collections.sort(pages);
    }

    /**
     * Convert all the data for a specific WebPage inside pages ArrayList with the
     * given param index. Return a String[] with each item in it representing the
     * index, URL, pagerank, links and keywords accordingly.
     * 
     * @param index
     *              Index of the WebPage data you want to get from the pages
     *              ArrayList.
     * @return
     *         a String[] with each item in it representing the
     *         index, URL, pagerank, links and keywords accordingly.
     */
    private String[] convertToStringArray(int index) {
        WebPage convertingWebPage = pages.get(index);
        return new String[] { "" + convertingWebPage.getIndex(), convertingWebPage.getUrl(),
                "" + convertingWebPage.getRank(), allEdgesForPage(convertingWebPage.getIndex()),
                convertingWebPage.keyWordsIntoString() };
    }

    /**
     * Create a GUITable (using JTable), the GUI have clickable headers (mouseEvent,
     * MouseAdapter) on all
     * lists which sort by that column when clicked. Clicking again on that column
     * would reverse the sorting order.
     */
    public void GuiTable() {
        JFrame newFrame = new JFrame();
        String header[] = { "Index", "URL", "PageRank", "Links", "Keywords" };
        String data[][] = new String[pages.size()][5];
        WebGraph reference = this;
        for (int i = 0; i < pages.size(); i++) {
            data[i] = reference.convertToStringArray(i);
        }
        JTable webGraphTable = new JTable(data, header);
        webGraphTable.setBounds(0, 0, 1280, 1024);
        webGraphTable.setAutoCreateRowSorter(true);
        newFrame.add(new JScrollPane(webGraphTable));
        newFrame.setSize(1280, 1024);
        newFrame.setVisible(true);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}