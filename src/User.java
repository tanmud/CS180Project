import java.util.ArrayList;

public class User implements UserInterface {


    private String name;
    private String username;
    private String password;
    private Bank bank;
    private ArrayList<Message> chats;

    // Constructor should create name, username, password, set bank with everything 0/null
    //       User.java user = this; in each class
    // ArrayList<Message> chats

    public User(String name, String username, String password, Bank bank) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.bank = bank;
        this.chats = new ArrayList<>();
    }

    @Override
    public String sendMessage(Message message) {

        int i = 0;
        for (int j = 0; j < this.chats.size(); j++) {

            if (this.chats.get(j).getRecipient().equals(message.getRecipient())) {

                i = j;
            }
        }
        this.chats.add(i + 1, message);
        return "Message sent successfully!";
    } // sends message and returns if it was successful or not; adds to chat arraylist
    //    boolean message(User.java user);

    @Override
    public ArrayList<Message> getChats() {
        return this.chats;
    }

    @Override
    public ArrayList<Message> getChat(User recipient) throws NoChatFoundException {

        ArrayList<Message> chat = this.chats;
        for (int j = 0; j < this.chats.size(); j++) {

            if (this.chats.get(j).getRecipient().equals(recipient) &&
                    !this.chats.get(j + 1).getRecipient().equals(recipient)) {

                chat.subList(j + 1, chat.size()).clear();
                break;
            }
        }
        if (chat.equals(this.chats)) {

            throw new NoChatFoundException("You have no chat with this user!");
        }
    } // get message history with specified person; check if it's a chat
    // have an exception with chat doesn't exist

    @Override
    public void deleteAccount() {
        this.name = null;
        this.username = null;
        this.password = null;
        this.bank = null;
        this.chats = null;
    } // set everything to default null values; delete message history, etc.


    // rest of the getter & setters
    @Override
    public String getName() {
        return this.name
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}