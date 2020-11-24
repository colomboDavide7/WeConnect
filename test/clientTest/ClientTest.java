package clientTest;

import com.githubcolomboDavide7.clientSide.Client;
import com.githubcolomboDavide7.clientSide.IClient;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;

public class ClientTest {

    private ServerSocket serverSocket;
    private final int serverPort = 49899;

    @Before
    public void shouldCreateServer(){
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch(IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void shouldCloseServerSocket(){
        try {
            this.serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldCreateClient(){
        System.out.println("* Client test: shouldCreateClient()\n");
        String ipAddress = "127.0.0.1";
        try {
            IClient c = Client.open(ipAddress, this.serverPort);    // open
            assertTrue(c.isBound());
            assertTrue(c.isConnected());
            assertTrue(c.matchIPAddress(ipAddress));
            assertTrue(c.matchPortNumber(this.serverPort));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void shouldCheckKnownHostList(){
        System.out.println("* Client test: shouldCheckKnownHostList()\n");

    }

}
