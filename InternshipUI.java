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
    private static final String START_MESSAGE = "Welcome!\n";
    private String[] startMenuOptions = {"Log In","Create Account", "Exit System"};
    private String[] mainMenuOptionsStudent = {"Edit personal information","Search for Internship/Apply","Review an Internship","Create your Resume","Edit Your Resume","View Resume","Print Resume to Text File","Log Out"};
    private String[] mainMenuOptionsEmployer = {"Edit personal information","Create a Company Profile", "Associate With an Existing Company","View Company Profile","Create a Job Listing","Edit a Job Listing","View Job Listing Applicants","Remove a Job Listing", "Review a Student", "Log Out"};
    private String[] mainMenuOptionsAdmin = {"Edit personal information","Search for an Internship","Remove User Account","Remove Company Profile","Edit Job Listing Visibility","Edit Review Visibility","Create New Admin Account", "Log Out"};
    private String[] internshipSearchOptions = {"Internship Title","Pay Rate","Required Skill"};
    private String[] internshipSortOptions = {"Alphabetically (Ascending)","Pay Rate (Ascending)"};
    private boolean loggedIn = false;
    private boolean onSystem = true;

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
        int userPermission = -1;
        int userCommand;
        System.out.println(START_MESSAGE);
        while(onSystem) {
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
                    case(2):
                        System.out.print("\nExiting the system. Take care!");
                        onSystem = false;
                        System.exit(0);
                }
            }

            // The main application menu loops, accessible once logged in. Changes depending on user type
            while(loggedIn) {
                try {
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
                    }
                } catch(Exception e) {
                    System.out.println("You entered invalid input. Try again.\n");
                }
            }
            System.out.println("You have been successfully logged out.\n");
        }
    }

    /**
     * The displayMainMenu method is a private helper method that displays the
     * main menu of the program.
     * 
     * @param menu The menu to be displayed
     */
    private void displayMenu(String[] menu) {
        String output = "~~~~~~~~~~~~Options~~~~~~~~~~~~\n";
        for(int i = 0; i < menu.length; i++) {
            if(i < 9) {
                output += "\t" + (i+1) + ".  " + menu[i] + "\n";
            }
            else {
                output += "\t" + (i+1) + ". " + menu[i] + "\n";
            }
        }
        //output += CHOICE_PROMPT;
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
                sortAndSearchInternships();
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
            //View your resume
            case(5):
                application.viewResume();
                break;
            //Print Resume to File
            case(6):
                application.printResumeToFile();
                break;
            //Log out
            case(7):
                application.logOff();
                loggedIn = false;
                break;
        }
    }
    
    /**
     * Private helper method which displays appropriate prompts for searching through the internship database.
     */
    private void sortAndSearchInternships() {
        try {
            System.out.println("How would you like your results sorted?");
            displayMenu(internshipSortOptions);

            System.out.print("Please enter the number next to your desired sorting: ");
            int userCommand = getUserCommand(internshipSearchOptions.length);

            switch(userCommand) {
                case(-1):
                    System.out.println("You chose an invalid choice. Try again.");
                    return;
                //Alphabetically (Ascending)
                case(0):
                    application.sortAlphabetically();
                    break;
                //pay rate
                    case(1):
                    application.sortPayRate();
                    break;
            }

            //Searching results loop
            ArrayList<JobListing> results = new ArrayList<>();
            System.out.println("\nWhat criteria would you like to search by?");
            displayMenu(internshipSearchOptions);
            
            System.out.print("Please enter the number next to your desired search method: ");
            userCommand = getUserCommand(internshipSearchOptions.length);

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
                    results = application.search(minPay);
                    formatSearchResults(results);
                    break;
                //By required skill
                case(2):
                    System.out.print("Please enter the name of the skill you'd like to search by: ");
                    String skillToSearch = scanner.nextLine();
                    results = application.searchBySkill(skillToSearch);
                    formatSearchResults(results);
                    break;
            }

                
            //If no results were found, end early
            if(results.isEmpty()) {
                return;
            }
            //Internship details loop 
            System.out.print("-------------------------------\nPlease enter the number of the internship you would like to learn more about and possibly apply for, or 0 to go back: ");
            int resultChoice = Integer.parseInt(scanner.nextLine()) - 1;    //Account for zero index
            while(resultChoice != -1) {       
                if(resultChoice < -1 || resultChoice >= results.size() + 1) {
                    System.out.println("You chose an internship number that wasn't listed. Try again, or type 0 to go back.");
                }
                else {
                    //If its a valid choice, print out the description of the JobListing
                    System.out.println(results.get(resultChoice).toStringforStudents());
                }
                System.out.println("If you'd like to learn about another option, enter the number of the internship; 0 to go back or A to apply: ");
                if(scanner.hasNextInt()){
                    resultChoice = Integer.parseInt(scanner.nextLine()) - 1;
                }else{
                    String otherChoice = scanner.nextLine();
                    if ((otherChoice.toLowerCase().contains("a") && this.application.getUser().getPermission() == 0)) {
                        Student student = (Student) this.application.getUser();
                        if(!application.applyForInternship(results.get(resultChoice))) {
                            return;
                        }
                        System.out.println("You successfully applied!\n");
                     }
                     resultChoice = -1;
                 }   
            }
        } catch (Exception e) {
            System.out.println("Your input was invalid.");
        }
    }
    
    /**
     * Private helper method used to print out cleanly formatted and descriptive search results.
     * @param results The ArrayList of results to format
     */
    private void formatSearchResults(ArrayList<JobListing> results) {
        if(results.size() == 0) {
            System.out.println("None of the internships have your criteria. Resetting...\n");
        }
        else {
            //search already checks hidden
            /* ArrayList<Integer> indicesOfHiddens = new ArrayList<Integer> ();
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).getVisibility() == false) {
                    indicesOfHiddens.add(0, i);
                }
            }
            for (Integer index: indicesOfHiddens) {
                results.remove(results.get(index));
            }*/
            System.out.println("-------------------------------\n" + results.size() + " result(s) found!\n");
        }

        for(int i = 0; i < results.size(); i++) {
            JobListing jobListing = results.get(i);
            System.out.println(i + 1 + ". " + jobListing.toStringSummary() + "\n");
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
                	System.out.println("\nProfile created! Be sure to associate with it once you return to the home screen.\n");
                }
                break;
            //Associate with a company profile
            case(2):
                application.associateCompany();
                break;
            case(3):
                application.viewCompanyProfile();
                break;
            //Create a Job Listing
            case(4):
                createListing();
                break;
            //Edit a Job Listing
            case(5):
                application.editJobListing();
                break;
            //View Job Listing Applicants
            case(6):
                application.viewJobListingApplicants();
                break;
            //Remove a Job Listing
            case(7):
                System.out.println("Please enter the title of the listing you would like to remove: ");
                String input2 = scanner.nextLine();
                application.removeJobListing(input2);
                break;
            //Review a Student
            case(8):
            	application.writeReview();
                break;
            //Log out
            case(9):
                application.logOff();
                loggedIn = false;
                break;
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
        			System.out.println("Returning to home screen.\n");
        			return;
        		}
        		else {
        			System.out.println("Your input was invalid. Please enter \"Y\" to confirm or \"N\" to exit.");
        		}
        	}
    		
    		//If confirmed, create the listing
    		application.createJobListing();
    	} catch(Exception e) {
    		System.out.println("An error occurred when trying to create your listing. Returning to home screen.\n");
    	}
    }
  

    /**
     * Private helper method which displays appropriate prompts for every option available to employers
     * and handles command selection for employers.
     * @param commandOption The number of the chosen option
     */
    private void resolveAdminOptions(int commandOption) {
        if(commandOption == -1) {
            System.out.println("That was an invalid choice. Please try again.\n");
        }
        switch(commandOption) {
            //Edit personal info
            case(0):
                application.editPersonalInfo();
                break;
            //Search for an Internship
            case(1):
                sortAndSearchInternships();
                break;
            //Remove User Account
            case(2):
                application.removeAccount();
                break;
            //Remove Company Profile
            case(3):
                application.removeProfile();
                break;
            //Edit job listing visibility 
            case(4):
                application.toggleJobListingVisibility();
                break;
            //Edit review visibility
            case(5):
            	application.toggleReviewVisibility();
            	break;
            //Create new admin
            case(6):
            	application.registerAdmin();
                break;
            //Log out
            case(7):
                application.logOff();
                loggedIn = false;
                break;
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
                System.out.println("\nHello, " + application.getUser().getFirstName() + "! Here's your main menu: ");
                return true;
            }
            else {
                System.out.println("\nThe email/password combination entered was invalid. Try again, or create an account.\n");
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
        while (!(accountType.equals("student") || accountType.equals("employer"))) {
            try {
                switch(scanner.nextLine().toUpperCase()) {
                    case("S"): 
                        accountType = "student";
                        break;
                    case("E"):
                        accountType = "employer";
                        break;
                    default:
                        System.out.println("You entered an invalid account option. Try again.\n");
                   }
                }
            catch (Exception e) {
                System.out.println("Your input was invalid. Account could not be created.\n");
            }
        }

        //If account was successfully created
        if(application.createAccount(accountType)) {
            //Make sure that their account is added to the database so they can log in afterwards
            System.out.println("Thank you " + application.getUser().getFirstName() + "! Your " + accountType + 
                " account has been created.\n");
        }
        else {
            System.out.println("Sorry, your account could not be created.\n");
        }
    }
}