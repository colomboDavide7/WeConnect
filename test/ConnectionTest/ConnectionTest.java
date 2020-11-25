package ConnectionTest;

import com.githubcolomboDavide7.connection.AbstractServerConnection;
import com.githubcolomboDavide7.connection.ConnectionFactory;
import org.junit.Test;

import java.net.ConnectException;

import static org.junit.Assert.*;

public class ConnectionTest {

    private final int DHCP_PORT_NUMBER = 5000;
    private final int MAX_HOST = 3;
    private final String DHCP_IP_ADDRESS = "127.0.0.4";
    private final int APP_PORT_NUM = 7000;
    private final int APP_MAX_HOST_SUPPORTED = 3;
    private final String APP_IP_ADDRESS = "127.0.0.1";

    @Test
    public void shouldReturnDynamicHostConfigConnection(){
        System.out.println("* Connection test: shouldReturnDynamicHostConfigConnection()\n");
        AbstractServerConnection dhcpConn = null;
        try {
            dhcpConn = ConnectionFactory.getDHCPConnection();
            assertTrue(dhcpConn.matchPortNumber(this.DHCP_PORT_NUMBER));
            assertTrue(dhcpConn.matchMaxSupportedHost(this.MAX_HOST));
            assertTrue(dhcpConn.matchIPAddress(this.DHCP_IP_ADDRESS));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void shouldReturnApplicationServerConnection(){
        System.out.println("* Connection test: shouldReturnApplicationServerConnection()\n");
        try {
            AbstractServerConnection conn = ConnectionFactory.getApplicationServerConnection();
            assertTrue(conn.matchPortNumber(this.APP_PORT_NUM));
            assertTrue(conn.matchMaxSupportedHost(this.APP_MAX_HOST_SUPPORTED));
            assertTrue(conn.matchIPAddress(this.APP_IP_ADDRESS));
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

}
