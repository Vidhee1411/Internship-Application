import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    public void setup() {
        user = new Employer("Arle","Carbuncle","email@email","password12345", null, UUID.fromString("user"));
    }

    @Test 
    void testLogOnWithCorrectEmailAndPassword() {
        assertTrue(user.logOn("email@email", "password12345"));
    }

    @Test 
    void testLogOnWithCorrectEmailIncorrectPassword() {
        assertFalse(user.logOn("email@email", "thisIsIncorrect"));
    }

    @Test 
    void testLogOnWithCorrectEmailNullPassword() {
        assertFalse(user.logOn("email@email", null));
    }

    @Test 
    void testLogOnWithIncorrectEmailCorrectPassword() {
        assertFalse(user.logOn("wrong@email", "password12345"));
    }

    @Test 
    void testLogOnWithNullEmailCorrectPassword() {
        assertFalse(user.logOn(null, "password12345"));
    }
}
