package stSets;

import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCSAlgebra;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;

/**
 *
 * @author thsc
 */
public class PlainTagSets {
    public static void main(String args[]) throws SharkKBException {
        STSet plainSet  = InMemoSharkKB.createInMemoSTSet();
        
        SemanticTag berlin = plainSet.createSemanticTag("Berlin", "http://www.berlin.de");
        
        STSet plainSet2  = InMemoSharkKB.createInMemoSTSet();
        
        plainSet.removeSemanticTag(berlin);
        SemanticTag paris = InMemoSharkKB.createInMemoSemanticTag("Paris", "http://www.paris.fr");
        SemanticTag paris2 = plainSet.merge(paris);
        
        InMemoSharkKB kb = new InMemoSharkKB();
        STSet topicSTSet = kb.getTopicSTSet();
        
        topicSTSet.merge(plainSet);
        
        plainSet.getSemanticTag(paris.getSI());
        
        SharkCSAlgebra.isAny(plainSet);
        
        SharkCSAlgebra.identical(plainSet, plainSet2);
        
        plainSet.merge(paris);
        plainSet.merge(plainSet2);
        
    }
}
