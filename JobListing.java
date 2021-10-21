import java.util.ArrayList;
import java.util.UUID;
/**
 * The JobListing class defines a JobListing that an employer creates and a
 * student can apply to
 * @author Joshua DuPuis
 */
public class JobListing {
    private UUID id;
    private String title;
    private String description;
    private boolean paid;
    private double payRate;
    private ArrayList<String> requiredSkills;
    private String company;
    private boolean hidden;
    private UUID id;
    private ArrayList<Student> applicants;

    /**
     * The default JobListing constructor creates an empty JobListing
     */
    public JobListing() {

    }

    /**
     * The parameterized constructor creates a JobListing and allows the
     * employer to define all of its attributes.
     * @param title The title of the JobListing
     * @param description The description of the JobListing
     * @param paid True if the internship is paid, false if not
     * @param payRate The payRate of the internship
     */
    public JobListing(String title, String description, Boolean paid, double payRate) {

    }

     /**
     * The parameterized constructor creates a JobListing and allows the
     * employer to define all of its attributes.
     * @param companyName the name of the company 
     * @param title The title of the JobListing
     * @param description The description of the JobListing
     * @param paid True if the internship is paid, false if not
     * @param payRate The payRate of the internship
     * @param id UUID of the listing\
     */
    public JobListing(String companyName,String title, String description, Boolean paid, double payRate,UUID id, ArrayList<Student> applicants) {

    }

    /**
     * The editTitle method allows a user to edit the title of a JobListing
     * @param title The new title of the JobListing
     */
    public void editTitle(String title) {

    }

    /**
     * The editDescription method allows a user to edit the description of a
     * JobListing
     * @param description The new description for the JobListing
     */
    public void editDescription(String description) {

    }

    /**
     * The editPay method allows a user to edit the pay rate of a JobListing
     * @param pay The new pay rate for the JobListing
     */
    public void editPay(boolean pay) {

    }

    /**
     * The setSkills method allows an employer to define the skills required to
     * apply for an internship. 
     * @param skills The list of skills required for an internship
     */
    public void setSkills(ArrayList<String> skills) {

    }

    /**
     * The viewApplicants method allows an employer to view all of the students
     * who applied to their internship.
     * @return A list containing all of the students who applied for an
     * internship
     */
    public ArrayList<Student> viewApplicants() {
        return null;
    }

    /**
     * The setVisibility method allows an employer or an administrator to
     * change whether a JobListing is visible or not
     * @param hidden True if JobListing should be hidden, false otherwise
     */
    public void setVisibility(boolean hidden) {

    }
    /**
     * gets the UUID of the listing
     * @return the listings UUID
     */
    public UUID getUUID(){
        return this.id;
    }

    /**
     * The addRequiredSkill method adds a required skill to the list of skills
     * needed to apply to a specific JobListing
     * @param skill The skill an employer wishes to add to a JobListing
     */
    public void addRequiredSkill(String skill) {

    }

    /**
     * The apply method adds a student to the ArrayList of applicants for a
     * speciifc JobListing
     */
    public void apply() {

    }

    /**
     * The getID method returns the UUID of the JobListing
     */
    public void getID() {

    }

}
