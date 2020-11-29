package clientTest;

import java.net.*;
import com.githubcolomboDavide7.client.*;
import com.githubcolomboDavide7.connection.*;
import com.githubcolomboDavide7.servers.*;

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
    public void shouldCreateClientAndOpenConnection(){
        System.out.println("* Client test: shouldCreateClientAndOpenConnection()\n");
        try {
            IClient c = new Client();
            c.open(KnownServer.APP_SERVER);
        } catch(ConnectException e) {
            e.printStackTrace();
            fail();
        }
    }

}
