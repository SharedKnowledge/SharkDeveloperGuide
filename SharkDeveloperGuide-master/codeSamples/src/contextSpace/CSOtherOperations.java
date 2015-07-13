package contextSpace;

import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.Interest;
import net.sharkfw.knowledgeBase.PeerSTSet;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkCSAlgebra;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class CSOtherOperations {
    
    public static void main(String args[]) throws SharkKBException {
        SemanticTag skiing = InMemoSharkKB.createInMemoSemanticTag("Skiing", "http://www.skiing.de");
        SemanticTag java = InMemoSharkKB.createInMemoSemanticTag("Java", "http://www.java.de");
        
        PeerSemanticTag alice = InMemoSharkKB.createInMemoPeerSemanticTag(
                        "Alice", // name
                        "http://www.sharksystem.net/alice.html", // si
                        "mail://alice@wonderland.net"); // address
        
        PeerSemanticTag bob = InMemoSharkKB.createInMemoPeerSemanticTag(
                "Bob", // name
                "http://www.sharksystem.net/bob.html", // si
                "mail://bob@wonderland.net"); // address
        
        // create interest (context space)
        STSet topics = InMemoSharkKB.createInMemoSTSet();
        topics.merge(skiing);
        topics.merge(java);
        
        PeerSTSet peers = InMemoSharkKB.createInMemoPeerSTSet();
        peers.merge(alice);
        peers.merge(bob);
        
        Interest interest = InMemoSharkKB.createInMemoInterest(topics, null, peers, null, null, null, SharkCS.DIRECTION_INOUT);
        System.out.println("interest:\n " + 
                L.contextSpace2String(interest));
        // create coordinates
        ContextCoordinates cc = InMemoSharkKB.createInMemoContextCoordinates(java, null, alice, null, null, null, SharkCS.DIRECTION_INOUT);
        System.out.println("CC:\n" + L.contextSpace2String(cc));
        
        if(SharkCSAlgebra.isIn(interest, cc)) {
            System.out.println("is in");
        } else {
            System.out.println("is in");
        }
        
        if(!SharkCSAlgebra.identical(interest, cc)) {
            System.out.println("Not identical");
        }
    }
}
