package org.kd.cmd.server.cache;

import java.util.concurrent.ConcurrentHashMap;

public class SimpleCache<K, V> {

    private ConcurrentHashMap<K, V> holder;

    public SimpleCache build() {
        this.holder = new ConcurrentHashMap<K, V>();
        return this;
    }

    public V get(K k) {
        return holder.get(k);
    }

    public ConcurrentHashMap<K, V> getHolder() {
        return holder;
    }
}
