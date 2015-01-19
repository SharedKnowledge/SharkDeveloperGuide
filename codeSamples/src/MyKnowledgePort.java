
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.peer.KEPConnection;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thsc
 */
public class MyKnowledgePort extends KnowledgePort {

    public MyKnowledgePort(SharkEngine se) {
        super(se);
    }

    @Override
    protected void doInsert(Knowledge knowledge, KEPConnection kepConnection) {
        //
    }

    @Override
    protected void doExpose(SharkCS interest, KEPConnection kepConnection) {
        //
    }
    
}
