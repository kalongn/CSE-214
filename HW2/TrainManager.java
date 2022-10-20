/**
 * This TrainManager class allow user to interact with a menu to utilize the double linked list for trainCar.
 * 
 * @author Ka_Long_Ngai 09/13/2022
 * 
 * Time used: ~2 hours
*/

import java.util.Scanner;

public class TrainManager {

    /**
     * This make sure the scanner is empty, so that it scanns the next item correctly.
     * @param input
     *      the Scanner object you want to reset.
     * @return
     *      an empty Scanner object.
     */     
    private static Scanner scannerReset(Scanner input) {
        input.nextLine();
        return input;
    }

    /**
     * This ask user to input a double value and return it after the scanner picks it up.
     * <p>Precondition: the double user input is a valid double</p>
     * <p>Postcondition: the double value will be return, if something went wrong, a print statement will appear</p>
     * @param input
     *      the scanner you are using to read user input.
     * @return
     *      the double value that user inputted.
     */
    private static double scannerReadDouble(Scanner input) {
        double returnResult = 0;
        try {
            returnResult = input.nextDouble();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was not a valid double");
            return -1;
        }
        return returnResult;
    }

    /**
     * This ask user to input a string value (one word aka no space allow) and return it after the scanner picks it up.
     * <p>Precondition: the string user input is a valid double</p>
     * <p>Postcondition: the string value will be return, if something went wrong, a print statement will appear</p>
     * @param input
     *      the scanner you are using to read user input.
     * @return
     *      the string value (the one word aka no space allow) that user inputted.
     */
    private static String scannerReadString(Scanner input) {
        String returnStr = "";
        try {
            returnStr = input.next();
        } catch (Exception InputMismatchExpcetion) {
            System.out.println("Input was a valid String");
        }
        return returnStr;
    }

    /**
     * This print a statement for user to to know what they are inputting, in this case it is name.
     * @param input
     *      the Scanner you are using to read user input.
     * @return
     *      the string value (one word) that user inputted.
     */
    private static String scannerReadName(Scanner input) {
        System.out.print("Enter a product name: ");
        return scannerReadString(input);
    }

    /**
     * Allow user to input whether the product is dangerous or not with Y or N option.
     * <p>Postcondition: the boolean value will be return, if something went wrong, a print statement will appear</p>
     * @param input
     *      the Scaner object you are using to read user input.
     * @return
     *      if Y: return true, If N false, anything else will be default to false.
     */
    private static boolean ScannerReadYN(Scanner input) {
        boolean returnBoolean = false;
        System.out.print("Enter is product dangerous? (y/n): ");
        String temp = scannerReadString(input);
        switch(temp.toUpperCase()) {
            case "Y":
                returnBoolean = true;
                break;
            case "N":
                returnBoolean = false;
                break;
            default:
                System.out.println("\nYou did not input a (y/n) option for dangerous.");
                break;
        }
        return returnBoolean;
    }

    /**
     * Print a respective statement base on what information user needs to input. and return that double value.
     * <p>Precondition: the selection input is a valid option</p>
     * <p>Postcondition: the double value will be return, if something went wrong, a print statement will appear</p>
     * @param input
     *      the Scaner object you are using to read user input.
     * @param selection
     *      <p> - CL: Car length</p>
     *      <p> - CW: Car weight</p>
     *      <p> - PW: Product weight</p>
     *      <p> - PV: Product value</p>
     * @return
     *      the double value that user inputted, if user did not input something valid. A -1.0 value will be return and a print
     *      statement will be there.
     */
    private static double scannerReadInformation(Scanner input, String selection) {
        double returnResult = -1.0;
        switch(selection.toUpperCase()) {
            case "CL":
                System.out.print("Enter car length in meters: ");
                returnResult = scannerReadDouble(input);
                input = scannerReset(input);
                break;
            case "CW":
                System.out.print("Enter car weight in tons: ");
                returnResult = scannerReadDouble(input);
                input = scannerReset(input);
                break;
            case "PW":
                System.out.print("Enter product weight in tons: ");
                returnResult = scannerReadDouble(input);
                input = scannerReset(input);
                break;
            case "PV":
                System.out.print("Enter product value in dollars: ");
                returnResult = scannerReadDouble(input);
                input = scannerReset(input);
                break;
            default:
                System.out.println("The follow select for scannerReadDouble is not a option, please check your selection input");
        }
        return returnResult;
    }


