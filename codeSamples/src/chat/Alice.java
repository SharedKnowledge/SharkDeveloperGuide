package chat;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.system.L;
import net.sharkfw.system.SharkException;

/**
 *
 * @author thsc
 */
public class Alice extends ChatPeerTCP {

    public Alice(String peerName, String peerSI, String address, int port) throws SharkProtocolNotSupportedException, IOException {
        super(peerName, peerSI, address, port);
    }
    
    @Override
    public void messageReceived(String message, PeerSemanticTag sender) throws SharkException, IOException {
        System.out.println("Alice has got that message:\n" + message);
        System.out.println("\nfrom:\n" + L.semanticTag2String(sender));
        
        System.out.println("Alice is going to reply with \"Hi Bob\"\n");
        
        this.chatKP.sendMessage("Hi Bob");

        // end of communication
        this.stop();
    }    
    
    public static void main(String[] args) throws SharkProtocolNotSupportedException, IOException {
        // settup alice peer
//        L.setLogLevel(L.LOGLEVEL_ALL);
        
       Alice alice = new Alice("Alice", 
               "http://www.sharksystem.net/alice.html",
               "tcp://localhost:7070",
               7070
       );
       
       System.out.println("Alice is running - start Bob now");
    }
}
