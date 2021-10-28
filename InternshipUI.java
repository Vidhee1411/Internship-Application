import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;


/**
 * The InternshipUI class creates a user interface for any user that opens
 * the internship application. It contains a run method which runs the
 * application and a private displayMainMenu method which displays the main
 * menu of the program.
 * @author Zachary Young
 * @author Joshua DuPuis
 */
public class InternshipUI {
    private InternshipApplication application;
    private Scanner scanner;
    private static final String START_MESSAGE = "Welcome!";
    private static final String CHOICE_PROMPT = "Please enter the number next to your preferred action: ";
    private String[] startMenuOptions = {"Log In","Create Account"};
    private String[] mainMenuOptionsStudent = {"Edit personal information","Search for Internship","Review an Internship","Create your Resume","Edit Your Resume"};
    private String[] mainMenuOptionsEmployer = {"Edit personal information","Create a Company Profile","Create a Job Listing","Edit a Job Listing","Remove a Job Listing", "Review a Student"};
    private String[] mainMenuOptionsAdmin = {"Edit personal information","Search for an Internship","Remove User Account","Remove Company Profile","Edit Job Listing Visibility","Edit Review Visibility","Create New Admin Account"};
    private String[] internshipSearchOptions = {"Internship Title","Pay Rate"};

    public InternshipUI() {
        scanner = new Scanner(System.in);
        application = new InternshipApplication();
    }

    /**
     * The run method runs the application, opening the main menu and allowing
     * the user to chose which feature of the application they would like to
     * use.
     */
    public void run() {
        boolean loggedIn = false;
        int userPermission = -1;
        int userCommand;
        System.out.println(START_MESSAGE);

        // The start menu loop. Loops until guest quits or successfully logs in
        while(!loggedIn) {
            displayMenu(startMenuOptions);
            userCommand = getUserCommand(startMenuOptions.length);

            if(userCommand == -1) {
                System.out.println("That was an invalid choice. Please try again.");
                continue;
            }

            switch(userCommand) {
                case(0):
                    if(!logOn()) continue;

                    loggedIn = true;
                    userPermission = application.getUser().getPermission();
                    break;
                case(1):
                    createAccount();
                    break;
            }
        }

        // The main application menu loops, accessible once logged in. Changes depending on user type
        boolean loggedOut = false;
        while(!loggedOut) {
            switch(userPermission) {
                //Options for administrators
                case(-1):
                    displayMenu(mainMenuOptionsAdmin);
                    userCommand = getUserCommand(mainMenuOptionsAdmin.length);
                    resolveAdminOptions(userCommand);
                    break;

                //Options for students
                case(0):
                    displayMenu(mainMenuOptionsStudent);
                    userCommand = getUserCommand(mainMenuOptionsStudent.length);
                    resolveStudentOptions(userCommand);
                    break;

                //Options for employers
                case(1):
                    displayMenu(mainMenuOptionsEmployer);
                    userCommand = getUserCommand(mainMenuOptionsEmployer.length);
                    resolveEmployerOptions(userCommand);
                    break;
                default:
                //May not be necessary, but could be a useful redundancy check for valid permissions
            }
        }
        //This is unreachable. Add a way to set loggedOut = true;                       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        System.out.println("You have been successfully logged out. Take care!");
    }

