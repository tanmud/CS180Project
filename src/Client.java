/**
 * Client class
 * Takes care of the client side of things
 *
 * @author Natalie Lam, L09
 * @version April 17, 2025
 */

public class Client implements ClientInterface {

    private int portNumber;
    private String hostName;

    @Override
    public int getPortNumber() {

        return this.portNumber;
    }

    @Override
    public String getHostName() {

        return this.hostName;
    }

    @Override
    public void setPortNumber(int portNumber) {

        this.portNumber = portNumber;
    }

    @Override
    public void setHostName(String hostName) {

        this.hostName = hostName;
    }

    public static void main(String[] args) {


    }
}
