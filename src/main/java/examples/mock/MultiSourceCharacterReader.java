package examples.mock;

import java.io.IOException;
import java.io.StringReader;

import java.nio.CharBuffer;

/**
 * A class that shows that all of the necessary methods will be called to ensure
 * that an object is in the correct state. Additionally, public fields will be
 * set with appropriate mocks.
 */
public class MultiSourceCharacterReader {

    private Readable _readable;

    private Readable _readableInitialized;

    public Readable _readablePublic;

    public MultiSourceCharacterReader(Readable readable) {
        _readable = readable;
    }

    public void initialize() {
        _readableInitialized = new StringReader("");
    }

    public void performAction(CharBuffer cb) throws IOException {
        _readable.read(cb);
        _readableInitialized.read(cb);
        _readablePublic.read(cb);
    }
}
