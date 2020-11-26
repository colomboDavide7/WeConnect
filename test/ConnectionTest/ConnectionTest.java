package ConnectionTest;

import com.githubcolomboDavide7.connection.*;
import java.net.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionTest {

    @Test
    public void shouldTestKnownServersConnectionParameters(){
        System.out.println("* Connection test: shouldTestKnownServersConnectionParameters()\n");

        // APPLICATION SERVER
        KnownServer appServer = KnownServer.get("app_server");
        assertEquals(7000, appServer.portNumber);
        assertEquals("127.0.0.1", appServer.IPAddress);
        // DYNAMIC HOST CONFIGURATION PROTOCOL
        KnownServer dhcp = KnownServer.get("dhcp");
        assertEquals(5000, dhcp.portNumber);
        assertEquals("127.0.0.1", dhcp.IPAddress);
        // PROXY
        KnownServer proxy = KnownServer.get("proxy");
        assertEquals(4000, proxy.portNumber);
        assertEquals("127.0.0.5", proxy.IPAddress);
        // FIREWALL
        KnownServer inferno = KnownServer.get("firewall");
        assertEquals(3000, inferno.portNumber);
        assertEquals("127.0.0.3", inferno.IPAddress);
    }

    @Test
    public void shouldReturnDynamicHostConfigConnection(){
        System.out.println("* Connection test: shouldReturnDynamicHostConfigConnection()\n");
        AbstractServerConnection dhcpConn = null;
        try {
            dhcpConn = ConnectionFactory.getServiceConnection("dhcp");
            assertTrue(dhcpConn.matchPortNumber(KnownServer.DHCP.portNumber));
            assertTrue(dhcpConn.matchMaxSupportedHost(KnownServer.DHCP.maxHost));
            assertTrue(dhcpConn.matchIPAddress(KnownServer.DHCP.IPAddress));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldReturnApplicationServerConnection(){
        System.out.println("* Connection test: shouldReturnApplicationServerConnection()\n");
        try {
            AbstractServerConnection conn = ConnectionFactory.getServiceConnection("app_server");
            assertTrue(conn.matchPortNumber(KnownServer.APP_SERVER.portNumber));
            assertTrue(conn.matchMaxSupportedHost(KnownServer.APP_SERVER.maxHost));
            assertTrue(conn.matchIPAddress(KnownServer.APP_SERVER.IPAddress));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

}
