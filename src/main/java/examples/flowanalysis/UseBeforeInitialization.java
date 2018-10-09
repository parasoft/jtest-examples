package examples.flowanalysis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Class that demonstrates application of "Use before Initialization" rule.
 */
public abstract class UseBeforeInitialization {

    /**
     * @param sSourceName
     * @param sType
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void process(String sType) throws IOException, ClassNotFoundException {
        ObjectInputStream source = null;
        try {
            source = new ObjectInputStream(new FileInputStream("..."));
            System.out.println("Resolved objects:");
            Object obj = source.readObject();
            while (obj != null) {
                ValueStorage storage = new ValueStorage();
                if (obj instanceof String) {
                    storage.setValue(obj);
                } else {
                    try {
                        String str = (String) source.readObject();
                        storage.setValue(str);
                    } catch (Exception e) {
                        System.out.println("Cannot resolve value");
                    }
                }
                System.out.println(storage.getValue());
                obj = source.readObject();
            }
        } finally {
            close(source);
        }
    }

    /**
     * @param stream
     * @throws IOException
     */
    private void close(InputStream stream) throws IOException {
        if (stream != null) {
            stream.close();
        }
    }

    public class ValueStorage {

        Object _value;

        public Object getValue() {
            return _value;
        }

        public void setValue(Object value) {
            _value = value;
        }
    }
}
