package threads;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author PSH
 * Date: 2018/5/31
 */
public class ReadAndWriteMap<K, V> {

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final Lock writeLock = lock.writeLock();
    private static final Lock readLock = lock.readLock();
    private Map<K, V> map;

    ReadAndWriteMap(Map<K, V> map) {
        this.map = map;
    }

    public V put(K key, V value) {
        writeLock.lock();
        try {
            V v = map.put(key, value);
            return v;
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K k) {
        readLock.lock();
        try {
            return map.get(k);
        } finally {
            readLock.unlock();
        }
    }
}
