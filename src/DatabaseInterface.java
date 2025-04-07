import java.util.ArrayList;

/**
 * DatabaseInterface Interface
 * Outlines the methods in the Database Class
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 2, 2025
 */

public interface DatabaseInterface {
    void startup();

    String createUser(String name, String username, String password) throws InvalidUserException;

    boolean convoExists(User user1, User user2);

    boolean userExists(User userFind);

    void sendMessage(User sender, User receiver, String message);

    ArrayList<Items> itemSearch(String sequence);

    void end();
}