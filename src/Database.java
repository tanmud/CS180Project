import java.util.*;
import java.io.*;

public class Database {
    // Writes users into the userlist.txt file
    private final String userFile = "userlist.txt";
    private final String conversationFile = "conversations.txt";
    private static ArrayList<User> userList;
    private static ArrayList<Conversation> conversations;
    private static Object userkeeper = new Object();
    private static Object convokeeper = new Object();

    public void startup() {
        File file = new File(userFile);
        if (file.createNewFile()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            synchronized (userkeeper) {
                User line = (User) ois.readObject();
                while (line != null) {
                    userList.add(line);
                    line = (User) ois.readObject();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        user.setBank(new Bank(user, new ArrayList<>, new ArrayList<>, 0));
        synchronized (userkeeper) {
            userList.add(user));
        }
        return "valid";
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            synchronized (userkeeper) {
                if (user.getUsername() == username) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Note: The same list is parsed twice because I want to prioritize items that have the String sequence in the name.
     * Method does check to ensure that an item isn't double counted.
     * @param sequence
     * @return ArrayList containing the found items from the search
     */
    public ArrayList<Items> itemSearch(String sequence) {
        ArrayList<Items> foundItems = new ArrayList<>();
        for (User user : userList) {
            for (int j = 0; j < user.getBank().getSelling().size(); j++) {
                if (user.getBank().getSelling()[j].getName().contains(sequence))
                    foundItems.add(user.getBank().getSelling()[j]);
            }
        }
        for (User user : userList) {
            for (int j = 0; j < user.getBank().getSelling().size(); j++) {
                if (user.getBank().getSelling()[j].getDescription().contains(sequence) &&
                        !foundItems.contains(user.getBank().getSelling()[j]))
                    foundItems.add(user.getBank().getSelling()[j]);
            }
        }
        return foundItems;
    }


    public void end() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            for (User user : userList) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}