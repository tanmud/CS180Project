import java.util.List;

public interface BankInterface {
    String buy(User seller, int quantity);
    String sell(User buyer, int quantity);
    String putItemSale(Items item, int quantity);
    String removeItemSale(Items item, int quantity);

    User getUser();
    void setUser(User user);
    List<Items> getSellingItems();
    List<Items> getOwnedItems();
    double getBalance();
    void setBalance(double balance);
}
