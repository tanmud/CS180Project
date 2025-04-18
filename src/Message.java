import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * CS180 Group Project -- Marketplace Message Class
 * <p>
 * Allows you to create a Message object,
 * which contains information like the message’s
 * sender, recipient, content, date, and time
 * the message is sent. Relevant accessor and
 * mutator methods for each attribute are available.
 *
 * @author Natalie Lam, lab sec L09
 * @version Apr 06, 2025
 */

public class Message implements MessageInterface, Serializable {

    private User sender;
    private User recipient;
    private String content;
    private LocalDate date;
    private LocalTime time;


    public Message(User sender, User recipient, String content) {

        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.date = LocalDate.now(); // server time zone;
        this.time = LocalTime.now(); // server time zone;
    }

    @Override
    public User getSender() {

        return this.sender;
    }

    @Override
    public void setSender(User sender) {

        this.sender = sender;
    }

    @Override
    public User getRecipient() {

        return this.recipient;
    }

    @Override
    public void setRecipient(User recipient) {

        this.recipient = recipient;
    }

    @Override
    public String getContent() {

        return this.content;
    }

    @Override
    public void setContent(String content) {

        this.content = content;
    }

    @Override
    public String dateToString() {
        // example: JANUARY 31, 2025
        String dateConvert = this.date.getMonth() + " " + this.date.getDayOfMonth() + ", " + this.date.getYear();
        return dateConvert;
    }

    @Override
    public LocalDate getDate() {

        return this.date;
    }

    @Override
    public void setDate(LocalDate date) {

        this.date = date;
    }

    @Override
    public String timeToString() {

        String timeConvert = this.time.getHour() + ":" + this.time.getMinute();
        return timeConvert;
    }

    @Override
    public LocalTime getTime() {

        return this.time;
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
    }


}
