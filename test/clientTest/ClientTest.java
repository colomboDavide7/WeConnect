package clientTest;

import java.net.*;
import com.githubcolomboDavide7.clientSide.*;
import com.githubcolomboDavide7.serverSide.*;

import com.githubcolomboDavide7.tools.AbstractTool;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

    private IServer myServer;
    private final int remotePort = 49899;
    private final String workingDir = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/knownhost/";

    @Before
    public void shouldCreateServer(){
        try {
            this.myServer = Server.open(remotePort);
            this.myServer.printHostName();
        } catch(ConnectException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @After
    public void shouldCloseServerSocket(){
        try {
            this.myServer.close();
        } catch(ConnectException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void shouldCreateClient(){
        System.out.println("* Client test: shouldCreateClient()\n");
        String ipAddress = "127.0.0.1";
        try {
            IClient c = Client.open(ipAddress, this.remotePort);    // open
            assertTrue(c.isBound());
            assertTrue(c.isConnected());
            assertTrue(c.matchIPAddress(ipAddress));
            assertTrue(c.matchPortNumber(this.remotePort));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void shouldTestKnownHostFileExistence(){
        System.out.println("* Client test: shouldTestKnownHostFileExistence()\n");
        assertFalse(AbstractTool.existFile(this.myServer.appendHostNameToPath(this.workingDir) + ".txt"));
    }

    @Test
    public void shouldCreateKnownHostFileAtFirstConnection(){
        System.out.println("* Client test: shouldCreateKnownHostFileAtFirstConnection()\n");
        String ipAddress = "127.0.0.1";
        try {
            IClient c = Client.open(ipAddress, this.remotePort);    // open
            assertTrue(AbstractTool.existFile(c.appendHostNameToPath(this.workingDir) + ".txt"));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
