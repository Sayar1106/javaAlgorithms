package com.javaAlgorithms;

public class ShellSort {
    public static void sort(Comparable[] arr){
        int interval = fetchInterval(arr.length);
        while(interval > 0){
            for (int i = interval; i < arr.length; i++){
                for(int j = i; j >= interval && less(arr[j], arr[j-interval]); j-=interval){
                    exch(arr, j, j-interval);
                }
            }
            interval = interval/3;
        }
    }
    private static int fetchInterval(int N){
       int  interval = 1;
       while (interval < N/3) interval  =  interval * 3 + 1;
       return interval;
    }

    private static boolean less(Comparable a, Comparable b)
    {return a.compareTo(b) < 0;}

    private static void exch(Comparable[] arr, int i, int j)
    {Comparable t = arr[i]; arr[i] = arr[j]; arr[j] = t;}

    public static void main(String[] args){
        String[] arr = {"Banana", "Orange", "Apple", "Grape", "Guava", "Mango"};
        sort(arr);

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
