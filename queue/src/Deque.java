import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private ItemWrapper first;
    private ItemWrapper last;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        ItemWrapper old = first;
        first = new ItemWrapper(item);
        first.prev = old;
        if (first.prev == null) {
            last = first;
        } else {
            old.next = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        ItemWrapper old = last;
        last = new ItemWrapper(item);
        last.next = old;
        if (last.next == null) {
            first = last;
        } else {
            old.prev = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        ItemWrapper old = first;
        first = first.prev;
        if (first != null) {
            first.next = null;
        } else {
            last = null;
        }
        size--;
        return old.value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        ItemWrapper old = last;
        last = last.next;
        if (last != null) {
            last.prev = null;
        } else {
            first = null;
        }
        size--;
        return old.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public Item next() {
                if (first == null) {
                    throw new NoSuchElementException();
                }
                ItemWrapper old = first;
                first = first.prev;
                return old.value;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                deque.addFirst(args[i]);
            } else {
                deque.addLast(args[i]);
            }
        }
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        while (deque.iterator().hasNext()) {
            System.out.println(deque.removeLast());
        }
        System.out.println(deque.isEmpty());
    }

    private class ItemWrapper {
        Item value;
        ItemWrapper next;
        ItemWrapper prev;
        private ItemWrapper(Item value) {
            this.value = value;
        }
    }
}
