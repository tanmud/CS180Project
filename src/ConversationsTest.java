import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ConversationsTest Class
 * Checks the methods in the Conversations class
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 1, 2025
 */

public class ConversationsTest {
    private User user1;
    private User user2;
    private User user3;
    private Conversation conversation;

    @BeforeEach
    void setUp() {
        user1 = new User("Alice", "alice123", "pass123", new Bank());
        user2 = new User("Bob", "bob456", "pass456", new Bank());
        user3 = new User("Charlie", "charlie789", "pass789", new Bank());
        user1.setBank(new Bank(user1));
        user2.setBank(new Bank(user2));
        user3.setBank(new Bank(user3));
        conversation = new Conversation(user1, user2);
    }

    @Test
    @DisplayName("Constructor initializes users and empty chats")
    void constructorInitialization() {
        assertSame(user1, conversation.getUser1());
        assertSame(user2, conversation.getUser2());
        assertTrue(conversation.getChats().isEmpty());
    }

    @Test
    @DisplayName("Send message from valid user1")
    void sendMessageFromUser1() throws Exception {
        String result = conversation.sendMessage(user1, "Hello Bob!");
        assertEquals("Message sent successfully!", result);
        assertEquals(1, conversation.getChats().size());
        // Checks if the message is sent and added to list

        Message msg = conversation.getChats().get(0);
        assertSame(user1, msg.getSender());
        assertSame(user2, msg.getRecipient());
        assertEquals("Hello Bob!", msg.getContent());
        // Checks if message content is the same
    }

    @Test
    @DisplayName("Send message from valid user2")
    void sendMessageFromUser2() throws Exception {
        String result = conversation.sendMessage(user2, "Hi Alice!");
        assertEquals("Message sent successfully!", result);
        assertEquals(1, conversation.getChats().size());
        // Checks if message has been added

        Message msg = conversation.getChats().get(0);
        assertSame(user2, msg.getSender());
        assertSame(user1, msg.getRecipient());
    }

    @Test
    @DisplayName("Prevent message from unauthorized user")
    void sendMessageFromInvalidUser() {
        assertThrows(InvalidUserException.class, () -> {
            conversation.sendMessage(user3, "I shouldn't be here!");
        });
        // Lambda function checks if another user is wrongly there
    }

    @Test
    @DisplayName("Check single user in conversation (positive)")
    void userInConvoPositive() {
        assertTrue(conversation.usersInConvo(user1));
        assertTrue(conversation.usersInConvo(user2));
    }

    @Test
    @DisplayName("Check single user in conversation (false)")
    void userInConvoFalse() {
        assertFalse(conversation.usersInConvo(user3));
    }

    @Test
    @DisplayName("Check user pair in conversation (both valid)")
    void usersInConvoBothValid() {
        assertTrue(conversation.usersInConvo(user1, user2));
    }

    @Test
    @DisplayName("Check user pair in conversation (one valid)")
    void usersInConvoOneValid() {
        assertTrue(conversation.usersInConvo(user1, user3));
        assertTrue(conversation.usersInConvo(user3, user2));
    }

    @Test
    @DisplayName("Check user pair in conversation (both invalid)")
    void usersInConvoBothInvalid() {
        User user4 = new User("Dave", "dave000", "pass000", new Bank());
        user4.setBank(new Bank(user4));
        assertFalse(conversation.usersInConvo(user3, user4));
    }

    @Test
    @DisplayName("Check same user pair")
    void usersInConvoSameUser() {
        assertFalse(conversation.usersInConvo(user1, user1));
        assertFalse(conversation.usersInConvo(user2, user2));
    }

    @Test
    @DisplayName("Multiple messages maintain order")
    void messageOrderPreservation() throws Exception {
        conversation.sendMessage(user1, "First");
        conversation.sendMessage(user2, "Second");
        conversation.sendMessage(user1, "Third");

        ArrayList<Message> chats = conversation.getChats();
        assertEquals(3, chats.size());
        assertEquals("First", chats.get(0).getContent());
        assertEquals("Second", chats.get(1).getContent());
        assertEquals("Third", chats.get(2).getContent());
    }

    @Test
    @DisplayName("Set and get chats directly")
    void setAndGetChats() {
        ArrayList<Message> newChats = new ArrayList<>();
        newChats.add(new Message(user1, user2, "Test"));
        conversation.setChats(newChats);

        assertSame(newChats, conversation.getChats());
        assertEquals(1, conversation.getChats().size());
    }

    @Test
    @DisplayName("Update conversation users")
    void updateUsers() {
        conversation.setUser1(user3);
        conversation.setUser2(user1);

        assertSame(user3, conversation.getUser1());
        assertSame(user1, conversation.getUser2());
    }
}
