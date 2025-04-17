/**
 * UserInterface class
 * Outlines the User class
 *
 * @author Natalie Lam, L09
 * @version Mar 30, 2025
 */

public interface UserInterface {
    void deleteAccount();

    boolean equals(User user);

    String getName();

    void setName(String name);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    Bank getBank();

    void setBank(Bank bank);
}
