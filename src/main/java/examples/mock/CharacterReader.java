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

    public String readString(Reader reader) throws IOException {
        StringBuffer buff = new StringBuffer();
        try {
            while (true) {
                char[] arr = new char[32];
                int count = reader.read(arr);
                if (count > 0) {
                    buff.append(arr, 0, count);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            if (buff.length() > 0) {
                return buff.toString();
            } else {
                throw e;
            }
        }

        if (buff.length() == 0) {
            return null;
        }
        return buff.toString();
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
