package clientTest;

import java.net.*;
import java.util.*;

import com.githubcolomboDavide7.client.*;
import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.appServer.IApplicationServer;
import com.githubcolomboDavide7.appServer.ApplicationServer;
import com.githubcolomboDavide7.tools.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

    private IApplicationServer myServer;
    private final int remotePort = 7000;
    private final String workingDir = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/knownhost/";

    @Before
    public void shouldCreateServer(){
        try {
            this.myServer = ApplicationServer.getApplicationServer();
        } catch(ConnectException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void shouldCreateClient(){
        System.out.println("* Client test: shouldCreateClient()\n");
        String ipAddress = "127.0.0.2";
        try {
            IClient c = Client.connect(ipAddress, this.remotePort);    // open
            assertTrue(c.matchIPAddress(ipAddress));
            assertTrue(c.matchPortNumber(this.remotePort));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void shouldFormatClientConnectionInfo(){
        System.out.println("* Client test: shouldFormatClientConnectionInfo()\n");
        String ipAddress = "127.0.0.2";
        try {
            IClient c = Client.connect(ipAddress, this.remotePort);    // open
            String expected = "IP_ADDRESS=127.0.0.2,PORT_NUMBER=7000";
            Map<ConnectionInfo, String> info = new HashMap<>();
            info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(this.remotePort));
            info.put(ConnectionInfo.IP_ADDRESS, ipAddress);
            assertEquals(expected, AbstractFormatter.formatConnectionInfo(info));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void shouldWriteClientConnectionInfoToFile(){
        System.out.println("* Client test: shouldWriteClientConnectionInfoToFile()\n");
        String ipAddress = "127.0.0.2";
        try {
            IClient c = Client.connect(ipAddress, this.remotePort);
            c.open();                                               // open
            String expected = "IP_ADDRESS=127.0.0.2,PORT_NUMBER=7000";
            List<String> lines = c.getEstablishedConnections();
            assertEquals(1, lines.size());
            assertEquals(expected, lines.get(0));
            c.close();                                              // close
        } catch(ConnectException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
