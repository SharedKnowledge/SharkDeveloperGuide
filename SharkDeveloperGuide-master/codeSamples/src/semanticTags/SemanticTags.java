package semanticTags;

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
public class SemanticTags {
    public static void main(String args[]) throws SharkKBException {
        String bankEnglSI = "http://en.wikipedia.org/wiki/Bank";

        SemanticTag bankTag1 = 
            InMemoSharkKB.createInMemoSemanticTag("Bank", bankEnglSI);
        
        String bankEOSI = "http://eo.wikipedia.org/wiki/Bank";
        
        String[] bankSIs = new String[] {bankEnglSI, bankEOSI};
        
        SemanticTag bankTag2 = 
            InMemoSharkKB.createInMemoSemanticTag("AnotherName", bankSIs);
        
        if(SharkCSAlgebra.identical(bankTag1, bankTag2)) {
            System.out.println("identical");
        } else {
            System.out.println("not identical");
        }
        
        SemanticTag noSI = 
            InMemoSharkKB.createInMemoSemanticTag("myFirstTag", (String) null);
        
        // try to remove a one of two SIs - that works
        bankTag2.removeSI(bankEOSI);
        
        System.out.println("removed SI from bankTag2: " + 
            L.semanticTag2String(bankTag2));
        
        // try to remove final SI from tag - fails
        try {
            bankTag1.removeSI(bankEnglSI);
        }
        catch(SharkKBException e) {
            System.out.println("failed: " + e.getMessage());
        }
        
        bankTag1.addSI(bankEOSI);
        System.out.println("bankTag1 after adding: " + 
            L.semanticTag2String(bankTag1));
        
        if(SharkCSAlgebra.isAny(noSI)) {
            System.out.println("no SI is any");
        }
        
        SemanticTag anyTag = 
            InMemoSharkKB.createInMemoSemanticTag("anyTag", SharkCS.ANYURL);
        
        if(SharkCSAlgebra.isAny(anyTag)) {
            System.out.println("anyTag is any");
        }
        
        System.out.println("Bank 2 before merging with bank1: " + L.semanticTag2String(bankTag2));
        SharkCSAlgebra.merge(bankTag2, bankTag1);
        System.out.println("after merging: " + L.semanticTag2String(bankTag2));
        
        System.out.println("Any tag before: " + L.semanticTag2String(anyTag));
        SharkCSAlgebra.merge(anyTag, bankTag2);
        System.out.println("any tag after merging: note: the any tag is gone! " + L.semanticTag2String(anyTag));
        
        String sharkSI = "http://www.sharksystem.net";
        SemanticTag shark = 
            InMemoSharkKB.createInMemoSemanticTag("Shark", sharkSI);
        
        SharkCSAlgebra.merge(bankTag2, shark);
        System.out.println("result: " + L.semanticTag2String(bankTag2));
        
    }
}
