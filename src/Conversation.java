import java.io.Serializable;
import java.util.ArrayList;

/**
 * Conversation class
 * Embeds the message class to create a coherent structure for one-
 * to-on conversations. Each conversation is serialized into a file
 * in DataBase.java
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 3, 2025
 */

public class Conversation implements ConversationInterface, Serializable {
    private User user1;
    private User user2;
    private ArrayList<Message> chats;

    public Conversation(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        chats = new ArrayList<>();
    }

    public String sendMessage(User sender, String message) throws InvalidUserException {
        User receiver;
        if (sender.equals(user1)) {
            receiver = user2;
        } else if (sender.equals(user2)) {
            receiver = user1;
        } else {
            throw new InvalidUserException("User is not in the chat!");
        } // Throws an exception if the wrong user is inputted as the sender

        this.chats.add(new Message(sender, receiver, message));
        return "Message sent successfully!";
    } // sends message and returns if it was successful or not; adds to chat arraylist
    //    boolean message(User.java user);

    public boolean usersInConvo(User user) {
        return user1.equals(user) || user2.equals(user);
    } // Checks whether a user is one of the user fields

    public boolean usersInConvo(User u1, User u2) {
        if (u1.equals(u2)) {
            return false; // Return false if it's the same user
        }
        return usersInConvo(u1) || usersInConvo(u2); // Returns true if both users are the user parameter
    }

    public ArrayList<Message> getChats() {
        return this.chats;
    }

    public void setChats(ArrayList<Message> chats) {
        this.chats = chats;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
