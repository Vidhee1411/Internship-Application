import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;
import java.text.DecimalFormat;

/**
 * The InternshipUI class creates a user interface for any user that opens
 * the internship application. It contains a run method which runs the
 * application and a private displayMainMenu method which displays the main
 * menu of the program.
 * @author Joshua DuPuis
 */
public class InternshipUI {
    private InternshipApplication application;
    private Scanner scanner;
    private static final String START_MESSAGE = "Welcome!";
    private static final String CHOICE_PROMPT = "Please enter the number next to your preferred action: ";
    private static final DecimalFormat DOLLAR_FORMAT = new DecimalFormat("$#,##0.00");
    private String[] startMenuOptions = {"Log In","Create Account"};
    private String[] mainMenuOptionsStudent = {"Edit personal information","Search for Internship","Review an Internship","Create your Resume","Edit Your Resume"};
    private String[] mainMenuOptionsEmployer = {"Edit personal information","Create a Company Profile","Create a Job Listing","Edit a Job Listing","Remove a Job Listing", "Review a Student"};
    private String[] mainMenuOptionsAdmin = {"Edit personal information","Search for an Internship","Remove User Account","Remove Employer Account","Remove Company Profile","Edit Job Listing Visibility","Edit Review Visibility","Create New Admin Account"};
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
                //May not be necessary, but could be a useful redundancy check
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

        int userCommand = -1;
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
                //The writeReview method itself could probably prompt the user.
                System.out.println("Please enter the title of the internship you would like to review");
                application.writeReview(PLACEHOLDER);
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
                System.out.println("\nThe email/password combination entered was invalid. Try again");
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

    
    /**
     * Private helper method which displays appropriate prompts for searching through the internship database.
     */
    private void searchInternship() {
        System.out.println("What criteria would you like to search by?");
        int userCommand = getUserCommand(internshipSearchOptions.length);
        System.out.print("Please enter the number next to your desired search method: ");

        try {
            switch(userCommand) {
                //By internship title
                case(0):
                    System.out.print("Please enter the title of the internship: ");
                    ArrayList<JobListing> results = application.search(scanner.nextLine());

                    int resultSize = results.size();
                    if(resultSize == 0) {
                        System.out.println("None of the internships have your criteria.");
                    }
                    else {
                        System.out.println(resultSize + " result(s) found!\n");
                    }

                    for(int i = 1; i <= resultSize; i++) {
                        //Can maybe replace all this with a future JobListing.toString()...
                        JobListing job = results.get(i);
                        System.out.println(i + ". " + job.getTitle() + "\n" + 
                            "Company: " + job.getCompanyName() + "\n" + 
                            "Pay Rate: " + DOLLAR_FORMAT.format(job.getPayRate()) + "/hr\n");
                    }
                    System.out.print("Please enter the number of the internship you would like to learn more about or 0 to go back: ");
                    break;
                //By pay rate
                case(1):
                    application.SEARCHOTHER();
                break;
            }
        } catch (Exception e) {
            System.out.println("Your input was invalid.");
        }
    }

    public static void main(String[] args) {
        InternshipUI ui = new InternshipUI();
        ui.run();
    }
}