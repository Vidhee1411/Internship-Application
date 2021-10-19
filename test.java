import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class test extends DataConstants {
    public static void main(String[] args){
        try {
			FileReader reader = new FileReader(Student_File_Name);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			
				JSONObject personJSON = (JSONObject)peopleJSON.get(1);
                JSONArray reviews = (JSONArray)personJSON.get("reviews");
                JSONObject review = (JSONObject)reviews.get(0);
                String reviewfn = (String)review.get("firstName");
				String firstName = (String)personJSON.get(Student_First_Name);
                System.out.print(reviewfn);

			
		} catch (Exception e) {
			e.printStackTrace();
        }
    }
}
