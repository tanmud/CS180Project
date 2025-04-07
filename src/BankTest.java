import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BankTest class
 * Test cases for the Bank class
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 2, 2025
 */

public class BankTest {
    private User user;
    private Bank bank;
    private User otherUser;
    private Items testItem;

    @BeforeEach
    void setUp() {
        user = new User("Test User", "testuser", "password123", new Bank());
        otherUser = new User("Other User", "otheruser", "password456", new Bank());
        bank = new Bank(user);
        user.setBank(bank);
        testItem = new Items("Laptop", "Gaming laptop", 3, user);
    }

    @Test
    @DisplayName("Successful item purchase")
    void successfulBuy() {
        // Setup seller's inventory
        Bank sellerBank = new Bank(otherUser);
        otherUser.setBank(sellerBank);
        Items sellerItem = new Items("Laptop", "Gaming laptop", 5, otherUser);
        sellerBank.getSelling().add(sellerItem);

        // Execute purchase
        String result = bank.buy(sellerItem, otherUser, 3);

        // Verify results
        assertEquals(2, sellerBank.getSelling().get(0).getQuantity());
        assertEquals(3, bank.getOwned().get(0).getQuantity());
        assertEquals("Test User bought 3 of Laptop from Other User", result);
    }

    @Test
    @DisplayName("Insufficient quantity for purchase")
    void insufficientQuantityBuy() {
        Bank sellerBank = new Bank(otherUser);
        otherUser.setBank(sellerBank);
        Items sellerItem = new Items("Laptop", "Gaming laptop", 2, otherUser);
        sellerBank.getSelling().add(sellerItem);

        String result = bank.buy(sellerItem, otherUser, 3);
        assertEquals("Transaction failed: insufficient quantity.", result);
    }

    @Test
    @DisplayName("Successful item sale")
    void successfulSell() {
        Bank bank = new Bank(otherUser);
        otherUser.setBank(bank);
        bank.getSelling().add(testItem);
        String result = bank.sell(testItem, otherUser, 2);
        assertTrue(result.contains("sold"));
    }

    @Test
    @DisplayName("Successful item listing for sale")
    void successfulPutItemSale() {
        bank.getOwned().add(new Items("Laptop", "Gaming laptop", 5, user));
        String result = bank.putItemSale(testItem, 3);

        assertEquals(2, bank.getOwned().get(0).getQuantity());
        assertEquals(3, bank.getSelling().get(0).getQuantity());
        assertEquals("Item moved!", result);
    }

    @Test
    @DisplayName("Remove item from sale")
    void successfulRemoveItemSale() {
        bank.getSelling().add(new Items("Laptop", "Gaming laptop", 5, user));
        String result = bank.removeItemSale(testItem, 3);

        assertEquals(2, bank.getSelling().get(0).getQuantity());
        assertEquals(3, bank.getOwned().get(0).getQuantity());
        assertEquals("Item moved!", result);
    }

    @Test
    @DisplayName("Full quantity removal from sale")
    void fullRemoveItemSale() {
        bank.getSelling().add(new Items("Laptop", "Gaming laptop", 5, user));
        String result = bank.removeItemSale(testItem, 5);

        assertTrue(bank.getSelling().isEmpty());
        assertEquals(5, bank.getOwned().get(0).getQuantity());
    }

    @Test
    @DisplayName("Add item to owned list")
    void addItemToOwned() {
        // Execute
        bank.addItemToOwned("Laptop", "Gaming laptop", 5);

        // Verify
        assertEquals(1, bank.getOwned().size(), "Should have one item in owned list");

        Items addedItem = bank.getOwned().get(0);
        assertAll("Item properties",
                () -> assertEquals("Laptop", addedItem.getName(), "Item name mismatch"),
                () -> assertEquals("Gaming laptop", addedItem.getDescription(), "Description mismatch"),
                () -> assertEquals(5, addedItem.getQuantity(), "Quantity mismatch"),
                () -> assertEquals(user, addedItem.getUser(), "User ownership mismatch")
        );
    }

    @Test
    @DisplayName("Balance getter/setter")
    void balanceManagement() {
        bank.setBalance(100.50);
        assertEquals(100.50, bank.getBalance(), 0.001);
    }

    @Test
    @DisplayName("User assignment")
    void userAssignment() {
        assertEquals(user, bank.getUser());
        bank.setUser(otherUser);
        assertEquals(otherUser, bank.getUser());
    }

    @Test
    @DisplayName("Item not found in sale removal")
    void itemNotFoundInRemoval() {
        String result = bank.removeItemSale(testItem, 1);
        assertEquals("Item not found", result);
    }

    @Test
    @DisplayName("Concurrent modification protection")
    void threadSafetyCheck() {
        assertDoesNotThrow(() -> {
            // Run seperate threads adding two items simultaneously
            new Thread(() -> {
                bank.getSelling().add(testItem);
            }).start();

            new Thread(() -> {
                bank.removeItemSale(testItem, 1);
            }).start();
        });
        assertTrue(bank.getSelling().contains(testItem));
    }
}
