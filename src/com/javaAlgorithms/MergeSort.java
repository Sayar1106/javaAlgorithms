package com.javaAlgorithms;

public class MergeSort {
    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int hi) {
        int i = low;
        int j = mid + 1;
        assert isSorted(arr, low, mid);
        assert isSorted(arr, mid + 1, hi);
        for (int k = low; k <= hi; k++) {
            aux[k] = arr[k];
        }
        for (int k = low; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(aux[i], aux[j])) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
        assert isSorted(arr, low, hi);
    }

    private static boolean less(Comparable a, Comparable b)
    {return a.compareTo(b) < 0;}


    private static void sort(Comparable[] arr, Comparable[] aux, int low, int hi){
        if(hi <= low) return;
        int mid = low + (hi - low)/2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, low, mid, hi);
    }

    private static boolean isSorted(Comparable[] arr, int low, int hi){
        for(int i = low + 1; i <= hi; i++){
            if(less(arr[i], arr[i - 1])) return false;
        }
        return true;
    }

    public static void sort(Comparable[] arr){
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }
    public static void main(String[] args){
        Comparable[] arr = {88, 40, 38, 5, 49, 63};
        sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

    }
}
