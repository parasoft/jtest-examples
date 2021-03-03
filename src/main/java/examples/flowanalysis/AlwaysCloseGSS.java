package examples.flowanalysis;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSManager;

public class AlwaysCloseGSS {

    public void process(byte[] tokens) {
        try {
            byte[] inputBuff = new byte[256];
            GSSManager.getInstance().createContext(tokens).initSecContext(inputBuff, 0, 256);
            // ...
        } catch (Exception ioe) {
            System.out.println("Exception occured: " + ioe);
        }
    }

    public void processClose(byte[] tokens) {
        GSSContext context = null;
        try {
            context = GSSManager.getInstance().createContext(tokens);
            byte[] inputBuff = new byte[256];
            context.initSecContext(inputBuff, 0, 256);
            // ...
        } catch (Exception ioe) {
            System.out.println("Exception occured: " + ioe);
        } finally {
            try {
                context.dispose();
            } catch (Exception e) {
            }
        }
    }
}
