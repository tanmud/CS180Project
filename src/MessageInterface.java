import java.time.LocalDate;
import java.time.LocalTime;

public interface MessageInterface {
    // User.java sender = this;
    // User.java receipient = user;
    // String content
    // String Date            in format {Month,Day,Year}
    // String time            in format {hour:min} 24 hour clock

    // add the getters and setters for properties!
    User getSender();
    void setSender(User sender);

    User getRecipient();
    void setRecipient(User recipient);

    String getContent();
    void setContent(String content);

    LocalDate getDate();
    void setDate(LocalDate date);

    LocalTime getTime();
    void setTime(LocalTime time);
}
