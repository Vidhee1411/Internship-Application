import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The DataWriter class writes data that is entered by different users to the
 * JSON files.
 * @author Zachary Young 
 * @author Joshua DuPuis
 */
public class DataWriter extends DataConstants {
    
    /**
     * The saveUsers method writes all of the Users in the system to the JSON
     * Users file to store data on each user.
     */
    public static void saveUsers() {
        UserDatabase users = UserDatabase.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for(int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(USER_FILE_NAME);
            file.write(jsonUsers.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The saveJobListings method writes all of the JobListings in the system
     * to the JSON JobListings file to store data on each JobListing
     */
    public static void saveJobListings() {
        SearchableDatabase companyJobDatabase = SearchableDatabase.getInstance();
        ArrayList<JobListing> jobsList = companyJobDatabase.getJobListings();
        JSONArray jsonListings = new JSONArray();

        for(int i = 0; i < jobsList.size(); i++) {
            jsonListings.add(getJobListingJSON(jobsList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(LISTING_FILE_NAME);
            file.write(jsonListings.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given User into a JSONObject.
     * @param user The user to convert into a JSONObject
     * @return The JSONObject representation of the user
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_PERMISSION, user.getPermission());
        return userDetails;
    }

    
    /**
     * Converts a given JobListing into a JSONObject.
     * @param jobListing The JobListing to convert into a JSONObject
     * @return The JSONObject representation of the JobListing
     */
    public static JSONObject getJobListingJSON(JobListing jobListing) {
        JSONObject listingDetails = new JSONObject();
        //A UUID needs to be added to JobListing. Either for the companyprofile or for itself.
        //Then a listingDetails.put can be given for it.
        listingDetails.put(LISTING_TITLE, jobListing.getTitle());
        listingDetails.put(LISTING_DESCRIPTION, jobListing.getDescription());
        listingDetails.put(LISTING_PAID, jobListing.getPaid());
        listingDetails.put(LISTING_RATE, jobListing.getPayRate());
        listingDetails.put(LISTING_SKILLS, jobListing.getSkills());
        listingDetails.put(LISTING_COMPANY, jobListing.getCompanyName());
        listingDetails.put(LISTING_HIDDEN, jobListing.getHidden());
        listingDetails.put(LISTING_APPLICANTS, jobListing.getApplicants());
        return listingDetails;
    }
}
