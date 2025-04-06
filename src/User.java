import java.io.Serializable;
import java.util.ArrayList;

public class User implements UserInterface, Serializable {
    private String name;
    private String username;
    private String password;
    private Bank bank;
    // Constructor should create name, username, password, set bank with everything 0/null
    //       User.java user = this; in each class
    // ArrayList<Message> chats

    public User(String name, String username, String password, Bank bank) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.bank = bank;
    }

    public void deleteAccount() {
        this.name = null;
        this.username = null;
        this.password = null;
        this.bank = null;
    } // set everything to default null values; delete message history, etc.

    // Returns true if the username of the two users are the same
    public boolean equals(User user) {
        if ((user.name).equals(this.name)) {
            return true;
        }
        return false;
    }

    // rest of the getter & setters
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public void setBank(Bank bank) {
        this.bank = bank;
    }
}