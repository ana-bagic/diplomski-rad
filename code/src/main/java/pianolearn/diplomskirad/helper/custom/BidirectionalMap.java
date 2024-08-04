package pianolearn.diplomskirad.helper.custom;

import java.util.HashMap;
import java.util.Map;

public class BidirectionalMap<K, V> {

    private final Map<K, V> keyToValueMap = new HashMap<>();
    private final Map<V, K> valueToKeyMap = new HashMap<>();

    @SafeVarargs
    public static <K, V> BidirectionalMap<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
        var map = new BidirectionalMap<K, V>();
        for (var entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public int size() {
        return keyToValueMap.size();
    }

    public void put(K key, V value) {
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
    }

    public V getFromKey(K key) {
        return keyToValueMap.get(key);
    }

    public K getFromValue(V value) {
        return valueToKeyMap.get(value);
    }
}