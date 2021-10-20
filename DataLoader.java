import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// unfinished 
/**
 * The DataLoader class is responsible for loading all of the data from the
 * JSON files.
 * @author Joshua DuPuis
 */
public class DataLoader extends DataConstants {
    private static final HashMap<String,JobListing> JobListings = new HashMap<>();
    private static final HashMap<String,User> users = new HashMap<>();
    
    
    /**
     * The getJobListings method loads all of the JobListings from the data
     * files into an arraylist of JobListings.
     * @return The ArrayList of JobListings loaded from the JSON files
     */
    public static ArrayList<JobListing> getJobListings(){
        ArrayList<JobListing> output = new ArrayList<>();
        try {
            FileReader reader = new FileReader(JOB_LISTING_FILE_NAME);
            JSONArray listings =  (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < listings.size()-1; i++){
                JSONObject listing = (JSONObject)listings.get(i);
                String title = (String)listing.get(LISTING_TITLE);
                String description = (String)listing.get(LISTING_DESCRIPTION);
                String companyName = (String)listing.get(LISTING_COMPANY_NAME);
                Boolean paid = (Boolean)listing.get(LISTING_PAID);
                Double payRate = (Double)listing.get(LISTING_PAY_RATE);
                UUID id = (UUID)listing.get(LISTING_ID);
                JobListing temp = new JobListing(companyName, title, description, paid, payRate, id, applicants)
                JobListings.put(temp.getUUID().toString(), temp);
                output.add(temp);
            }
         } catch (Exception e) {
             e.printStackTrace();
            }
            return output;
        }


