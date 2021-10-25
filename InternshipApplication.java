import java.util.ArrayList;
/**
 * The InternshipApplication class is a Facade which runs every aspect of the
 * internship application. It allows students to search and apply for an
 * internship as well as review companies, employers to post jobs and review
 * students, and administrators to remove listings, remove accounts, and
 * register new administrators.
 * @author Joshua DuPuis
 */
public class InternshipApplication {
    private UserDatabase userList;
    private User user;
    private SearchableDatabase jobsCompaniesList;

    /**
     * The InternshipApplication constructor initializes the facade that will
     * run the entire program.
     */
    public InternshipApplication() {

    }

    /**
     * The CreateAccount method allows a user to create an account as either an
     * employer or student.
     * 
     * @param accountType the type of account to create
     * @return True if the account was successfully created, false otherwise
     */
    public boolean createAccount(String accountType) {

        return false;
    }

    /**
     * The logOn method allows a user with an existing account to log on to the
     * system. Also sets the InternshipApplication's user equal to the logged in user.
     * @param email The User's email
     * @param password The User's password
     * 
     * @return true for a successful login, false otherwise.
     */
    public boolean logOn(String email, String password) {
        return false;
    }

    /**
     * The logOff method logs a user out of the system once they are finished
     * using it
     */
    public void logOff() {

    }

    /**
     * The search method allows a user to search through all of the internship
     * listings to find one to apply for if they are a student or update if
     * they are an employer
     * @param title The title of the JobListing the user is looking for
     * @return Returns all of the JobListings with the title entered by
     * the user
     */
    public ArrayList<JobListing> search (String title) {
        return null;
    }

    /**
     * The editPersonalInfo method allows a user to edit the personal
     * information associated with their account
     */
    public void editPersonalInfo() {

    }

    /**
     * The writeReview method allows either an employer or a student to write a
     * review.
     * @param user The user writing the review
     * @param rating The number rating out of 5 of the student or company being
     * reviewed
     * @param comment The reviewer's comment about the reviewee
     * @return The review created in this method
     */
    public Review writeReview(User user, int rating, String comment) {
        //Depending on the user type (student or employer, based on permission), 
        //restrict them to reviewing only students (if employer) or job listings (if student).
        return null;
    }

    /**
     * The createResume method allows a student user to create a resume that
     * employers can view when the student applies for a job
     */
    public void createResume() {
        //Create the resume and then add it to the student account passed to the command
    }

    /**
     * The editResume method allows a student to edit information on their
     * resume
     */
    public void editResume() {

    }

    /**
     * The createJobListing method allows an employer to create a Job Listing
     * that students can apply for
     * @return The JobListing created by the employer
     */
    public JobListing createJobListing(){
        return null;
    }

    /**
     * The editJobListing method allows an employer to edit the description of
     * an existing job listing and change its visibility setting
     * @param jobListing The jobListing the employer wants to edit
     */
    public void editJobListing(JobListing jobListing) {

    }

    /**
     * The removeJobListing method allows an employer to remove a job listing
     * from the database of listings.
     * @param jobListing The job listing the employer wants to remove
     */
    public void removeJobListing(JobListing jobListing) {

    }

    /**
     * The createCompanyProfile method allows employers to create a profile
     * describing their company
     * @return The CompanyProfile the employer creates
     */
    public CompanyProfile createCompanyProfile(String name, String hqaddress) {
        return null;
    } 

    /**
     * The associateCompany method allows employers to associate with a
     * company profile that already exists in the system
     */
    public void associateCompany() {

    }

    /**
     * The removeProfile method allows an administrator to remove a
     * CompanyProfile they deem illigitimate
     * @param company The name of the company who's profile the admin wants
     * to remove
     * @return Returns true if the company exits, false otherwise
     */
    public boolean removeProfile(CompanyProfile company) {
      return false;
    }

    /**
     * The removeAccount method allows an Administrator to remove a student or
     * employer account
     * @param user The account that the Administrator wishes to remove
     */
    public void removeAccount(User user) {

    }

    /**
     * The registerAdmin method allows administrators to register other
     * administrators to the system. No employee and no student can do this.
     */
    public void registerAdmin() {

    }

    /**
     * The applyForInternshipMethod allows a student to apply for an internship
     * in the database
     * @param listing The internship the user wants to apply for
     */
    public void applyForInternship(JobListing listing) {

    }
}
