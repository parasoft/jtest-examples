package examples.mock;

import java.io.IOException;
import java.io.Reader;

import java.nio.CharBuffer;

/**
 * A class that shows that all of the necessary method calls will be mocked.
 */
public class CharacterReader {

    public void read(Reader reader) throws IOException {
        if (reader.ready()) {
            prepareCharBuffer(reader);
        }
    }

    private CharBuffer prepareCharBuffer(Reader reader) throws IOException {
        class ReadableHandler {

            public CharBuffer read(Readable readable) throws IOException {
                CharBuffer cb = CharBuffer.wrap("");
                readable.read(cb);
                return cb;
            }
        }
        ReadableHandler handler = new ReadableHandler();
        return handler.read(reader);
    }
}
