package stSets;

import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 * @author Thomas Schwotzer
 */
public class TaxonomySample {
    public static void main(String args[]) throws SharkKBException {
        SharkKB kb = new InMemoSharkKB();
        
        // we are going to handle topics as taxonomy
        Taxonomy tx = kb.getTopicsAsTaxonomy();
        
        // describe programming languages and java as part of a taxonomy
        TXSemanticTag pl = tx.createTXSemanticTag("PL", "http://en.wikipedia.org/wiki/Programming_language");
        TXSemanticTag java = tx.createTXSemanticTag("Java", "http://en.wikipedia.org/wiki/Java_%28programming_language%29");
        
        // move java "under" pl
        java.move(pl);
        
        // describe C#
        TXSemanticTag csharp = tx.createTXSemanticTag("CSharp", "http://en.wikipedia.org/wiki/C_Sharp_%28programming_language%29");
        
        // define c# as sub topic of programming lanugages
        csharp.move(pl);
        
        System.out.println(L.stSet2String(tx));
    }
}
