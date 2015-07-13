package kp;

import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.kp.KPListener;
import net.sharkfw.peer.KnowledgePort;

/**
 *
 * @author thsc
 */
public class MyKPListener implements KPListener {

    @Override
    public void exposeSent(KnowledgePort kp, SharkCS sentMutualInterest) {
        System.out.println("expose received");
    }

    @Override
    public void insertSent(KnowledgePort kp, Knowledge sentKnowledge) {
        System.out.println("insert received");
    }

    @Override
    public void knowledgeAssimilated(KnowledgePort kp, ContextPoint newCP) {
        System.out.println("KP has assimilated something");
    }
}
