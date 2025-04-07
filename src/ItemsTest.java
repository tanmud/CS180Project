import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains items class test.
 *
 * @author christianbancroft
 * @version April 5, 2025
 */

public class ItemsTest {

    @Test
    public void testItemFieldsAndGettersSetters() {
        Bank dummyBank = null;

        // Create a valid User
        User user = new User("Alice", "alice123", "securepass", dummyBank);

        // Create an Items object and set values
        Items item = new Items("Water Bottle", "A 1-liter reusable water bottle.", 2, user);

        // Assertions
        assertEquals("Water Bottle", item.getName());
        assertEquals("A 1-liter reusable water bottle.", item.getDescription());
        assertEquals(2, item.getQuantity());
        assertEquals(user, item.getUser());

        // Check user fields
        assertEquals("Alice", item.getUser().getName());
        assertEquals("alice123", item.getUser().getUsername());
        assertEquals("securepass", item.getUser().getPassword());
    }
}

