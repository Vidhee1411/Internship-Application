import java.util.UUID;

/**
 * The Administrator  class creates a administrator account in the internship system.
 * @author 10/17/2021 Vidhee Patel
 */
public class Administrator extends User{
    private static final int PERMISSION = -1;
    
    public Administrator(String firstName, String lastName, String email, String password,UUID id) {
        super(firstName, lastName, email, password,id);
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
    public boolean removeListing(JobListing listing) {
        SearchableDatabase temp = SearchableDatabase.getInstance();
        temp.removeJobListing(listing);
        return true;
    }
    
    /**
     * The removeAccount method removes the given user from the system.
     * @param user the user to be removed
     */
    public void removeAccount(User user) {
     SearchableDatabase temp = SearchableDatabase.getInstance();
     temp.removeUser(user);
    }

    /**
     * The registerAccount method registers the new administrator account in the system.
     * @param firstName The firstName of the Administrator.
     * @param lastName The lastName of the Administrator.
     * @param email The email of the Administrator.
     * @param password Password of the account.
     */
    public void registerAccount(String firstName, String lastName, String email, String password) {
        SearchableDatabase temp = SearchableDatabase.getInstance();
        temp.addUser(new Administrator(firstName, lastName, email, password, id));
    }

    public UUID getUUID() {
        return super.getUserUUID();
     }

    public int getPermission() {
        return PERMISSION;
    }
}
