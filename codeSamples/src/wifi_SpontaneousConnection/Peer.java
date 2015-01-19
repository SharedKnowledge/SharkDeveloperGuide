package wifi_SpontaneousConnection;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;
import net.sharkfw.protocols.tcp.TCPConnection;

/**
 *
 * @author thsc
 */
public class Peer {
    private final SharkEngine se;
    private final KnowledgePort kp;
    
    public Peer(String name, int portNumber) throws SharkProtocolNotSupportedException, IOException {
        SharkEngine engine = new J2SEAndroidSharkEngine();
        this.se = engine;
        
        this.kp = new ExampleKP(name, engine);
        
        // start listening on tcp sockets
        engine.startTCP(portNumber);
    }
    
    public void connect(int portNumber) throws IOException {
        TCPConnection tcpConnection = new TCPConnection("localhost", portNumber);
        
        this.se.getKepStub().handleNewConnectionStream(tcpConnection);
    }
    
    public void stop() {
        this.se.stop();
    }
}
