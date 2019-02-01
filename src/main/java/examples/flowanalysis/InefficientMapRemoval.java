package examples.flowanalysis;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.*;

public class InefficientMapRemoval {

    /**
     * Example of incorrect map iteration
     * 
     * @param map
     */
    public void inefficientRemoval(Map<?, ?> map) {
        Iterator<?> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            map.remove(key);
        }
    }

    /**
     * Example of incorrect map iteration
     * 
     * @param map
     */
    public void inefficientRemovalEntry(Map<?, ?> map) {
        Iterator<?> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iter.next();
            Object key = entry.getKey();
            map.remove(key);
        }
    }

    /**
     * Example of correct map iteration
     * 
     * @param map
     */
    public void effectiveRemoval(Map<?, ?> map) {
        Iterator<?> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }

    /**
     * Example of correct map iteration
     * 
     * @param map
     */
    public void effectiveRemovalKeySet(Map<?, ?> map) {
        Iterator<?> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }
}
