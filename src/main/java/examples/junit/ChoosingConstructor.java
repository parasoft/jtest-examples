package examples.junit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.CharBuffer;

/**
 * A class that shows that all of the overloaded constructors will be used in
 * the generated test cases.
 */
public class ChoosingConstructor {

    private Readable _readable;

    private InputStream _inStream;

    private OutputStream _outStream;

    public ChoosingConstructor() {
    }

    public ChoosingConstructor(Readable readable) {
        _readable = readable;
    }

    public ChoosingConstructor(InputStream inStream) {
        this(null, inStream, null);
    }

    public ChoosingConstructor(OutputStream outStream) {
        this(null, null, outStream);
    }

    public ChoosingConstructor(Readable readable, InputStream inStream, OutputStream outStream) {
        _readable = readable;
        _inStream = inStream;
        _outStream = outStream;
    }

    public void performAction() throws IOException {
        read();
        getFromStream();
        putToStream();
    }

    public void read() throws IOException {
        _readable.read(CharBuffer.wrap(""));
    }

    public void getFromStream() throws IOException {
        _inStream.read();
    }

    public void putToStream() throws IOException {
        _outStream.write(1);
    }
}
