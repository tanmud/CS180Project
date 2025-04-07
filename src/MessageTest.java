import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * MessageTest class
 * Tests the methods of the message class
 *
 * @author Tanish Mudaliar, L09
 * @version Mar 30, 2024
 */

class MessageTest {

    private User sender;
    private User recipient;
    private String content;
    private Message message;

    @BeforeEach
    void setUp() {
        sender = new User("Sender Name", "username1", "password1", new Bank());
        recipient = new User("Recipient Name", "username2", "password2", new Bank());
        sender.setBank(new Bank(sender));
        recipient.setBank(new Bank(recipient));
        content = "Hello, this is a test message!";
        message = new Message(sender, recipient, content);
    }

    @Test
    void testConstructor() {
        assertEquals(sender, message.getSender());
        assertEquals(recipient, message.getRecipient());
        assertEquals(content, message.getContent());
        assertNotNull(message.getDate());
        assertNotNull(message.getTime());
    }

    @Test
    void testSetSender() {
        User newSender = new User("newSender Name", "username3", "password3", new Bank());
        newSender.setBank(new Bank(sender));
        message.setSender(newSender);
        assertEquals(newSender, message.getSender());
    }

    @Test
    void testSetRecipient() {
        User newRecipient = new User("NewRecipient name", "username4", "password4", new Bank());
        newRecipient.setBank(new Bank(recipient));
        message.setRecipient(newRecipient);
        assertEquals(newRecipient, message.getRecipient());
    }

    @Test
    void testSetContent() {
        String newContent = "Updated content for the message.";
        message.setContent(newContent);
        assertEquals(newContent, message.getContent());
    }

    @Test
    void testDateToString() {
        LocalDate date = LocalDate.of(2025, 1, 31); // Example date
        message.setDate(date);
        String expectedDateString = "JANUARY 31, 2025";
        assertEquals(expectedDateString, message.dateToString());
    }

    @Test
    void testSetDate() {
        LocalDate newDate = LocalDate.of(2023, 10, 15);
        message.setDate(newDate);
        assertEquals(newDate, message.getDate());
    }

    @Test
    void testTimeToString() {
        LocalTime time = LocalTime.of(14, 30); // Example time: 2:30 PM
        message.setTime(time);
        String expectedTimeString = "14:30";
        assertEquals(expectedTimeString, message.timeToString());
    }

    @Test
    void testSetTime() {
        LocalTime newTime = LocalTime.of(9, 45); // Example time: 9:45 AM
        message.setTime(newTime);
        assertEquals(newTime, message.getTime());
    }
}
