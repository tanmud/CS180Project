public interface MessageInterface {
    // User sender = this;
    // User receipient = user;
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

    String getDate();
    void setDate(LocalDate date);

    String getTime();
    void setTime(LocalTime time);
}
