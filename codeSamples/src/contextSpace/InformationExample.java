package contextSpace;

import java.util.Enumeration;
import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.Information;
import net.sharkfw.knowledgeBase.Interest;
import net.sharkfw.knowledgeBase.PeerSTSet;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class InformationExample {
    public static void main(String args[]) throws SharkKBException {
        SemanticTag shark = InMemoSharkKB.createInMemoSemanticTag("Shark", "http://www.sharksystem.net");
        
        PeerSemanticTag alice =  
                InMemoSharkKB.createInMemoPeerSemanticTag(
                        "Alice", // name
                        "http://www.sharksystem.net/alice.html", // si
                        "mail://alice@wonderland.net"); // address
        ContextCoordinates cc = InMemoSharkKB.createInMemoContextCoordinates(
                shark, // topic
                alice, // originator
                alice, // peer
                null, // remote peer
                null, // time
                null, // location
                SharkCS.DIRECTION_OUT); // direction

        System.out.println("Coordinates: \n" + L.contextSpace2String(cc));
        
        ContextPoint cp = InMemoSharkKB.createInMemoContextPoint(cc);
        Information info = cp.addInformation("Hello Shark");
        
        InMemoSharkKB kb = new InMemoSharkKB();
        
        ContextPoint kbCP = kb.createContextPoint(cc);
        Information kbInfo = kbCP.addInformation(info.getContentAsString());
        
        ContextPoint kbCP2 = kb.getContextPoint(cc);
        
        // create parts of interest
        STSet intTopics = InMemoSharkKB.createInMemoSTSet();
        PeerSTSet intPeers = InMemoSharkKB.createInMemoPeerSTSet();
        
        // add copy of shark tag
        intTopics.merge(shark);
        // add diving tag
        intTopics.createSemanticTag("Diving", "http://www.diving.de");
        
        intPeers.merge(alice);
        
        // create interest
        Interest interest = InMemoSharkKB.createInMemoInterest(intTopics, null, intPeers, null, null, null, SharkCS.DIRECTION_OUT);
        
        // use it to retrieve information from kb
        Enumeration<ContextPoint> cpEnum = kb.getContextPoints(interest);
        if(cpEnum != null) {
            System.out.println("found information");
            System.out.println(L.knowledge2String(cpEnum));
        } else {
            System.out.println("no information found");
        }
        
        cpEnum = kb.getContextPoints(interest, false);
        if(cpEnum != null) {
            System.out.println("found information");
            System.out.println(L.knowledge2String(cpEnum));
        } else {
            System.out.println("no information found");
        }
        
    }
}
