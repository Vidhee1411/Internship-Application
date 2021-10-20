import java.util.UUID;

/**
 * The User class creates a User account in the internship system.
 * @author Joshua DuPuis
 */
public abstract class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int permission;

    /**
     * The User constructor initializes a User object/account and sets their
     * first name, last name, email, and password to the correct instance
     * variables 
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email
     * @param password The user's password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    /**
    * The editFirstName method allows a user to edit their first name in the
    * system
    * @param name The name the user would like to set as their first name
    */
    public void editFirstName(String name) {
        
    }
    
    /**
     * The editLastName method allows a user to edit their last name in the
     * system
     * @param name The name the user would like to set as their last name
     */
    public void editLastName(String name) {

    }

    /**
     * The editEmail method allows the user to edit their email in the system
     * @param email The email address the user would like to set as their email
     */
    public void editEmail(String email) {

    }

    /**
     * The editPassword method allows the user to edit their password in the
     * system
     * @param password The new password the user would like to use
     */
    public void editPassword(String password) {

    }

    /**
     * The logOn method allows a user to log into the system.
     * @param email The email associated with the user's account
     * @param password The password associated with the user's account
     */
    public void logOn(String email, String password) {

    }
}
