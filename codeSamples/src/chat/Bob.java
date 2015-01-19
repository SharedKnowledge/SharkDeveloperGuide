package chat;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;
import net.sharkfw.system.SharkException;

/**
 *
 * @author thsc
 */
public class Bob extends ChatPeerTCP {

    public Bob(String peerName, String peerSI, String address, int port) throws SharkProtocolNotSupportedException, IOException {
        super(peerName, peerSI, address, port);
    }
    
    @Override
    public void messageReceived(String message, PeerSemanticTag sender) throws SharkException, IOException {
        System.out.println("Bob has received something:\n" + message);
        System.out.println("\nfrom:\n" + L.semanticTag2String(sender));
        
        // end of communication
        this.stop();
    }    
    
    public static void main(String[] args) throws SharkProtocolNotSupportedException, IOException, SharkException, InterruptedException {
        // settup alice peer
//        L.setLogLevel(L.LOGLEVEL_ALL);
        
        System.out.println("Alice must run first");
        
       Bob bob = new Bob("Bob", 
               "http://www.sharksystem.net/bob.html",
               "tcp://localhost:7071",
               7071
       );
       
       System.out.println("Bob started - is going to send hello to Alice");
       
        PeerSemanticTag alice = InMemoSharkKB.createInMemoPeerSemanticTag("Alice",                
                "http://www.sharksystem.net/alice.html",
                "tcp://localhost:7070");
       
       bob.chatKP.sendMessage("Hi Alice", alice);       
    } 
}
