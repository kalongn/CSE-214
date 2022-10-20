/** 
 * This Course class allow user to create Course object
 * 
 * @author Ka_Long_Ngai 08/25/2022
 * 
 * Time used: ~1 hour
*/

public class Course implements Cloneable{
    
    private String name, department, instructor; /*the name of the course, 
     the name of the deparment and the name of the instructor.*/
    private int code; //the course number.
    private byte section; // the section number for the course.

    /**
     * Return an instance of Course.
     * 
     * @param name
     *      The name of the course.
     * @param department
     *      The department of the course.
     * @param code
     *      The code number of the course.
     * @param section
     *      The section of the course.
     * @param instructor
     *      The instructor of the course.
     * Precondition:
     *      code and section must 
     *       be within the limited range aka greater or equal to 0.
     * @exception IllegalArgumentException 
     *      Appears whenever code or section 
     *       is not within the limited range aka less then zero.
     */
    public Course(String name, String department, int code, byte section, String instructor) 
     throws IllegalArgumentException {
        this.name = name;
        this.department = department;
        this.setCode(code);
        this.setSection(section);
        this.instructor = instructor;
    }
    
    /**
     * @return 
     *      The name of the course.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Update the name of the course.
     * @param name 
     *      The name of the course that you want to change to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 
     *      The department of the course.
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Update the department name.
     * @param department 
     *      The department of the course that you want to change to.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return 
     *      The code of the course.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Update the code of the course.
     * @param code 
     *      The code of the course that you want to change to.
     * @exception IllegalArgumentException
     *      Appears whenever <code>code</code> is not within the 
     *       limited range aka less then zero.
     */
    public void setCode(int code) throws IllegalArgumentException{
        if(code < 0) {
            throw new IllegalArgumentException("Your input for class code exceed the limited range");
        } else {
            this.code = code;
        }
    }

    /**
     * @return
     *      The section of the course.
     */
    public byte getSection() {
        return this.section;
    }

    /**
     * Update the section of the course.
     * @param section
     *      The section of the course that you want to change to.
     * @exception IllegalArgumentException
     *      Appears whenever section is not within the limited range aka less then zero.
     */
    public void setSection(byte section) throws IllegalArgumentException{
        if(section < 0) {
            throw new IllegalArgumentException("Your input for section exceed the limited range");
        } else {
            this.section = section;
        }
    }

    /**
     * @return
     *      The instructor of the course.
     */
    public String getInstructor() {
        return this.instructor;
    }

    /**
     * Update the instructor of the course.
     * @param instructor
     *      The instructor of the course that you want to change to.
     */
    public void setIntructor(String instructor) {
        this.instructor = instructor;
    }

    /*
     * @return
     *      The String of all information into a line for testing purposes  
     */
    @Override
    public String toString() {
        return "" + name + ", "+ department + ", " + code + ", " + section + ", " + instructor;
    }

    /*
     * @return
     *      The value is a copy of this course in Object class, typecasted require before it can be use as a course.
     */
    @Override
    public Object clone() throws IllegalArgumentException {
        if (this instanceof Course) {
            return (Object) new Course(this.name, this.department, this.code, this.section, this.instructor);
        }
        throw new IllegalArgumentException("The object you are trying to clone is not a Course");
    }

    /* 
     * @return
     *      True: The courses that are being compare are the same.
     *      False: The courses that are being compare aren't the same.
     * @exception IllegalArgumentException 
     *      When object is not an instance of course object.
     */
    @Override
    public boolean equals(Object object) throws IllegalArgumentException{
        
        if(object instanceof Course) {
            Course temp = (Course) object;
            return this.name.equals(temp.getName()) && this.department.equals(temp.getDepartment()) 
              && this.code == temp.getCode() && this.section == temp.getSection() 
              && this.instructor.equals(temp.getInstructor());
        }
        else {
            throw new IllegalArgumentException("Object is not a Course Class");
        }
    }

    /* test code
    public static void main(String[] args) {
        Course oneTest = new Course("Data Structure", "Computer Science", 214, (byte)1, "Esmaili ");
        System.out.println(oneTest);
        //oneTest.setCode(-999);
        oneTest.setCode(100);
        System.out.println(oneTest);
        boolean test = oneTest.equals(oneTest.clone());
        System.out.println(test);
        Course twoTest = new Course("Data Structure", "Computer Science", 214, (byte)1, "Test");
        test = oneTest.equals(twoTest.clone());
        System.out.println(test);
    } */
}
