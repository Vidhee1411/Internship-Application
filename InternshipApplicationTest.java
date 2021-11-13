import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class InternshipApplicationTest {
    private User user;
    private int permission;
    private SearchableDatabase database;

    @BeforeClass
    public void oneTimeSetup() {
    }

    @AfterClass
    public void oneTimeTearDown() {

    }

    @Before
    public void setUp() {
        this.database = DataLoader.loadData();
    }

    @After
    public void tearDown() {

    }

    /*@Test
    public void testCreateAccountWithStudent() {
        InternshipApplication app = new InternshipApplication();
        InputStream backup = System.in;
        InputStream input = new ByteArrayInputStream("Jennifer\nLupfalter\nblessedRNG@lucky.edu\nLadyLuck54321".getBytes());
        System.setIn(input);
        assertTrue(app.createAccount("Student"));
        System.setIn(backup);
    }*/

    @Test
    public void testLogOn() {
        String email = "joshDup@email.sc.edu";
        String password = "12345678";
        InternshipApplication app = new InternshipApplication();
        assertTrue(app.logOn(email, password));
    }

    @Test
    public void testLogOff() {
        InternshipApplication app = new InternshipApplication();
        app.logOn("joshDup@email.sc.edu", "12345678");
        app.logOff();
    }

    @Test
    public void testSearchByPayRateNormal() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(app.search(25.0).get(0).getTitle(), "Web Developer");
        assertEquals(app.search(25.0).get(0).getPaid(), true);
        assertEquals(app.search(25.0).get(0).getLocation(), "SouthCarolina");
        assertEquals(app.search(25.0).get(0).getPayRate(), 55.00);
        assertEquals(app.search(25.0).get(0).getCompany(), "WeyLand-Yutani");
        assertEquals(app.search(25.0).get(1).getTitle(), "Genetic Reasearh Intern");
        assertEquals(app.search(25.0).get(1).getPaid(), true);
        assertEquals(app.search(25.0).get(1).getLocation(), "SouthCarolina");
        assertEquals(app.search(25.0).get(1).getPayRate(), 25.00);
        assertEquals(app.search(25.0).get(1).getCompany(), "WeyLand-Yutani");
    }

    @Test
    public void testSearchByPayRateSizeGreaterThan25() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(1, app.search(30.0).size());
    }

    @Test
    public void testSearchByPayRateSizeLessThan25() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(2, app.search(20.0).size());    
    }

    @Test
    public void testSearchByPayRateSizeEquals25() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(2, app.search(25.0).size());
    }

    @Test
    public void testSearchByPayRateSizeEquals55() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(1, app.search(55.0).size());
    }

    @Test 
    public void testSearchByPayRateSizeGreaterThan55() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(0, app.search(85.0).size());
    }

    @Test
    public void testSearchByTitleNormal() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(1, app.search("Web Developer").size());
    }

    @Test
    public void testSearchByTitleSubstring() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(1, app.search("dev").size());
    }

    @Test
    public void testSearchByTitleNullTitle() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(2, app.search("").size());
    }

    @Test
    public void testSearchByTitleNotInDatabase() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(0, app.search("z").size());
    }

    @Test
    public void testSearchBySkillNormal() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(2, app.searchBySkill("morally ambiguous").size());
    }

    @Test
    public void testSearchByNullSkill() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(0, app.searchBySkill("").size());    
    }

    @Test
    public void testSearchByNonExistentSkill() {
        InternshipApplication app = new InternshipApplication();
        assertEquals(0, app.searchBySkill("grand ole fellow").size());
    }

    @Test
    public void testAssociateCompanyNormal() {
        String email = "Keithr@mailfraud.com";
        String password = "password";
        InternshipApplication app = new InternshipApplication();
        app.logOn(email, password); // This user was previously associated with a company - duplicate profile below
        CompanyProfile comp = new CompanyProfile("WeyLand-Yutani", "135 Sulaco Drive ", "pushing the boundries of science");
        Employer emp = (Employer) app.getUser();
        assertEquals(emp.getCompany().getCompanyName(), comp.getCompanyName());
        assertEquals(emp.getCompany().getAddress(), comp.getAddress());
        assertEquals(emp.getCompany().getDescription(), comp.getDescription());
    }

    @Test //Tests for a student that previously applied for a listing through the applyForInternship method
    public void testApplyForInternshipNormal() {
        String studentEmail = "joshDup@email.sc.edu";
        String studentPassword = "12345678";
        String employerEmail = "Keithr@mailfraud.com";
        String employerPassword = "password";
        InternshipApplication app = new InternshipApplication();
        app.logOn(employerEmail, employerPassword);
        Employer emp = (Employer) app.getUser();
        JobListing jl = emp.getCompany().getListings().get(0);
        app.logOff();
        app.logOn(studentEmail, studentPassword);
        Student stud = (Student) app.getUser();
        assertEquals(jl.getApplicants().get(0),stud);
    }


}
