package stSets;

import java.util.Enumeration;
import net.sharkfw.knowledgeBase.SNSemanticTag;
import net.sharkfw.knowledgeBase.SemanticNet;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 * @author Thomas Schwotzer
 */
public class SemanticNetSample {
    public static void main(String args[]) throws SharkKBException {
        SharkKB kb = new InMemoSharkKB();
        
        // handle topics as semantic net
        SemanticNet sn = kb.getTopicsAsSemanticNet();
        
        // describe Germany
        SNSemanticTag germany = sn.createSemanticTag("Germany", "http://en.wikipedia.org/wiki/Germany");
        
        // describe Berlin
        SNSemanticTag berlin = sn.createSemanticTag("Berlin", "http://en.wikipedia.org/wiki/Berlin");
        
        // define a relationname
        String isPartOf = "isPartOf";

        // define berlin to be a part of Germany
        berlin.setPredicate(isPartOf, germany);
        
        /* 
         * what parts of Germany are defined yet?
         */ 
        Enumeration<SNSemanticTag> partsEnum = germany.sourceTags(isPartOf);
        // berlin has relations to what tags?
        Enumeration<SNSemanticTag> targetTags = berlin.targetTags(isPartOf);
        
        System.out.println(L.stSet2String(sn));
        
    }       
}
