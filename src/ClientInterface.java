/**
 * ClientInterface class
 * Outlines the client side
 *
 * @author Natalie Lam, L09
 * @version April 17, 2025
 */

public interface ClientInterface {

    // private int portNumber;
    // private String hostName;

    int getPortNumber();
    String getHostName();
    void setPortNumber(int portNumber);
    void setHostName(String hostName);
}