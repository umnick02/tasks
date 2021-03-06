import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int DEFAULT_SIZE = 32;
    private int size;
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= items.length) {
            Item[] newItems = (Item[]) new Object[items.length * 3/2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size >= DEFAULT_SIZE*2 && size < items.length/3) {
            Item[] newItems = (Item[]) new Object[items.length/2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        int ind = StdRandom.uniform(size);
        Item last = items[size-1];
        Item curr = items[ind];
        items[ind] = last;
        --size;
        return curr;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i;
            private int[] order = StdRandom.permutation(size);

            @Override
            public boolean hasNext() {
                return i != size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = items[order[i]];
                i++;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println(queue.isEmpty());
        for (String arg : args) {
            queue.enqueue(arg);
        }
        System.out.println(queue.size());
        System.out.println(queue.sample());
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }

}