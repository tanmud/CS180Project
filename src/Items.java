import java.io.Serializable;
import java.util.Objects;

/**
 * This class contains the code for the items class.
 *
 * @author christianbancroft
 * @version March 31, 2025
 */

public class Items implements ItemsInterface, Serializable {
    private String name;
    private String description;
    private int quantity;
    private User user;


    public Items(String name, String description, int quantity, User user) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.user = user;
    }
    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items)) return false;
        Items items = (Items) o;
        return name.equals(items.name) && description.equals(items.description);
    }

}

