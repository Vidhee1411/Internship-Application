import static org.junit.jupiter.api.Assertions.*;

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
    public void testCreateAccountWithStudent() {
        InternshipApplication app = new InternshipApplication();
        InputStream backup = System.in;
        InputStream input = new ByteArrayInputStream("Jennifer\nLupfalter\nblessedRNG@lucky.edu\nLadyLuck54321".getBytes());
        System.setIn(input);
        assertTrue(app.createAccount("Student"));
        System.setIn(backup);
    }

    @Test
    public void testLogOn() {
        InternshipApplication app = new InternshipApplication();
        InputStream backup = System.in;
        InputStream input = new ByteArrayInputStream("Jennifer\nLupfalter\nblessedRNG@lucky.edu\nLadyLuck54321".getBytes());
        System.setIn(input);
        String email = "blessedRNG@lucky.edu";
        String password = "LadyLuck54321";
        app.createAccount("student");
        System.setIn(backup);
        assertTrue(app.logOn(email,password));
    }
}
