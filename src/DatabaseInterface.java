public interface DatabaseInterface {
    public void startup();
    public String createUser(String name, String username, String password);
    public boolean userExists(String username);
    public ArrayList<Items> itemSearch(String sequence);
    public void end();
}