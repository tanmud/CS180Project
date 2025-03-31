public interface BankInteface {
    // User user
    // items<> selling
    // Items<> owned
    // double balance
    // Constructor should initialize user, create empty list of items, balance

    String buy(User seller, int quantity);
    //First checks  if item is in list
    // Check if it can buy
    // returns what happened
    // decreases quantity in items by the quantity in the parameter and copies the item to Item<> owned.
    // if quantity of the selling item is 0, remove item from seller

    String sell(User buyer, int quantity);
    // String result = buyer.buy(this, quantity);
    // based on result create an output

    String putItemSale(Items item, int quantity);
    // first check if item exists in owned, if not print "not there"
    // if it's there copy item from owned to selling AND ADD DESCRIPTION

    String removeItemSale(Items item, int quantity);
    // first check if item exists in owned, if not print "not there"
    // if it's there copy item from owned to selling AND REM0VE DESCRIPTION

}
