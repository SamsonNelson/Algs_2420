package ElementSymbolTable;

import java.util.HashMap;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdIn;

public class GPA {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("A+", 4.33);
		map.put("A", 4.00);
		map.put("A-", 3.67);
		map.put("B+", 3.33);
		map.put("B", 3.00);
		map.put("B-", 2.67);
		map.put("C+", 2.33);
		map.put("C", 2.00);
		map.put("C-", 1.67);
		map.put("D", 1.00);
		map.put("F", 0.00);

		String ch = "Y";
		double count = 0.00, sum = 0.00;
		while (ch.equals("Y")) {
			System.out.println("Enter grade: ");
			String g = StdIn.readString();
			if (!map.containsKey(g)) {
				System.out.println("Invalid key");
				continue;
			}
			System.out.println("More Grades ? (Y/N)");
			ch = StdIn.readString();
			count++;
			sum += map.get(g);
		}
		System.out.println("GPA = " + sum / count);
		sc.close();
	}
}
