import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setup() {
        user = new Employer("Arle","Carbuncle","email@email","password12345", null, UUID.randomUUID());
    }

    @Test 
    public void testLogOnWithCorrectEmailAndPassword() {
        assertTrue(user.logOn("email@email", "password12345"));
    }

    @Test 
    public void testLogOnWithCorrectEmailIncorrectPassword() {
        assertFalse(user.logOn("email@email", "thisIsIncorrect"));
    }

    @Test 
    public void testLogOnWithCorrectEmailNullPassword() {
        assertFalse(user.logOn("email@email", null));
    }

    @Test 
    public void testLogOnWithIncorrectEmailCorrectPassword() {
        assertFalse(user.logOn("wrong@email", "password12345"));
    }

    @Test 
    public void testLogOnWithNullEmailCorrectPassword() {
        assertFalse(user.logOn(null, "password12345"));
    }
}
