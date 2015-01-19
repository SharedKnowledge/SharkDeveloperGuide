package kp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sharkfw.knowledgeBase.Interest;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.peer.KEPConnection;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;
import net.sharkfw.system.SharkException;

/**
 *
 * @author thsc
 */
public class MyKP extends KnowledgePort {

    public MyKP(SharkEngine se) {
        super(se);
    }

    @Override
    protected void doInsert(Knowledge knowledge, KEPConnection kepConnection) {
        // do something useful here - or not
    }

    @Override
    protected void doExpose(SharkCS interest, KEPConnection kepConnection) {
        // do something useful here - or not
        if( kepConnection.receivedMessageEncrypted() 
                &&
            kepConnection.receivedMessageSigned()) {
            // message was signed and encrypted
        }
        
        Interest i = InMemoSharkKB.createInMemoInterest();
        String address = "mail://alice@wonderland.gov";
        try {
            kepConnection.expose(i);
            kepConnection.expose(interest, address);
            PeerSemanticTag sender = kepConnection.getSender();
            
            Knowledge k = InMemoSharkKB.createInMemoKnowledge();
            this.sendKnowledge(k, sender);
            this.sendInterest(interest, sender);
            
        } catch (SharkException ex) {
            // do something
        } catch (IOException ex) {
            Logger.getLogger(MyKP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
