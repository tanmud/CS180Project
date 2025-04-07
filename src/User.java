import java.io.Serializable;

/**
 * User class
 * Structures the fields and methods of a user that can be used for the other classes
 * to keep track of different accounts.
 *
 * @author Natalie Lam, L09
 * @version Mar 28, 2025
 */

public class User implements UserInterface, Serializable {
    private String name;
    private String username;
    private String password;
    private Bank bank;

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
        return (user.name).equals(this.name);
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