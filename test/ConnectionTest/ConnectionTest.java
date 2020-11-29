package ConnectionTest;

import com.githubcolomboDavide7.connection.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionTest {

    @Test
    public void shouldTestKnownServersConnectionParameters(){
        System.out.println("* Connection test: shouldTestKnownServersConnectionParameters()\n");

        // APPLICATION SERVER
        KnownServer appServer = KnownServer.get("app_server");
        assertEquals(7000, appServer.portNumber);
        assertEquals("127.0.0.2", appServer.IPAddress);
        // DYNAMIC HOST CONFIGURATION PROTOCOL
        KnownServer dhcp = KnownServer.get("dhcp");
        assertEquals(5000, dhcp.portNumber);
        assertEquals("127.0.0.4", dhcp.IPAddress);
        // PROXY
        KnownServer proxy = KnownServer.get("proxy");
        assertEquals(4000, proxy.portNumber);
        assertEquals("127.0.0.5", proxy.IPAddress);
        // FIREWALL
        KnownServer inferno = KnownServer.get("firewall");
        assertEquals(3000, inferno.portNumber);
        assertEquals("127.0.0.3", inferno.IPAddress);
    }

}
