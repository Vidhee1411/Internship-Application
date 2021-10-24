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
    private boolean visible;
    private ArrayList<Student> applicants;

    /**
     * The default JobListing constructor creates an empty JobListing
     */
    public JobListing() {
        requiredSkills = new ArrayList<String>();
        applicants = new ArrayList<Student>();
        visible = true;
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
        requiredSkills = new ArrayList<String>();
        applicants = new ArrayList<Student>();
        this.title = title;
        this.description = description;
        this.paid = paid;
        this.payRate = payRate;
        visible = true;
    }

    /**
     * The editTitle method allows a user to edit the title of a JobListing.
     * @param title The new title of the JobListing
     */
    public void editTitle(String title) {
        this.title = title;
    }

    /**
     * The getTitle method returns the title of the JobListing.
     * @return The title of the JobListing
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * The editDescription method allows a user to edit the description of a
     * JobListing.
     * @param description The new description for the JobListing
     */
    public void editDescription(String description) {
        this.description = description;
    }

    /**
     * The getDescription method returns the description of the JobListing.
     * @return The description of the job listing
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The editPay method allows a user to edit the pay rate of a JobListing.
     * @param pay The new pay rate for the JobListing
     */
    public void editPay(boolean pay) {
        this.paid = pay;
    }
    
    /**
     * The getPaid method tells the user whether an internship is paid or not.
     * @return True if the internship is paid, false otherwise
     */
    public boolean getPaid() {
        return this.paid;
    }

    /**
     * The editPayRate method allows a user to edit the pay rate associated
     * with the internship.
     * @param payRate The new pay rate of the internship
     */
    public void editPayRate(double payRate) {
        this.payRate = payRate;
    }

    /**
     * The getPayRate method returns the pay rate of the internship.
     * @return The pay rate of the internship
     */
    public double getPayRate(){
        return this.payRate;
    }

    /**
     * The setVisibility method allows an employer or an administrator to
     * change whether a JobListing is visible or not.
     * @param visible True if JobListing should be visible, false otherwise
     */
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    /**
     * The getVisibility method returns whether an internship is visible or not
     * on the system.
     * @return True if the internship should be visible, false otherwise
     */
    public boolean getVisibility() {
        return this.visible;
    }

    /**
     * The getUUID method returns the UUID of the listing.
     * @return The UUID of the listing
     */
    public UUID getUUID(){
        return this.id;
    }

    /**
     * The addRequiredSkill method adds a required skill to the list of skills
     * needed to apply to a specific JobListing.
     * @param skill The skill an employer wishes to add to a JobListing
     */
    public void addRequiredSkill(String skill) {
        requiredSkills.add(skill);
    }

    /**
     * The removeRequiredSkill method removes a skill from the list of requried
     * skills if the skill is contained in that list.
     * @param skill The skill to be removed from the list
     * @return True if the skill was removed, false if it wasn't in the list
     */
    public boolean removeRequiredSkill(String skill) {
        return requiredSkills.remove(skill);
    }

    /**
     * The getRequiredSkills method returns a list of the skills required for
     * an internship.
     * @return A list of the skills required for an internship
     */
    public ArrayList<String> getRequiredSkills() {
        return this.requiredSkills;
    }

    /**
     * The apply method adds a student to the ArrayList of applicants for a
     * speciifc JobListing
     * @param student The student applying for the internship
     */
    public void apply(Student student) {
        applicants.add(student);
    }

    /**
     * The getApplicants method allows an employer to view all of the students
     * who applied to their internship.
     * @return A list containing all of the students who applied for an
     * internship
     */
    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }

    /**
     * The getID method returns the UUID of the JobListing
     */
    public UUID getID() {
        return id;
    }

}
