import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataLoadTest {
    
    public static void main(String[] args){ 
        SearchableDatabase database = DataLoader.loadData();
        InternshipApplication temp = new InternshipApplication();
        InternshipUI tempUI = new InternshipUI();
         tempUI.run();
         System.out.println("");
         temp.logOff();
    }
}
