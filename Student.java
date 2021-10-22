import java.util.UUID;
import java.util.ArrayList;

/**
 * The Student class creates a Student account in the internship system and
 * allows them to create a resume to apply for a job.
 * @author Vidhee Patel and Joshua DuPuis
 */
public class Student extends User{
    private String yearInSchool;
    private Resume resume;
    private ArrayList<Review> reviewsFromCompanies;
    private static final int PERMISSION = 0;

    /**
     * The Student constructor creates a student object with their first name
     * @param firstName The first name of the student
     * @param lastName The last name of the student
     * @param email The student's school email
     * @param password The password for the student's account
     * @param yearInSchool The student's year in school
     */
    public Student(String firstName, String lastName, String email, String password, String yearInSchool) {
        super(firstName, lastName, email, password);
        this.yearInSchool = yearInSchool;
    }
    
    /**
     * The createResume method allows the student to create a resume. It adds
     * the student's first name, last name, and email to the resume.
     */
    public void createResume() {
        resume = new Resume(super.getFirstName(), super.getLastName(), super.getEmail(), this.yearInSchool);
    }

    /**
     * The getResume method returns the student's resume.
     * @return The student's resume
     */
    public Resume getResume() {
        return this.resume;
    }

    /**
     * The setYearInSchool method changes the student's year in school
     * @param yearInSchool The student's new year in school
     */
    public void setYearInSchool(String yearInSchool) {
        this.yearInSchool = yearInSchool;
    }

    /**
     * The getYearInSchool method returns the student's year in school.
     * @return The student's year in school
     */
    public String getYearInSchool() {
        return this.yearInSchool;
    }

    /**
     * The reviewCompany method allows students to review companies they have
     * previously worked for.
     * @param rating The rating out of five that the student gives the company
     * @param comment The comment to accompany the review
     * @param company The company that the student is reviewing
     * @return The review the student creates
     */
    public Review reviewCompany(int rating, String comment, CompanyProfile company) {
        return null;
    }

    public ArrayList<Review> getReviews() {
        return reviewsFromCompanies;
    }

    public void applyForInternship(JobListing listing) {

    }

    public void addReview(Review review) {

    }

    public UUID getID() {
        return super.getID();
     }
}
