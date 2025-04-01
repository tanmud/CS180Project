import java.time.LocalDate;
import java.time.LocalTime;

public class Message implements MessageInterface {

    // ADD CONSTRUCTOR

    @Override
    public User getSender() {

        return null;
    }

    @Override
    public void setSender(User sender) {

    }

    @Override
    public User getRecipient() {

        return null;
    }

    @Override
    public void setRecipient(User recipient) {

    }

    @Override
    public String getContent() {

        return "";
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public String getDate() {

        return "";
    }

    @Override
    public void setDate(LocalDate date) {

    }

    @Override
    public String getTime() {

        return "";
    }

    @Override
    public void setTime(LocalTime time) {

    }
}
