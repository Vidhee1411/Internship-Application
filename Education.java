/**
 * The Education class support the resume class to set the education 
 * of the student.
 * @author Vidhee Patel and Joshua DuPuis
 */
public class Education {
    private String nameOfUniversity;
    private String collegeMajor;
    private double GPA;
    private String expectedGradDate;

    /**
     * This is the default constructor
     */
    public Education () {

    }
    
    /**
     * This is the parameterized constructor of the education class.
     * @param nameofUniversity The name of the university the student attended
     * @param major The student's major
     * @param gpa The student's GPA
     * @param expectedGradDate The student's expected graduation date
     */
    public Education(String nameOfUniversity, String major, double gpa, String expectedGradDate){
        this.nameOfUniversity = nameOfUniversity;
        this.collegeMajor = major;
        this.GPA = gpa;
        this.expectedGradDate = expectedGradDate; 
    }

    /**
     * The setName method sets the name of University.
     * @param name University name
     */
    public void setName(String name) {
        this.nameOfUniversity = name;
    }

    /**
     * The getName method returns the name of the University.
     * @return nameofUniversity
     */
    public String getName() {
        return nameOfUniversity;
    }

    /**
     * The setMajor method sets the major of the student.
     * @param major
     */
    public void setMajor(String major) {
        this.collegeMajor = major;
    }
    
    /**
     * The getMajor method returns the major of the student.
     * @return collegeMajor
     */
    public String getMajor() {
        return collegeMajor;
    }

    /**
     * The setGPA method sets the GPA of the student.
     * @param gpa
     */
    public void setGPA(double gpa) {
        this.GPA = gpa;
    }

    /**
     * The getGPA method return the gpa of the student.
     * @return
     */
    public double getGPA() {
        return GPA;
    }

    /**
     * The setExpectedGradDate setd the expected graduation date in the form of
     * MM/YYYY.
     * @param date
     */
    public void setExpectedGradDate(String date)
    {
        this.expectedGradDate = date;
    }

    /**
     * The getExpectedGradDate returns the expected graduation date.
     * @return expectedGradDate
     */
    public String getExpectedGradDate() {
        return expectedGradDate;
    }

    /**
     * The toString method converts an education experience to a String
     * @return A string representation of an education experience
     */
    public String toString() {
        return nameOfUniversity + ":\n   " + "Graduation Date: " + 
            expectedGradDate + "\n   " + "Major: " + collegeMajor + "\n   " +
            "GPA: " + GPA + "\n";
    }

}
