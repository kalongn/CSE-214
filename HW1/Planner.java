/**
 * This Plannerclass allow user to create Planner object
 * 
 * @author Ka_Long_Ngai 08/27/2022
 * 
 * Time used: ~2 and 1/2 hours
*/

public class Planner implements Cloneable {

    private final int MAX_COURSES = 50; //final maxium size of the array.
    private Course[] courseList; //the array that will 50 spots to put all the courses in.
    private int item_currently_in_list; //an int that shows how many courses are in the ourseList.

    /**
     * Constructs an instance of the Planner with no Course objects in it.
     * Postcondition:
     *      This Planner has been initialized to an empty list of courses.
     */
    public Planner() {
        courseList = new Course[MAX_COURSES];
        item_currently_in_list = 0;
    }

    /**
     * Constructs an instance of the Planner with no Course objects in it.
     * @param listSize
     *      allow user to change the size of the list if desired.
     * Postcondition:
     *      This Planner has been initialized to an empty list of courses.
     */
    public Planner(int listSize) {
        courseList = new Course[listSize];
        item_currently_in_list = 0;
    }

    /**
     * Determins the number of courses currently in the list
     * Preconditions:
     *      This Planner has been instatiated.
     * @return
     *      The number of courses in this Planner.
     */
    public int size() {
        return item_currently_in_list;
    }

    /**
     * @return
     *      True: if there is nothing in the list.
     *      False: if there is something in the list.
     */
    public boolean emptyList() {
        return this.size() <= 0;
    }

