package com.systems.demo.apnewsdemo.algorithms.searching;

public class BinarySearch {

    //assuming the array provided is sorted


    public static boolean binarySearch(int [] array,int value) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (value == array[mid]) {
                return true;
            } else if (value < array[mid] ) {
                high = mid - 1; // Search in the left half
            } else {
                low = mid + 1; // Search in the right half
            }
    }
        return false;
    }

}
