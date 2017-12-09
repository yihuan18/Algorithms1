import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Yihuan on 2017/12/9.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public RandomizedQueue() {                 // construct an empty randomized queue
        a = (Item[]) new Object[1];
        N = 0;
    }

    public boolean isEmpty() {                 // is the randomized queue empty?
        return N == 0;
    }

    public int size() {                        // return the number of items on the randomized queue
        return N;
    }

    public void enqueue(Item item) {           // add the item
        if(item == null)
            throw new IllegalArgumentException();

        if(N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    private void resize(int size){
        Item[] copy = (Item[]) new Object[size];
        for(int i = 0 ; i < N ; i++)
            copy[i] = a[i];
        a = copy;
    }

    private void makeRandom(){
        if(N <= 1)
            return;
        int rand = StdRandom.uniform(N);
        Item temp = a[N-1];
        a[N-1] = a[rand];
        a[rand] = temp;
    }

    public Item dequeue() {                    // remove and return a random item
        if(isEmpty())
            throw new NoSuchElementException();

        makeRandom();
        Item item = a[--N];
        a[N] = null;
        if(N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample(){                     // return a random item (but do not remove it)
        if(isEmpty())
            throw new NoSuchElementException();

        makeRandom();
        return a[N-1];
    }

    @Override
    public Iterator<Item> iterator() {         // return an independent iterator over items in random order

        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{

        private int i = N;
        private Item[] items;

        private ArrayIterator(){
            items = (Item[]) new Object[N];
            System.arraycopy(a , 0 , items , 0 , N);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();

            if(i > 1){
                int rand = StdRandom.uniform(i);
                Item temp = items[i-1];
                items[i-1] = items[rand];
                items[rand] = temp;
            }
            return items[--i];
        }
    }

    public static void main(String[] args) {    // unit testing (optional)]

    }
}
