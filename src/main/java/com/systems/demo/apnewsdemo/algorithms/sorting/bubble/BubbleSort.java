package com.systems.demo.apnewsdemo.algorithms.sorting.bubble;

public class BubbleSort {

    public static int[] bubbleSort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 0; i < array.length; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int temp;
        if (array[i] > array[j]) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }

    }
}

class main {

    public static void main(String[] args) {
        int[] array = { 3, 2, 6, 8, 4, 5, 0, 9, 11, 1 };
        BubbleSort.printArray(array);
        System.out.println();
        BubbleSort.bubbleSort(array);
        BubbleSort.printArray(array);
    }
}