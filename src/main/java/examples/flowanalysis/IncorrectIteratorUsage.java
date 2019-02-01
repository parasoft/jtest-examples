package examples.flowanalysis;

import java.util.HashSet;
import java.util.Iterator;

abstract class IncorrectIteratorUsage {

    /**
     * Removes all elements from a collection, leaving only elements of a given type
     * 
     * @param collection
     * @param tp
     */
    public void removeAllElementsIncorrect(HashSet<?> collection, Class<?> tp) {
        Iterator<?> iter = collection.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (tp.isAssignableFrom(obj.getClass())) {
                continue;
            }
            collection.remove(obj);
        }
    }

    /**
     * Removes all elements from a collection, leaving only elements of a given type
     * 
     * @param collection
     * @param tp
     */
    public void removeAllElementsCorrect(HashSet<?> collection, Class<?> tp) {
        Iterator<?> iter = collection.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (tp.isAssignableFrom(obj.getClass())) {
                continue;
            }
            iter.remove();
        }
    }
}
