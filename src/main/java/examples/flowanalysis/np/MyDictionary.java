package examples.flowanalysis.np;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Class that demonstrate methods which not accept 'null' as argument.
 */
public class MyDictionary {

    Dictionary dict = new Hashtable();

    public void putToDictionary(Object key, Object value) {
        String strKey = null;
        if (key instanceof String) {
            strKey = (String) key;
        }
        dict.put(strKey, value);
    }

    public String getFromDictionary(Object key) {
        return (String) dict.get(key);
    }
}
