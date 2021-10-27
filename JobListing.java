import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;
/**
 * The JobListing class defines a JobListing that an employer creates and a
 * student can apply to
 * @author Joshua DuPuis
 */
public class JobListing {
    private static final DecimalFormat DOLLAR_FORMAT = new DecimalFormat("$#,##0.00");

    private UUID id;
    private String title;
    private String description;
    private String location;
    private boolean paid;
    private double payRate;
    private String company;
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
    public JobListing(String title, String description, String location, Boolean paid, double payRate, String company) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.paid = paid;
        this.payRate = payRate;
        this.company = company;
        requiredSkills = new ArrayList<String>();
        applicants = new ArrayList<Student>();
        visible = true;
    }

     /**
     * The parameterized constructor creates a fully formed joblisting. Used in loading from json
     * @param title The title of the JobListing
     * @param description The description of the JobListing
     * @param paid True if the internship is paid, false if not
     * @param payRate The payRate of the internship
     * @param id UUID of the listing\
     * @param applicants the listings applicants
     * @param requiredSKills required skills for the listing 
     * @param location the location of the job
     * @param visable a boolean indicating if the listing is visable or hidden
     */
    public JobListing(String title, String description, String location, Boolean paid, double payRate, String company, UUID id, ArrayList<Student> applicants, ArrayList<String> requiredSkills, boolean visible) {
        this.title = title;
        this.description = description;
        this.paid = paid;
        this.payRate = payRate;
        this.company = company;
        this.id = id;
        this.applicants = applicants;
        this.requiredSkills = requiredSkills;
        this.visible = visible;
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
     * @param description The new description for the listing.
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
     * The editLocation method allows a user to edit where a job will be -
     * whether it will be in person or remote.
     * @param location The location of the job
     */
    public void editLocation(String location) {
        this.location = location;
    }

    /**
     * The getLocation method allows a user to get the location of a JobListing
     * @return The location of a listing
     */
    public String getLocation() {
        return location;
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
     * The editPayRate method allows a user to edit the pay rate of a
     * JobListing.
     * @param payRate The new pay rate for the listing
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

    public void editCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
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
     * Returns a complete, neatly formatted String representation of the JobListing.
     * @return The String summary of the JobListing
     */
    public String toString() {
        return title + "\nCompany: " + company + "\nDescription: " + description + "\nLocation: " + location +  "\nPaid: " + paid + "\nPay rate per hour: " + DOLLAR_FORMAT.format(payRate);
    }

    /**
     * Returns a brief, neatly formatted summary of the JobListing.
     * @return The String summary of the JobListing
     */
    public String toStringSummary() {
        return title + "\n" + 
                "Company: " + company + "\n" + 
                "Pay Rate: " + DOLLAR_FORMAT.format(payRate) + "/hr\n";
    }
}
