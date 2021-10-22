import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The DataWriter class writes data that is entered by different users to the
 * JSON files.
 * 
 * @author Zachary Young
 * @author Joshua DuPuis
 */
public class DataWriter extends DataConstants {
    /**
     * The saveUsers method writes all of the Users in the system to the JSON Users
     * file to store data on each user.
     */
    public static void saveUsers() {
        UserDatabase users = UserDatabase.getInstance();
        ArrayList<User> userList = users.getUsers();
        
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Employer> employers = new ArrayList<>();
        ArrayList<Administrator> administrators = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            switch(user.getPermission()) {
                case 0: //User is a student
                    students.add((Student) user);
                    break;
                case 1: //User is an employer
                    employers.add((Employer) user);
                    break;
                case 2:
                    administrators.add((Administrator) user);
                    break;
                default:
                    System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " (ID: " +
                    user.getUUID() + ") has invalid permissions. They will not be written to the database.");
            }
        }

        saveStudents(students);
        saveEmployers(employers);
        saveAdministrators(administrators);
    }

    /**
     * Private helper method for writing an ArrayList of Students to the Student.json file.
     * @param studentList The ArrayList of Students to write
     */
    private static void saveStudents(ArrayList<Student> studentList) {
        JSONArray jsonStudents = new JSONArray();

        for(Student student : studentList) {
            JSONObject studentDetails = new JSONObject();
            studentDetails.put(STUDENT_ID, student.getUUID().toString());
            studentDetails.put(STUDENT_FIRST_NAME, student.getFirstName());
            studentDetails.put(STUDENT_LAST_NAME, student.getLastName());
            studentDetails.put(STUDENT_EMAIL, student.getEmail());
            studentDetails.put(STUDENT_PASSWORD, student.getPassword());
            studentDetails.put(STUDENT_PERMISSION, 0);
            studentDetails.put(STUDENT_REVIEWS, arrayListToJsonOfIDs(student.getReviews()));
            studentDetails.put(STUDENT_RESUME_ID, student.getResume().getID());
            jsonStudents.add(studentDetails);
        }
       
        try {
            FileWriter file = new FileWriter(STUDENT_FILE_NAME);
            file.write(jsonStudents.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private helper method for writing an arrayList of Employers to the Employers.json file.
     * @param employerList The ArrayList of Employer to write
     */
    private static void saveEmployers(ArrayList<Employer> employerList) {
        JSONArray jsonEmployer = new JSONArray();
        for(Employer employer : employerList) {
            JSONObject employerDetails = new JSONObject();
            employerDetails.put(EMPLOYER_ID, employer.getUUID().toString());
            employerDetails.put(EMPLOYER_FIRST_NAME, employer.getFirstName());
            employerDetails.put(EMPLOYER_LAST_NAME, employer.getLastName());
            employerDetails.put(EMPLOYER_EMAIL, employer.getEmail());
            employerDetails.put(EMPLOYER_PASSWORD, employer.getPassword());
            employerDetails.put(EMPLOYER_PERMISSION, employer.getPermission());
            employerDetails.put(EMPLOYER_REVIEWS, arrayListToJsonOfIDs(employer.getReviews()));
            employerDetails.put(EMPLOYER_ASSOCIATED_COMPANY, employer.getCompany().toString());
            jsonEmployer.add(employerDetails);
        }

        try {
            FileWriter file = new FileWriter(EMPLOYER_FILE_NAME);
            file.write(jsonEmployer.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private helper method for writing an arrayList of Administrator to the Administrator.json file.
     * @param adminList The ArrayList of Students to write
     */
    private static void saveAdministrators(ArrayList<Administrator> adminList) {
        JSONArray jsonAdministrator = new JSONArray();
        for(Administrator administrator : adminList) {
            JSONObject adminDetails = new JSONObject();
            adminDetails.put(ADMIN_ID, administrator.getUUID().toString());
            adminDetails.put(ADMIN_FIRST_NAME, administrator.getFirstName());
            adminDetails.put(ADMIN_LAST_NAME, administrator.getLastName());
            adminDetails.put(ADMIN_EMAIL, administrator.getEmail());
            adminDetails.put(ADMIN_PASSWORD, administrator.getPassword());
            adminDetails.put(ADMIN_PERMISSION, administrator.getPermission());
            jsonAdministrator.add(adminDetails);
        }

        try {
            FileWriter file = new FileWriter(ADMIN_FILE_NAME);
            file.write(jsonAdministrator.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The saveJobListings method writes all of the JobListings in the system to the
     * JSON JobListings file to store data on each JobListing
     */
    public static void saveJobListings() {
        SearchableDatabase companyJobDatabase = SearchableDatabase.getInstance();
        ArrayList<JobListing> jobsList = companyJobDatabase.getJobListings();
        JSONArray jsonListings = new JSONArray();

        for (int i = 0; i < jobsList.size(); i++) {
            jsonListings.add(getJobListingJSON(jobsList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(JOB_LISTING_FILE_NAME);
            file.write(jsonListings.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given JobListing into a JSONObject.
     * 
     * @param jobListing The JobListing to convert into a JSONObject
     * @return The JSONObject representation of the JobListing
     */
    public static JSONObject getJobListingJSON(JobListing jobListing) {
        JSONObject listingDetails = new JSONObject();
        listingDetails.put(LISTING_ID, jobListing.getID().toString());
        listingDetails.put(LISTING_TITLE, jobListing.getTitle());
        listingDetails.put(LISTING_DESCRIPTION, jobListing.getDescription());
        listingDetails.put(LISTING_PAID, jobListing.getPaid());
        listingDetails.put(LISTING_PAY_RATE, jobListing.getPayRate());
        listingDetails.put(LISTING_REQUIRED_SKILLS, arrayListToJsonOfIDs(jobListing.getSkills()));
        listingDetails.put(LISTING_COMPANY_NAME, jobListing.getCompanyName());
        listingDetails.put(LISTING_HIDDEN, jobListing.getHidden());
        listingDetails.put(LISTING_APPLICANT_IDS, arrayListToJsonOfIDs(jobListing.getApplicants()));
        return listingDetails;
    }

    /**
     * The saveCompanyProfiles method writes all of the CompanyProfiles in the system to the
     * JSON Companyprofiles file to store data on each CompanyProfile.
     */
    public static void saveCompanyProfiles() {
        SearchableDatabase companyJobDatabase = SearchableDatabase.getInstance();
        ArrayList<CompanyProfile> companyProfileList = companyJobDatabase.getCompanyProfiles();
        JSONArray jsonCompanies = new JSONArray();

        for(int i = 0; i < companyProfileList.size(); i++) {
            jsonCompanies.add(getCompanyProfileJSON(companyProfileList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(COMPANY_PROFILE_FILE_NAME);
            file.write(jsonCompanies.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given CompanyProfile into a JSONObject.
     * 
     * @param company The CompanyProfile to convert into a JSONObject
     * @return The JSONObject representation of the CompanyProfile
     */
    public static JSONObject getCompanyProfileJSON(CompanyProfile company) {
        JSONObject companyDetails = new JSONObject();
        companyDetails.put(COMPANY_ID, company.getID().toString());
        companyDetails.put(COMPANY_NAME, company.getCompanyName());
        //May have to add a get/set address in company profile. 
        companyDetails.put(COMPANY_HQ_ADDRESS, company.getAddress());
        companyDetails.put(COMPANY_DESCRIPTION, company.getDescription());
        companyDetails.put(COMPANY_LISTINGS_IDS, arrayListToJsonOfIDs(company.getListings()));
        companyDetails.put(COMPANY_REVIEWS, company.getReviews());
        //May have to add a get/set reviews in company profile
        //Add employer list to CompanyProfile (since the JSON tracks employer IDs)
        //Then loop through that list to get all the IDs to put in the JSON
        return companyDetails;
    }

    /**
     * Private helper method use to convert an ArrayList of objects into a JSONArray with each object's ID
     * @param arrayList The arrayList of objects to convert
     * @return A JSONArray containing the IDs of the ArrayList's contents
     */
    private static JSONArray arrayListToJsonOfIDs(ArrayList<? extends Object> arrayList) {
        JSONArray jsonArray = new JSONArray();
        for(Object object : arrayList) {
            jsonArray.add(object.getID());
        }
        return jsonArray;
    } 
}