package wifi_SpontaneousConnection;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class Example {
    
    // die getrennte Variante findet sich in den Classes Alice und Bob
    public static void main(String args[]) throws IOException, InterruptedException, SharkProtocolNotSupportedException {
        L.setLogLevel(L.LOGLEVEL_ALL);
        
        Peer alice = new Peer("Alice", 7070);
        Peer bob = new Peer("Bob", 7071);
        
        // establish connection Alice-Bob
        alice.connect(7071);
        
        Thread.sleep(10000);
        
        alice.stop();
        bob.stop();
    }
}
