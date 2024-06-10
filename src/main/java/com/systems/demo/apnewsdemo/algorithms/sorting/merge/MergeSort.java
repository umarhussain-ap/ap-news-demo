package com.systems.demo.apnewsdemo.algorithms.sorting.merge;// Java program for Merge Sort
import java.io.*;
import java.time.Duration;

class MergeSort {

	// Merges two subarrays of array[].
	// First subarray is array[start..middle]
	// Second subarray is array[middle+1..end]
	void merge(double[] array, int start, int middle, int end)
	{
		// Find sizes of two sub-arrays to be merged
		int leftArraySize = middle - start + 1;
		int rightArraySize = end - middle;

		// Create temp arrays
		double[] leftArray = new double[leftArraySize];
		double[] rightArray = new double[rightArraySize];

		// Copy data to temp arrays
        for (int i = 0; i < leftArraySize; ++i) {
            leftArray[i] = array[start + i];
        }
        for (int j = 0; j < rightArraySize; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }

		// Merge the temp arrays

		// Initial indices of first and second sub-arrays
		int i = 0, j = 0;

		// Initial index of merged sub-array array
		int k = start;
		while (i < leftArraySize && j < rightArraySize) {
			if (leftArray[i] <= rightArray[j]) {
				array[k] = leftArray[i];
				i++;
			}
			else {
				array[k] = rightArray[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of leftArray[] if any
		while (i < leftArraySize) {
			array[k] = leftArray[i];
			i++;
			k++;
		}

		// Copy remaining elements of rightArray[] if any
		while (j < rightArraySize) {
			array[k] = rightArray[j];
			j++;
			k++;
		}
	}

	// Main function that sorts array[lowestIndex..highestIndex] using
	// merge()
	int sort(double[] array, int lowestIndex, int highestIndex,int callStackSize)
	{
		if (lowestIndex < highestIndex) {
			callStackSize++;
			// Find the middle point
			int middle = lowestIndex + (highestIndex - lowestIndex) / 2;

			// Sort first and second halves
			callStackSize += sort(array, lowestIndex, middle,callStackSize);
			sort(array, middle + 1, highestIndex,callStackSize);

			// Merge the sorted halves
			merge(array, lowestIndex, middle, highestIndex);
		}
		return callStackSize;
	}

	// A utility function to print array of size n
	static void printArray(int arr[])
	{
		int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
		System.out.println();
	}

	// Driver code
	public static void main(String args[])
	{

		double[] max_array = new double[100000000];

		for(int i=0;i<max_array.length; i++){
			max_array[i] = Math.random();
		}

		//int arr[] = { 12, 11, 13, 5, 6, 7 };


		MergeSort ob = new MergeSort();
		long start = System.nanoTime();
		System.out.println("merging started");
		int callstack = ob.sort(max_array, 0, max_array.length - 1,0);
		System.out.println("merging ended");
		System.out.println("callstack: "+callstack);
		System.out.println(Duration.ofNanos(System.nanoTime()-start));
		//printArray(arr);
	}
}
/* This code is contributed by Rajat Mishra */
