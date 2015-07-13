package wifi_SpontaneousConnection;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class Bob {
    
    public static void main(String args[]) throws IOException, InterruptedException, SharkProtocolNotSupportedException {
        L.setLogLevel(L.LOGLEVEL_ALL);
        Peer bob = new Peer("Bob", 7071);

        // wait for alice 2 connect
        Thread.sleep(10000);
//        Thread.sleep(Integer.MAX_VALUE);
        
        System.out.println("Shutting down Bob");
        bob.stop();
    }
}
