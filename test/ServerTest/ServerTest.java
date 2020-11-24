package ServerTest;

import com.githubcolomboDavide7.serverSide.*;
import java.net.*;

import org.junit.*;
import static org.junit.Assert.*;

public class ServerTest {

    private final int remotePort = 49899;

    @Test
    public void shouldCreateServer(){
        System.out.println("* Server test: shouldCreateServer()\n");
        try {
            IServer s = Server.open(remotePort);
            assertTrue(s.matchPortNumber(remotePort));
        }catch(ConnectException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

}
