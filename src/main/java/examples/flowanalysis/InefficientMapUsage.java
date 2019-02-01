package examples.flowanalysis;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;

public class InefficientMapUsage {

    /**
     * Example of incorrect map iteration
     * 
     * @param map
     */
    public void inefficientIteration(Map<?, ?> map) {
        Iterator<?> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object value = map.get(key); // VIOLATION
            // do something with value
        }
    }

    /**
     * Example of correct map iteration
     * 
     * @param map
     */
    public void effectiveIteration(Map<?, ?> map) {
        Iterator<?> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            // do something with value
        }
    }
}
