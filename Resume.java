/**
 * The resume class creates a resume of the student in the internship system.
 * @author Vidhee Patel and Joshua DuPuis
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
public class Resume {
    private String studentFirstName;
    private String studentLastName;
    private String studentUSCEmail;
    private String yearInSchool; 
    private ArrayList<String> skills;
    private ArrayList<String> classes;
    private ArrayList<Education> education;
    private ArrayList<WorkExperience> workExperiences;  

    /**
     * The default resume constructor creates a resume and sets all of the
     * instance variables equal to null
     */
    public Resume() {
        studentFirstName = "";
        studentLastName = "";
        studentUSCEmail = "";
        yearInSchool = "";
    }

    /**
     * The parameterized resume constructor creates a resume object and sets
     * the instance variables corresponding to the students first name, last
     * name, email, and year in school equal to the values the user enters.
     * @param firstName The first name of the student
     * @param lastName The last name of the student
     * @param email The school email of the student
     * @param yearInSchool The student's year in school
     */
    public Resume(String firstName, String lastName, String email, String yearInSchool) {
        this.studentFirstName = firstName;
        this.studentLastName = lastName;
        this.studentUSCEmail = email;
        this.yearInSchool = yearInSchool;
        skills = new ArrayList<String>();
        classes = new ArrayList<String>();
        education = new ArrayList<Education>();
        workExperiences = new ArrayList<WorkExperience>();
    }

    /**
     * The second Resume parameterized constructor creates a resume object that
     * the dataLoader will use to load all student resumes.
     * @param firstName The firstName of a student
     * @param lastName The lastName of a student
     * @param email The email of a student
     * @param yearInSchool The student's year in school
     * @param skills Skills the student has
     * @param classes Classes the student has taken
     * @param education Education institutions the student has attended
     * @param workExperiences Previous work experiences the student has
     */
    public Resume(String firstName, String lastName, String email, String yearInSchool,  ArrayList<String> skills, ArrayList<String> classes, ArrayList<Education> education, ArrayList<WorkExperience> workExperiences) {
        this.studentFirstName = firstName;
        this.studentLastName = lastName;
        this.studentUSCEmail = email;
        this.skills = skills;
        this.classes = classes;
        this.education = education;
        this.workExperiences = workExperiences;
        this.yearInSchool = yearInSchool;
    }

    /**
     * The editFirstName method allows a student to edit their first name on
     * their resume.
     * @param firstName The new first name of the student
     */
    public void editFirstName(String firstName) {
        this.studentFirstName = firstName;
    }

    /**
     * The editLastName method allows a student to edit their last name on
     * their resume.
     * @param lastName The new last name of the student
     */
    public void editLastName(String lastName) {
        this.studentLastName = lastName;
    }

    /**
     * Teh editEmail method allows a student to edit their email on their
     * resume.
     * @param email The student's new email
     */
    public void editEmail(String email) {
        this.studentUSCEmail = email;
    }

    /**
     * The editYearInSchool method allows a student to edit their year in
     * school.
     * @param year The student's new year in school
     */
    public void editYearInSchool (String year) {
        this.yearInSchool = year;
    }

    /**
     * The addSkill method allows a student to add a skill to their resume
     * @param skill
     */
    public void addSkill(String skill) {
        skills.add(skill);
    }

    /**
     * The addClass method allows a student to add a class that they have
     * taken to their resume.
     * @param className The name of the class to add
     */
    public void addClass(String className) {
        classes.add(className);
    }

    /**
     * The addCExperience method allows a student to add a previous
     * WorkExperience to their resume.
     * @param experience The student's previous work experience
     */
    public void addExperience(WorkExperience experience) {
        workExperiences.add(experience);
    }

    /**
     * The addEducation method adds an Education to a student's resume.
     * @param education The education to be added to the resume
     */
    public void addEducation(Education education) {
        this.education.add(education);
    }

    /**
     * The removeSkill method removes a skill from a student's resume if the
     * skill is on their resume.
     * @param skill The skill to be removed from the resume
     */
    public void removeSkill(String skill) {
        for (String iterate: this.skills) {
            if (iterate.equals(skill)){
                this.skills.remove(iterate);
            }
        }
    }

    /**
     * The removeClass method removes a class from a student's resume if it
     * is on their resume.
     * @param className The name of the class to be removed
     */
    public void removeClass(String className) {
        for (String iterate: this.classes) {
            if (iterate.equals(className)) {
                this.classes.remove(iterate);
            }
        }
    }

    /**
     * The removeExperience method removes an experience from a student's 
     * resume if it is on their resume.
     * @param experience The experience to be removed from the resume
     */
    public void removeExperience(WorkExperience experience) {
        for (WorkExperience iterate: this.workExperiences) {
            if (iterate.equals(experience)) {
                this.workExperiences.remove(iterate);
            }
        }
    }

    /**
     * The removeEducation method removes an education from a student's resume
     * if it is on their resume.
     * @param education The Education to be removed from the resume
     */
    public void removeEducation(Education education) {
        for (Education iterate: this.education) {
            if (iterate.equals(education)) {
                this.education.remove(iterate);
            }
        }
    }

    /**
     * The getFristName method returns the first name of the student.
     * @return The first name of the student
     */
    public String getFirstName() {
        return this.studentFirstName;
    }

    /**
     * The getLastName method returns the last name of the student.
     * @return The last name of the student
     */
    public String getLastName() {
        return this.studentLastName;
    }

    /**
     * The getEmail method returns the student's school email.
     * @return The student's school email
     */
    public String getEmail() {
        return this.studentUSCEmail;
    }

    /**
     * The getYearInSchool method returns the student's year in school.
     * @return The student's year in school
     */
    public String getYearInSchool() {
        return this.yearInSchool;
    }

    /**
     * The getSkills method returns the skills on the student's resume.
     * @return A list of the skills on the student's resume
     */
    public ArrayList<String> getSkills() {
        return this.skills;
    }

    /**
     * The getClasses method returns the classes on the student's resume.
     * @return A list of the classes on the student's resume
     */
    public ArrayList<String> getClasses() {
        return this.classes;
    }

    /**
     * The getEducation method returns the education experiences on the
     * student's resume.
     * @return A list of the education experiences on the student's resume
     */
    public ArrayList<Education> getEducation() {
        return education;
    }

    /**
     * The getExperiences method returns the previous work experiences on the
     * student's resume.
     * @return The previous work experiences on the student's resume
     */
    public ArrayList<WorkExperience> getExperiences() {
        return workExperiences;
    }
    /**
     * writes the resume to a txt file
     */
    public void toFile(){
        try {
            File myObj = new File("resume.txt");
            myObj.createNewFile();
            FileWriter writer = new FileWriter("resume.txt");
            writer.write(this.studentFirstName + " " + this.studentLastName + "\n");
            writer.write("Year in school: " + this.yearInSchool + "\n");
            writer.write("Contact: " + this.studentUSCEmail + "\n");
            writer.write("\nSkills: \n");
            for(String skill: skills){
            writer.write(skill + "\n");
            }
            writer.write("\nClasses: \n");
            for(String class_: this.classes){
                writer.write(class_ + "\n");
            }
            writer.write("\nEducation: \n");
            for(Education education: this.education){
                writer.write(education.toString() + "\n");
            }
            writer.write("\nWork Experiences: \n");
            for(WorkExperience work: this.workExperiences){
                writer.write(work.toString() + "\n");
            }
            writer.close();
            
           

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

     /**
     * The toString method returns a string containing all of the information
     * in the resume.
     * @return The String representation of the resume
     */
    public String toString() {
        String ret = studentFirstName + " " + studentLastName + "\n" + studentUSCEmail + "\n";
        for (Education ed: education) {
            ret += ed.toString() + "\n";
        }

        ret += "\nClasses: \n";

        for(String class1: classes) {
            ret += class1 + "\n";
        }

        ret += "\nExperiences: \n";

        for (WorkExperience we: workExperiences) {
            ret += we.toString() + "\n";
        }

        ret += "\nSkills: \n";

        for (String skill: skills) {
            ret += skill + "\n";
        }

        return ret;
    }
    
}