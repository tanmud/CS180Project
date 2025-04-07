/**
 * ItemsInterface Class
 * Outlines the items class that implemented in another file
 *
 * @author Christian Bancroft, L09
 * @version Apr 1, 2025
 */

public interface ItemsInterface {

    String getName();

    void setName(String user);

    String getDescription();

    void setDescription(String description);

    int getQuantity();

    void setQuantity(int quantity);

    User getUser();

    void setUser(User user);
}