package chat;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.Information;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkCSAlgebra;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.peer.KEPConnection;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;
import net.sharkfw.system.SharkException;

/**
 *
 * @author thsc
 */
public class ChatKP extends KnowledgePort {
    public static final String CHAT_TOPIC_SI = "http://www.sharksystem.net/examples/chat/chatinterest.html";
    private PeerSemanticTag remotePeer = null;
    private ChatKPListener listener = null;
    private final PeerSemanticTag owner;

    public ChatKP(SharkEngine se, PeerSemanticTag owner) {
        super(se);
        this.owner = owner;
    }

    /**
     * Probably, a chat entry has been received - handle it
     * @param k
     * @param kepConnection 
     */
    @Override
    protected void doInsert(Knowledge k, KEPConnection kepConnection) {
        Enumeration<ContextPoint> cpEnum = k.contextPoints();
        
        if(cpEnum != null) {
            ContextPoint cp = cpEnum.nextElement();
            
            ContextCoordinates cc = cp.getContextCoordinates();
            
            SemanticTag topic = cc.getTopic();
            if(!SharkCSAlgebra.identical(topic, this.getChatTopic())) {
                // this knowledge is not about chatting
                return;
            }
            
            PeerSemanticTag rPeer = cc.getRemotePeer();
            if(!SharkCSAlgebra.identical(rPeer, this.owner)) {
                // remote peer does not want to talk to me 
                return;
            }
            
            // remember that sender
            PeerSemanticTag peer = cc.getPeer();
            this.remotePeer = peer;
            
            // seems to be a chat message in knowledge - unwrap
            Iterator<Information> infoIter = cp.getInformation();
            if(infoIter != null) {
                String message;
                try {
                    message = infoIter.next().getContentAsString();
                
                    // notify listener about new message
                    this.messageReceived(message, peer);
                } catch (Exception ex) {
                    // something went wrong - should be handled in a final version
                }
            }
        }
    }
    
    // creates an object any time - could be made better...
    private SemanticTag getChatTopic() {
        SemanticTag chatTopic = InMemoSharkKB.createInMemoSemanticTag("ChatTopic", ChatKP.CHAT_TOPIC_SI);
        
        return chatTopic;
    }

    public void addListener(ChatKPListener listener) {
        this.listener = listener;
    }
    
    /**
     * used to send reply to peer that already has sent a message
     * @param message
     * @throws SharkException
     * @throws IOException 
     */
    public void sendMessage(String message) throws SharkException, IOException {
        this.sendMessage(message, this.remotePeer);
    }
    
    /**
     * send a message to remote peer
     * @param message message to be delivered
     * @param recipient
     * @throws net.sharkfw.system.SharkException something with Shark went wrong
     * @throws java.io.IOException connection failed
     */
    public void sendMessage(String message, PeerSemanticTag recipient) throws SharkException, IOException {
        // create context point for new message
        
        // first create coordinates
        SemanticTag chatTopic = this.getChatTopic();
        ContextCoordinates cc = InMemoSharkKB.createInMemoContextCoordinates(
                chatTopic, // its a chat message
                this.owner, // originator is owner of engine
                this.owner, // peer is owner as well
                recipient, // want to talk with connected peer
                null, // time and place is irrelevant
                null, 
                SharkCS.DIRECTION_OUT // we want to send something
        );
        
        // all metadata set - lets create a context point
        ContextPoint cp = InMemoSharkKB.createInMemoContextPoint(cc);
        
        // add message
        cp.addInformation(message);
        
        // create knowledge
        Knowledge k = InMemoSharkKB.createInMemoKnowledge();
        
        // add context point
        k.addContextPoint(cp);
        
        // send to remote peer
        this.sendKnowledge(k, recipient);
    }

    private void messageReceived(String message, PeerSemanticTag sender) throws SharkException, IOException {
        if(this.listener != null) {
            this.listener.messageReceived(message, sender);
        }
    }

    /**
     * does nothing - we handle anything by exchanging knowledge
     * @param interest
     * @param kepConnection 
     */
    @Override
    protected void doExpose(SharkCS interest, KEPConnection kepConnection) {
        //
    }
}
