package kp;

import net.sharkfw.kp.KPListener;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;

/**
 * This class has no other task than to be holder of the main method.
 * @author thsc
 */
public class Main {
    public static void main(String args[]) {
        SharkEngine se = new J2SEAndroidSharkEngine();
        
        KnowledgePort kp = new MyKP(se);
        
        KPListener myListener = new MyKPListener();
        
        kp.addListener(myListener);
        
        
    }
    
}
