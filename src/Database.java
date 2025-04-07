import java.io.*;
import java.util.ArrayList;

/**
 * Database class
 * Reads and writes into the two main files that preserves user, message, and sales data.
 * Maintains updates to the userlist and conversationslist that will be used in the future.
 *
 * @author Tanish Mudaliar, L09
 * @version Mar 30, 2025
 */

public class Database {
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<Conversation> conversationsList = new ArrayList<>();
    private static Object userkeeper = new Object();
    private static Object convokeeper = new Object();
    // Writes users into the userlist.txt file
    private final String userFile = "userlist.txt";
    private final String conversationsFile = "conversationslist.txt";

    public void startup() {
        // Create File objects for each file
        File user = new File(userFile);
        File convo = new File(conversationsFile);
        loadUsers(user);
        loadConversations(convo); //Run both classes to load information into their respective ArrayList
    }

    private void loadUsers(File file) {
        if (file.length() == 0)
            return; // end if file emppty

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            synchronized (userkeeper) {
                while (true) {
                    userList.add((User) ois.readObject());
                }
            }
        } catch (EOFException e) { // Normal termination
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadConversations(File file) {
        if (file.length() == 0)
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            synchronized (convokeeper) {
                while (true) {
                    conversationsList.add((Conversation) ois.readObject());
                }
            }
        } catch (EOFException e) { // Normal termination
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String createUser(String name, String username, String password) throws InvalidUserException {
        if (userExists(username)) { // Check if user exists
            throw new InvalidUserException("User exists!");
        } else if (password.length() < 8) { // Check if password is valid
            throw new InvalidUserException("Invalid password");
        }
        User user = new User(name, username, password, new Bank());
        user.setBank(new Bank(user));
        // Update list
        synchronized (userkeeper) {
            userList.add(user);
        }
        return "valid";
    }

    public boolean convoExists(User user1, User user2) {
        synchronized (convokeeper) {
            for (Conversation convo : conversationsList) {
                if (convo.usersInConvo(user1, user2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            synchronized (userkeeper) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sendMessage(User sender, User receiver, String message) {
        int convoIdx = 0;
        if (!convoExists(sender, receiver)) {
            // Creates new convo
            synchronized (convokeeper) {
                conversationsList.add(new Conversation(sender, receiver));
                convoIdx = conversationsList.size() - 1;
            }
        }
        synchronized (convokeeper) {
            // Updates convo
            for (int i = 0; i < conversationsList.size(); i++) {
                if (conversationsList.get(i).usersInConvo(sender, receiver)) {
                    convoIdx = i;
                }
            }
            conversationsList.get(convoIdx).sendMessage(sender, message);
        }
    }

    //Note: The same list is parsed twice because I want to prioritize items that have the String sequence in the name.
    public ArrayList<Items> itemSearch(String sequence) {
        ArrayList<Items> foundItems = new ArrayList<>();
        for (User user : userList) {
            for (int j = 0; j < user.getBank().getSelling().size(); j++) {
                if (user.getBank().getSelling().get(j).getName().contains(sequence))
                    foundItems.add(user.getBank().getSelling().get(j));
            }
        }
        for (User user : userList) {
            for (int j = 0; j < user.getBank().getSelling().size(); j++) {
                if (user.getBank().getSelling().get(j).getDescription().contains(sequence) &&
                        !foundItems.contains(user.getBank().getSelling().get(j)))
                    foundItems.add(user.getBank().getSelling().get(j));
            }
        }
        return foundItems;
    }

    public void end() {
        try (ObjectOutputStream uoos = new ObjectOutputStream(new FileOutputStream(userFile));
             ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream(conversationsFile))) {
            // Write both files seperately
            synchronized (userkeeper) {
                for (User user : userList) {
                    uoos.writeObject(user);
                }
            }
            synchronized (convokeeper) {
                for (Conversation convo : conversationsList) {
                    coos.writeObject(convo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}