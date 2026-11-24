import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedHashSet<E> implements Iterable<E> {

    private static class Entry<E> {
        final E key;
        final int hash;
        Entry<E> next;
        Entry<E> before;
        Entry<E> after;

        Entry(E key, int hash, Entry<E> next) {
            this.key = key;
            this.hash = hash;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<E>[] table;
    private int size;
    private int threshold;

    private Entry<E> head;
    private Entry<E> tail;

    @SuppressWarnings("unchecked")
    public MyLinkedHashSet() {
        table = new Entry[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private int hash(Object key) {
        int h = key.hashCode();

        return h ^ (h >>> 16);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int oldCap = table.length;
        int newCap = oldCap << 1;
        Entry<E>[] newTable = new Entry[newCap];
        Entry<E>[] old = table;
        table = newTable;
        threshold = (int) (newCap * LOAD_FACTOR);


        for (int i = 0; i < oldCap; i++) {
            Entry<E> e = old[i];
            while (e != null) {
                Entry<E> next = e.next;
                int idx = indexFor(e.hash, newCap);
                e.next = newTable[idx];
                newTable[idx] = e;
                e = next;
            }
        }
    }

    public boolean add(E e) {
        if (e == null) throw new NullPointerException("null not allowed");
        int h = hash(e);
        int i = indexFor(h, table.length);


        for (Entry<E> p = table[i]; p != null; p = p.next) {
            if (p.hash == h && p.key.equals(e)) {
                return false; // вже є
            }
        }

        Entry<E> newEntry = new Entry<>(e, h, table[i]);
        table[i] = newEntry;
        linkLast(newEntry);
        size++;

        if (size > threshold) resize();
        return true;
    }

    private void linkLast(Entry<E> e) {
        if (tail == null) {
            head = tail = e;
        } else {
            e.before = tail;
            tail.after = e;
            tail = e;
        }
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        int h = hash(o);
        int i = indexFor(h, table.length);
        for (Entry<E> p = table[i]; p != null; p = p.next) {
            if (p.hash == h && p.key.equals(o)) return true;
        }
        return false;
    }

    public boolean remove(Object o) {
        if (o == null) return false;
        int h = hash(o);
        int i = indexFor(h, table.length);
        Entry<E> prev = null;
        Entry<E> p = table[i];
        while (p != null) {
            if (p.hash == h && p.key.equals(o)) {

                if (prev == null) table[i] = p.next;
                else prev.next = p.next;

                unlinkOrder(p);
                size--;
                return true;
            }
            prev = p;
            p = p.next;
        }
        return false;
    }

    private void unlinkOrder(Entry<E> e) {
        Entry<E> b = e.before;
        Entry<E> a = e.after;
        if (b == null) head = a;
        else { b.after = a; e.before = null; }
        if (a == null) tail = b;
        else { a.before = b; e.after = null; }
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        for (int i = 0; i < table.length; i++) table[i] = null;
        head = tail = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Entry<E> next = head;
            Entry<E> lastReturned = null;

            @Override
            public boolean hasNext() { return next != null; }

            @Override
            public E next() {
                if (next == null) throw new NoSuchElementException();
                lastReturned = next;
                next = next.after;
                return lastReturned.key;
            }

            @Override
            public void remove() {
                if (lastReturned == null) throw new IllegalStateException();
                MyLinkedHashSet.this.remove(lastReturned.key);
                lastReturned = null;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Entry<E> e = head;
        while (e != null) {
            sb.append(e.key);
            if (e.after != null) sb.append(", ");
            e = e.after;
        }
        return sb.append("]").toString();
    }
}