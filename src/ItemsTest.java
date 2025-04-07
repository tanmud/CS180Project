import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {
    private User user1;
    private User user2;
    private Items baseItem;

    @BeforeEach
    void setUp() {
        user1 = new User("User1", "user1", "password123", new Bank());
        user2 = new User("User2", "user2", "password456", new Bank());
        user1.setBank(new Bank(user1));
        user2.setBank(new Bank(user2));
        baseItem = new Items("Laptop", "Gaming laptop", 5, user1);
    }

    @Test
    @DisplayName("Constructor and getters work correctly")
    void constructorAndGetters() {
        assertEquals("Laptop", baseItem.getName());
        assertEquals("Gaming laptop", baseItem.getDescription());
        assertEquals(5, baseItem.getQuantity());
        assertEquals(user1, baseItem.getUser());
    }

    @Test
    @DisplayName("Setters update fields properly")
    void settersUpdateFields() {
        baseItem.setName("Tablet");
        baseItem.setDescription("Portable device");
        baseItem.setQuantity(10);
        baseItem.setUser(user2);

        assertAll("Updated fields",
                () -> assertEquals("Tablet", baseItem.getName()),
                () -> assertEquals("Portable device", baseItem.getDescription()),
                () -> assertEquals(10, baseItem.getQuantity()),
                () -> assertEquals(user2, baseItem.getUser())
        );
    }

    @Test
    @DisplayName("Equality check ignores quantity and user")
    void equalityIgnoresQuantityAndUser() {
        Items sameAttributes = new Items("Laptop", "Gaming laptop", 3, user2);
        Items differentName = new Items("Phone", "Gaming laptop", 5, user1);
        Items differentDesc = new Items("Laptop", "Business laptop", 5, user1);

        assertAll("Equality checks",
                () -> assertTrue(baseItem.equals(sameAttributes)),
                () -> assertFalse(baseItem.equals(differentName)),
                () -> assertFalse(baseItem.equals(differentDesc))
        );
    }

    @Test
    @DisplayName("Equals handles null and different types")
    void equalsEdgeCases() {
        String notAnItem = "Laptop";
        assertAll("Edge cases",
                () -> assertFalse(baseItem.equals(null)),
                () -> assertFalse(baseItem.equals(notAnItem)) // Invalid type asessment
        );
    }
}