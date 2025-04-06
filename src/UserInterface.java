import java.util.ArrayList;

public interface UserInterface {

    // extends Bank?

    // Account Name
    // Account Username
    // Password
    // Bank bank
    // Constructor should create name, username, password, set bank with everything 0/null
    //       User.java user = this; in each class
    // ArrayList<Message> chat
    public void deleteAccount();
    public boolean equals(User user);
    public String getName();
    public void setName(String name);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    Bank getBank();
    void setBank(Bank bank);
}