    /**
     * This program basically print out a menu, then ask user which option they want to use. It is operate by switches for efficiency.
     * Then within each option here are codes that allow the the option to function correctly. If user make any mistake within those process.
     * A print statement will let user know what error they are commiting.
     * @param args
     *      main args allowing program to run.
     */
    public static void main(String[] args) {
        TrainLinkedList mainTrainList = new TrainLinkedList();
        Scanner userInput = new Scanner(System.in);
        boolean terminateCheck = true, tempDangerous;
        String tempName;
        double tempWeight, tempValue, tempLength;

        while(terminateCheck) {
            System.out.print("\n(F) Cursor Forward\n(B) Cursor Backward\n(I) Insert Car After Cursor\n(R) Remove Car at Cursor\n(L) Set Product Load\n(S) Search For Product\n(T) Display Train\n(M) Display Manifest\n(D) Remove Dangerous Cars\n(Q) Quit\n\nEnter a selection: ");
            String characterInput = userInput.next().toUpperCase();
            System.out.println();
            userInput.nextLine();
            switch(characterInput) {
                case "F":
                    try {
                        mainTrainList.cursorForward();
                        System.out.println("Cursor moved forward");
                    } catch (Exception NullPointerException) {
                        System.out.println("No next car, cannot move cursor forward.");
                    }
                    break;
                case "B":
                    try {
                        mainTrainList.cursorBackward();
                        System.out.println("Cursor moved backward");
                    } catch (Exception NullPointerException) {
                        System.out.println("No previous car, cannot move cursor backward.");
                    }
                    break;
                case "I":
                    tempLength = scannerReadInformation(userInput, "CL");
                    tempWeight = scannerReadInformation(userInput, "CW");
                    try {
                        mainTrainList.insertAfterCursor(new TrainCar(tempLength, tempWeight));
                    } catch (Exception illegalArgumentException) {
                        System.out.println("\nYour input value for this TrainCar object is not valid.");
                        break;
                    }
                    System.out.println("\nNew train car " + tempLength + " meters " + tempWeight + " tons inserted into train");
                    break;
                case "R":
                    try {
                        System.out.println("Cars successfully unlinked. The following load has been removed from the train:\n" + mainTrainList.removeCursor().getProductLoad());
                    } catch (Exception NullPointerException) {
                        System.out.println("Cursor is null, please add a traincar first before removing.");
                    }
                    break;
                case "L":
                    tempName = scannerReadName(userInput);
                    userInput = scannerReset(userInput);
                    tempWeight = scannerReadInformation(userInput, "PW");
                    tempValue = scannerReadInformation(userInput, "PV");
                    tempDangerous = ScannerReadYN(userInput);
                    try {
                        mainTrainList.loadProductStatUpdate(new ProductLoad(tempName, tempWeight, tempValue, tempDangerous));
                    } catch (Exception nullPointerException) {
                        System.out.println("\nThe cursor is null");
                        break;
                    }
                    System.out.println("\n" + tempWeight + " tons of " + tempName + " added to the current car.");
                    break;
                case "S":
                    tempName = scannerReadName(userInput);
                    System.out.println();
                    mainTrainList.findProduct(tempName);
                    break;
                case "T":
                    System.out.println(mainTrainList);
                    break;
                case "M":
                    mainTrainList.printManifest();
                    break;
                case "D":
                    if(mainTrainList.size() > 0){
                        mainTrainList.removeDangerousCars();
                    } else {
                        System.out.println("There are currently no train car, please add first before attempting this selection.");
                    }
                    break;
                case "Q":
                    System.out.println("Program terminating successfully...\n");
                    terminateCheck = false;
                    break;
                default:
                    System.out.println("Current input is not a selection, please check your input.\n");
                    break;
            }

        }
        
    }
}
