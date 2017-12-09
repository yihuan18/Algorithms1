import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Yihuan on 2017/12/9.
 */
public class Deque<Item> implements Iterable<Item>{

    private Node first , last;
    private int size;

    private class Node{
        Item item;
        Node next , before;
    }

    public Deque(){                          // construct an empty deque
        first = null;
        last = null;
    }

    public boolean isEmpty(){                // is the deque empty?
        return first == null;
    }

    public int size() {                        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {          // add the item to the front
        if(item == null)
            throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if(oldFirst == null)
            last = first;
        else {
            first.next = oldFirst;
            oldFirst.before = first;
        }
        size++;
    }

    public void addLast(Item item) {           // add the item to the end
        if(item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        if(isEmpty())
            first = last;
        else {
            oldLast.next = last;
            last.before = oldLast;
        }
        size++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if(isEmpty())
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        else first.before = null;
        size--;
        return item;
    }

    public Item removeLast() {                 // remove and return the item from the end
        if(isEmpty())
            throw new NoSuchElementException();

        Item item = last.item;
        last = last.before;
        if(last == null) first = null;
        else last.next = null;
        size--;
        return item;
    }


    @Override
    public Iterator<Item> iterator() {          // return an iterator over items in order from front to end

        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {   // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();

        System.out.println("isEmpty()" + deque.isEmpty());
        for(int i = 0 ; i < 10 ; i++){
            deque.addFirst(i);
            deque.addLast(i);
        }

        System.out.print("after insert : ");

        System.out.println("size : " + deque.size());

        System.out.print("after delete : ");

        deque.removeFirst();
        deque.removeLast();
        System.out.print(deque.size());

    }
}
