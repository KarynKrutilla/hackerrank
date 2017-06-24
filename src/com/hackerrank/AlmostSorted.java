package com.hackerrank;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/almost-sorted
 */
public class AlmostSorted {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Get array size and all input values for the array
        int arraySize = in.nextInt();
        int[] inputValues = new int[arraySize];
        for(int i = 0; i < arraySize; i++) {
            inputValues[i] = in.nextInt();
        }

        // Requirement: If the array is already sorted, output yes on the first line.
        //              You do not need to output anything else.
        if(isSorted(inputValues)) {
            System.out.println("yes");
            return;
        }

        // Otherwise we find the out of order values:
        int firstOutOfOrderIndex=0;
        int secondOutOfOrderIndex=0;

        // Check for first value that is out of order
        for(int i = 0; i < arraySize-1; i++) {
            if(inputValues[i] > inputValues[i+1]) {
                firstOutOfOrderIndex = i;
                break;
            }
        }
        // Check for last value that is out of order
        for(int i = arraySize-1; i > 0; i--) {
            if ( inputValues[i] < inputValues[i - 1]){
                secondOutOfOrderIndex = i;
                break;
            }
        }

        // Try to swap these two values, see if that sorts the array
        swap(inputValues, firstOutOfOrderIndex, secondOutOfOrderIndex);
        if(isSorted(inputValues)) {
            // Requirement: If you can sort the array by swapping l and r, output swap l r in the second line.
            //              l and r are the indices of the elements to be swapped,
            //              assuming that the array is indexed from 1 to n.
            System.out.println("yes");
            System.out.println("swap " + (firstOutOfOrderIndex+1) + " " + (secondOutOfOrderIndex+1));
            return;
        }

        // If that swap didn't do the trick, try to reverse the rest of the inner pairs, then see if that sorts the array
        reverse(inputValues, firstOutOfOrderIndex+1, secondOutOfOrderIndex-1);
        if(isSorted(inputValues)) {
            // Requirement: If it is possible to sort the array by reversing the segment d[l...r],
            //              output reverse l r in the second line.
            //              l and r are the indices of the first and last elements of the subsequence to be reversed,
            //              assuming that the array is indexed from 1 to n
            System.out.println("yes");
            System.out.println("reverse " + (firstOutOfOrderIndex+1) + " " + (secondOutOfOrderIndex+1));
            return;
        }

        // Requirement: If you cannot sort the array in either of the above ways, output no in the first line.
        System.out.println("no");
    }

    /**
     * Checks if a given array is sorted
     * @param input the array to check
     * @return true if the array is sorted, otherwise false
     */
    private static boolean isSorted(int[] input) {
        for(int i = 0; i < input.length-1; i++) {
            if(input[i] > input[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * swaps two values in a given int[]
     * @param valuesToSwap the array containing the values
     * @param firstIndex the first index to swap
     * @param secondIndex the second index to swap
     * @return inputValues array with the two values swapped
     */
    private static int[] swap(int[] valuesToSwap, int firstIndex, int secondIndex) {
        int temp = valuesToSwap[firstIndex];
        valuesToSwap[firstIndex] = valuesToSwap[secondIndex];
        valuesToSwap[secondIndex] = temp;
        return valuesToSwap;
    }

    /**
     * Reverses everything by swapping inner pairs between firstIndex and secondIndex in given inputValues array
     * @param valuesToReverse the array that will be partly reversed
     * @param firstIndex beginning index for reverse
     * @param secondIndex ending index for reverse
     * @return inputValues array, with everything between firstIndex and secondIndex reversed
     */
    private static int[] reverse(int[] valuesToReverse, int firstIndex, int secondIndex) {
        while(secondIndex > firstIndex) {
            swap(valuesToReverse, firstIndex++, secondIndex--);
        }
        return valuesToReverse;
    }
}
