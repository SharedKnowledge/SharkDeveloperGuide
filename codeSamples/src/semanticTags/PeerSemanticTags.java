package semanticTags;

import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class PeerSemanticTags {
    public static void main(String args[]) throws SharkKBException {
        String aliceSI = "http://www.sharksystem.net/alice.html";
        
        String aliceMail = "mail://alice@wonderland.net";
        String aliceTCP = "tcp://shark.wonderland.net:7070";

        String[] aliceSIs = new String[] {aliceSI};
        String[] aliceAddr = new String[] {aliceMail, aliceTCP};
        
        PeerSemanticTag alice = 
            InMemoSharkKB.createInMemoPeerSemanticTag("Alice", aliceSIs, aliceAddr);
    
        System.out.println(L.semanticTag2String(alice));
    }
}
