package test;

import java.util.Arrays;

public class CaseInsensitiveOrder {

	public static void main(String[] args) {
		
		String[] letters = {  "B", "z", "b", "A", "a","c", "C" };
		
		Arrays.sort(letters);
		
		System.out.println(Arrays.toString(letters));
		
		Arrays.sort(letters,String.CASE_INSENSITIVE_ORDER);
		
		System.out.println(Arrays.toString(letters));
		
		
	}

}
