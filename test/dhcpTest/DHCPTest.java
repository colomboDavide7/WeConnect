package dhcpTest;

import com.githubcolomboDavide7.appServer.*;
import com.githubcolomboDavide7.connection.*;
import java.net.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class DHCPTest {

    @Test
    public void shouldCreateDHCPServer(){
        System.out.println("* DHCP test: shouldCreateDHCPServer()\n");
        try {
            IDynamicHostConfig dhcp = DynamicHostConfig.connect();
            assertTrue(dhcp.matchIPAddress(KnownServer.DHCP.IPAddress));
            assertTrue(dhcp.matchPortNumber(KnownServer.DHCP.portNumber));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    

}
