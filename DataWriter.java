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
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(JOB_LISTING_FILE_NAME);
            file.write(jsonUsers.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given User into a JSONObject based on the type of User they are.
     * 
     * @param user The user to convert into a JSONObject
     * @return The JSONObject representation of the user
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        switch(user.getPermission()) {
            case 0: //User has student permissions
                Student student = (Student) user;
                userDetails.put(STUDENT_ID, student.getId().toString());
                userDetails.put(STUDENT_FIRST_NAME, student.getFirstName());
                userDetails.put(STUDENT_LAST_NAME, student.getLastName());
                userDetails.put(STUDENT_EMAIL, student.getEmail());
                userDetails.put(STUDENT_PASSWORD, student.getPassword());
                userDetails.put(STUDENT_PERMISSION, student.getPermission());
                userDetails.put(STUDENT_REVIEWS, arrayListToJsonOfIDs(student.getReviews()));
                userDetails.put(STUDENT_RESUME_ID, student.getResume().toString());
                break;
            case 1: //User has employer permissions
                Employer employer = (Employer) user;
                userDetails.put(EMPLOYER_ID, employer.getId().toString());
                userDetails.put(EMPLOYER_FIRST_NAME, employer.getFirstName());
                userDetails.put(EMPLOYER_LAST_NAME, employer.getLastName());
                userDetails.put(EMPLOYER_EMAIL, employer.getEmail());
                userDetails.put(EMPLOYER_PASSWORD, employer.getPassword());
                userDetails.put(EMPLOYER_PERMISSION, employer.getPermission());
                userDetails.put(EMPLOYER_REVIEWS, arrayListToJsonOfIDs(employer.getReviews()));
                userDetails.put(EMPLOYER_ASSOCIATED_COMPANY, employer.getCompany().toString());
                break;
            case 2: //User has administrator permissions
                Administrator administrator = (Administrator) user;
                userDetails.put(ADMIN_ID, administrator.getId().toString());
                userDetails.put(ADMIN_FIRST_NAME, administrator.getFirstName());
                userDetails.put(ADMIN_LAST_NAME, administrator.getLastName());
                userDetails.put(ADMIN_EMAIL, administrator.getEmail());
                userDetails.put(ADMIN_PASSWORD, administrator.getPassword());
                userDetails.put(ADMIN_PERMISSION, administrator.getPermission());
                break;
            default:
                System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " (ID: " +
                user.getID() + ") has invalid permissions. They will not be written to the database.");
        }

        return userDetails;
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