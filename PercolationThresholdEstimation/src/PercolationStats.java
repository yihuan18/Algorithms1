/**
 * Created by Yihuan on 2017/11/28.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private double probability[];

    public PercolationStats(int n , int trails){
        //perform trials independent experiments on an n-by-n grid
        int times = 0;
        probability = new double[trails];

        while(times < trails){ //perform trials independent experiments
            Percolation percolation = new Percolation(n);
            int count = 0;
            while (true){ //in one trail
                if(percolation.percolates()){
                    probability[times++] = (double)count/(double)(n*n);
                    break;
                }

                int row , col;
                row = StdRandom.uniform(n)+1;
                col = StdRandom.uniform(n)+1;
                //System.out.println("Random number row:" + row);
                //System.out.println("Random number col:" + col);

                if(!percolation.isOpen(row , col)){
                    percolation.open(row , col);
                    //System.out.println("open row & col :" + row + " "+ col);
                    count++;
                }
            }
        }
//        for(int i = 0 ; i < trails ; i++){
//            System.out.println(probability[i]);
//        }
    }

    public double mean(){
        // sample mean of percolation threshold
        return StdStats.mean(probability);
    }

    public double stddev(){
        // sample standard deviation of percolation threshold
        return StdStats.stddev(probability);
    }

    public double confidenceLo(){
        // low  endpoint of 95% confidence interval

        return mean() - 1.96*stddev();
    }

    public double confidenceHi(){
        // high endpoint of 95% confidence interval
        return mean() + 1.96*stddev();
    }

    public static void main(String[] args){
        // test client (described below)
        PercolationStats test = new PercolationStats(StdIn.readInt() , StdIn.readInt());
        //PercolationStats test = new PercolationStats(5 , 2);
        System.out.println("mean = " + test.mean());
        System.out.println("stddev = " + test.stddev());
        System.out.println("95% confidence interval = " + "[" + test.confidenceLo() + " , " + test.confidenceHi() + "]");

    }
}