    /**
     * Add a new course to the list at a specific position on the list.
     * @param newCourse 
     *      The new course to add to the list.
     * @param position
     *      The position(preference) of this course on the list.
     * Preconditions:
     *      This course object has been instantiated and 1<= position <=
     *            items_currently_in_list+1.
     *      The number of Course objects in this Planner is less than MAX_COURSES.
     * PostConditions:
     *      The new Course is now listed in the correct preference on the list.
     *      All courses that were originally greater than or equal to position are moved bacmm one position.
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range.
     * @throws FullPlannerException
     *      Indicates that there is no more room in the Planner to record an additional Course.
     */
    public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException {
        if(this.size()>= MAX_COURSES) {
            throw new FullPlannerException();
        } else if(position > 0 && position <= this.size()+1) {
            for(int i = this.size(); i > position-1; i--) {
                courseList[i] = courseList[i-1];
            }
            courseList[position-1] = newCourse;
            item_currently_in_list++;
        } else {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
    }

    /**
     * Add a new course to the end of the list.
     * @param newCourse
     *      The new course to add to the list.
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range.
     * @throws FullPlannerException
     *      Indicates that there is no more room in the Planner to record an additional Course.
     */
    public void addCourse(Course newCourse) throws IllegalArgumentException, FullPlannerException {
        addCourse(newCourse, this.size()+1);
    }

    /**
     * Remove the course at a specific position.
     * @param position
     *      The position in the Planner where the Course will be removed from.
     * Preconditions:
     *      This Planner has been instantiated an 1<=position<=items_currently_in_list.
     * Postconditions:
     *      The Course at the desire position has been removed.
     *      All Courses that were originally greater than or equal to position are moved
     *       backward one position.
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range.
     */
    public void removeCourse(int position) throws IllegalArgumentException {
        if(position > 0 && position <= this.size()+1) {
            for(int i = position-1; i < this.size(); i++) {
                courseList[i] = courseList[i+1];
            }
            courseList[this.size()] = null;
            item_currently_in_list--;
        } else {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
    }

    /**
     * Return the course at a specific location.
     * @param position
     *      Position of the Course to retrieve.
     * Preconditions:
     *      The Planner object has been instantiated and 1<=position<=item_currently_in_list and there is something in the list.
     * @return
     *      The Course at the specified position in this Planner object.
     * @throws IllegalArgumentException
     *      Indicates that position is not within the valid range and the list is not empty.
     */
    public Course getCourse(int position) throws IllegalArgumentException {
        if(emptyList()) {
            throw new IllegalArgumentException("The planner is empty, please add course first");
        }
        if(position > 0 && position <= this.size()+1) {
            return courseList[position-1];
        } else {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
    }

    /**
     * filter the list by a specific department input and print them out.
     * @param planner - The list of courses to search in.
     * @param department - The 3 letter department code for a Course.
     * Preconditions:
     *      This Planner object has been instantiated.
     * Postconditions:
     *      Displays a nearly formatted table of each course filtered from the Planner. Keep the preference numbers the same.
     */
    public static void filter(Planner planner, String department) {
        Planner returnPlanner = (Planner)planner.clone();
        for(int i = 0; i < returnPlanner.size(); i++) {
            if(!returnPlanner.courseList[i].getDepartment().equals(department)) {
                returnPlanner.courseList[i] = null;
            }
        }
        returnPlanner.PrintAllCourses();
    }

    /**
     * check of the course exist or not by comparing the attribute using the .equals override in course class.
     * @param course
     *      The Course we are looking for.
     * Preconditions:
     *      This Planner and Course has both been instantiated.
     * @return
     *      True if the Planner contains this Course, false otherwise.
     */
    public boolean exists(Course course) {
        if(emptyList()) {
            System.out.println("The planner is empty, please add course before checking the course exist or not");
            return false;
        }
        for(int i = 0; i < this.size(); i++) {
            if(courseList[i].equals((Object)course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getting the index of the course we are looking for.
     * @param course
     *      The Course we are looking for.
     * Preconditions:
     *      This Planner, Course has both been instantiated and the Course actually exist.
     * @return
     *      The relative location of the course in the Planner
     */
    public int courseSearch(Course course) {
        if(exists(course)) {
            for(int i = 0; i < this.size(); i++) {
                if(courseList[i].equals(course)) {
                    return i+1;
                }
            }
        }
        return -1;
    }

    /**
     * Create a copy of this Planner. Subsequent changes to the copy will not affect the original and vice versa
     * Preconditions:
     *      This Planner object has been instantiated.
     * @return
     *      A copy(backup) of this Planner object.
     */
    @Override
    public Object clone() {
        Planner returnPlanner = new Planner();
        for(int i = 0; i < this.size(); i++) {
            returnPlanner.courseList[i] = (Course)this.courseList[i].clone();
        }
        returnPlanner.item_currently_in_list = this.item_currently_in_list;
        return (Object)returnPlanner;
    }

    /**
     * @return
     *      True: The Planners that are being compare are the same.
     *      False: The Planners that are being compare aren't the same.
     */
    @Override
    public boolean equals(Object object) {
        Planner temp = (Planner)object;
        if(object instanceof Planner && this.size() == (temp.size())) {
            for(int i = 0; i<this.size(); i++) {
                if(!this.courseList[i].equals(temp.courseList[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Prints a nearly formatted table of each item in the list with its position number as shown in the sameple output.
     * Preconditions:
     *      This Planner has been instantiated.
     * Postconditions:
     *      Displays a nearly formatted table of each course from the Planner.
     */
    public void PrintAllCourses() {
        System.out.print(this.toString());
    }

    /* 
     * Get the String represntation of this Planner object, which is a neatly formatted table of each Course in the Planner on its
     * own line with its position number as shown in the sample output.
     * @return
     *      The String representation of this Planner object.
     */
    @Override
    public String toString() {
        String returnStr = String.format("%-4s%-24s%-12s%-6s%-9s%-10s", "No.", "Course Name", 
         "Department", "Code", "Section", "Instructor");
        returnStr+="\n-------------------------------------------------------------------------------\n";
        for(int i = 0; i < this.size(); i++) {
            if(this.courseList[i] != null) {
                returnStr+=String.format("%-4s%-24s%-13s%-10s%-4s%-26s", " "+(i+1), this.courseList[i].getName(), 
                 this.courseList[i].getDepartment(), ""+this.courseList[i].getCode(), 
                 "0"+this.courseList[i].getSection(), this.courseList[i].getInstructor());
                returnStr+="\n";
            }
        }
        return returnStr;
    }

    /*Test Code
    public static void main(String[] args) throws IllegalArgumentException, FullPlannerException {
        Planner PleaseWork = new Planner();
        PleaseWork.addCourse(new Course("Data Structure", "CSE", 214, (byte)1, "Ahmad Esmaili"), 1);
        PleaseWork.addCourse(new Course("Linear Algebra", "AMS", 210, (byte)2, "Alan Tucker"),2);
        PleaseWork.addCourse(new Course("System Foundamentals", "CSE", 220, (byte)1, "Kevin McDonnell"), 2);
        PleaseWork.addCourse(new Course("Elements of Music", "MUS", 119, (byte)2, "Taylor Ackley"),4);
        PleaseWork.addCourse(new Course("CSE 101", "CSE", 101, (byte)2, "Ka Long Ngai"),5);
        System.out.println("\nSize of List is " + PleaseWork.size() + "\n");
        PleaseWork.PrintAllCourses();
        PleaseWork.removeCourse(3);
        PleaseWork.PrintAllCourses();
        filter(PleaseWork, "CSE");
        Planner testGet = new Planner();
        testGet.addCourse(PleaseWork.getCourse(2));
        testGet.PrintAllCourses();
        System.out.println("Course exist test: " + PleaseWork.exists(new Course("CSE 101", "CSE", 101, (byte)2, "Ka Long Ngai")));
        System.out.println("Course exist test: " + PleaseWork.exists(new Course("CSE 101", "CSE", 101, (byte)2, "Not Me")));
    }*/
}
