package com.javaAlgorithms;
import java.util.Random;

public class KnuthShuffle {
    public void shuffle(Object[] a){
        int n = a.length;
        Random rand = new Random();

        for (int i = 0; i < n; i++){
            int r = rand.nextInt(i);
            exch(a, i, r);
        }
    }

    private void exch(Object[] arr, int i, int r){
        Object temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args){
        KnuthShuffle kn = new KnuthShuffle();
        Object[] arr = {12, 34, 2, 9, 56, 19};
        for (Object o: arr){
            System.out.println(o);
        }
        kn.shuffle(arr);
        for (Object o: arr){
            System.out.println(o);
        }
    }

}
