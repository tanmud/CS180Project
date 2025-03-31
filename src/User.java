public interface UserInterface {
    // Account Name
    // Account Username
    // Password
    // Bank bank
    // Constructor should create name, username, password, set bank with everything 0/null, friends null.
    //       User user = this; in each class
    // ArrayList<Message>
    String sendMessage(Message message); // sends message and returns if it was successful or not; adds to chat arraylist
//    boolean message(User user);
    String getChat(User recipient); // get message history with specified person; check if it's a chat
    boolean deleteAccount();

    User getName();


}
