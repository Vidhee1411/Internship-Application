import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataLoadTest {
    
    public static void main(String[] args){ 
        SearchableDatabase a = DataLoader.loadData();
        ArrayList<User> temp = a.getUsers();
        

    }
}
