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
    public Student(String firstName, String lastName, String email, String password, String yearInSchool, UUID id) {
        super(firstName, lastName, email, password, id);
        this.yearInSchool = yearInSchool;
        reviewsFromCompanies = new ArrayList<Review>();
    }
    
    /**
     * The createResume method allows the student to create a resume. It adds
     * the student's first name, last name, and email to the resume.
     */
    public void createResume() {
        resume = new Resume(super.getFirstName(), super.getLastName(), super.getEmail(), this.yearInSchool);
    }
    /**
     * sets students resume to the resume passed to the method
     * @param resume the resume being added to the student 
     */
    public void setResume(Resume resume){
        this.resume = resume;
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
     * The reviewCompany method allows students to review a company they have
     * previously worked for and adds the newly created review to the company's
     * page.
     * @param rating The rating out of five that the student gives the company
     * @param comment The comment to accompany the review
     * @param company The company that the student is reviewing
     */
    public void reviewCompany(int rating, String comment, CompanyProfile company) {
        Review studentReview = new Review(super.getFirstName(), super.getLastName(), rating, comment);
        company.addReview(studentReview);
    }


    /**
     * The getReviews method returns an ArrayList containing all of the reviews
     * that employers have written about the student.
     * @return The list of reviews employers have written about the student
     */
    public ArrayList<Review> getReviews() {
        return reviewsFromCompanies;
    }

    /**
     * The applyForInternship method allows a student to apply for any
     * internship in the system.
     * @param listing The internship a student wants to apply for
     */
    public void applyForInternship(JobListing listing) {
        listing.apply(this);
    }

    /**
     * The add review method accepts a review from an employer and adds it to
     * the list of reviews about the student.
     * @param review The review written by an employer to be added to the
     * student's ArrayList of reviews
     */
    public void addReview(Review review) {
        reviewsFromCompanies.add(review);
    }

    /**
<<<<<<< HEAD
     * The add reviews method accepts an Arraylist and sets reviewsFromCompanies equal to it.
     * used in loading data from JSON
     * @param reviews an ArrayList of reviews 
     */
    public void setReviews(ArrayList<Review> reviews) {
        reviewsFromCompanies = reviews;
    }

=======
     * The getUUID method returns the UUID of the student
     * @return The UUID of the student
     */
>>>>>>> e3da00650834efaf38b0f3d20523cb88212b371d
    public UUID getUUID() {
        return super.getuserUuid();
    }

    /**
     * The getPermission method returns the permission value for a student
     * @return An int containing the value 0 - a student's user's permission
     * value
     */
    public int getPermission() {
        return PERMISSION;
    }
}
