import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Bank system for a specific User,
 * allowing them to buy/sell items, list items for sale,
 * and manage inventory in a thread-safe environment.
 *
 * @author Alina Liu, L09
 * @version Apr 2, 2025
 */

public class Bank implements BankInterface, Serializable {
    private static Object sellingkeeper = new Object();
    private static Object ownedkeeper = new Object();
    private User user;
    private ArrayList<Items> selling;
    private ArrayList<Items> owned;
    private double balance;

    public Bank() {
        this.user = null;
        this.balance = 0.0;
        this.selling = new ArrayList<>();
        this.owned = new ArrayList<>();
    } // This constructor is only supposed to be used when initializing a User object

    public Bank(User user) {
        this.user = user;
        this.balance = 0.0;
        this.selling = new ArrayList<>();
        owned = new ArrayList<>();

    }

    @Override
    public String buy(Items itemToBuy, User seller, int quantity) {
        Bank sellerBank = seller.getBank();
        synchronized (sellingkeeper) {
            for (int i = 0; i < sellerBank.selling.size(); i++) {
                if (sellerBank.selling.get(i).equals(itemToBuy) && sellerBank.selling.get(i).getQuantity() >= quantity) {
                    // Reduce quantity from seller's item
                    sellerBank.selling.get(i).setQuantity(sellerBank.selling.get(i).getQuantity() - quantity);
                    // copies item
                    Items purchased = new Items(sellerBank.selling.get(i).getName(),
                            sellerBank.selling.get(i).getDescription(), quantity, this.user);
                    // buyer's owned list
                    synchronized (ownedkeeper) {
                        owned.add(purchased);
                    }
                    // removes after quantity reaches zero
                    if (sellerBank.selling.get(i).getQuantity() == 0) {
                        sellerBank.selling.remove(i);
                    }
                    return String.format("%s bought %d of %s from %s",
                            this.user.getName(), quantity,
                            itemToBuy.getName(), seller.getName());
                } else if (sellerBank.selling.get(i).equals(itemToBuy) && sellerBank.selling.get(i).getQuantity() < quantity) {
                    return "Transaction failed: insufficient quantity.";
                }
            }
        }

        return "Transaction failed: item not found";
    }

    @Override
    public String sell(Items itemToSell, User buyer, int quantity) {
        // Delegate logic to buyer's buy() function
        String result = buyer.getBank().buy(itemToSell, this.user, quantity);
        if (result.contains("Transaction")) {
            return result;
        } else {
            return String.format("%s sold %d of %s to %s",
                    this.user.getName(), quantity,
                    itemToSell.getName(), buyer.getName());
        }
    }

    // Puts an owned item up for sale by copying it into the selling list.
    @Override
    public String putItemSale(Items item, int quantity) {
        synchronized (ownedkeeper) {
            for (int i = 0; i < owned.size(); i++) {
                if (owned.get(i).equals(item) && (owned.get(i).getQuantity() >= quantity)) {
                    // Create a copy of the item for the selling list
                    Items itemForSale = new Items(item.getName(), item.getDescription(), quantity, this.user);
                    owned.get(i).setQuantity(owned.get(i).getQuantity() - quantity);
                    synchronized (sellingkeeper) {
                        selling.add(itemForSale);
                    }
                    if (owned.get(i).getQuantity() == 0) {
                        owned.remove(i);
                    }
                    return "Item moved!";
                } else if (owned.get(i).getQuantity() < quantity) {
                    return "Insufficient quantity";
                }
            }
        }
        return "Item not found";
    }

    // Removes a listed item from the selling list by clearing its description.
    @Override
    public String removeItemSale(Items item, int quantity) {
        synchronized (sellingkeeper) {
            for (int i = 0; i < selling.size(); i++) {
                if (selling.get(i).equals(item) && (selling.get(i).getQuantity() >= quantity)) {
                    Items itemForSale = new Items(item.getName(), item.getDescription(), quantity, this.user);
                    selling.get(i).setQuantity(selling.get(i).getQuantity() - quantity);
                    synchronized (ownedkeeper) {
                        owned.add(itemForSale);
                    }
                    if (selling.get(i).getQuantity() == 0) {
                        selling.remove(i);
                    }
                    return "Item moved!";
                } else if (selling.get(i).getQuantity() < quantity) {
                    return "Insufficient quantity";
                }
            }
        }
        return "Item not found";
    }

    //Adds Item to the owned list
    public void addItemToOwned(String name, String description, int quantity) {
        synchronized (ownedkeeper) {
            owned.add(new Items(name, description, quantity, user));
        }
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