    /**
     * The displayMainMenu method is a private helper method that displays the
     * main menu of the program.
     * 
     * @param menu The menu to be displayed
     */
    private void displayMenu(String[] menu) {
        String output = "What would you like to do?\n";
        for(int i = 0; i < menu.length; i++) {
            output += "\t" + (i+1) + ".  " + menu[i] + "\n";
        }
        output += CHOICE_PROMPT;
        System.out.println(output);
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

    
    /**
     * Private helper method which displays appropriate prompts for every option available to students
     * and handles command selection for students.
     * @param commandOption The number of the chosen option
     */
    private void resolveStudentOptions(int commandOption) {
        if(commandOption == -1) {
            System.out.println("That was an invalid choice. Please try again.");
        }
        switch(commandOption) {
            //Edit personal info
            case(0):
                application.editPersonalInfo();
                break;
            //Search for internship
            case(1):
                searchInternship();
                break;
            //Review an Internship
            case(2):
                application.writeReview();
                break;
            //Create your resume
            case(3):
                application.createResume();
                break;
            //Edit your resume
            case(4):
                application.editResume();
                break;
        }
    }
    
    /**
     * Private helper method which displays appropriate prompts for searching through the internship database.
     */
    private void searchInternship() {
        try {
            //Searching results loop
            ArrayList<JobListing> results = new ArrayList<>();
            System.out.println("What criteria would you like to search by?");
            displayMenu(internshipSearchOptions);
            
            int userCommand = getUserCommand(internshipSearchOptions.length);
            System.out.print("Please enter the number next to your desired search method: ");

            switch(userCommand) {
                case(-1):
                    System.out.println("You chose an invalid choice. Try again.");
                //By internship title
                case(0):
                    System.out.print("Please enter the title of the internship: ");
                    results = application.search(scanner.nextLine());
                    formatSearchResults(results);
                    break;
                //By pay rate
                case(1):
                    System.out.print("Please enter the minimum pay rate you'd like (e.g. 7.30 for $7.30/hr): ");
                    double minPay = Double.parseDouble(scanner.nextLine());
                    application.search(minPay);
                    results = application.search(scanner.nextLine());
                    formatSearchResults(results);
                    break;
            }

                
            //If no results were found, end early
            if(results.isEmpty()) {
                return;
            }
            //Internship details loop 
            System.out.print("Please enter the number of the internship you would like to learn more about or 0 to go back: ");
            int resultChoice = Integer.parseInt(scanner.nextLine()) - 1;    //Account for zero index
            while(resultChoice != -1) {       
                if(resultChoice < -1 || resultChoice >= results.size() + 1) {
                    System.out.println("You chose an internship number that wasn't listed. Try again, or type 0 to go back.");
                }
                else {
                    //If its a valid choice, print out the full description of the JobListing
                    System.out.println(results.get(resultChoice).toString());
                }
                System.out.println("If you'd like to learn about another option, enter the number of the internship; 0 to go back: ");
                resultChoice = Integer.parseInt(scanner.nextLine()) - 1;
            }
        } catch (Exception e) {
            System.out.println("Your input was invalid.");
        }
    }
    
    /**
     * Private helper method used to print out cleanly formatted and descriptive search results.
     * @param results
     */
    private void formatSearchResults(ArrayList<JobListing> results) {
        int resultSize = results.size();
        if(resultSize == 0) {
            System.out.println("None of the internships have your criteria. Resetting...\n");
        }
        else {
            System.out.println(resultSize + " result(s) found!\n");
        }

        for(int i = 1; i <= resultSize; i++) {
            JobListing jobListing = results.get(i);
            System.out.println(i + ". " + jobListing.toStringSummary());
        }
    }

    /**
     * Private helper method which displays appropriate prompts for every option available to employers
     * and handles command selection for employers.
     * @param commandOption The number of the chosen option
     */
    private void resolveEmployerOptions(int commandOption) {
        if(commandOption == -1) {
            System.out.println("That was an invalid choice. Please try again.");
        }
        switch(commandOption) {
            //Edit personal info
            case(0):
                application.editPersonalInfo();
                break;
            //Create a Company Profile
            case(1):
            	// Have method itself print out the specific errors if the profile can't be created
                if(application.createCompanyProfile()) {
                	System.out.println("Profile created! Returning to home screen.");
                }
                break;
            //Create a Job Listing
            case(2):
                createListing();
                break;
            //Edit a Job Listing
            case(3):
                application.editJobListing();
                break;
            //Remove a Job Listing
            case(4):
                application.removeJobListing();
                break;
            //Review a Student
            case(5):
                //The writeReview method itself could probably prompt the user to find the student to review.
            	application.writeReview();
        }
    }
    
    /**
     * Private helper method which displays appropriate prompts and handles user input while creating a job listing.
     */
    private void createListing() {
    	try {
        	//Confirmation loop
        	System.out.print("Confirm new listing(Y/N): ");
    		while(true) {
        		String response = scanner.nextLine().trim().toLowerCase();
        		if(response.equals("y")) {
        			break;
        		}
        		else if(response.equals("n")) {
        			System.out.println("Returning to home screen.");
        			return;
        		}
        		else {
        			System.out.println("Your input was invalid. Please enter \"Y\" to confirm or \"N\" to exit.");
        		}
        	}
    		
    		//If confirmed, create the listing
    		application.createJobListing();
    	} catch(Exception e) {
    		System.out.println("An error occurred when trying to create your listing. Returning to home screen.");
    	}
    }
  

    /**
     * Private helper method which displays appropriate prompts for every option available to employers
     * and handles command selection for employers.
     * @param commandOption The number of the chosen option
     */
    private void resolveAdminOptions(int commandOption) {
        if(commandOption == -1) {
            System.out.println("That was an invalid choice. Please try again.");
        }
        switch(commandOption) {
            //Edit personal info
            case(0):
                application.editPersonalInfo();
                break;
            //Search for an Internship
            case(1):
                searchInternship();
                break;
            //Remove User Account
            case(2):
                application.removeAccount();
                break;
            //Remove Company Profile
            case(3):
                if(!application.removeProfile()) {
                    System.out.println("The Company profile does not exist.");
                };
                break;
            //Edit job listing visibility 
            case(4):
                //Application has no direct way to change the visibility of a listing. Is a new method required?
                application.toggleJobListingVisibility();
                break;
            //Edit review visibility
            case(5):
            	//There is no facade option for this. Maybe add it there or do another private method here?
            	application..
            	break;
            //Create new admin
            case(6):
            	//Needs a specific method in the facade or a lot of prompting here.
            	//Either way, it would need to be immediately written to the database like
            	//any other newly created account.
            	Administrator admin = new Administrator(null, null, null, null, null);
            	
        }
    }
    
    /**
     * Private helper method which displays appropriate prompts for logging in to the user.
     * @return True if the login was successful, false otherwise.
     */
    private boolean logOn() {
        String email = "";
        String password = "";
        Console console = System.console();

        try {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            System.out.print("Enter password: ");
            password = String.valueOf(console.readPassword());

            if(application.logOn(email, password)) {
                System.out.println("\nHello, " + application.getUser().getFirstName());
                return true;
            }
            else {
                System.out.println("\nThe email/password combination entered was invalid. Try again, or create an account.");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
  
    /**
     * Private helper method which displays appropriate prompts for creating a student or employer account.
     */
    private void createAccount() {
        System.out.print("What type of account would you like to create? ([S]tudent or [E]mployer): ");
        String accountType = "";
        try {
            switch(scanner.nextLine()) {
                case("S"): 
                    accountType = "student";
                    break;
                case("E"):
                    accountType = "employer";
                    break;
                default:
                    System.out.println("You entered an invalid account option. Try again.");
            }
        } catch (Exception e) {
            System.out.println("Your input was invalid. Account could not be created.");
        }

        //If account was successfully created
        if(application.createAccount(accountType)) {
            //Make sure that their account is added to the database so they can log in afterwards
            System.out.println("Thank you " + application.getUser().getFirstName() + "! Your " + accountType + 
                " account has been created.");
        }
        else {
            System.out.println("Sorry, your account could not be created.");
        }
    }
}