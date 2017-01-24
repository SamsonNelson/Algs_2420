package test;

import java.util.Arrays;

public class MyBinarySearch {

	public static void main(String[] args) {
		MyBinarySearch mbs = new MyBinarySearch();
        int[] arr = {2, 4, 6, 8, 9, 12, 10, 19, 14, 16};
        System.out.println("Key 14's position: "+mbs.binarySearch(arr, 14));
        int[] arr1 = {6,34,78,123,432,900};
        System.out.println("Key 432's position: "+mbs.binarySearch(arr1, 432));

	}
	
	public int binarySearch(int[] inputArr, int key) {
		
        Arrays.sort(inputArr);
        for (int number : inputArr) {
        	   System.out.println("Number = " + number);
        	   }
        
        int start = 0;
        int end = inputArr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (inputArr[mid] == key) {
                return mid;
            }
            if (inputArr[mid] > key) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
