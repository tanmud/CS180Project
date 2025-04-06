/**
 * This class contains the code for the items interface.
 *
 * @author christianbancroft
 * @version March 31, 2025
 */

public interface ItemsInterface {
    // String name
    // String Description
    // int quantity
    // User user

    // getters and setters
    String getName();
    void setName(String user);

    String getDescription();
    void setDescription(String description);


    int getQuantity();
    void setQuantity(int quantity);

    User getUser();
    void setUser(User user);
}
