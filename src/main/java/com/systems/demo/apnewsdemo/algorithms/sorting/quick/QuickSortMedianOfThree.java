package com.systems.demo.apnewsdemo.algorithms.sorting.quick;

public class QuickSortMedianOfThree {
    public static void main(String[] args) {
        int[] array = {14, 9, 28, 3, 7, 63, 89, 5};
        printArray(array);
        sort(array);
        printArray(array);
    }

    public static void sort(int[] array) {
        shuffle(array); // Shuffle the array (optional but recommended)
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low >= high) return;

        // Select the pivot using median-of-three
        int first = low;
        int second = low < high ? low + 1 : high;
        int third = low + 1 < high ? low + 2 : high;
        int pivot = Math.max(Math.min(array[first], array[second]),
            Math.min(Math.max(array[first], array[second]), array[third]));

        // Partition the array
        int left = low;
        int right = high;
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }

        // Recursively sort the subarrays
        quickSort(array, low, right);
        quickSort(array, left, high);
    }

    public static void printArray(int[] array) {
        for (int input : array) {
            System.out.print(input + " ");
        }
        System.out.println();
    }

    public static void shuffle(int[] array) {
        // Implement array shuffling (optional but recommended)
        // You can use Fisher-Yates or any other shuffling algorithm
        // ...
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

