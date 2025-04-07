import java.time.LocalDate;
import java.time.LocalTime;

/**
 * MessageInterface Interface
 * Creates a message outline for the method class that's
 * implemented elsewhere.
 *
 * @author Natalie Lam, L09
 * @version Mar 30, 2025
 */

public interface MessageInterface {
    User getSender();

    void setSender(User sender);

    User getRecipient();

    void setRecipient(User recipient);

    String getContent();

    void setContent(String content);

    String dateToString();

    LocalDate getDate();

    void setDate(LocalDate date);

    String timeToString();

    LocalTime getTime();

    void setTime(LocalTime time);
}
