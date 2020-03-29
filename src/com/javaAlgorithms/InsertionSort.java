package com.javaAlgorithms;

public class InsertionSort {
    public static void sort(Comparable[] arr){
       for (int i=1; i < arr.length; i++){
           for (int j=i; j > 0 && less(arr[j], arr[j-1]); j--){
               exch(arr, j, j-1);
           }
       }
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
