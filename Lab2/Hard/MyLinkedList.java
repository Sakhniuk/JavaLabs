public class MyLinkedList implements MyList {
    private static class Node {
        Object item;
        Node prev;
        Node next;

        Node(Node prev, Object element, Node next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    private Node node(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        Node x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) x = x.prev;
        }
        return x;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void linkBefore(Object e, Node succ) {
        Node pred = succ.prev;
        Node newNode = new Node(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) first = newNode;
        else pred.next = newNode;
        size++;
    }

    private void linkLast(Object e) {
        Node l = last;
        Node newNode = new Node(l, e, null);
        last = newNode;
        if (l == null) first = newNode;
        else l.next = newNode;
        size++;
    }

    private Object unlink(Node x) {
        Object element = x.item;
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) first = next;
        else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) last = prev;
        else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    @Override
    public void add(Object e) {
        linkLast(e);
    }

    @Override
    public void add(int index, Object element) {
        checkIndexForAdd(index);
        if (index == size) linkLast(element);
        else linkBefore(element, node(index));
    }

    @Override
    public void addAll(Object[] c) {
        if (c == null) throw new NullPointerException("Array is null");
        for (Object o : c) linkLast(o);
    }

    @Override
    public void addAll(int index, Object[] c) {
        if (c == null) throw new NullPointerException("Array is null");
        checkIndexForAdd(index);
        if (c.length == 0) return;
        if (index == size) {
            for (Object o : c) linkLast(o);
        } else {
            Node succ = node(index);
            for (Object o : c) linkBefore(o, succ);
        }
    }

    @Override
    public Object get(int index) {
        return node(index).item;
    }

    @Override
    public Object remove(int index) {
        return unlink(node(index));
    }

    @Override
    public void set(int index, Object element) {
        Node x = node(index);
        x.item = element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) return index;
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item)) return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node x = first; x != null; x = x.next) {
            sb.append(x.item);
            if (x.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
