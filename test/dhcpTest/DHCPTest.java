package dhcpTest;

import com.githubcolomboDavide7.appServer.*;
import com.githubcolomboDavide7.client.Client;
import com.githubcolomboDavide7.client.IClient;
import com.githubcolomboDavide7.connection.*;
import java.net.*;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DHCPTest {

    private IDynamicHostConfig dhcp;
    @Before
    public void setupDHCPServer(){
        try{
             this.dhcp = DynamicHostConfig.connect();
             this.dhcp.open();
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void closeDHCPServer(){
        try {
            this.dhcp.close();
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldCreateDHCPServer(){
        System.out.println("* DHCP test: shouldCreateDHCPServer()\n");
        assertTrue(dhcp.matchIPAddress(KnownServer.DHCP.IPAddress));
        assertTrue(dhcp.matchPortNumber(KnownServer.DHCP.portNumber));
    }

    @Test
    public void shouldAssignIPAddressToHost(){
        System.out.println("* DHCP test: shouldCreateDHCPServer()\n");
        try {
            IClient c = Client.connect(KnownServer.DHCP);
            c.open();
            List<String> connections = dhcp.getEstablishedConnections();
            assertEquals(1, connections.size());
            c.close();
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldAcceptMultipleHosts(){
        System.out.println("* DHCP test: shouldAcceptMultipleHosts()\n");
        try {
            IClient c = Client.connect(KnownServer.DHCP);
            c.open();
            IClient c2 = Client.connect(KnownServer.DHCP);
            c2.open();
            List<String> connections = dhcp.getEstablishedConnections();
            assertEquals(2, connections.size());
            c.close();
            c2.close();
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

}
