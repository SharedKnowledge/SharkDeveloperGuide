package contextSpace;

import net.sharkfw.knowledgeBase.FragmentationParameter;
import net.sharkfw.knowledgeBase.Interest;
import net.sharkfw.knowledgeBase.PeerSTSet;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkCSAlgebra;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class ContextualizationExample {
    public static void main(String args[]) throws SharkKBException {
        // create alice interest
        STSet aliceTopics = InMemoSharkKB.createInMemoSTSet();
        aliceTopics.createSemanticTag("Shark", "http://www.sharksystem.net");
        
        PeerSemanticTag alice = InMemoSharkKB.createInMemoPeerSemanticTag(
                        "Alice", // name
                        "http://www.sharksystem.net/alice.html", // si
                        "mail://alice@wonderland.net"); // address
        
        PeerSTSet alicePeers = InMemoSharkKB.createInMemoPeerSTSet();
        alicePeers.merge(alice);
        
        Interest aliceInterest = InMemoSharkKB.createInMemoInterest(aliceTopics, alice, alicePeers, null, null, null, SharkCS.DIRECTION_OUT);
        System.out.println("Alice interest:\n " + 
                L.contextSpace2String(aliceInterest));
        
        // create bob interest
        PeerSTSet bobPeers = InMemoSharkKB.createInMemoPeerSTSet();
        bobPeers.createPeerSemanticTag("Bob", // name
                        "http://www.sharksystem.net/bob.html", // si
                        "mail://bob@wonderland.net"); // address
        
        PeerSTSet bobRemotePeers = InMemoSharkKB.createInMemoPeerSTSet();
        bobRemotePeers.merge(alice);
        
        Interest bobInterest = InMemoSharkKB.createInMemoInterest(null, null, bobPeers, bobRemotePeers, null, null, SharkCS.DIRECTION_INOUT);
        System.out.println("Bob interest:\n " + 
                L.contextSpace2String(bobInterest));
        
        FragmentationParameter fp = FragmentationParameter.getZeroFP();
        FragmentationParameter[] fps = new FragmentationParameter[SharkCS.MAXDIMENSIONS];
        for(int d = 0; d < SharkCS.MAXDIMENSIONS; d++) {
            fps[d] = fp;
        }
        
        Interest mutualInterest = SharkCSAlgebra.contextualize(bobInterest, aliceInterest, fps);
        System.out.println("Alice calculates: Bob / Alice:\n " + 
                L.contextSpace2String(mutualInterest));
        
        mutualInterest = SharkCSAlgebra.contextualize(aliceInterest, bobInterest, fps);
        System.out.println("Bob calculates: Alice / Bob:\n " + 
                L.contextSpace2String(mutualInterest));
    }
}
