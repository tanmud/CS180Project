import java.util.ArrayList;

/**
 * ConversationInterface Interface
 * Outlines the conversations class.
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 3, 2025
 */

public interface ConversationInterface {
    String sendMessage(User sender, String message) throws InvalidUserException;

    boolean usersInConvo(User user);

    boolean usersInConvo(User u1, User u2);

    ArrayList<Message> getChats();

    void setChats(ArrayList<Message> chats);

    User getUser1();

    void setUser1(User user1);

    User getUser2();

    void setUser2(User user2);
}
