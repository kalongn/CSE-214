/**
 * This PlannerManager class allow user to interact with a Planner
 * 
 * @author Ka_Long_Ngai 08/28/2022
 * 
 * Time used: ~2 hours
*/

import java.util.Scanner;

public class PlannerManager implements Cloneable{

    /**
     * The main method runs a menu driven application which first creates an empty Planner object. The program prompt
     *  user for a command to execute an operation. Once a command has been chosen, the program may ask the yser
     *  for additional information if necessary, and performs the operation.
     * 
     * In this case, I importer scanner in order to read user input. Then I use a while loop to continusly run the function
     * so that it doesn't terminate after just 1 section. I used a switch to determine what user is inputting as it is 
     * more efficent then using a bunch of if else statement. Then I basically print out whatever is required in that 
     * specific section and track the record.
     * 
     * @param args
     *      Main argument for a executable program.
     * @throws IllegalArgumentException
     *      When an input section is not apart of the avaliable selection and if the planner is empty but you trying to 
     *       look up, get or remove courses.
     * @throws FullPlannerException
     *      When the Planner is full and can no longer add any courses.
     */
    public static void main(String[] args) throws IllegalArgumentException, FullPlannerException {

        Planner regularPlanner = new Planner(); //The regular planner
        Planner backupRegularPlanner = new Planner(); //The backup planner
        Scanner userInput = new Scanner(System.in); //Scanner for user input.
        boolean terminateCheck = true, backup = false; //TerminateCheck for while loop to check when to terminate, backup to check of a backup was created or not.
        String courseName, departmentName, instructorName; /*the name of the course, 
         the name of the deparment and the name of the instructor, here for scanner.*/
        int courseCode, position; //the course number and the position the item store it, here for scanner.
        byte courseSection; //the section number for the course, here for scanner.

        while (terminateCheck) {
            System.out.println("\n(A) Add Course\n(G) Get Course\n(R) Remove Course\n(P) Print Courses in Planner\n(F) Filter by Department Code\n(L) Look For Course\n(S) Size\n(B) Backup\n(PB) Print Courses in Backup\n(RB) Revert to Backup\n(Q) Quit");
            System.out.print("\nEnter a Section: ");
            String characterInput = userInput.next();
            System.out.println();
            userInput.nextLine();
            switch(characterInput) {
                case "A":
                    System.out.print("Enter a course name: ");
                    courseName = userInput.nextLine();
                    System.out.print("Enter deparment: ");
                    departmentName = userInput.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = userInput.nextInt(); 
                    System.out.print("Enter course section: ");
                    courseSection = (byte)userInput.nextInt();
                    System.out.print("Enter instructor: ");
                    userInput.nextLine();
                    instructorName = userInput.nextLine();
                    System.out.print("Enter position: ");
                    position = userInput.nextInt();
                    regularPlanner.addCourse(new Course(courseName, departmentName, courseCode, courseSection, instructorName), position);
                    System.out.println("\n" + departmentName + " " + courseCode + ".0" + courseSection + " successfully added to planner.");
                    break;
                case "G":
                    System.out.print("Enter position: ");
                    position = userInput.nextInt();
                    Planner printAssist = new Planner();
                    printAssist.addCourse(regularPlanner.getCourse(position));
                    System.out.println();
                    printAssist.PrintAllCourses();
                    break;
                case "R":
                    System.out.print("Enter position: ");
                    position = userInput.nextInt();
                    Course assist = regularPlanner.getCourse(position);
                    regularPlanner.removeCourse(position);
                    System.out.println("\n" + assist.getDepartment() + " " + 
                     assist.getCode() + ".0" + assist.getSection() + " has been successfully removed from the planner.");
                    break;
                case "P":
                    System.out.println("Planner: ");
                    regularPlanner.PrintAllCourses();
                    break;
                case "F":
                    System.out.print("Enter deparment code: ");
                    departmentName = userInput.nextLine();
                    System.out.println();
                    Planner.filter(regularPlanner, departmentName);
                    break;
                case "L":
                    System.out.print("Enter a course name: ");
                    courseName = userInput.nextLine();
                    System.out.print("Enter deparment: ");
                    departmentName = userInput.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = userInput.nextInt();
                    System.out.print("Enter course section: ");
                    courseSection = (byte)userInput.nextInt();
                    System.out.print("Enter instructor: ");
                    userInput.nextLine();
                    instructorName = userInput.nextLine();
                    Course beChecked = new Course(courseName, departmentName, courseCode, courseSection, instructorName);
                    if(regularPlanner.exists(beChecked)) {
                        System.out.println("\n" + departmentName + " " + courseCode + ".0" + courseSection 
                         + " is found in the planner at position " + regularPlanner.courseSearch(beChecked) + ".");
                    } else {
                        System.out.println("\nCourse not found in the planner.");
                    }
                    break;
                case "S":
                    System.out.println("There are " + regularPlanner.size() + " courses in the planner.");
                    break;
                case "B":
                    System.out.println("Created a backup of the current planner.");
                    backup = true;
                    backupRegularPlanner = (Planner)regularPlanner.clone();
                    break;
                case "PB":
                    if(backup != false) {
                        System.out.println("Planner (backup): ");
                        backupRegularPlanner.PrintAllCourses();
                    } else {
                        System.out.println("\nA backup was not created, Use section B to create a backup.");
                    }
                    break;
                case "RB":
                    if(backup != false) {
                        regularPlanner = (Planner)backupRegularPlanner.clone();
                        System.out.println("\nPlanner successfully reverted to the backup copy.");
                    } else {
                        System.out.println("\nA backup was not created, Use section B to create a backup.");
                    }
                    break;
                case "Q":
                    System.out.println("Program terminating successfully...");
                    terminateCheck = false;
                    break;
                default:
                    terminateCheck = false;
                    userInput.close();  
                    throw new IllegalArgumentException("Input is not a valid section.");
            }
        }
        userInput.close();    
    }
}
