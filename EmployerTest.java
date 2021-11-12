import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class EmployerTest {
    
    @BeforeClass
    public static void oneTimeSetup() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

    }

    @Before
    public static void setUp() {

    }

    @After
    public static void tearDown() {

    }

    @Test
    public void testAssociateCompanyNormal() {
        Employer emp = new Employer("Joe", "Mon", "emp@company.com", "12345678");
        CompanyProfile com = new CompanyProfile("Company", "111 1st Drive", "We are a company - go figure");
        emp.associateCompany(com);
        assertEquals(emp.getCompany(), com);
    }

    @Test
    public void testAssociateCompanyWrongCompany() {
        Employer emp = new Employer("Joe", "Mon", "emp@company.com", "12345678");
        CompanyProfile com = new CompanyProfile("Company", "111 1st Drive", "We are a company - go figure");
        CompanyProfile com2 = emp.getCompany();
        emp.associateCompany(com);
        assertNotEquals(com2, emp.getCompany());
    }

    @Test
    public void testAssociateCompanyNull() {
        Employer emp = new Employer("Joe", "Mon", "emp@company.com", "12345678");
        emp.associateCompany(null);
        assertEquals(emp.getCompany(), null);
    }

    /*@Test
    public void testReviewAddNormal() {
        Student student = new Student("Jack", "Back", "jb@email.sc.edu", "123456789", "junior");
        Employer emp = new Employer("Joe", "Mon", "emp@company.com", "12345678");
        Review rev = new Review("Joe", "Mon", 4.0, "A good student");
        emp.reviewStudent(4, "A good student", student);
        assertEquals(student.getReviews().get(0), rev);
    }*/

    /*@Test
    public void testReviewWithNullValues(){
        Student student = new Student("Jack", "Back", "jb@email.sc.edu", "123456789", "junior");
        Employer emp = new Employer("Joe", "Mon", "emp@company.com", "12345678");
        Review rev = new Review("Joe", "Mon", 0, null);
        emp.reviewStudent(0, null, student);
        assertEquals(rev, student.getReviews().get(0));
    }*/





}
