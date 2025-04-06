import java.util.ArrayList;

public interface DatabaseInterface {
    public void startup();
    public String createUser(String name, String username, String password) throws InvalidUserException;
    public boolean convoExists(User user1, User user2);
    public boolean userExists(User userFind);
    public void sendMessage(User sender, User receiver, String message);
    public ArrayList<Items> itemSearch(String sequence);
    void end();
}