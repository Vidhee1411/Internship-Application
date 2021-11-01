import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
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
        this.database = DataLoader.loadData();
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
        System.out.print("Please enter your first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Please enter your last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();

        UUID newUUID = UUID.randomUUID();
        String password = null;

        switch(accountType.toLowerCase()) {
            case "student":
                // Confirm email matches sc.edu and is of sufficient length
                while (!(email.substring(email.length() - 6, email.length()).equals("sc.edu")) ) {
                    System.out.println("Please enter your school email containing \"sc.edu\"");
                    email = scanner.nextLine();
                }
                password = confirmPassword();
                if(password == null) break;

                System.out.println("Enter your year in school (e.g. junior)");
                String year = scanner.nextLine();
                Student s1 = new Student(firstname,lastname,email,password,year);
                database.addUser(s1);
                this.user = s1;
                user.setUserUUID(UUID.randomUUID());
                return true;

            case "employer":
                password = confirmPassword();
                if(password == null) break;

                Employer e1 = new Employer(firstname, lastname, email, password);
                database.addUser(e1);
                this.user = e1;
                user.setUserUUID(UUID.randomUUID());
                return true;
        }
        return false;
    }

    /**
     * Private helper method used to handle password creation when creating an account, including confirmation.
     * Passwords must be 8 characters or longer.
     * 
     * @return The password of the account, or null if unsuccessful
     */
    private String confirmPassword() {
        Console console = System.console();
        try {
            while(true) {
                char[] passwordArr = console.readPassword("Please enter the password you want to use (8 or more characters): ");
                String passwordAttempt1 = new String(passwordArr);
                if(passwordAttempt1.length() < 8) {
                    System.out.println("Your password must be 8 characters or longer.");
                    continue;
                }
                char[] passwordArr2 = console.readPassword("Confirm your password by re-entering it: ");
                String passwordAttempt2 = new String(passwordArr2);
                if(passwordAttempt1.equals(passwordAttempt2)) {
                    System.out.println("Password created sucessfully!");
                    return passwordAttempt2;
                }
                System.out.println("The passwords you've entered do not match. Try again.");
            }
        } catch(Exception e) {
            System.out.println("You have entered invalid input. Please retry if you want to create an account.");
            return null;
        }
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
     * using it. Writes current database data to JSON files through the DataWriter class.
     */
    public void logOff() {
        System.out.println("\nLogging off...");
        DataWriter.saveUsers();
        DataWriter.saveCompanyProfiles();
        DataWriter.saveJobListings();
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
     * The searchBySkill method searches the database for any job listings that have the 
     * specified skill as a required skill.
     * @param skill The name of the skill desired
     * @return The ArrayList of all job listings with the given skill
     */
    public ArrayList<JobListing> searchBySkill(String skill) {
        ArrayList<JobListing> search_result = database.searchListingsBySkill(skill);
        return search_result;
    }

    /**
     * This sort method allows a user to alphabetically sort the listings from the database.
     */
    public void sortAlphabetically() {
        database.sortListingsAlphabetically();
    }

    /**
     * This sort method allows a user to sort the listings in the database by pay amount.
     */
    public void sortByPay() {
        database.sortListingsbyPay();
    }

    /**
     * The editPersonalInfo method allows a user to edit the personal
     * information associated with their account
     */
    public void editPersonalInfo() {
        System.out.println("Your Personal Information:\nFirst name: " + user.getFirstName() + "\nLast Name: " + user.getLastName() + "\nEmail: " + user.getEmail() + "\nPassword: " + user.getPassword());
        System.out.println("What would you like to edit?");
        System.out.println("[F]irst name\n[L]ast name\n[E]mail\n[P]assword");
        try {
            String answer = scanner.nextLine().toLowerCase();
            switch(answer) {
                case "f":
                    System.out.println("What would you like your first name to be?");
                    String newFirstName = scanner.nextLine();
                    user.editFirstName(newFirstName);
                    System.out.println("The first name on your account is: " + user.getFirstName());
                    break;
                case "l":
                    System.out.println("What would you like your last name to be?");
                    String newLastName = scanner.nextLine();
                    user.editLastName(newLastName);
                    System.out.println("The last name on your account is: " + user.getLastName());
                    break;
                case "e":
                    System.out.println("What would you like your email to be?");
                    String newEmail = scanner.nextLine();
                    if(newEmail.length() < 3) {
                        System.out.println("Email is too short. Returning...\n");
                    } 
                    //If it's a student changing their email address, check for .edu
                    if(permission == 0 && !(newEmail.substring(newEmail.length()-6).equals("sc.edu"))) {
                        System.out.println("Your new email must still be one associated with the university.");
                        return;
                    }
                    user.editEmail(newEmail);
                    System.out.println("The email on your account is: " + user.getEmail());

                    break;
                case "p":
                    System.out.println("What would you like your new password to be?");
                    String newPassword = scanner.nextLine();
                    if(newPassword.length() < 8) {
                        System.out.println("Your password must be 8 characters or longer.");
                        return;
                    }
                    user.editPassword(newPassword);
                    System.out.println("The password on your account is: " + user.getPassword());
                    break;
                default:
                    System.out.println("You entered an option that wasn't listed. Restart this command if you want to try again.\n");
                    return;
            }
        } catch(Exception e) {
            System.out.println("You've entered invalid input. Restart the command to try again.\n");
        }
       
    }

    /**
     * The writeReview method allows either an employer or a student to write a
     * review.
     */
    public void writeReview() {
        try {
            if (permission > 1 || permission < 0) {
                System.out.println("You don't have valid permissions to use this command.\n");
                return;
            }
            //Student leaving a review on a company profile
            else if(permission == 0) {
                Student student = (Student) this.user;
                System.out.print("Enter the name of the company you'd like to review: ");
                ArrayList<CompanyProfile> companyChoices = database.searchProfiles(scanner.nextLine());
                if(companyChoices.isEmpty()) {
                    System.out.println("No companies were found with the given name. Retry this command if you want to try again.\n");
                    return;
                } 
    
                formatCompanyProfiles(companyChoices);
                System.out.println("If any of these choices match the company you want to review, enter the number listed beside it. Enter 0 to exit.");
                int index = getUserCommand(companyChoices.size());
                if(index == -1) {
                    System.out.println("Returning...\n");
                    return;
                }
                else {
                    CompanyProfile companyToReview = companyChoices.get(index);
                    System.out.print("On a scale of 1 to 5 (no decimals), what is your rating of the company (e.g. 3)? ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    if(rating > 5 || rating < 1) {
                        System.out.println("You entered an invalid rating. The review could not be created.\n");
                        return;
                    }
                    System.out.println("Enter the comment you would like to leave with your review: ");
                    String comment = scanner.nextLine().trim();

                    student.reviewCompany(rating, comment, companyToReview);
                    System.out.println("Your review for " + companyToReview.getCompanyName() + " has been made successfully. Returning...\n");
                }
            }
            //Employer leaving review on a student profile
            else {
                Employer employer = (Employer) this.user;
                Student student = null;

                System.out.print("Enter the email of the student you'd like to leave a review for: ");
                String studentEmail = scanner.nextLine();
                for(User user : database.getUsers()) {
                    if(user.getPermission() == 0 && user.getEmail().equals(studentEmail)) {
                        student = (Student) user;
                    }
                }
                if(student == null) {
                    System.out.println("No student account has the email you've provided.\n");
                    return;
                }

                System.out.print("On a scale of 1 to 5 (no decimals), what is your rating of the student (e.g. 3)? ");
                int rating = scanner.nextInt();
                scanner.nextLine();
                if(rating > 5 || rating < 1) {
                    System.out.println("You entered an invalid rating. The review could not be created.\n");
                    return;
                }
                System.out.println("Enter the comment you would like to leave with your review: ");
                String comment = scanner.nextLine().trim();
                employer.reviewStudent(rating, comment, student);
                System.out.println("Your review for " + student.getFirstName() + " " + 
                    student.getLastName() + " has been made successfully. Returning...\n");
            }
        } catch(Exception e) {
            System.out.println("You entered invalid input. Retry the command to try again.\n");
        }
    }

    /**
     * Private helper method used to print out cleanly formatted and descriptive JobListings, including visibility.
     * @param reviews The ArrayList of listings to print out
     */
    private void formatCompanyProfiles(ArrayList<CompanyProfile> companies) {
        int companiesSize = companies.size();
        System.out.println(companiesSize + " listing(s) available:\n");

        for(int i = 1; i <= companiesSize; i++) {
            CompanyProfile company = companies.get(i-1);
            System.out.println("\t" + i + ". " + company.toString() + "\n");
        }
    }

    /**
     * The createResume method allows a student user to create a resume that
     * employers can view when the student applies for a job
     */
    public void createResume() {
        if(permission != 0) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Student student = (Student) this.user;
        ArrayList<Integer> skillIndexes = new ArrayList<>();
        ArrayList<Integer> classIndexes = new ArrayList<>();
        ArrayList<WorkExperience> workExperiences = new ArrayList<>();
        ArrayList<Education> educations = new ArrayList<>();

        System.out.println("Resumes require skills and classes to be added to your profile.\n" 
            + "Please enter any skills you'd like to add to your account profile (one at a time), or enter \"0\" to continue."
            + "\nThese are the current skills on your profile: " + student.getSkills());
        String input = scanner.nextLine();
        while (!(input.equals("0"))) {
            if (student.getSkills().contains(input)) {
                System.out.println("That skill is already in your profile.");
            } else {
                student.addSkill(input);
            }
            System.out.println("Enter another skill, or enter \"0\" to continue.");
            input = scanner.nextLine();
        }

        System.out.println("Please enter any classes you'd like to add to your account profile (one at a time), or enter \"0\" to continue."
        + "\nThese are the current classes on your profile: " + student.getClasses());
        input = scanner.nextLine();
        while (!(input.equals("0"))) {
            if (student.getClasses().contains(input)) {
                System.out.println("That class is already in your profile.");
            } else {
                student.addClass(input);
            }
            System.out.println("Enter another class, or enter \"0\" to continue.");
            input = scanner.nextLine();
        }

        try {
            //Get appropriate skills
            System.out.println("Which skills would you like to add to your resume? Enter the name of each skill one at a time, and type 0 when done.");
            System.out.println("These are your current skills: " + student.getSkills());
            String skillName = scanner.nextLine();
            while(!skillName.equals("0")) {
                int skillIndex = student.getSkillIndex(skillName);
                if(skillIndex != -1) {
                    skillIndexes.add(skillIndex);
                    System.out.println("Skill added successfully.");
                }
                else {
                    System.out.println("That skill isn't on your profile. Be sure to add any new skills to your profile first!" 
                        + "\nEnter the name of each skill (one at a time), and type 0 when done.");
                }
                skillName = scanner.nextLine();
            }

            //Get appropriate classes
            System.out.println("Which classes would you like to add to your resume? Enter the name of each class one at a time, and type 0 when done.");
            System.out.println("These are your current classes: " + student.getClasses());
            String className = scanner.nextLine();
            while(!className.equals("0")) {
                int classIndex = student.getClassIndex(className);
                if(classIndex != -1) {
                    classIndexes.add(classIndex);
                    System.out.println("Class added successfully.");
                }
                else {
                    System.out.println("That class isn't on your profile. Be sure to add any new classes to your profile first!" 
                    + "\nEnter the name of each class (one at a time), and type 0 when done.");
                }
                className = scanner.nextLine();
            }

            //Create work experiences
            System.out.print("Would you like to add any work experiences [Y/N]? ");
            String yesNoOption = scanner.nextLine().toLowerCase();
            int count = 1;
            while(!yesNoOption.equals("n")) {
                if(yesNoOption.equals("y")) {
                    System.out.println("Work Experience #" + count + ":");
                    System.out.print("\tWhat is the title of this work experience? ");
                    String jobTitle = scanner.nextLine();
                    System.out.print("\tWhat is the company/organization you worked for? ");
                    String company = scanner.nextLine();
                    System.out.print("\tProvide the date range of this experience (e.g. 7/7/21-8/7/21): ");
                    String dateRange = scanner.nextLine();
                    System.out.print("\tGive a description of this work experience: ");
                    String description = scanner.nextLine();

                    workExperiences.add(new WorkExperience(jobTitle, company, dateRange, description));
                    System.out.print("\nWork experience #" + count++ + " has been added. Would you like to add another [Y/N]? ");
                }
                else { 
                    System.out.print("You entered an answer that wasn't Y or N. Try again: ");
                }
                yesNoOption = scanner.nextLine().toLowerCase();
                System.out.println();
            }
            
            //Create Educations
            System.out.print("Would you like to add any education (colleges, major, gpa, etc.) [Y/N]? ");
            yesNoOption = scanner.nextLine().toLowerCase();
            count = 1;
            while(!yesNoOption.equals("n")) {
                if(yesNoOption.equals("y")) {
                    System.out.println("Education Item #" + count + ":");
                    System.out.print("\tWhat university/college did you attend? ");
                    String nameOfUniversity = scanner.nextLine();
                    System.out.print("\tWhat is/was your major at said university/college? ");
                    String major = scanner.nextLine();
                    System.out.print("\tWhat was your GPA there? (e.g. 2.45, 4.00): ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("\tWhat is your expected grad date (or actual grad date if graduated) (e.g. Spring 2024)? ");
                    String expectedGradDate = scanner.nextLine();

                    educations.add(new Education(nameOfUniversity, major, gpa, expectedGradDate));
                    System.out.print("\nEducation Item #" + count++ + " has been added. Would you like to add another [Y/N]? ");
                }
                else { 
                    System.out.print("You entered an answer that wasn't Y or N. Try again: ");
                }
                yesNoOption = scanner.nextLine().toLowerCase();
            }
            student.createResume(skillIndexes, classIndexes, workExperiences, educations);
            System.out.println("Your resume was created successfully and has been added to your profile! Returning...\n");
        } catch(Exception e) {
            System.out.println("You entered invalid input. Retry the command to try again.\n");
        }
    }

    /**
     * The editResume method allows a student to edit information on their
     * resume by overwriting it with a new one.
     */
    public void editResume() {
        if(permission != 0) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Student student = ((Student)user);
        boolean editingResume = true;
        while(editingResume) {
            System.out.println("Your current resume:\n" + student.getResume().toString());
            System.out.println("What would you like to edit on your resume?");
            System.out.println("1. Skills\n2. Previous Work Experiences\n3. Education\n4. Classes\n5. Return to Main Menu");
            System.out.println("Please enter the number of your preferred action: ");
            String input = scanner.nextLine();
            switch(input) {
                case("1"):
                    System.out.println("Skills in your profile: " + student.getSkills());
                    System.out.println("Please enter \"1\" to add a skill to your resume, \"2\" to remove a skill from your resume, or \"3\" to go back:");
                    String input2 = scanner.nextLine();
                    switch (input2) {
                        case("1"):
                            System.out.println("Please enter a skill to add to your resume");
                            String newSkill = scanner.nextLine();
                            if (student.getSkills().indexOf(newSkill) == -1) {
                                student.addSkill(newSkill);
                            }
                            if (student.getResume().getSkills().indexOf(newSkill) != -1) {
                                System.out.println("You entered a skill that is already on your resume. Returning to previous screen.");
                                break;
                            }
                            student.getResume().addSkill(newSkill);
                            break;
                        case("2"):
                            System.out.println("Please enter a skill to remove from your resume");
                            String removeSkill = scanner.nextLine();
                            if (student.getResume().getSkills().indexOf(removeSkill) == -1) {
                                System.out.println("You entered a skill that isn't on your resume. Returning to previous screen.");
                                break;
                            }
                            student.getResume().removeSkill(removeSkill);
                            break;
                        case("3"):
                            break;
                    }
                    break;
                case("2"):
                    System.out.println("Work experiences on your resume: ");
                    ArrayList <WorkExperience> experiences = student.getResume().getExperiences();
                    for (WorkExperience exp: experiences) {
                        System.out.println(exp.toString());
                    }
                    System.out.println("Please enter \"1\" to add a Work Experience to your resume, \"2\" to remove a Work Experience from your resume, or \"3\" to go back:");
                    String input3 = scanner.nextLine();
                    switch (input3) {
                        case("1"):
                            System.out.print("\tWhat is the title of your new work experience? ");
                            String jobTitle = scanner.nextLine();
                            System.out.print("\tWhat is the company/organization you worked for? ");
                            String company = scanner.nextLine();
                            System.out.print("\tProvide the date range of this experience (e.g. 7/7/21-8/7/21): ");
                            String dateRange = scanner.nextLine();
                            System.out.print("\tGive a description of this work experience: ");
                            String description = scanner.nextLine();
                            student.getResume().addExperience(new WorkExperience(jobTitle, company, dateRange, description));
                            break;
                        case("2"):
                            System.out.println("What is the title of the work experience you would like to remove?");
                            String removeJob = scanner.nextLine();
                            for (WorkExperience exp: experiences) {
                                if (exp.getJobTitle().equals(removeJob)) {
                                    student.getResume().removeExperience(exp);
                                    break;
                                }
                            }
                            break;
                        case("3"):
                            break;
                    }
                    break;
                case("3"):
                    System.out.println("Education on your resume: ");
                    ArrayList <Education> education = student.getResume().getEducation();
                    for (Education edu: education) {
                        System.out.println(edu.toString());
                    }
                    System.out.println("Please enter \"1\" to add an Education to your resume, \"2\" to remove an Education from your resume, or \"3\" to go back:");
                    String input4 = scanner.nextLine();
                    switch (input4) {
                        case("1"):
                            System.out.print("\tWhat university/college did you attend? ");
                            String nameOfUniversity = scanner.nextLine();
                            System.out.print("\tWhat is/was your major at said university/college? ");
                            String major = scanner.nextLine();
                            System.out.print("\tWhat was your GPA there? (e.g. 2.45, 4.00): ");
                            double gpa = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("\tWhat is your expected grad date (or actual grad date if graduated) (e.g. Spring 2024)? ");
                            String expectedGradDate = scanner.nextLine();
                            student.getResume().addEducation(new Education(nameOfUniversity, major, gpa, expectedGradDate));
                            break;
                        case("2"):
                            System.out.println("What is the name of the university of the education you would like to remove?");
                            String removeEducation = scanner.nextLine();
                            for (Education edu: education) {
                                if (edu.getName().equals(removeEducation)) {
                                    student.getResume().removeEducation(edu);
                                    break;
                                }
                            }
                            break;
                        case("3"):
                            break;
                    }
                    break;
                case("4"):
                    System.out.println("Classes in your profile: " + student.getClasses());
                    System.out.println("Please enter \"1\" to add a class to your resume, \"2\" to remove a class from your resume, or \"3\" to go back:");
                    String input5 = scanner.nextLine();
                    switch (input5) {
                        case("1"):
                            System.out.println("Please enter a class to add to your resume");
                            String newClass = scanner.nextLine();
                            if (student.getClasses().indexOf(newClass) == -1) {
                                student.addClass(newClass);
                            }
                            if (student.getResume().getClasses().indexOf(newClass) != -1) {
                                System.out.println("You entered a class that is already on your resume. Returning to previous screen.");
                                break;
                            }
                            student.getResume().addClass(newClass);
                            System.out.println();
                            break;
                        case("2"):
                            System.out.println("Please enter a class to remove from your resume");
                            String removeClass = scanner.nextLine();
                            if (student.getResume().getClasses().indexOf(removeClass) == -1) {
                                System.out.println("You entered a class that isn't on your resume. Returning to previous screen.");
                                break;
                            }
                            student.getResume().removeClass(removeClass);
                            break;
                        case("3"):
                            break;
                    }
                break;
                case("5"):
                    System.out.println("Returning to main menu");
                    editingResume = false;
                    break;
            }
        }
    }

    /**
     * The viewResume method allows a student to view their resume.
     */
    public void viewResume() {
        if(permission != 0) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Student student = (Student) user;
        if(student.getResume() == null) {
            System.out.println("You have not created a resume yet. You can only view your resume once you've made one!\n");
            return;
        }
        System.out.println("\n" + student.getResume().toString());
    }

    /**
     * The printResumeToFile method allows a student to print the contents of their resume
     * to a text file called "resume.txt".
     */
    public void printResumeToFile() {
        if(permission != 0) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Student student = (Student) user;
        student.getResume().toFile();

        System.out.println("\nYour resume was successfully printed to 'resume.txt'!\n");
    }

    /**
     * The createJobListing method allows an employer to create a Job Listing
     * that students can apply for on their company profile.
     */
    public void createJobListing(){
        if(permission != 1) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Employer employer = (Employer) this.user;
        CompanyProfile company = employer.getCompany();
        if(company == null) {
            System.out.println("You currently have no company listed on your profile. Create a new company profile or associate with " + 
                "a pre-existing one before creating a job listing.");
            return;
        }
        
        try {
            System.out.print("What is the title of the listing? ");
            String title = scanner.nextLine();
            System.out.print("What is the description of the listing? ");
            String description = scanner.nextLine();
            System.out.print("Where is the job/internship being offerred (e.g. Online, Five Points, etc.)? ");
            String location = scanner.nextLine();
            System.out.print("What is the pay rate for the position in dollars per hour (e.g. 7.25)? If unpaid, enter 0: ");
            double payRate = scanner.nextDouble();
            scanner.nextLine();
            boolean paid = (payRate <= 0.00);
            JobListing jobListingToAdd = new JobListing(title, description, location, paid, payRate, company.getCompanyName());
            jobListingToAdd.setUUID(UUID.randomUUID());
            company.addListing(jobListingToAdd); 
            System.out.println("Your listing has been added. Be sure to add any required skills by editing your listing back at the home menu!\n"); 
        } catch(Exception e) {
            System.out.println("You entered invalid input. Retry the command to try again.\n");
        }
    }

    /**
     * The editJobListing method allows an employer to edit the description of
     * an existing job listing and change its visibility setting
     * @param jobListing The jobListing the employer wants to edit
     */
    public void editJobListing() {
        if(permission != 1) {
            System.out.println("You don't have valid permissions to use this command.\n");
            return;
        }
        Employer employer = (Employer) this.user;
        ArrayList<JobListing> companyListings = employer.getCompany().getListings();

        if(companyListings.isEmpty()) {
            System.out.println("There are no listings to edit on your company profile.\n");
            return;
        }

        try {
            System.out.print("Please enter the number of the listing you would you like to edit the information of: ");
            formatListingVisibilities(companyListings);
            int listingChoice = getUserCommand(companyListings.size());
            JobListing listingToEdit = companyListings.get(listingChoice);

            System.out.println("What would you like to edit?");
            System.out.println("Title\n" + "Description\n" + "Location\n" + "Pay Rate\n" + "Visibility\n" + "Skills\n");
            String answer = scanner.nextLine().toLowerCase();
            switch(answer) {
                case "title":
                    System.out.println("What would you like the title to be?");
                    String newTitle= scanner.nextLine();
                    listingToEdit.editTitle(newTitle);
                    break;
                case "description":
                    System.out.println("What would you like the description to be?");
                    String newDescription = scanner.nextLine();
                    listingToEdit.editDescription(newDescription);
                    break;
                case "location":
                    System.out.println("What would you like the location to be?");
                    String newLocation = scanner.nextLine();
                    listingToEdit.editLocation(newLocation);
                    break;
                case "pay rate":
                case "payrate":
                    System.out.println("What would you like the pay rate to be (in $/hour, e.g. 7.25)?");
                    double newPayRate = scanner.nextDouble();
                    scanner.nextLine();
                    listingToEdit.editPayRate(newPayRate);
                    break;
                case "visibility":
                    System.out.println("Toggling visibility...");
                    listingToEdit.setVisibility(!listingToEdit.getVisibility());
                    break;
                case "skill":
                case "skills":
                    editJobListingSkills(listingToEdit);
                    break;
                default:
                    System.out.println("You entered an option that wasn't listed. Restart this command if you want to try again\n.");
                    return;
            }
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("You entered invalid input. Retry the command to try again.\n");
        }
    }
    
    /**
     * Private helper method which prompts the user to add or remove skills from a job listing.
     * @param listing
     */
    private void editJobListingSkills(JobListing listing) {
        System.out.println("These are the current skills: " + listing.getRequiredSkills());
        boolean input = true;
        while(input) {
            System.out.println("Would you like to [A]dd or [R]emove a skill? Type 'A' to add, 'R' to remove, or 'Q' to quit.");
            try {
                String choice = scanner.nextLine().toLowerCase();
                switch(choice) {
                    case("a"):
                        System.out.print("Enter the name of the skill you want to add: ");
                        listing.addRequiredSkill(scanner.nextLine());
                        break;
                    case("r"):
                        if(listing.getRequiredSkills().isEmpty()) {
                        System.out.println("There are no skills to remove.");
                        break;
                        }
                        System.out.print("Enter the name of the skill you want to remove: ");
                        listing.removeRequiredSkill(scanner.nextLine());
                        break;
                    case("q"):
                        System.out.println("Returning...\n");
                        input = false;
                    default:
                        System.out.println("You entered an invalid option. Try again.");
                }
                System.out.println("These are the current skills: " + listing.getRequiredSkills());
            } catch(Exception e) {
                System.out.println("You entered invalid input. Retry the command to try again.\n");
            }
        }
    }

    public void viewJobListingApplicants() {
        if(permission != 1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return;
        }
        Employer employer = (Employer) this.user;
        ArrayList<JobListing> employerListings = employer.getCompany().getListings();
        if(employerListings.isEmpty()) {
            System.out.println("You must have pre-existing job listings before you can view the applicants for them!\n");
            return;
        }
        formatListingVisibilities(employerListings);
        System.out.println("Please enter the number of the listing you would like to view the applicants of, or 0 to go back: ");
        int listingChoice = Integer.parseInt(scanner.nextLine()) - 1;
        while(listingChoice != -1) {       
            if(listingChoice < -1 || listingChoice >= employerListings.size() + 1) {
                System.out.println("You chose a listing that wasn't listed. Try again with a valid choice, or type 0 to go back.");
            }
            else {
                //If its a valid choice, attempt to view applicants
                JobListing chosenListing = employerListings.get(listingChoice);
                ArrayList<Student> chosenApplicants = chosenListing.getApplicants();
                if(chosenApplicants.isEmpty()) {
                    System.out.println("The selected job listing currently has no applicants to view.\n");
                }
                else {
                    for(Student applicant : chosenApplicants) {
                        System.out.println(applicant.toString() + "\n---------------------------");
                    }
                }
                System.out.print("If you'd like to view applicants of another listing, enter the number of the listing; 0 to go back: ");
            }
            listingChoice = Integer.parseInt(scanner.nextLine()) - 1;
        }
        System.out.println(); //For formatting
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
            return;
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
                        return;
                    }

                    for (int i = 0; i < studentReviews.size(); i++) {
                        System.out.println((i+1) + ". " + studentReviews.get(i).toString() + "\nVisible: " + studentReviews.get(i).getVisibility());
                    }
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
                        System.out.println( i + ". " + results.get(i-1).getCompanyName() + "\n");
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

                    for (int i = 0; i < profileReviews.size(); i++) {
                        System.out.println((i+1) + ". " + profileReviews.get(i).toString() + "\nVisible: " + profileReviews.get(i).getVisibility());
                    }
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
        if (reviews.size() == 0) {
            return;
        }
        ArrayList<Integer> hiddenIndices = new ArrayList<Integer>();
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getVisibility() == false) {
                hiddenIndices.add(0, i);
            }
        }
        for (Integer index: hiddenIndices) {
            reviews.remove(reviews.get(index));
        }
        System.out.println(reviews.size() + " review(s) available:\n");

        for(int i = 1; i <= reviews.size(); i++) {
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
        if (permission != 1 || permission != 0) {
            System.out.println("You don't have permission to use this command. Returning to home screen . . .");
            return;
        }
        ArrayList <JobListing> removeResults;
        if (permission == -1) {
            removeResults = database.searchListings(title);
        } 
        Employer employer = (Employer) user;
        removeResults = employer.getCompany().getListings();
        
        for (int i = 1; i <= removeResults.size(); i++) {
            System.out.println(i + ": " + removeResults.get(i-1).toStringSummary());
        }
        System.out.println("Please enter the number of the job listing you would like to remove or enter 0 to go back to the main menu: ");
        int input = getUserCommand(removeResults.size());
        if (input == -1) {
            return;
        }
        JobListing listingToRemove = removeResults.get(input);
        employer.getCompany().removeListing(listingToRemove);
        database.removeJobListing(listingToRemove);

        System.out.println("\nThe job listing has been removed. Returning...\n");
    }

    /**
     * The createCompanyProfile method allows employers to create a profile
     * describing their company
     * @return True if the profile was successfully created, false otherwise
     */
    public boolean createCompanyProfile() {
        if(permission != 1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return false;
        }
        try {
            System.out.print("Please enter the name of your company: ");
            String name = scanner.nextLine();
            System.out.print("Please enter the address of your company's headquarters: ");
            String hqAddress = scanner.nextLine();
            System.out.print("Please enter a description of your company: ");
            String description = scanner.nextLine();
            CompanyProfile companyProfileToAdd = new CompanyProfile(name, hqAddress, description);
            companyProfileToAdd.setUUID(UUID.randomUUID());
            this.database.addProfile(companyProfileToAdd);
            return true;
        } catch(Exception e) {
            System.out.println("You've entered invalid input. Returning to previous screen...\n");
            return false;
        }

    } 

    /**
     * The associateCompany method allows employers to associate with a
     * company profile that already exists in the system
     */
    public void associateCompany() {
        if(permission != 1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return;
        }
        //Check if any profiles exist at all
        ArrayList<CompanyProfile> allProfiles = database.getCompanyProfiles();
        if(allProfiles.isEmpty()) {
            System.out.println("\nWhoops! There are no existing company profiles at this time. Why don't you create one?\n");
            return;
        }

        try {
            System.out.println("Please enter the name of the company you would like to associate with: ");
            String companyToSearch = scanner.nextLine();

            //Check if any search results match
            ArrayList<CompanyProfile> profiles = database.searchProfiles(companyToSearch);
            if(profiles.isEmpty()) {
                System.out.println("\nNo company profile could be found with that name. If it doesn't exist, try creating a new company profile.\n");
                return;
            }

            System.out.println();   //formatting
            for (int i = 1; i <= profiles.size(); i++) {
                System.out.println(i + ": " + profiles.get(i-1).toString() + "----------------------------------");
            }
            System.out.println("Please enter the number of the profile you would like to associate with: ");
            int number = getUserCommand(profiles.size());
            if (number == -1) {
                System.out.println("\nYou entered an invalid number. Returning to the home screen.\n");
                return;
            }
            Employer employer = (Employer) user;
            employer.associateCompany(profiles.get(number));   
            System.out.println("\nCompany associated! Now you can begin to add listings to the profile if you desire.\n"); 
        } catch(Exception e) {
            System.out.println("\nYou've entered invalid input. Returning to previous screen...\n");
            return;
        }
    }

    public void viewCompanyProfile() {
        if(permission != 1) {
            System.out.println("You don't have valid permissions to do this.\n");
            return;
        }
        Employer employer = (Employer) this.user;

        if(employer.getCompany() == null) {
            System.out.println("\nYou have to create a company profile or associate with a pre-existing one before you can view it. Returning...\n");
            return;
        }
        System.out.println("Here's your current company profile: \n" + 
            employer.getCompany().toString());
    }

    /**
     * The removeProfile method allows an administrator to remove a
     * CompanyProfile they deem illigitimate
     * @param company The name of the company who's profile the admin wants
     * to remove
     * @return Returns true if the company exits, false otherwise
     */
    public void removeProfile() {
      if (permission != -1) {
          System.out.println("You don't have permission to use this command. Returning to home screen . . .");
          return;
      }
      try {
          System.out.println("What is the name of the company you would like to remove?");
          String nameInput = scanner.nextLine();
          CompanyProfile profileToRemove = null;
            for(CompanyProfile profile : database.getCompanyProfiles()) {
                if(profile.getCompanyName().equals(nameInput)) {
                    profileToRemove = profile;
                }
            }
            if(profileToRemove == null) {
                System.out.println("No company profile is associated with that name. Returning to previous menu...\n");
                return;
            }
            System.out.println("Are you sure you want to remove this profile? [Y/N]");
            String choice = scanner.nextLine().toLowerCase();
            if(choice.equals("n")) {
                System.out.println("Aborting command. Returning to previous menu...\n");
                return;
            }
            else if(choice.equals("y")) {
                database.removeProfile(profileToRemove);
                System.out.println("The specified profile has been removed.\n");
                return;
            }
            else {
                System.out.println("You entered something that wasn't 'Y' or 'N'. Restart the command if you want to try again.\n");
            }

      } catch(Exception e) {
        System.out.println("You entered an invalid input. Returning...\n");
        return;
      }
      return;
    }

    /**
     * The removeAccount method allows an Administrator to remove a student or
     * employer account
     * @param user The account that the Administrator wishes to remove
     */
    public void removeAccount() {
        if (permission != -1) {
            System.out.println("You don't have permission to use this command. Returning to home screen . . .");
            return;
        }
        
        try {
            System.out.print("What is the email of the user you want to remove? ");
            String emailInput = scanner.nextLine();

            User usertoRemove = null;
            for(User user : database.getUsers()) {
                if(user.getEmail().equals(emailInput)) {
                    usertoRemove = user;
                }
            }
            if(usertoRemove == null) {
                System.out.println("No account is associated with that email. Returning to previous menu...\n");
                return;
            }
        
            System.out.println("Are you sure you want to remove this user? [Y/N]");
            String choice = scanner.nextLine().toLowerCase();
            if(choice.equals("n")) {
                System.out.println("Aborting command. Returning to previous menu...\n");
                return;
            }
            else if(choice.equals("y")) {
                Administrator user = (Administrator) this.user;
                user.removeAccount(usertoRemove);
                System.out.println("The specified user has been removed.\n");
                return;
            }
            else {
                System.out.println("You entered something that wasn't 'Y' or 'N'. Restart the command if you want to try again.\n");
            }
        } catch(Exception e) {
            System.out.println("You entered an invalid input. Returning...\n");
        }
    }

    /**
     * The registerAdmin method allows administrators to register other
     * administrators to the system. No employee and no student can do this.
     */
    public void registerAdmin() {
        if(this.user.getPermission() != -1){
            System.out.println("You don't have permission to use this command. Returning to home screen . . .");
            return;
        }
        try {
            System.out.println("What is the first name of the new administrator?");
            String firstName = scanner.nextLine();
            System.out.println("What is the last name of the new administrator?");
            String lastName = scanner.nextLine();
            System.out.println("What is the email of the new administrator?");
            String email = scanner.nextLine();
            System.out.println("What is the password of the new administrator?");
            String password = scanner.nextLine();
            UUID newUUID = UUID.randomUUID();

            database.addUser(new Administrator(firstName, lastName, email, password, newUUID));
            System.out.println("A new admininstrator has successfully been registered.");
        } catch(Exception e) {
            System.out.println("You entered an invalid input. Please restart the command to try again.\n");
            return;
        }
    }

    /**
     * The applyForInternship method allows a student to apply for an internship.
     * in the database
     * @param listing The internship the user wants to apply for
     * @return True if the application is successful, false otherwise
     */
    public boolean applyForInternship(JobListing listing) {
        if(this.user.getPermission() == 0) {
            Student student = (Student) this.user;
            if(student.getResume() == null) {
                System.out.println("You need to create a resume before you can apply for a listing.\n");
                return false;
            }
            else if(listing.getApplicants().contains(student)) {
                System.out.println("You've already applied for this job listing before.\n");
                return false;
            }
            else {
                listing.apply(student);
                return true;
            }
        }
        else {
            System.out.println("You don't have permission to use this command. Returning to home screen . . .");
            return false;
        }
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
     * Private helper method which properly adjusts a user's prompted choice to
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
