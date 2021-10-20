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
            FileReader reader = new FileReader(job_Listing_File_Name);
            JSONArray listings =  (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < listings.size()-1; i++){
                JSONObject listing = (JSONObject)listings.get(i);
                String title = (String)listing.get(listing_Title);
                String description = (String)listing.get(listing_Description);
                String companyName = (String)listing.get(listing_Company_Name);
                Boolean paid = (Boolean)listing.get(listing_Paid);
                Double payRate = (Double)listing.get(listing_Pay_Rate);
                UUID id = (UUID)listing.get(listing_ID);
                JobListing temp = new JobListing(title, description, paid, payRate, id)
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
        FileReader reader = new FileReader(admin_File_Name);
        JSONArray Admins =  (JSONArray)new JSONParser().parse(reader);
        for(int i = 0; i < Admins.size()-1; i++){
            JSONObject admin = (JSONObject)Admins.get(i);
            String firstName = (String)admin.get(admin_First_Name);
            String lastName = (String)admin.get(admin_Last_Name);
            String email = (String)admin.get(admin_Email);
            String password = (String)admin.get(admin_Password);
            int permission =((Long)admin.get(admin_Permission)).intValue();
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
           FileReader reader = new FileReader(student_File_Name);
           JSONArray students =  (JSONArray)new JSONParser().parse(reader);
           for(int i = 0; i < students.size()-1; i++){
               JSONObject student = (JSONObject)students.get(i);
               String firstName = (String)student.get(student_First_Name);
               String lastName = (String)student.get(student_Last_Name);
               String email = (String)student.get(student_Email);
               String password = (String)student.get(student_Password);
               int permission =((Long)student.get(student_permission)).intValue();
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
            FileReader reader = new FileReader(job_Listing_File_Name);
            JSONArray CompanyProfiles =  (JSONArray)new JSONParser().parse(reader);
            for(int i = 0; i < CompanyProfiles.size()-1; i++){
                JSONObject companyprofile = (JSONObject)CompanyProfiles.get(i);
                String companyName = (String)companyprofile.get(company_Name);
                String hqAddress = (String)companyprofile.get(company_HQ_Adress);
                String description = (String)companyprofile.get(company_HQ_Adress);
                UUID companyID = (UUID)companyprofile.get(company_ID);
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
        JSONArray resumes = (JSONArray)student.get(student_Resumes);
        for(int i = 0; i < resumes.size()-1; i++){
            JSONObject resume = (JSONObject)resumes.get(i);
            String email = (String)student.get(student_Email);
            String yearInSchool = (String)resume.get(resume_School_year);
            ArrayList<String> skills = getSkills(student);
            ArrayList<Education> education = getEducation(student);
            ArrayList<WorkExperience> workExperiences = getWorkExperiences(student);
            output.add(new Resume(email, yearInSchool, skills, education, workExperiences));
        }
        return output;
    }
    
    private ArrayList<String> getSkills(JSONObject student){
        ArrayList<String> output = new ArrayList<>();
        JSONArray skillindex = (JSONArray)student.get(resume_Skill_indexes);
        JSONArray skills = (JSONArray)student.get(student_Skills);
        for(int i = 0; i < skills.size()-1; i++){
            int index = ((Double)skillindex.get(i)).intValue();
            output.add((String)skills.get(index));
        }
        return output;
    }

    private ArrayList<Education> getEducation(JSONObject student){
        ArrayList<Education> output = new ArrayList<>();
        JSONArray educationExperiences = (JSONArray)student.get(resume_Education);
        for(int i = 0; i < educationExperiences.size()-1; i++){
            JSONObject educationExperience = (JSONObject)educationExperiences.get(i);
            String nameofUniversity = (String)educationExperience.get(education_University_Name);
            String major = (String)educationExperience.get(education_Major);
            Double gpa = (Double)educationExperience.get(education_GPA);
            String expectedGradDate = (String)educationExperience.get(eudcation_Graduation_Date);
            Education temp = new Education(nameofUniversity, major, gpa, expectedGradDate);
            output.add(temp);
        }
        return output;
    }

    private ArrayList<WorkExperience> getWorkExperiences(JSONObject student){
        ArrayList<WorkExperience> output = new ArrayList<>();
        JSONArray workExperiences = (JSONArray)student.get(resume_Work_Experience);
        for(int i = 0; i < workExperiences.size()-1; i++){
            JSONObject workExperience = (JSONObject)workExperiences.get(i);
            String jobTitle = (String)workExperience.get(experience_Title);
            String company = (String)workExperience.get(experience_Company);
            String dateRange = (String)workExperience.get(experience_Date_Range);
            String description = (String)workExperience.get(experience_Description);
            WorkExperience temp = new WorkExperience(jobTitle, company, dateRange, description);
            output.add(temp);
        }
        return output;
    }

    
    private ArrayList<Review> getReviews(JSONObject reviewed){
        ArrayList<Review> output = new ArrayList<>();
        JSONArray reviews = (JSONArray)reviewed.get(Reviews);
        for(int i = 0; i < reviews.size()-1; i++){
            JSONObject review = (JSONObject)reviews.get(i);
            String firstName = (String)review.get(review_First_Name);
            String lastName = (String)review.get(review_Last_Name);
            String message = (String)review.get(review_Message);
            int rating = ((Long)review.get(review_Rating)).intValue();
            output.add(new Review(firstName, lastName, rating, message));
        }
        return output;
    }


}
