package basicScenario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.sharkfw.kep.SharkProtocolNotSupportedException;
import net.sharkfw.knowledgeBase.*;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
import net.sharkfw.kp.KPListener;
import net.sharkfw.peer.J2SEAndroidSharkEngine;
import net.sharkfw.peer.KnowledgePort;
import net.sharkfw.peer.SharkEngine;
import net.sharkfw.peer.StandardKP;
import net.sharkfw.system.L;
import net.sharkfw.system.SharkSecurityException;

/**
 * This is the example code taken from the programmers handbook.
 *
 * Alice describes a subject called "Java" by creating a tag.
 * Alice then describes herself by creating a tag for herself.
 * Next Alice defines a context, in which she puts an information, thereby creating a ContextPoint.
 * To be able to exchange information with other peer, Alice now creates an interest,
 * describing the context in which she had put the information.
 *
 * To make the interest usable, Alice creates a new KnowledgePort for handling the interest.
 * To make the KnowledgePort reachable from the outside, Alice starts TCP-Networking next.
 * The interest and her information are now passively available.
 *
 * Next Alice describes another peer called "bob" living on the same machine (see Bob.java).
 * Alice publishes her interest in java to bob, to see if he is interested.
 * 
 * @author mfi
 */

public class Alice implements KPListener {
    public static SharkEngine aliceSE;
    
  public static void main(String[] args) throws SharkKBException, IOException, SharkSecurityException, SharkProtocolNotSupportedException {
    // Create a new SharkEngine
    aliceSE = new J2SEAndroidSharkEngine();

    // Create a new knowledgebase for this peer
    SharkKB aliceKB = new InMemoSharkKB();

    // Create a peer to describe the topic "Shark"
    SemanticTag shark = aliceKB.createSemanticTag("Shark", "http://www.sharksystem.net/");

    // Create a peer to describe ourselves
    String[] aliceAddr = new String[1];
    aliceAddr[0] = "tcp://localhost:7070";

    PeerSemanticTag alice = aliceKB.createPeerSemanticTag("Alice", "http://www.sharksystem.net/alice.html", aliceAddr);

     aliceKB.setOwner(alice);

    // Create new coordinates before creating a ContextPoint
    ContextCoordinates cc = aliceKB.createContextCoordinates(shark, /*originator*/ alice, /* peer */ alice, /*remote peer*/ null, /*time*/ null, /*place*/ null, SharkCS.DIRECTION_OUT);

    // Create a ContextPoint to add information to
    ContextPoint cp = aliceKB.createContextPoint(cc);
    
    // Add a string to the ContextPoint at the given coordinates
    cp.addInformation("I like Shark");

    // Create a KnowledgePort to handle the interest
    StandardKP kp = new StandardKP(aliceSE, cc, aliceKB);
    
    // keep connection 10 seconds open 
    aliceSE.setConnectionTimeOut(10000);
    
    Alice alicePeer = new Alice();
    kp.addListener(alicePeer);
    
    // Start the TCP-networking component on the SharkEngine to send and receive traffic
    aliceSE.startTCP(7070);   
//    aliceSE.startWifiDirect();

    // Now the peer is passively waiting for incoming traffic

    // Create a new peer that will act as out partner for communications
    PeerSemanticTag bob = aliceKB.createPeerSemanticTag("Bob", "http://www.sharksystem.net/bob.html", "tcp://localhost:7071");

    // publish the KnowledgePort to our partner
    aliceSE.publishKP(kp, bob);
    
    System.out.print("Bob has to run before starting Alice. If so, he "
            + "has already received something from Alice");
  }

    @Override
    public void exposeSent(KnowledgePort kp, SharkCS sentMutualInterest) {
        // ignore
    }

    @Override
    public void insertSent(KnowledgePort kp, Knowledge sentKnowledge) {
        // just to illustrate the idea.. Alice shuts down after sending knowledge
        System.out.println("Alice has sent something - enough for today...");
        
        System.out.println(L.knowledge2String(sentKnowledge));
        
        aliceSE.stop();
    }

    @Override
    public void knowledgeAssimilated(KnowledgePort kp, ContextPoint newCP) {
        // ignore
    }
}
