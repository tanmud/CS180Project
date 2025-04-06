import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Bank bank;

    @BeforeEach
    void setUp() {// Mock Bank object
        user = new User("John Doe", "johndoe", "password123", new Bank());
        user.setBank(new Bank(user));
    }

    @Test
    void testConstructor() {
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        // The constructor creates a new Bank class
    }

    @Test
    void testSetName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void testSetUsername() {
        user.setUsername("janedoe");
        assertEquals("janedoe", user.getUsername());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    @Test
    void testSetBank() {
        Bank newBank = new Bank(user); // Mock new Bank object
        user.setBank(newBank);
        assertEquals(newBank, user.getBank());
    }

    @Test
    void testDeleteAccount() {
        user.deleteAccount();
        assertNull(user.getName());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getBank());
    }

    @Test
    void testEqualsTrue() {
        User sameUser = new User("John Doe", "johndoe", "anotherpassword", bank);
        assertTrue(user.equals(sameUser));
    }

    @Test
    void testEqualsFalse() {
        User differentUser = new User("Jane Doe", "janedoe", "password456", bank);
        assertFalse(user.equals(differentUser));
    }
}