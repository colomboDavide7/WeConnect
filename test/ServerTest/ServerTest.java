package ServerTest;

import com.githubcolomboDavide7.client.Client;
import com.githubcolomboDavide7.client.IClient;
import com.githubcolomboDavide7.connection.*;

import java.net.*;
import java.util.*;

import com.githubcolomboDavide7.appServer.IApplicationServer;
import com.githubcolomboDavide7.appServer.ApplicationServer;
import com.githubcolomboDavide7.tools.AbstractFormatter;
import org.junit.*;
import static org.junit.Assert.*;

public class ServerTest {

    private final int remotePort = 7000;
    private final String workingDir = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/knownserver/";

    @Test
    public void shouldCreateServer(){
        System.out.println("* Server test: shouldCreateServer()\n");
        try {
            IApplicationServer s = ApplicationServer.getApplicationServer();
            assertTrue(s.matchPortNumber(remotePort));
            s.close();
        }catch(ConnectException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void shouldFormatServerConnectionInfo(){
        System.out.println("* Server test: shouldFormatServerConnectionInfo()\n");
        String expected = "IP_ADDRESS=127.0.0.1,PORT_NUMBER=7000";
        Map<ConnectionInfo, String> info = new HashMap<>();
        info.put(ConnectionInfo.PORT_NUMBER, Integer.toString(this.remotePort));
        info.put(ConnectionInfo.IP_ADDRESS, "127.0.0.1");
        assertEquals(expected, AbstractFormatter.formatConnectionInfo(info));
    }

    @Test
    public void shouldCreateServerConnectionFile(){
        System.out.println("* Server test: shouldCreateServerConnectionFile()\n");
        try {
            IApplicationServer s = ApplicationServer.getApplicationServer();
            s.open();                                                           // open
            System.out.println("Opened");
            String expected = "IP_ADDRESS=127.0.0.2,PORT_NUMBER=7000";
            IClient c = Client.create("127.0.0.2", this.remotePort);
            List<String> serverConnections = s.getEstablishedConnections();
            assertEquals(expected, serverConnections.get(0));
            s.close();                                                          // close
        }catch(ConnectException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    public void shouldAcceptMultipleHost(){
        System.out.println("* Server test: shouldAcceptMultipleHost()\n");
        try {
            IApplicationServer s = ApplicationServer.getApplicationServer();
            s.open();                                                           // open the server
            System.out.println("Opened");
            String expected = "IP_ADDRESS=127.0.0.2,PORT_NUMBER=7000";
            IClient c = Client.create("127.0.0.2", this.remotePort);
            c.open();
            IClient c1 = Client.create("127.0.0.2", this.remotePort);
            c1.open();
            List<String> serverConnections = s.getEstablishedConnections();
            assertEquals(2, serverConnections.size());
            assertEquals(expected, serverConnections.get(0));
            assertEquals(expected, serverConnections.get(1));
            s.close();                                                          // close the server
        }catch(ConnectException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

}
