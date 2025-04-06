import java.util.ArrayList;

public interface UserInterface {

    // extends Bank?

    // Account Name
    // Account Username
    // Password
    // Bank bank
    // Constructor should create name, username, password, set bank with everything 0/null
    //       User.java user = this; in each class
    // ArrayList<Message> chats
    String sendMessage(Message message); // sends message and returns if it was successful or not; adds to chat arraylist

    void setChats(ArrayList<Message> chats);

    //    boolean message(User.java user);
    ArrayList<Message> getChat(User recipient) throws NoChatFoundException; // get message history with specified person; check if it's a chat
    void deleteAccount(); // set everything to default null values; delete message history, etc.

//    User getName();

    // rest of the getter & setters
    String getName();
    void setName(String name);

    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);

    ArrayList<Message> getChats(); // convert chat list to readable format
    // setchat is essentially the sendMessage

    Bank getBank();
}
