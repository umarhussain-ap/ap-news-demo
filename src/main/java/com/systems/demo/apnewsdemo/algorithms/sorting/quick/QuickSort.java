package com.systems.demo.apnewsdemo.algorithms.sorting.quick;

public class QuickSort {
    //create helper methods

    public static void swap(int[]array,int i,int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //create the pivot for quick sort use the right most element for quick sort
    public static int partition(int []array, int high, int low) {
        int pivot = array[high];

        int index = (low-1);

        for(int j= low; j<=high-1; j++) {
               if(array[j]<pivot) {
                   index++;
                   swap(array,index,j);
               }
        }
        swap(array,index+1, high);
        return (index+1);
    }

    static Integer quicksort(int[]arr, int high, int low,Integer iterations) {
        if(low<high) {
            iterations++;
            int partitionKey = partition(arr, high, low);
           iterations += quicksort(arr, partitionKey-1,low,iterations);
           iterations += quicksort(arr, high,partitionKey+1,iterations);
        }
        return iterations;
    }

    public static void print(int[]array) {
        for (int i=0; i<array.length;i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}

class main {

    public static void main(String[] args) {
        int [] array = {3,5,6,3,8,7,1};
        QuickSort.print(array);
        Integer iterations = 0;
        iterations = QuickSort.quicksort(array,array.length-1,0,iterations);
        System.out.println("iterations: "+iterations);
        QuickSort.print(array);

    }
}