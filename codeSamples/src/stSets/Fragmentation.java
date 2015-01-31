package stSets;

import java.util.Vector;
import net.sharkfw.knowledgeBase.FragmentationParameter;
import net.sharkfw.knowledgeBase.SNSemanticTag;
import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticNet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 *
 * @author thsc
 */
public class Fragmentation {
    public static void main(String args[]) throws SharkKBException {
        Fragmentation f = new Fragmentation();
        System.out.println("++++++++++ plain in fragmentation ++++++++++++++++");
        f.plainfragmentation();
        
        System.out.println("++++++++++ taxonomy fragmentation ++++++++++++++++");
        f.taxonomyfragmentation();
        
        System.out.println("++++++++++ semantic net fragmentation ++++++++++++++++");
        f.snfragmentation();
    }
    
    public void taxonomyfragmentation() throws SharkKBException {
        Taxonomy txSource = InMemoSharkKB.createInMemoTaxonomy();
        TXSemanticTag cs = txSource.createTXSemanticTag("CS", "http://www.cs.de");
        TXSemanticTag pl =  txSource.createTXSemanticTag("PL", "http://www.pl.de");
        TXSemanticTag sw = txSource.createTXSemanticTag("SW", "http://www.sw.de");
        TXSemanticTag java = txSource.createTXSemanticTag("Java", "http://www.java.de");
        TXSemanticTag csharp = txSource.createTXSemanticTag("CSharp", "http://www.csharp.de");
        
        // make pl and sw subtags of cs
        pl.move(cs);
        sw.move(cs);
        
        // make java and csharp subtags of pl
        java.move(pl);
        csharp.move(pl);
        
        System.out.println("source:" + L.stSet2String(txSource));
        
        SemanticTag anchor = InMemoSharkKB.createInMemoSemanticTag("plAnchor", "http://www.pl.de");
        System.out.println("anchor:" + L.semanticTag2String(anchor));
        
        boolean superTags = true;
        boolean subTags = true;
        int depth = 1;
        
        FragmentationParameter fp = new FragmentationParameter(superTags, subTags, depth);
        
        STSet fragment = txSource.fragment(anchor, fp);
        System.out.println("fragment:" + L.stSet2String(fragment));
    }
    
    private void printProps(Vector<String> allowedProps) {
        for(int i = 0; i < allowedProps.size(); i++) {
            System.out.print(allowedProps.elementAt(i));
            if(i+1 < allowedProps.size()) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
        }

    }

    public void snfragmentation() throws SharkKBException {
        SemanticNet snSource = InMemoSharkKB.createInMemoSemanticNet();
        SNSemanticTag alisha, ben, carmen, deng, elias;
        
        alisha = snSource.createSemanticTag("Alisha", "http://www.alisha.de");
        ben = snSource.createSemanticTag("Ben", "http://www.ben.de");
        carmen = snSource.createSemanticTag("Carmen", "http://www.carmen.de");
        deng = snSource.createSemanticTag("Deng", "http://www.deng.de");
        elias = snSource.createSemanticTag("Elias", "http://www.elias.de");
        
        // define property names simply as strings
        String motherProp = "mother";
        String sisterProp = "sister";
        String cousinProp = "cousin";
        String fatherProp = "father";
        
        // make relations explicit
        alisha.setPredicate(sisterProp, carmen);
        alisha.setPredicate(motherProp, ben);
        
        carmen.setPredicate(motherProp, deng);
        
        ben.setPredicate(fatherProp, elias);
        ben.setPredicate(cousinProp, deng);
        
        System.out.println("source:" + L.stSet2String(snSource));
        
        SemanticTag anchor = InMemoSharkKB.createInMemoSemanticTag("AlishaAnchor", "http://www.alisha.de");
        System.out.println("anchor:" + L.semanticTag2String(anchor));
        
        // define allowed properties
        Vector<String> allowedProps = new Vector();
        allowedProps.add(motherProp);
        
        FragmentationParameter fp = 
                new FragmentationParameter(
                        allowedProps, // follow those props
                        null, // no forbidden props
                        2); // depth == 2
        
//        FragmentationParameter fp = 
//                new FragmentationParameter(
//                        allowedProps, // follow those props
//                        null, // no forbidden props
//                        2); // depth == 2
        
        SemanticNet fragment = snSource.fragment(anchor, fp);
        System.out.print("allowed props: "); 
        this.printProps(allowedProps);
        System.out.println("after fragmentation:" + L.stSet2String(fragment));
        
        // contextualization
        STSet context = InMemoSharkKB.createInMemoSTSet();
        context.merge(alisha);
        context.merge(carmen);
        
        // switch following line on and off and see what happens in resulting fragment!
        allowedProps.add(cousinProp);
        // do contextualization
        fragment = snSource.contextualize(context, fp);
        System.out.print("allowed props: "); 
        this.printProps(allowedProps);
        System.out.println("after contextualization:" + L.stSet2String(fragment));
        
        Vector<String> forbiddenProps = new Vector();
        forbiddenProps.add(motherProp);
        fp = new FragmentationParameter(
                        null, // follow those props
                        forbiddenProps, // no forbidden props
                        2); // depth == 2
        fragment = snSource.fragment(anchor, fp);
        System.out.print("forbidden props: "); 
        this.printProps(forbiddenProps);
        System.out.println("after fragmentation:" + L.stSet2String(fragment));

    }
    
    public void plainfragmentation() throws SharkKBException {
        STSet source  = InMemoSharkKB.createInMemoSTSet();
        
        source.createSemanticTag("alpha", "http://www.alpha.de");
        source.createSemanticTag("beta", "http://www.beta.de");
        source.createSemanticTag("gamma", "http://www.gamma.de");

        System.out.println("source:" + L.stSet2String(source));
        
        SemanticTag anchor = InMemoSharkKB.createInMemoSemanticTag("alphaAnchor", "http://www.alpha.de");
        
        System.out.println("anchor:" + L.semanticTag2String(anchor));
        STSet fragment = source.fragment(anchor);
        
        System.out.println("fragment:" + L.stSet2String(fragment));
    }
}
