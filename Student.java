/**
 * The Student class creates a student account in the internship system.
 * @author Vidhee Patel and Joshua DuPuis
 */
import java.util.ArrayList;

public class Student extends User{
    private Resume resume;
    private ArrayList<Review> reviewsFromCompanies;

    public Student(String firstName, String lastName, String email, String password, int permission) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.permission = permission;
    }
    
    public void createResume() {
        
    }

    public void editResume() {

    }

    public Review reviewCompany(String firstName, String lastName, int rating, String comment, CompanyProfile company) {
        return null;
    }

    public String displayReview() {
        return "";
    }

    public void applyForInternship() {

    }
}
