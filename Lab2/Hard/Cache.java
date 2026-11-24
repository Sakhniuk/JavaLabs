import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> {

    private static class Entry<V> {
        final V value;
        final long expiresAt;

        Entry(V value, long expiresAt) {
            this.value = value;
            this.expiresAt = expiresAt;
        }

        boolean isExpired() {
            return expiresAt != 0 && System.currentTimeMillis() > expiresAt;
        }
    }

    private final int maxSize;
    private final long ttlMillis;
    private final LinkedHashMap<K, Entry<V>> map;

    public Cache(int maxSize, long ttlMillis) {
        this.maxSize = maxSize;
        this.ttlMillis = ttlMillis;
        this.map = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, Entry<V>> eldest) {
                return size() > Cache.this.maxSize;
            }
        };
    }

    public void put(K key, V value) {
        if (key == null || value == null)
            throw new NullPointerException("Cache doesn't allow null");
        long expiresAt = ttlMillis > 0 ? System.currentTimeMillis() + ttlMillis : 0;
        map.put(key, new Entry<>(value, expiresAt));
    }

    public V get(K key) {
        if (key == null) throw new NullPointerException("Cache doesn't allow null");
        Entry<V> e = map.get(key);
        if (e == null) return null;
        if (e.isExpired()) {
            map.remove(key);
            return null;
        }
        return e.value;
    }

    public int size() { return map.size(); }

    public void remove(K key) {
        if (key == null) throw new NullPointerException("Cache doesn't allow null");
        map.remove(key);
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Тестування Cache (maxSize=3, TTL=500ms)");

        Cache<String, Integer> cache = new Cache<>(3, 500);

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        System.out.println("Після додавання A, B, C:");
        System.out.println("A = " + cache.get("A"));
        System.out.println("Розмір: " + cache.size());

        cache.put("D", 4);
        System.out.println("\nПісля додавання D (ліміт 3):");
        System.out.println("B = " + cache.get("B"));
        System.out.println("A = " + cache.get("A"));
        System.out.println("C = " + cache.get("C"));
        System.out.println("D = " + cache.get("D"));

        System.out.println("\nПеревірка заборони null:");
        try {
            cache.put(null, 5);
        } catch (NullPointerException e) {
            System.out.println("Спроба додати null-ключ -> " + e.getMessage());
        }
        try {
            cache.put("E", null);
        } catch (NullPointerException e) {
            System.out.println("Спроба додати null-значення -> " + e.getMessage());
        }

        cache.remove("C");
        System.out.println("\nПісля видалення C. Розмір: " + cache.size());


        System.out.println("\nЧекаємо 600 мс (TTL = 500 мс)...");
        Thread.sleep(600);
        System.out.println("A = " + cache.get("A"));
        System.out.println("D = " + cache.get("D"));
        System.out.println("Розмір після спливання: " + cache.size());
    }
}