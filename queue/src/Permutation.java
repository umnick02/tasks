import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        String s;
        while ((s = StdIn.readString()) != null) {
            queue.enqueue(s);
        }
        int k = Integer.parseInt(args[0]);
        Iterator<String> iterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }
}
