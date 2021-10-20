/**
 * The Education class support the resume class to set the education 
 * of the student.
 * @author Vidhee Patel
 */
public class Education {
    private String nameofUniversity;
    private String collegeMajor;
    private double GPA;
    private String expectedGradDate;

    public Education(String nameofUniversity, String major, double gpa, String expectedGradDate){

    }

    /**
     * The setName method sets the name of University.
     * @param name University name
     */
    public void setName(String name) {
        this.nameofUniversity = name;
    }

    /**
     * The getName method returns the name of the University.
     * @return nameofUniversity
     */
    public String getName() {
        return nameofUniversity;
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

}
