package chat;

import java.io.IOException;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.system.SharkException;

/**
 * @author thsc
 */
public interface ChatKPListener {
    public void messageReceived(String message, PeerSemanticTag sender) throws SharkException, IOException;
}
