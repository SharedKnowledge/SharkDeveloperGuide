package properties;

import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;

/** 
 * 
 * @author thsc 
 */ 
public class Properties {
    public static void main(String args[]) throws SharkKBException {
        SemanticTag shark = 
                InMemoSharkKB.createInMemoSemanticTag("Shark", "http://www.sharksystem.net/");
        
        shark.setProperty("propName1", "propValue1");
        String value = shark.getProperty("propName1");
        
        shark.setProperty("propName2", "propValue2", false);
    }    
}
