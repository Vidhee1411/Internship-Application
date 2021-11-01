import java.util.UUID;
/**
 * The Employer class creates a employer account in the internship system.
 * @author Vidhee Patel and Joshua DuPuis
 */
public class Employer extends User {
     private CompanyProfile associatedCompany;
     private static final int PERMISSION = 1;
    
    /**
     * The Employer constructor for loading data creates an employer object 
     * and sets each of the parameters equal to the User class' same name 
     * instance variables.
     * @param firstName The first name of the employer
     * @param lastName The last name of the employer
     * @param email The employer's email address
     * @param password The employer's password
     */
     public Employer(String firstName, String lastName, String email, String password) {
         super(firstName, lastName, email, password);
        
     }

     /**
      * This Employer constructor functions the same way as the one above,
      * and also allows for specification of a company the employer associates
      * with and a UUID identifier.
      * @param firstName The first name of the employer
      * @param lastName The last name of the employer
      * @param email The email of the employer
      * @param password The employer's password
      * @param associatedCompany The company the employer associates with
      * @param id The UUID of the employer
      */
     public Employer(String firstName, String lastName, String email, String password, CompanyProfile associatedCompany,UUID id) {
        super(firstName, lastName, email, password, id);
        this.associatedCompany = associatedCompany;

    }
    
     /**
     * The associateCompany method allows an employer to associate with a
     * company and their profile
     * @param company The company the employer associates with
     */
    public void associateCompany(CompanyProfile company) {
        this.associatedCompany = company;
    }

    /**
     * The reviewStudent method allows an employer to create a review about a
     * student and post it on the student's page.
     * @param rating The rating out of 10 they give the student
     * @param comment The description of the review
     * @param student The student the employer is reviewing
     */
    public void reviewStudent(int rating, String comment, Student student) {
        Review review = new Review (this.firstName, this.lastName, rating, comment);
        student.addReview(review);
    }

    /**
     * The getUUID method returns the UUID of the employer.
     * @return The UUID of the employer
     */
    public UUID getUUID() {
        return super.getUserUUID();
    }

    /**
     * The getCompany method returns the CompanyProfile that the employer
     * associates with.
     * @return The CompanyProfile associated with the employer
     */
    public CompanyProfile getCompany() {
        return this.associatedCompany;
    }

    /**
     * The getPermission method returns the permission value for an employer
     * @return An int containing the value 1 - an employer user's permission
     * value
     */
    public int getPermission() {
        return PERMISSION;
    }
}
