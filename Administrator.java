/**
 * The Administrator  class creates a administrator account in the internship system.
 * @author 10/17/2021 Vidhee Patel
 */
public class Administrator extends User{
    
    public Administrator(String firstName, String lastName, String email, String password, int permission) {
        super(firstName, lastName, email, password);
    }

    /**
     * The hideReview method hides the given review.
     * @param review The review to be hidden.
     */
    public void hideReview(Review review) {
        review.toggleVisibility();
    }

    /**
     * The removeListing method removes the given listing.
     * @param company The company's listing to be removed.
     * @return true if the company is removed.
     */
    public boolean removeListing(CompanyProfile company) {
        company.removeListing();
        return true;
    }
    
    /**
     * The removeAccount method removes the given user from the system.
     * @param user the user to be removed
     */
    public void removeAccount(User user) {
       for(User users: UserDatabase.getUsers()) {
           if( users.getuserUuid.equals(this.user.getuserUuid)) {
                users.remove(this.user.getuserUuid);
           }
       }
    }

    /**
     * The registerAccount method registers the new administrator account in the system.
     * @param firstName The firstName of the Administrator.
     * @param lastName The lastName of the Administrator.
     * @param email The email of the Administrator.
     * @param password Password of the account.
     */
    public void registerAccount(String firstName, String lastName, String email, String password) {
        Administrator(firstName,lastName,email,password);
    }

}
