import java.util.*;
import java.io.*;

public class Database {
    // Writes users into the userlist.txt file
    private final String userFile = "userlist.txt";
    private final String conversationsFile = "conversationslist.txt";
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Conversation> conversationsList = new ArrayList<>();
    private static Object userkeeper = new Object();
    private static Object convokeeper = new Object();

    public void startup() {
        File user = new File(userFile);
        File convo = new File(conversationsFile); // Create File objects for each file
        loadUsers(user);
        loadConversations(convo); //Run both classes to load information into their respective ArrayList
    }

    private void loadUsers(File file) {
        if (file.length() == 0)
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            synchronized (userkeeper) {
                while (true) {
                    userList.add((User) ois.readObject());
                }
            }
        } catch (EOFException e) { /* Normal termination */
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
        } catch (EOFException e) { /* Normal termination */
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name
     * @param username
     * @param password
     * @return "valid" if correct
     */
    public String createUser(String name, String username, String password) throws InvalidUserException {
        if (userExists(username)) {
            throw new InvalidUserException("User exists!");
        } else if (password.length() < 8) {
            throw new InvalidUserException("Invalid password");
        }
        User user = new User(name, username, password, new Bank());
        user.setBank(new Bank(user));
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
                if (user.getName().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sendMessage(User sender, User receiver, String message) {
        int convoIdx = 0;
        if (!convoExists(sender, receiver)) {
            synchronized (convokeeper) {
                conversationsList.add(new Conversation(sender, receiver));
                convoIdx = conversationsList.size() - 1;
            }
        }
        synchronized (convokeeper) {
            for (int i = 0; i < conversationsList.size(); i++) {
                if (conversationsList.get(i).usersInConvo(sender, receiver)) {
                    convoIdx = i;
                }
            }
            conversationsList.get(convoIdx).sendMessage(sender, message);
        }
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

    public static void main(String[] args) {
        Database db = new Database();
        db.startup();
        db.end();
    }
}