public interface Message {
    // User sender = this;
    // User receipient = user;
    // String content
    // String Date            in format {Month,Day,Year}
    // String time            in format {hour:min} 24 hour clock
    String sendMessage(Message message); // sends message and returns if it was successful or not; writes to chats file
    String getChat(User recipient); // get message history with specified person
}
