import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a Bank system for a specific User,
 * allowing them to buy/sell items, list items for sale,
 * and manage inventory in a thread-safe environment.
 */

public class Bank implements BankInterface, Serializable {
    private User user;
    private ArrayList<Items> selling;
    private ArrayList<Items> owned;
    private double balance;
    private static Object sellingkeeper = new Object();
    private static Object ownedkeeper = new Object();

    public Bank() {
        this.user = null;
        this.balance = 0.0;
        this.selling = new ArrayList<>();
        this.owned = new ArrayList<>();

    }

    public Bank(User user) {
        this.user = user;
        this.balance = 0.0;
        this.selling = new ArrayList<>();
        owned = new ArrayList<>();

    }

    @Override
    public String buy(User seller, int quantity) {
        Bank sellerBank = seller.getBank();

        synchronized (sellingkeeper) {
            for (Items item : sellerBank.selling) {
                if (item.getQuantity() >= quantity) {
                    // Reduce quantity from seller's item
                    item.setQuantity(item.getQuantity() - quantity);

                    // copies item
                    Items purchased = new Items();
                    purchased.setName(item.getName());
                    purchased.setDescription(item.getDescription());
                    purchased.setQuantity(quantity);
                    purchased.setUser(this.user);

                    // buyer's owned list
                    synchronized (ownedkeeper) {
                        owned.add(purchased);
                    }

                    // removes after quantity reaches zero
                    if (item.getQuantity() == 0) {
                        sellerBank.selling.remove(item);
                    }

                    return "Item bought successfully.";
                }
            }
        }

        return "Purchase failed: item not found or insufficient quantity.";
    }

    @Override
    public String sell(User buyer, int quantity) {
        // Delegate logic to buyer's buy() function
        String result = buyer.getBank().buy(this.user, quantity);
        return "Attempted to sell: " + result;
    }

    /**
     * Puts an owned item up for sale by copying it into the selling list.
     * @param item The item to list for sale.
     * @param quantity The quantity to list.
     * @return Message indicating success or failure.
     */
    @Override
    public String putItemSale(Items item, int quantity) {
        synchronized (ownedkeeper) {
            for (Items ownedItem : owned) {
                if (ownedItem.getName().equals(item.getName()) && ownedItem.getQuantity() >= quantity) {
                    // Create a copy of the item for the selling list
                    Items itemForSale = new Items();
                    itemForSale.setName(item.getName());
                    itemForSale.setQuantity(quantity);
                    itemForSale.setDescription("For Sale");
                    itemForSale.setUser(this.user);

                    selling.add(itemForSale);
                    return "Item listed for sale.";
                }
            }
        }
        return "Item not available in owned items.";
    }

    /**
     * Removes a listed item from the selling list by clearing its description.
     * @param item The item to remove from sale.
     * @param quantity The quantity to remove.
     * @return Message indicating success or failure.
     */
    @Override
    public synchronized String removeItemSale(Items item, int quantity) {
        synchronized (sellingkeeper) {
            for (Items sellingItem : selling) {
                if (sellingItem.getName().equals(item.getName()) && sellingItem.getQuantity() >= quantity) {
                    sellingItem.setDescription(""); // Clear sale label
                    return "Item removed from sale.";
                }
            }
        }
        return "Item not found in selling list.";
    }

    // getters and setters

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public List<Items> getSelling() {
        return selling;
    }

    @Override
    public List<Items> getOwned() {
        return owned;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
