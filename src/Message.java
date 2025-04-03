import java.time.LocalDate;
import java.time.LocalTime;

public class Message implements MessageInterface {

    private User sender;
    private User recipient;
    private String content;
    private LocalDate date;
    private LocalTime time;


    public Message(User sender, User recipient, String content) {

        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.date = LocalDate.now(); // server time zone; but i think you can change time zones? more complex though
        this.time = LocalTime.now(); // server time zone; but i think you can change time zones? more complex though
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
