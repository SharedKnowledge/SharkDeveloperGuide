package semanticTags;

import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.SpatialSemanticTag;
import net.sharkfw.knowledgeBase.geom.SharkGeometry;
import net.sharkfw.knowledgeBase.geom.inmemory.InMemoGeometry;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.system.L;

/**
 * Use sharkfw 2.10.1 or higher!
 * @author thsc
 */
public class SpatialSemanticTags {
    public static void main(String args[]) throws SharkKBException {
        String wkt = "";
        SharkGeometry geom = InMemoGeometry.createGeomByWKT(wkt);
        
        SpatialSemanticTag berlinHTW = 
                InMemoSharkKB.createInMemoSpatialSemanticTag(geom);
        
        berlinHTW.getGeometry().getWKT();
    
        System.out.println(L.semanticTag2String(berlinHTW));
    }
}
