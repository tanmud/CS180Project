import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {

    @Test
    public void testItemFieldsAndGettersSetters() {
        Bank dummyBank = null;

        // Create a valid User
        User user = new User("Alice", "alice123", "securepass", dummyBank);

        // Create an Items object and set values
        Items item = new Items();
        item.setName("Water Bottle");
        item.setDescription("A 1-liter reusable water bottle.");
        item.setQuantity(2);
        item.setUser(user);

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
