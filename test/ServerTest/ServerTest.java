package ServerTest;

import com.githubcolomboDavide7.client.Client;
import com.githubcolomboDavide7.client.IClient;
import com.githubcolomboDavide7.connection.ConnectionInfo;
import com.githubcolomboDavide7.connection.KnownServer;
import com.githubcolomboDavide7.servers.*;
import java.net.*;
import java.util.*;

import com.githubcolomboDavide7.tools.AbstractFormatter;
import org.junit.*;
import static org.junit.Assert.*;

public class ServerTest {

    private final int remotePort = 7000;
    private final String workingDir = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/knownserver/";

    @Test
    public void shouldAcceptClient(){
        System.out.println("* Server test: shouldAcceptClient()\n");
        try {
            IApplicationServer server = ApplicationServer.getApplicationServer();
            server.open();  // Open the server

            IClient c = new Client();
            c.open(KnownServer.APP_SERVER);     // open the client

            List<String> connections = server.getEstablishedConnections();
            assertEquals(1, connections.size());
            Map<ConnectionInfo, String> info = AbstractFormatter.parseRecord(connections.get(0));
            assertEquals(KnownServer.APP_SERVER.IPAddress, info.get(ConnectionInfo.IP_ADDRESS));
            assertEquals(KnownServer.APP_SERVER.portNumber, Integer.parseInt(info.get(ConnectionInfo.PORT_NUMBER)));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

}
