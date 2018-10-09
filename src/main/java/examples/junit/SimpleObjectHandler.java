package examples.junit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that shows that multiple assertions will be used in the generated
 * test cases.
 */
public class SimpleObjectHandler {

    private Object element;

    public SimpleObjectHandler(Object element) {
        super();
        this.element = element;
    }

    public static Collection<Integer> getList() {
        List<Integer> list = new ArrayList<Integer>();
        return list;
    }

    public static <K, V> Map<K, V> getMap() {
        Map<K, V> map = new HashMap<K, V>();
        return map;
    }

    public String getString() {
        return "";
    }

    public Object getElement() {
        return element;
    }
}
