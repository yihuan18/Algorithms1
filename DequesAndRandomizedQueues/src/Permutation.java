import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * Created by Yihuan on 2017/12/9.
 */
public class Permutation {
    public static void main(String[] args){
        int k = Integer.valueOf(args[0]);
        int count = 0;
        RandomizedQueue<String> stringRandomizedQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()){
            stringRandomizedQueue.enqueue(StdIn.readString());
        }

        Iterator<String> iterator = stringRandomizedQueue.iterator();

        if(k == 0)
            return;
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            count++;
            if(count == k)
                break;
        }
    }
}
