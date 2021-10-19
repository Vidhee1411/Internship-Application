import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
/**
 * The DataLoader class is responsible for loading all of the data from the
 * JSON files.
 * @author Joshua DuPuis
 */
public class DataLoader extends DataConstants {
    
    /**
     * The getJobListings method loads all of the JobListings from the data
     * files into an arraylist of JobListings.
     * @return The ArrayList of JobListings loaded from the JSON files
     */
    public ArrayList<JobListing> getJobListings(){
        return null;
    }

    /**
     * The getUsers method loads all of the Users from the data files into
     * an ArrayList of JobListings
     * @return The ArrayList of Users loaded from the JSON files
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.addAll(getAdmins());
        return null;
    }
    
    private ArrayList<User> getAdmins(){
     try {
        FileReader reader = new FileReader(admin_File_Name);
     } catch (Exception e) {
         e.printStackTrace();
     }
     JSONParser parser = new JSONParser();
     JSONArray Admins = parser.parse());

    }
}
