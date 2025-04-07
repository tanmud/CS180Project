import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.ArrayList;

public class DatabaseTest {
    private Database db;
    private final String testUserFile = "userlist.txt";
    private final String testConvoFile = "conversationslist.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Clear previous files and static lists
        new File(testUserFile).delete();
        new File(testConvoFile).delete();
        Database.userList.clear();
        Database.conversationsList.clear();

        db = new Database();
    }

    @AfterEach
    void tearDown() {
        db.end();
    }

    @Test
    @DisplayName("Test startup with empty files")
    void startupWithEmptyFiles() {
        assertDoesNotThrow(() -> db.startup());
        assertEquals(0, Database.userList.size());
        assertEquals(0, Database.conversationsList.size());
    }

    @Test
    @DisplayName("Create valid user")
    void createValidUser() throws Exception {
        String result = db.createUser("John Doe", "johndoe", "password123");
        assertEquals("valid", result);
        assertEquals(1, Database.userList.size());
    }

    @Test
    @DisplayName("Prevent duplicate username creation")
    void preventDuplicateUsers() {
        assertThrows(InvalidUserException.class, () -> {
            db.createUser("John Doe", "johndoe", "password123");
            db.createUser("John Doe", "johndoe", "password456");
        });
    }

    @Test
    @DisplayName("Reject short passwords")
    void rejectShortPassword() {
        assertThrows(InvalidUserException.class,
                () -> db.createUser("Short", "shortpass", "1234"),
                "Should throw for password < 8 characters"
        );
    }

    @Test
    @DisplayName("Find existing user")
    void userExistsPositive() throws Exception {
        db.createUser("Alice", "alice123", "password123");
        assertTrue(db.userExists("alice123"));
    }

    @Test
    @DisplayName("Handle non-existent user")
    void userExistsNegative() {
        assertFalse(db.userExists("nonexistent"));
    }

    @Test
    @DisplayName("Create new conversation when sending first message")
    void sendMessageCreatesConversation() throws Exception {
        User u1 = createTestUser("user1");
        User u2 = createTestUser("user2");

        db.sendMessage(u1, u2, "Hello");
        assertEquals(1, Database.conversationsList.size());
    }

    @Test
    @DisplayName("Find existing conversation")
    void conversationExistsPositive() throws Exception {
        User u1 = createTestUser("user1");
        User u2 = createTestUser("user2");
        db.sendMessage(u1, u2, "Test");

        assertTrue(db.convoExists(u1, u2));
    }

    @Test
    @DisplayName("Item search prioritizes name matches")
    void itemSearchPrioritization() throws Exception {
        // Setup items
        User seller = createTestUser("seller");
        Items nameMatch = new Items("Laptop", "Electronics", 1, null);

        Items descMatch = new Items("Notebook", "Laptop case", 1, null);

        seller.getBank().getSelling().add(nameMatch);
        seller.getBank().getSelling().add(descMatch);

        // Test search
        ArrayList<Items> results = db.itemSearch("Laptop");
        assertEquals(2, results.size());
        assertTrue(results.get(0).getName().contains("Laptop"));
    }

    @Test
    @DisplayName("Persist data through shutdown cycle")
    void persistenceTest() throws Exception {
        // Create test data
        User u = createTestUser("testuser");
        db.end();

        // Reload database
        Database newDb = new Database();
        newDb.startup();

        assertTrue(newDb.userExists("testuser"));
    }

    private User createTestUser(String username) throws InvalidUserException {
        db.createUser(username, username, "password123");
        return Database.userList.stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .orElseThrow();
    }
}

