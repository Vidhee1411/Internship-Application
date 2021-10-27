import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;


/**
 * The InternshipApplication class is a Facade which runs every aspect of the
 * internship application. It allows students to search and apply for an
 * internship as well as review companies, employers to post jobs and review
 * students, and administrators to remove listings, remove accounts, and
 * register new administrators.
 * @author Joshua DuPuis
 */
public class InternshipApplication {;
    private User user;
    private SearchableDatabase database;
    private Scanner scanner;

    /**
     * The InternshipApplication constructor initializes the facade that will
     * run the entire program.
     */
     InternshipApplication() {
        this.database = SearchableDatabase.getInstance();
        this.scanner = new Scanner(System.in);
    }

    /**
     * The CreateAccount method allows a user to create an account as either an
     * employer or student.
     * 
     * @param accountType The type of account to be created
     * @return True if the account was successfully made, false otherwise
     */
    public boolean createAccount(String accountType) {
        Console console = System.console();
        System.out.println("Please enter your  first name ");
        String firstname = scanner.nextLine();
        System.out.println("Please enter your last name");
        String lastname = scanner.nextLine();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();
        char[] passwordarr = console.readPassword("Please enter password:");
        String password = new String(passwordarr);
        switch(accountType.toLowerCase()) {
            case "student":
                System.out.println("Enter your year in school");
                String year = scanner.nextLine();
                Student s1 = new Student(firstname,lastname,email,password,year);
                database.addUser(s1);
                s1.createResume();
            case "employer":
                Employer e1 = new Employer(firstname, lastname, email, password);
                database.addUser(e1);
            }
    }

    /**
     * The logOn method allows a user with an existing account to log on to the
     * system
     * @param email The User's email
     * @param password The User's password
     * 
     * @return true for a successful login, false otherwise.
     */
    public boolean logOn(String email, String password) {
        boolean log = user.logOn(email, password);       
        return log;
    }

    /**
     * The logOff method logs a user out of the system once they are finished
     * using it
     */
    public void logOff() {
        System.out.println("Logging off");
        System.exit(0);
    }

    /**
     * This search method allows a user to search through all of the internship
     * listings to find one to apply for if they are a student or update if
     * they are an employer
     * @param title The title of the JobListing the user is looking for
     * @return Returns all of the JobListings with the title entered by
     * the user
     */
    public ArrayList<JobListing> search(String title) {
        return null;
    }

    /**
     * This search method allows a user to search through all of the internship
     * listings by pay rate, starting with a specified minimum. 
     * 
     * @param payRate The title of the JobListing the user is looking for
     * @return Returns all of the JobListings that meet or exceed the rate entered by
     * the user
     */
    public ArrayList<JobListing> search(double payRate) {
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
    public void writeReview() {
        //Old parameters: User user, int rating, String comment

    	//This method should prompt the employer to search through students, and prompt students to
    	//search through listings to find/select the correct one to review. As it is now, the 
    	//InternshipUI has to pass all three parameters as well as prompt the user to
    	//figure out what to leave a review on, which can probably be better handled here 
    	//in this Application class (which has direct access to the User and databases.
    	
    	//If we don't want to split this into two separate review methods, we need to
    	//check user permissions to see what they should be looking for (e.g. if user.getPermission()
    	// == 0, then it's a student, so let them search listings only.
    }

    /**
     * The createResume method allows a student user to create a resume that
     * employers can view when the student applies for a job
     * @return The resume created by the student
     */
    public Resume createResume() {
        return null;
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
    public void editJobListing() {
    	//This method should have no parameters and should prompt the employer to search through
    	//their own listings. As it is now, the InternshipUI has to somehow pass a JobListing to 
    	//this method, which can probably be better handled here in this Application class.
    }

    /**
     * The removeJobListing method allows an employer to remove a job listing
     * from the database of listings.
     * @param jobListing The job listing the employer wants to remove
     */
    public void removeJobListing() {
    	//This method should have no parameters and should prompt the employer to search through
    	//their own listings to find the one to remove. As it is now, the InternshipUI has to somehow pass 
    	//a JobListing to this method, which can probably be better handled here in this Application class.
        database.removeJobListing(jobListing);
    }

    /**
     * The createCompanyProfile method allows employers to create a profile
     * describing their company
     * @return True if the profile was successfully created, false otherwise
     */
    public boolean createCompanyProfile() {
        System.out.print("Please enter the name of your company: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the address of your company's headquarters: ");
        String hqaddress = scanner.nextLine();
        System.out.print("Please enter a description of your company: ");
        String description = scanner.nextLine();

        return false;
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
    public boolean removeProfile() {
      //Old parameter: CompanyProfile company
      return false;
    }

    /**
     * The removeAccount method allows an Administrator to remove a student or
     * employer account
     * @param user The account that the Administrator wishes to remove
     */
    public void removeAccount() {
        //Old parameter: User user
    	//UI class should probably not have to pass a User to the method. Instead, this method
    	//should use the InternshipApplication's Database variable to prompt/find it.
    	//So we want to remove the parameter.
    }

    /**
     * The registerAdmin method allows administrators to register other
     * administrators to the system. No employee and no student can do this.
     */
    public void registerAdmin() {
        if(this.user.getPermission() == -1){
            
        }
    }

    /**
     * The applyForInternshipMethod allows a student to apply for an internship
     * in the database
     * @param listing The internship the user wants to apply for
     */
    public void applyForInternship(JobListing listing) {
        if(this.user.getPermission() == 0)
        listing.apply((Student)this.user);
    }
    
    /**
     * gets the permission level (int) from the current user
     * @return
     */
    public int getUserPermission(){
        return this.user.getPermission();
    }

    public User getUser(){
        return this.user;
    }
}
