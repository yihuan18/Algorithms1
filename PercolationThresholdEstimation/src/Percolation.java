/**
 * Created by Yihuan on 2017/11/28.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int ifBlocked[];
    private WeightedQuickUnionUF sites;
    private int siteRowNum;
    private int openSiteCount;

    public Percolation(int n){      //create n-by-n grid,with all sites blocked
        if(n <= 0)
            throw new IllegalArgumentException();

        siteRowNum = n;
        ifBlocked = new int[n*n+1]; //from 1 to n*n
        for(int i = 0 ; i < ifBlocked.length ; i++){
            ifBlocked[i] = 1;
        }
        openSiteCount = 0;

        //2 more sites with 1 on the top connecting the first row and 1 on the bottom connecting the last row
        sites = new WeightedQuickUnionUF(n*n+2);
        //System.out.println("sites Initialization: " + "siteRowNum=" + siteRowNum);
    }

    public void open(int row , int col){        //open site(row,col) if it is not open already
        if(row <= 0 || row > siteRowNum || col <= 0 || col > siteRowNum)
            throw new IllegalArgumentException();

        if(isOpen(row , col)) {
            //System.out.println("row and col:" + row + "and" + col + "is already opened!");
            return;
        }

        ifBlocked[(row-1)*siteRowNum + col] = 0;
        openSiteCount++;
        if(row == 1)
            sites.union(0 , (row-1)*siteRowNum + col);
        if(row == siteRowNum)
            sites.union(siteRowNum*siteRowNum+1 , (row-1)*siteRowNum + col);

        //connect the left site
        if(col > 1 && isOpen(row , col-1))
            sites.union((row-1)*siteRowNum + col , (row-1)*siteRowNum + col - 1);
        //connect the right site
        if(col < siteRowNum && isOpen(row , col+1))
            sites.union((row-1)*siteRowNum + col , (row-1)*siteRowNum + col + 1);
        //connect the upper site
        if(row > 1 && isOpen(row-1 , col))
            sites.union((row-1)*siteRowNum + col , (row-2)*siteRowNum + col);
        //connect the down site
        if(row < siteRowNum && isOpen(row+1 , col))
            sites.union((row-1)*siteRowNum + col , row*siteRowNum + col);

        return;
    }

    public boolean isOpen(int row , int col){   //is site(row,col) open?

        if(row <= 0 || row > siteRowNum || col <= 0 || col > siteRowNum)
            throw new IllegalArgumentException();

        return (ifBlocked[(row-1)*siteRowNum + col] == 0);
    }

    public boolean isFull(int row , int col){       //is site(row , col) full?
        if(row <= 0 || row > siteRowNum || col <= 0 || col > siteRowNum)
            throw new IllegalArgumentException();

        return (sites.connected((row-1)*siteRowNum + col , 0));

    }

    public int numberOfOpenSites()   //number of open site
    {
        return openSiteCount;
    }

    public boolean percolates(){
        return (sites.connected(0 , siteRowNum*siteRowNum+1));
    }

    public static void main(String[] args){

    }

}
