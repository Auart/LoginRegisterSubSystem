package com.example.utils;

import java.util.HashMap;
import java.util.Map;

public class HashMapProxy<K, V> extends HashMap<K, V> {

    public HashMapProxy(int initialCapacity) {
        super(initialCapacity);
    }

    public HashMapProxy() {
        super();
    }

    public HashMapProxy(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public HashMapProxy<K,V> putObj(K key, V values) {
        super.put(key, values);
        return this;
    }
}
