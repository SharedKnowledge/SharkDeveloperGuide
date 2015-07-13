package chat;

import java.io.IOException;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.peer.SharkEngine;

/**
 *
 * @author thsc
 */
public abstract class ChatPeerTCP implements ChatKPListener {
    private SharkEngine se = null;
    protected final ChatKP chatKP;
    
    // set up new chat partner
    public ChatPeerTCP(String peerName, String peerSI, String address, int port) throws SharkProtocolNotSupportedException, IOException {
        this.se = new J2SEAndroidSharkEngine();
        
        // create PeerSemanticTag describing peer itself
        PeerSemanticTag peer = InMemoSharkKB.createInMemoPeerSemanticTag(peerName, peerSI, address);
        
        // create chatkp
        this.chatKP = new ChatKP(this.se, peer);
        
        // subscribe to news
        this.chatKP.addListener(this);
        
        // start listening at address - can throw exceptions
        this.se.startTCP(port);
    }
    
    public void stop() {
        this.se.stop();
    }
}
