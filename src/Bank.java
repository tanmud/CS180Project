import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a Bank system for a specific User,
 * allowing them to buy/sell items, list items for sale,
 * and manage inventory in a thread-safe environment.
 */

public class Bank implements BankInterface {
    private User user;

    private final List<Items> selling = Collections.synchronizedList(new ArrayList<>());

    private final List<Items> owned = Collections.synchronizedList(new ArrayList<>());

    private double balance;

    public Bank() {
        this.user = null;
        this.balance = 0.0;
    }

    public Bank(User user) {
        this.user = user;
        this.balance = 0.0;
    }

    @Override
    public synchronized String buy(User seller, int quantity) {
        Bank sellerBank = seller.getBank();

        synchronized (sellerBank.selling) {
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
                    synchronized (owned) {
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
    public synchronized String sell(User buyer, int quantity) {
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
    public synchronized String putItemSale(Items item, int quantity) {
        synchronized (owned) {
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
        synchronized (selling) {
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
    public List<Items> getSellingItems() {
        return selling;
    }

    @Override
    public List<Items> getOwnedItems() {
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
