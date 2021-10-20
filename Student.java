/**
 * The Student class creates a student account in the internship system.
 * @author Vidhee Patel
 */
import java.util.ArrayList;

public class Student extends User{
    private Resume resume;
    private ArrayList<Review> reviewFromCompanies;

    public Student(String firstName, String lastName, String email, String password, int permission) {
        super(firstName, lastName, email, password);
    }

    public Student(String firstName, String lastName, String email, String password, int permission, ArrayList<Review> reviews, ArrayList<Resume> resumes) {
        super(firstName, lastName, email, password);
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
