package ServerTest;

import org.junit.*;
import static org.junit.Assert.*;

public class ServerTest {

    private final int remotePort = 7000;
    private final String workingDir = "/Users/davidecolombo/Desktop/myGitRepo/WeConnect/knownserver/";

    @Test
    public void shouldCreateServer(){
        System.out.println("* Server test: shouldCreateServer()\n");
        assertEquals(1, 2);
    }

}
