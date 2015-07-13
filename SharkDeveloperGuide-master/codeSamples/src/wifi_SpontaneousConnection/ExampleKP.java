package wifi_SpontaneousConnection;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.peer.KEPConnection;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;
import net.sharkfw.system.L;
import net.sharkfw.system.SharkException;

/**
 *
 * @author thsc
 */
public class ExampleKP extends KnowledgePort {
    private final String name;

    public ExampleKP(String peerName, SharkEngine se) {
        super(se);
        this.name = peerName;
    }

    @Override
    protected void doInsert(Knowledge knowledge, KEPConnection kepConnection) {
        System.out.print("Knowledge received: (");
        System.out.println(this.name + "): ");
        System.out.println(L.knowledge2String(knowledge));
    }

    @Override
    protected void doExpose(SharkCS interest, KEPConnection kepConnection) {
        System.out.print("interest received: (");
        System.out.println(this.name + "): ");
        System.out.println(L.contextSpace2String(interest));
        
        try {
            System.out.println(this.name + ": send reply");
            kepConnection.expose(interest);
        } catch (Exception ex) {
            System.err.println("problems:" + ex.getMessage());
        }
    }
}
