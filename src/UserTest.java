//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class UserTest {
//
//    Bank bank1 = null;
//    Bank bank2 = null;
//    private User u1 = new User("user1", "username1", "password1", bank1);
//    private User u2 = new User("user2", "username2", "password2", bank2);
//    private User u3 = new User("user3", "username3", "password3", bank2);
//    private Message m1 = new Message (u1, u2, "msg1");
//    private Message mFinal = new Message(u1, u2, "finalMSG");
//    private Message m3 = new Message(u1, u3, "afterMSG");
//    private Message newMessage = new Message (u1, u2, "new message");
//    private ArrayList<Message> chats = new ArrayList<>(Arrays.asList(m1, m1, m1, m1, m1, mFinal, m3, m3));
//
//    // test the sendMessage method
//    @Test
//    public void sendMessageShouldAddToTheEndOfTheMessageHistory() {
//
//        givenAchatCollection();
//        findLastMessageAndSendIt();
//        chatShouldBeInsertedAtLastMessageWithThatPerson();
//    }
//
//    private void givenAchatCollection() {
//
//        // Message(User sender, User recipient, String content)
//        // User(String name, String username, String password, Bank bank)
//
//        u1.setChats(chats);
//    }
//
//    private void findLastMessageAndSendIt() {
//
//        u1.sendMessage(newMessage);
//    }
//
//    private void chatShouldBeInsertedAtLastMessageWithThatPerson() {
//
//        ArrayList<Message> chats2 = new ArrayList<>(Arrays.asList(m1, m1, m1, m1, m1, mFinal, newMessage, m3, m3));
//        assertEquals(u1.getChats(), chats2);
//    }
//
//    // test the getChats method
//    @Test
//    public void testGettingChats() {
//
//        givenAchatCollection();
//        testIfItReturnsTheChat();
//    }
//
//    private void testIfItReturnsTheChat() {
//
//        u1.getChats();
//        assertEquals(chats, u1.getChats());
//    }
//
//
//}

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