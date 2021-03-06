import java.util.UUID;

/**
 * The User class creates a User account in the internship system.
 * @author Joshua DuPuis and Vidhee Patel
 */
public abstract class User {
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;

    /**
     * The User constructor initializes a User object and sets their first
     * name, last name, email, and password to their respective instance
     * variables.
     * @param firstName The first name of the user
     * @param lastName The last name of the user
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
     * The User constructor initializes a User object/account and sets their
     * first name, last name, email, and password to the correct instance
     * variables.
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email
     * @param password The user's password
     * @param id the user's UUID
     */
    public User(String firstName, String lastName, String email, String password, UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    /**
    * The editFirstName method allows a user to edit their first name in the
    * system
    * @param name The name the user would like to set as their first name
    */
    public void editFirstName(String name) {
        this.firstName = name;
    }
    
    /**
     * The editLastName method allows a user to edit their last name in the
     * system
     * @param name The name the user would like to set as their last name
     */
    public void editLastName(String name) {
        this.lastName = name;
    }

    /**
     * The editEmail method allows the user to edit their email in the system
     * @param email The email address the user would like to set as their email
     */
    public void editEmail(String email) {
        this.email = email;
    }

    /**
     * The editPassword method allows the user to edit their password in the
     * system
     * @param password The new password the user would like to use
     */
    public void editPassword(String password) {
        this.password =password;
    }

    /**
     * The getFirstName method returns the User's first name.
     * @return The first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * The getLastName method returns the User's last name.
     * @return The last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * The getEmail method returns the User's email.
     * @return The email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * The getpassword method returns the User's password.
     * @return The user's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * The logOn method allows a user to log into the system.
     * @param email The email associated with the user's account
     * @param password The password associated with the user's account
     */
    public boolean logOn(String email, String password) {
        if(email == getEmail() && password == getPassword()) {
            return true;
        }
        return false;
    }

    /**
     * The getUserUUID method returns the UUID of the user.
     * @return The user's UUID
     */
    public UUID getUserUUID() {
        return this.id;
    }

    /**
     * The setUserUUID method sets the UUID of the user.
     * @param uuid The UUID to assign to the user
     */
    public void setUserUUID(UUID id) {
        this.id = id;
    }


    /**
     * The abstract method getPermission returns a user's permission identifier
     * - a number that is unique to each type of user.
     * @return The permission value of the user
     */
    public abstract int getPermission();
}