    /**
     * The getUsers method loads all of the Users from the data files into
     * an ArrayList of JobListings
     * @return The ArrayList of Users loaded from the JSON files
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(getAdmins());
        return null;
    }
    
    private static ArrayList<User> getAdmins(){
        ArrayList<User> output = new ArrayList<User>();
     try {
        FileReader reader = new FileReader(ADMIN_FILE_NAME);
        JSONArray Admins =  (JSONArray)new JSONParser().parse(reader);
        for(int i = 0; i < Admins.size()-1; i++){
            JSONObject admin = (JSONObject)Admins.get(i);
            String firstName = (String)admin.get(ADMIN_FIRST_NAME);
            String lastName = (String)admin.get(ADMIN_LAST_NAME);
            String email = (String)admin.get(ADMIN_EMAIL);
            String password = (String)admin.get(ADMIN_PASSWORD);
            int permission =((Long)admin.get(ADMIN_PERMISSION)).intValue();
            output.add(new Administrator(firstName, lastName, email, password, permission));
        }
     } catch (Exception e) {
         e.printStackTrace();
        }
        return output;
    }

    private ArrayList<Student> getStudents(){
        ArrayList<Student> output = new ArrayList<Student>();
        try {
           FileReader reader = new FileReader(STUDENT_FILE_NAME);
           JSONArray students =  (JSONArray)new JSONParser().parse(reader);
           for(int i = 0; i < students.size()-1; i++){
               JSONObject student = (JSONObject)students.get(i);
               String firstName = (String)student.get(STUDENT_FIRST_NAME);
               String lastName = (String)student.get(STUDENT_LAST_NAME);
               String email = (String)student.get(STUDENT_EMAIL);
               String password = (String)student.get(STUDENT_PASSWORD);
               int permission =((Long)student.get(STUDENT_PERMISSION)).intValue();
               ArrayList<Review> reviews = getReviews(student);
               ArrayList<Resume> resumes = getResumes(student);
               output.add(new Student(firstName, lastName, email, password, permission, reviews, resumes));
           }
        } catch (Exception e) {
            e.printStackTrace();
           }
           return output;
    }

    private ArrayList<CompanyProfile> getCompanyProfiles(){
        try {
            FileReader reader = new FileReader(JOB_LISTING_FILE_NAME);
            JSONArray CompanyProfiles =  (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < CompanyProfiles.size()-1; i++){
                JSONObject companyprofile = (JSONObject)CompanyProfiles.get(i);
                String companyName = (String)companyprofile.get(COMPANY_NAME);
                String hqAddress = (String)companyprofile.get(COMPANY_HQ_ADDRESS);
                String description = (String)companyprofile.get(COMPANY_DESCRIPTION);
                UUID companyID = (UUID)companyprofile.get(COMPANY_ID);
                ArrayList<Review> reviews = getReviews(companyprofile);
                CompanyProfile temp = new CompanyProfile(companyName, hqAddress, description, companyID, reviews, listings)
                //todo add listings 
             
                
            }
         } catch (Exception e) {
             e.printStackTrace();
            }
            return output;
    }

  
    private ArrayList<Resume> getResumes(JSONObject student){
        ArrayList<Resume> output = new ArrayList<>();
        JSONArray resumes = (JSONArray)student.get(STUDENT_RESUMES);
        for(int i = 0; i < resumes.size()-1; i++){
            JSONObject resume = (JSONObject)resumes.get(i);
            String email = (String)student.get(STUDENT_EMAIL);
            String yearInSchool = (String)resume.get(RESUME_SCHOOL_YEAR);
            ArrayList<String> skills = getSkills(student);
            ArrayList<Education> education = getEducation(student);
            ArrayList<WorkExperience> workExperiences = getWorkExperiences(student);
            output.add(new Resume(email, yearInSchool, skills, education, workExperiences));
        }
        return output;
    }
    
    private ArrayList<String> getSkills(JSONObject student){
        ArrayList<String> output = new ArrayList<>();
        JSONArray skillindex = (JSONArray)student.get(RESUME_SKILL_INDEXES);
        JSONArray skills = (JSONArray)student.get(STUDENT_SKILLS);
        for(int i = 0; i < skills.size()-1; i++){
            int index = ((Double)skillindex.get(i)).intValue();
            output.add((String)skills.get(index));
        }
        return output;
    }

    private ArrayList<Education> getEducation(JSONObject student){
        ArrayList<Education> output = new ArrayList<>();
        JSONArray educationExperiences = (JSONArray)student.get(RESUME_EDUCATION);
        for(int i = 0; i < educationExperiences.size()-1; i++){
            JSONObject educationExperience = (JSONObject)educationExperiences.get(i);
            String nameofUniversity = (String)educationExperience.get(EDUCATION_UNIVERSITY_NAME);
            String major = (String)educationExperience.get(EDUCATION_MAJOR);
            Double gpa = (Double)educationExperience.get(EDUCATION_GPA);
            String expectedGradDate = (String)educationExperience.get(EDUCATION_GRADUATION_DATE);
            Education temp = new Education(nameofUniversity, major, gpa, expectedGradDate);
            output.add(temp);
        }
        return output;
    }

    private ArrayList<WorkExperience> getWorkExperiences(JSONObject student){
        ArrayList<WorkExperience> output = new ArrayList<>();
        JSONArray workExperiences = (JSONArray)student.get(RESUME_WORK_EXPERIENCE);
        for(int i = 0; i < workExperiences.size()-1; i++){
            JSONObject workExperience = (JSONObject)workExperiences.get(i);
            String jobTitle = (String)workExperience.get(EXPERIENCE_TITLE);
            String company = (String)workExperience.get(EXPERIENCE_COMPANY);
            String dateRange = (String)workExperience.get(EXPERIENCE_DATE_RANGE);
            String description = (String)workExperience.get(EXPERIENCE_DESCRIPTION);
            WorkExperience temp = new WorkExperience(jobTitle, company, dateRange, description);
            output.add(temp);
        }
        return output;
    }

    
    private ArrayList<Review> getReviews(JSONObject reviewed){
        ArrayList<Review> output = new ArrayList<>();
        JSONArray reviews = (JSONArray)reviewed.get("revi");
        for(int i = 0; i < reviews.size()-1; i++){
            JSONObject review = (JSONObject)reviews.get(i);
            String firstName = (String)review.get(REVIEW_FIRST_NAME);
            String lastName = (String)review.get(REVIEW_LAST_NAME);
            String message = (String)review.get(REVIEW_MESSAGE);
            int rating = ((Long)review.get(REVIEW_RATING)).intValue();
            output.add(new Review(firstName, lastName, rating, message));
        }
        return output;
    }


}
