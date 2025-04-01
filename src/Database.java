import java.util.*;
import java.io.*;
import java.

public class Database implements Runnable {
    // Writes users into the userlist.txt file
    private final String fileName = "userlist.txt";
    private static ArrayList<User> userList;
    private static Object gatekeeper = new Object();

    public void startup() {
        File file = new File(fileName);
        if (file.createNewFile()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileOutputStream(fileName))) {
            User line = (User) ois.readObject();
            while (line != null) {
                userList.add(line);
                line = (User) ois.readObject(line);
            }
        } catch (Exception e) {
            e.printStackTrace;
        }
    }

    /**
     * @param name
     * @param username
     * @param password
     * @return "valid" if correct
     */
    public String createUser(String name, String username, String password) {
        if (userExists(username)) {
            return "user exists";
        } else if (password.length() < 8) {
            return "invalid password";
        }
        User user = new User(name, username, password, new Bank(), new ArrayList<Message> inbox);
        user.setBank(Bank(user, new ArrayList<>, new ArrayList<>, 0));
        synchronized (bank) {
            userList.add(user));
        }
        return "valid";
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            synchronized (gatekeeper) {
                if (user.getUsername() == username) {
                    return true;
                }
            }
        }
        return false;
    }

    public
}