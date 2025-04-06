import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CS180 Group Project -- Marketplace MessageTest Class
 * <p>
 * A document with the JUnit tests for
 * Message.java, testing the dateToString
 * and timeToString methods.
 *
 * @author Natalie Lam, lab sec L09
 * @version April 06, 2025
 */

public class MessageTest {

    private LocalDate date = LocalDate.of(2025, 04, 05);
    private LocalTime time =  LocalTime.of(23, 33, 01);
    User u1 = null;
    User u2 = null;
    Message testMessage = new Message(u1, u2, "test");


    // test dateToString
    @Test
    public void convertedDateReturnsDetailedFormat() {

        compareExpectedDateWithResult();
    }

    private void compareExpectedDateWithResult() {

        testMessage.setDate(this.date);
        String newDate = testMessage.dateToString();
        assertEquals("APRIL 5, 2025", newDate);
    }

    // test dateToString
    @Test
    public void convertedTimeReturnsDetailedFormat() {

        compareExpectedTimeWithResult();
    }

    private void compareExpectedTimeWithResult() {

        testMessage.setTime(this.time);
        String newTime = testMessage.timeToString();
        assertEquals("23:33", newTime);
    }


}
