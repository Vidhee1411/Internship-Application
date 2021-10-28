import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import java.io.Console;


/**
 * The InternshipApplication class is a Facade which runs every aspect of the
 * internship application. It allows students to search and apply for an
 * internship as well as review companies, employers to post jobs and review
 * students, and administrators to remove listings, remove accounts, and
 * register new administrators.
 * @author Joshua DuPuis
 */
public class InternshipApplication {
    private User user;
    private int permission;
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
        char[] passwordArr = console.readPassword("Please enter password:");
        String password = new String(passwordArr);
        switch(accountType.toLowerCase()) {
            case "student":
                System.out.println("Enter your year in school");
                String year = scanner.nextLine();
                if(email.substring(email.length() - 3, email.length()).equals("sc.edu")){
                    Student s1 = new Student(firstname,lastname,email,password,year);
                    database.addUser(s1);
                    s1.createResume();
                    return true;
                }
                return false;
            case "employer":
                Employer e1 = new Employer(firstname, lastname, email, password);
                database.addUser(e1);
                return true;
            }
            return false;
    }

    /**
     * The logOn method allows a user with an existing account to log on to the
     * system. Additionally sets the appropriate instance variables in the InternshipApplication 
     * for the logged on user.
     * 
     * @param email The User's email attempt
     * @param password The User's password attempt
     * @return true for a successful login, false otherwise.
     */
    public boolean logOn(String email, String password) {        
        //Assign the right user and permissions if logOn was successful
        for(User user : database.getUsers()) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                this.user = user;
                permission = this.user.getPermission();
                return true;
            }
        }
        return false;
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
        ArrayList<JobListing> search_result = new ArrayList<JobListing>();
        search_result = database.searchListings(title);
        return search_result;
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
        ArrayList<JobListing> search_result = new ArrayList<JobListing>();
        search_result = database.searchListingsbyPay(payRate);
        return search_result;
    }

    /**
     * This sort method allows a user to alphabetically sort the listings from the database.
     */
    public void sortAlphabetically() {
        database.sortListingsAlphabetically();
    }

    /**
     * The editPersonalInfo method allows a user to edit the personal
     * information associated with their account
     */
    public void editPersonalInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to edit?");
        System.out.println("Firstname"+"\n" + "Lastname" +"\n" + "email" +"\n"+"password");
        String answer = scanner.nextLine().toLowerCase();
        switch(answer) {
            case "firstname":
                user.editFirstName(answer);
                break;
            case "lastname":
                user.editLastName(answer);
                break;
            case "email":
                user.editEmail(answer);
                break;
            case "password":
                user.editPassword(answer);
                break;
        }
        scanner.close();
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
        if (permission == 1) {

            this.user.ReviewStudent();
        }
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
     * The toggleJobListingVisibility method allows an employer to toggle the visibility setting
     * of a given jobListing.
     */
    public void toggleJobListingVisibility() {
        if(permission != -1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return;
        }

        try {
            ArrayList<JobListing> currentListings = database.getJobListings();
            System.out.println("Please enter the number of the listing you would like to toggle the visibility of, or 0 to go back: ");
            formatListingVisibilities(currentListings);
            int listingChoice = Integer.parseInt(scanner.nextLine()) - 1;
            while(listingChoice != -1) {       
                if(listingChoice < -1 || listingChoice >= currentListings.size() + 1) {
                    System.out.println("You chose a listing that wasn't listed. Try again with a valid choice, or type 0 to go back.");
                }
                else {
                    //If its a valid choice, toggle the visibility
                    JobListing chosenListing = currentListings.get(listingChoice);
                    boolean currentVisibility = chosenListing.getVisibility();
                    chosenListing.setVisibility(!currentVisibility);
    
                    if(!currentVisibility) {
                        System.out.println("The chosen listing is now visible.");
                    }
                    else {
                        System.out.println("The chosen listing is now hidden.");
                    }
                    System.out.print("If you'd like to toggle the visibility of another listing, enter the number of the internship; 0 to go back: ");
                }
                listingChoice = Integer.parseInt(scanner.nextLine()) - 1;
            }
        } catch(Exception e) {
            System.out.println("You've entered invalid input.");
        }
        System.out.println("\nReturning...\n");
    }

    /**
     * Private helper method used to print out cleanly formatted and descriptive JobListings, including visibility.
     * @param listings The ArrayList of listings to print out
     */
    private void formatListingVisibilities(ArrayList<JobListing> listings) {
        int listingsSize = listings.size();
        if(listingsSize == 0) {
            System.out.println("There are no job listings at this time...\n");
        }
        else {
            System.out.println(listingsSize + " listing(s) available:\n");
        }

        for(int i = 1; i <= listingsSize; i++) {
            JobListing jobListing = listings.get(i-1);
            System.out.println(i + ". " + jobListing.toStringSummary() +
                                "\n\tVisibility: " + jobListing.getVisibility() + "\n");
        }
    }

    /**
     * The toggleJobListingVisibility method allows an employer to toggle the visibility setting
     * of a given jobListing.
     */
    public void toggleReviewVisibility() {
        if(permission != -1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return;
        }

        try {
            System.out.println("Are you changing the visibility of a review on a [S]tudent profile or a [C]ompany profile? Enter 'S' or 'C' for your choice: ");
            String reviewLocation = scanner.nextLine().toLowerCase();
            int reviewChoice;

            switch(reviewLocation) {
                case("s"):
                    System.out.print("What's the email of the student? ");
                    String emailInput = scanner.nextLine();

                    Student student = null;
                    for(User user : database.getUsers()) {
                        if(user.getEmail().equals(emailInput) && user.getPermission() == 0) {
                            student = (Student) user;
                        }
                    }
                    if(student == null) {
                        System.out.println("No student account was found with that email.\n");
                        return;
                    }

                    ArrayList<Review> studentReviews = student.getReviews();
                    if(studentReviews.isEmpty()) {
                        System.out.println("There are no reviews on the student profile to toggle. Returning...\n");
                    }

                    formatReviews(studentReviews);
                    System.out.print("Please enter the number of the review to toggle the visibility of: ");
                    reviewChoice = getUserCommand(studentReviews.size());
                    if(reviewChoice == -1) {
                        System.out.println("You chose an option that doesn't exist. Restart the command if you want to try again.\n");
                        return;
                    }
                    else {
                        studentReviews.get(reviewChoice).toggleVisibility();
                        System.out.println("The chosen review's visibility has been toggled.");
                        return;
                    }
                case("c"):
                    System.out.print("What's the name of the company? ");
                    String companyName = scanner.nextLine();
                    ArrayList<CompanyProfile> results = database.searchProfiles(companyName);
                    if(results.isEmpty()) {
                        System.out.println("No company profiles match the given name. Restart the command if you want to try again.\n");
                        return;
                    }

                    System.out.println("Which company is the one you'll be toggling the review visibility for?");
                    for(int i = 1; i <= results.size(); i++) {
                        System.out.println("\t" + i + ". " + results.get(i-1).toString() + "\n");
                    }
                    int profileIndex = getUserCommand(results.size());
                    if(profileIndex == -1) {
                        System.out.println("You chose an option that doesn't exist. Restart the command if you want to try again.\n");
                        return;
                    }
                    CompanyProfile profileChoice= results.get(profileIndex);
                    ArrayList<Review> profileReviews = profileChoice.getReviews();
                    if(profileReviews.isEmpty()) {
                        System.out.println("There are no reviews on the company profile to toggle. Returning...\n");
                    }

                    formatReviews(profileReviews);
                    System.out.print("Please enter the number of the review to toggle the visibility of: ");
                    reviewChoice = getUserCommand(profileReviews.size());
                    if(reviewChoice == -1) {
                        System.out.println("You chose an option that doesn't exist. Restart the command if you want to try again.\n");
                        return;
                    }
                    else {
                        profileReviews.get(reviewChoice).toggleVisibility();
                        System.out.println("The chosen review's visibility has been toggled.");
                        return;
                    }
                default:
                    System.out.println("You've chosen an option that doesn't exist. Restart the command if you want to try again.\n");
                    return;
            }
        } catch(Exception e) {
            System.out.println("You've entered invalid input. Returning to previous screen...\n");
            return;
        }
    }

    /**
     * Private helper method used to print out cleanly formatted and descriptive JobListings, including visibility.
     * @param reviews The ArrayList of listings to print out
     */
    private void formatReviews(ArrayList<Review> reviews) {
        int reviewsSize = reviews.size();
        System.out.println(reviewsSize + " listing(s) available:\n");

        for(int i = 1; i <= reviewsSize; i++) {
            Review review = reviews.get(i-1);
            System.out.println("\t" + i + ". " + review.toString() +
                                "\n\tVisibility: " + review.getVisibility() + "\n");
        }
    }


    /**
     * The removeJobListing method allows an employer to remove a job listing
     * from the database of listings.
     * @param jobListing The job listing the employer wants to remove
     */
    public void removeJobListing(String title) {
        if (permission == 1) {
            System.out.println("You don't have permission to access this method. Returning to home screen . . .");
            return;
        }
        ArrayList <JobListing> removeResults;
        if (permission == -1) {
            removeResults = database.searchListings(title);
        } else {
            Employer employer = (Employer) user;
            removeResults = employer.getCompany().getListings();
        }
        for (int i = 1; i <= removeResults.size(); i++) {
            System.out.println(i + ": " + removeResults.get(i-1).toStringSummary());
        }
        System.out.println("Please enter the number of the job listing you would like to remove or enter 0 to go back to the main menu: ");
        int input = getUserCommand(removeResults.size());
        if (input == -1) {
            return;
        }
        JobListing listingToRemove = removeResults.get(input);
        database.removeJobListing(listingToRemove);
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
        String hqAddress = scanner.nextLine();
        System.out.print("Please enter a description of your company: ");
        String description = scanner.nextLine();
        this.database.addProfile(new CompanyProfile(name, hqAddress, description));

        return false;
    } 

    /**
     * The associateCompany method allows employers to associate with a
     * company profile that already exists in the system
     */
    public void associateCompany(String company) {
        ArrayList<CompanyProfile> profiles = database.searchProfiles(company);
        for (int i = 1; i <= profiles.size(); i++) {
            System.out.println(i + ": " + profiles.get(i-1).toString());
        }
        System.out.println("Please enter the number of the profile you would like to associate with: ");
        int number = getUserCommand(profiles.size());
        if (number == -1) {
            System.out.println("You entered an invalid number. Returning to the home screen.")
            return;
        }
        Employer employer = (Employer) user;
        employer.associateCompany(profiles.get(number));

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

    /**
     * Private helper method which properly adjusts a user's number of choice to
     * account for zero indexes. Returns the index of the choice after adjusting.
     * 
     * @param numCommands The total number of choices the user has to choose from
     * @return The index of the desired command, or -1 if the user's choice is out of range
     */
    private int getUserCommand(int numCommands) {
        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if(command >= 0 && command <= numCommands-1) return command;
        return -1;
    }
}
