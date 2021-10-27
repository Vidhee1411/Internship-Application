import java.util.UUID;
import java.util.ArrayList;

/**
 * The Student class creates a Student account in the internship system and
 * allows them to create a resume to apply for a job.
 * @author Vidhee Patel and Joshua DuPuis
 */
public class Student extends User{
    private String yearInSchool;
    private Resume resume;
    private ArrayList<Review> reviewsFromCompanies;
    private static final int PERMISSION = 0;
    private ArrayList<String> skills;
    private ArrayList<String> classes;

    /**
     * The Student constructor creates a student object with their first name
     * @param firstName The first name of the student
     * @param lastName The last name of the student
     * @param email The student's school email
     * @param password The password for the student's account
     * @param yearInSchool The student's year in school
     */
    public Student(String firstName, String lastName, String email, String password, String yearInSchool) {
        super(firstName, lastName, email, password);
        this.yearInSchool = yearInSchool;
        reviewsFromCompanies = new ArrayList<Review>();
    }

    public Student(String firstName, String lastName, String email, String password, String yearInSchool, UUID id, ArrayList<String> skills, ArrayList<String> classes, ArrayList<Review> reviews) {
        super(firstName, lastName, email, password, id);
        this.yearInSchool = yearInSchool;
        this.skills = skills;
        this.classes = classes;
        this.reviewsFromCompanies = reviews;
    }
    
    /**
     * The createResume method allows the student to create a resume. It adds
     * the student's first name, last name, and email to the resume.
     */
    public void createResume() {
        resume = new Resume(super.getFirstName(), super.getLastName(), super.getEmail(), this.yearInSchool);
        /*if (skills != null) {
            for (String skill: skills) {
                resume.addSkill(skill);
            }
        }

        if (classes != null) {
            for (String course: classes) {
                resume.addClass(course);
            }
        }*/
    }
    /**
     * The setResume method sets a student's resume to the resume passed in by
     * the user.
     * @param resume The resume being added to the student.
     */
    public void setResume(Resume resume){
        this.resume = resume;
    }

    /**
     * The getResume method returns the student's resume.
     * @return The student's resume
     */
    public Resume getResume() {
        return this.resume;
    }

    /**
     * The setYearInSchool method changes the student's year in school
     * @param yearInSchool The student's new year in school
     */
    public void setYearInSchool(String yearInSchool) {
        this.yearInSchool = yearInSchool;
    }

    /**
     * The getYearInSchool method returns the student's year in school.
     * @return The student's year in school
     */
    public String getYearInSchool() {
        return this.yearInSchool;
    }

    /**
     * The reviewCompany method allows students to review a company they have
     * previously worked for and adds the newly created review to the company's
     * page.
     * @param rating The rating out of five that the student gives the company
     * @param comment The comment to accompany the review
     * @param company The company that the student is reviewing
     */
    public void reviewCompany(int rating, String comment, CompanyProfile company) {
        Review studentReview = new Review(super.getFirstName(), super.getLastName(), rating, comment);
        company.addReview(studentReview);
    }


    /**
     * The getReviews method returns an ArrayList containing all of the reviews
     * that employers have written about the student.
     * @return The list of reviews employers have written about the student
     */
    public ArrayList<Review> getReviews() {
        return reviewsFromCompanies;
    }

    /**
     * The applyForInternship method allows a student to apply for any
     * internship in the system.
     * @param listing The internship a student wants to apply for
     */
    public void applyForInternship(JobListing listing) {
        listing.apply(this);
    }

    /**
     * The add review method accepts a review from an employer and adds it to
     * the list of reviews about the student.
     * @param review The review written by an employer to be added to the
     * student's ArrayList of reviews
     */
    public void addReview(Review review) {
        reviewsFromCompanies.add(review);
    }
    
    /**
     * The setReviews method accepts an Arraylist<Review> and sets reviewsFromCompanies to it.
     * used in loading data from JSON
     * @param reviews an ArrayList<Review> of reviews 
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviewsFromCompanies = reviews;
    }

    /**
     * The getSkills method returns all of the skills the student has entered on their account.
     * @return An ArrayList<String> of skills
     */
    public ArrayList<String> getSkills() {
        return this.skills;
    }

    /**
     * The setSkills method accepts an ArrayList<String> and sets the user's
     * list of skills equal to it.
     * @param skills An ArrayList<String> containing the student's skills
     */
    public void setSkills(ArrayList<String> skills){
        this.skills = skills;
    }

    /**
     * The getSkills method returns all of the classes the student has entered on their account.
     * @return An ArrayList<String> of classes
     */
    public ArrayList<String> getClasses() {
        return this.classes;
    }

    /**
     * The setClasses method accpets an ArrayList<String> and sets the
     * student's list of classes equal to it
     * @param classes An ArrayList<String> containing the students classes
     */
    public void setClasses(ArrayList<String> classes){
        this.classes = classes;
    }

    /**
     * The getUUID method gets the Student's UUID
     * @return The UUID of the student
     */
    public UUID getUUID() {
        return super.getUserUUID();
    }
    
    /**
     * gets the index of a skill
     * @param skill the skill being searched for 
     * @return returns the index of the skill if found or -1 if not found
     */
    public int getSkillIndex(String skill){
        return skills.indexOf(skill);
    }
    
    /**
     * gets the index of a class
     * @param course the class being searched for. 
     * @return returns the index of the class if found or -1 if not found
     */
    public int getClassIndex(String course){
        return classes.indexOf(course);
    }

    /**
     * The getPermission method returns the permission value for a student
     * @return An int containing the value 0 - a student's user's permission
     * value
     */
    public int getPermission() {
        return PERMISSION;
    }
}
