package security;

import java.security.PrivateKey;
import java.security.PublicKey;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.pki.SharkCertificate;
import net.sharkfw.pki.SharkPublicKeyStorage;
import net.sharkfw.pki.SigningPeer;

/**
 *
 * @author thsc
 */
public class KeyStoreExample {
    public static void main(String[] args) throws SharkKBException {
//        FSSharkKB baseKB = new FSSharkKB("testKB");
//        J2SEAndroidSharkEngine se = new J2SEAndroidSharkEngine();
//        SharkPublicKeyStorage ks = new FSSharkKBPublicKeyStorage(baseKB, se);
//        
//        PrivateKey privateKey = null;
//        ks.setPrivateKey(privateKey);
//        PeerSemanticTag peer = null;
//        byte[] signature = null;
//        SigningPeer signingPeer = new SigningPeer(peer, signature);
//        
//        PublicKey publicKey = null;
//        long valid = 1000 * 60 * 24 * 12 * 365; // 365 Tage
//        ks.addPublicKey(publicKey, peer, signingPeer, valid);
//        
//        SharkCertificate certificate = ks.getCertificate(peer);
//        ks.getPrivateKey();
    }
}
