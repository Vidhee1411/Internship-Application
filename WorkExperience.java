/**
 * The WorkExperience class allows the user to define a previous work
 * experience that they can add to their resume.
 * @author  10/18/2021 Joshua DuPuis
 * @author 10/17/2021 Vidhee Patel
 */
public class WorkExperience {
    private String jobTitle;  
    private String company;
    private String dateRange;
    private String description;

    /**
     * The default WorkExperience constructor creates an empty WorkExperience.
     */
    public WorkExperience(){
        jobTitle = "";
        company = "";
        dateRange = "";
        description = "";
    }
    /**
     * The parameterized constructor creates a work experience and allows 
     * the student to customize its attributes before adding it to the resume
     * @param jobTitle The job title of the previous experience
     * @param company The name of the company the experience was with
     * @param dateRange The time period of how long the student worked - in
     * MM/DD/YYYY format
     * @param description The description of their previous experience
     */
    public WorkExperience(String jobTitle, String company, String dateRange, String description) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.dateRange = dateRange;
        this.description = description;
    }

    /**
     * The editJobTitle method allows a user to edit the title of a job.
     * @param newTitle The new job title
     */
    public void editJobTitle(String newTitle) {
        this.jobTitle = newTitle;
    }

    /**
     * The editCompany method allows a user to edit the name of the company
     * they worked for.
     * @param company The new company name.
     */
    public void editCompany(String company) {
        this.company = company;
    }

    /**
     * The editDateRange method allows a user to edit the times in which the
     * experience took place.
     * @param dateRange The new timespan of the WorkExperience
     */
    public void editDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    /**
     * The editDescription method allows a user to edit the description of their
     * previous experience.
     * @param description The new description of their experience
     */
    public void editDescription(String description) {
        this.description = description;
    }

    /**
     * The getJobTitle method returns the title of the WorkExperience.
     * @return A String containing the title of the WorkExperience
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * The getCompany method returns the name of the company the student worked
     * for during the experience.
     * @return A String containing the name of the company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * The getDateRange method returns the date range of the WorkExperience.
     * @return A String containing the date range of the WorkExperience
     */
    public String getDateRange() {
        return this.dateRange;
    }

    /**
     * The getDescription method returns the description of the WorkExperience.
     * @return A string containing the description of the WorkExperience
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The toString method returns a String containing all of the information
     * in the work experience.
     * @return A String description of the work experience
     */
    public String toString() {
        return "Company: " +company + "\n   " + "Employment date: " + dateRange + "\n   " + 
            "Job Title: " + jobTitle + "\n   " + "Description: " + description;
    }

}
