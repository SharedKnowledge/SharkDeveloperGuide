package semanticTags;

import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TimeSemanticTag;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class TimeSemanticTags {
    
    public static void main(String args[]) throws SharkKBException {
        long now = System.currentTimeMillis();
        
        long dayLength = 24 * 60 * 60 * 1000; 
        
        TimeSemanticTag time = 
                InMemoSharkKB.createInMemoTimeSemanticTag(now, dayLength);
    
        System.out.println(L.semanticTag2String(time));
    }
}
