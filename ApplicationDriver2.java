import java.util.ArrayList;
public class ApplicationDriver2 {
    public static void main( String [] args ) {
        InternshipApplication app = new InternshipApplication();
        ArrayList<User> users = app.getUsers();
        for (User u: users) {
            System.out.println(u.getEmail() + " " + u.getPassword());
        }
    }
}
