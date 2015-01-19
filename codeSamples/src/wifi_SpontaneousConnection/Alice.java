package wifi_SpontaneousConnection;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class Alice {
    public static void main(String args[]) throws IOException, InterruptedException, SharkProtocolNotSupportedException {
        System.err.println("Run Bob first!!");
        Peer alice = new Peer("Alice", 7070);
        
        // establish connection Alice-Bob
        alice.connect(7071);
        
        Thread.sleep(10000);
//        Thread.sleep(Integer.MAX_VALUE);
        
        alice.stop();
        
        L.setLogLevel(L.LOGLEVEL_ALL);
    }
    
}
