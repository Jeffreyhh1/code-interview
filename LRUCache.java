import java.util.Map;
import java.util.Iterator;
import java.util.LinkedList;
import java.time.*;
import java.util.concurrent.*;

public class LRUCache<K, V> {
    private int capacity;
    private long expireTimeInMillis;
    private Map<K, CacheNode> cacheMap;
    private LinkedList<CacheNode> cacheList;
    private ScheduledExecutorService scheduler;

    private class CacheNode {
        K key;
        V value;
        Instant lastAccess;

        CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.lastAccess = Instant.now();
        }
    }

    public LRUCache(int capacity, long expireTimeInSeconds) {
        this.capacity = capacity;
        this.expireTimeInMillis = expireTimeInSeconds * 1000;
        this.cacheMap = new ConcurrentHashMap<>();
        this.cacheList = new LinkedList<>();
        this.scheduler = Executors.newScheduledThreadPool(1);

        this.scheduler.scheduleAtFixedRate(this::removeExipredNodes, expireTimeInSeconds, 4, TimeUnit.SECONDS);

    }

    public synchronized V get(K key) {
        CacheNode node = cacheMap.get(key);
        if (node == null || isExpired(node)) {
            cacheMap.remove(key);
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public synchronized void set(K key, V value) {
        CacheNode node = cacheMap.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            if (cacheList.size() >= capacity) {
                CacheNode oldestNode = cacheList.removeLast();
                cacheMap.remove(oldestNode.key);
            }
            CacheNode newNode = new CacheNode(key, value);
            cacheMap.put(key, newNode);
            cacheList.addFirst(newNode);
        }
    }

    private void moveToHead(CacheNode node) {
        cacheList.remove(node);
        node.lastAccess = Instant.now();
        cacheList.addFirst(node);
    }

    private boolean isExpired(CacheNode node) {
        return Duration.between(node.lastAccess, Instant.now()).toMillis() >= expireTimeInMillis;
    }

    private synchronized void removeExipredNodes() {
        Iterator<CacheNode> iterator = cacheList.descendingIterator();
        while (iterator.hasNext()) {
            CacheNode node = iterator.next();
            if (isExpired(node)) {
                iterator.remove();
                cacheMap.remove(node);
            } else {
                break;
            }
        }
    }

    public void printCache() {
        for (CacheNode node : cacheList) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LRUCache<Integer, String> cache = new LRUCache<>(3, 5);

        cache.set(1, "one");
        Thread.sleep(1000);
        cache.set(2, "two");
        Thread.sleep(1000);
        cache.set(3, "three");
        Thread.sleep(1000);
        cache.get(1);
        // Thread.sleep(1000);
        // cache.set(4, "four");

        System.out.println("Cache after setting 4 elements and getting 1:");
        cache.printCache();

        Thread.sleep(5000); // Sleep for 5 seconds to allow expiry of some elements
        System.out.println("Cache after 5 seconds:");
        cache.printCache();
    }

}
