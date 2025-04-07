import java.util.List;

/**
 * BankInterface Interface
 * Outline for the Bank Class
 *
 * @author Tanish Mudaliar, L09
 * @version Mar 30, 2025
 */

public interface BankInterface {

    String buy(Items itemToBuy, User seller, int quantity);

    String sell(Items itemToSell, User buyer, int quantity);

    String putItemSale(Items item, int quantity);

    String removeItemSale(Items item, int quantity);

    User getUser();

    void setUser(User user);

    List<Items> getSelling();

    List<Items> getOwned();

    double getBalance();

    void setBalance(double balance);
}