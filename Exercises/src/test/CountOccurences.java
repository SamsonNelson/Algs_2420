package test;

public class CountOccurences {

	public static void main(String[] args) {
		CountOccurences myOccurence = new CountOccurences();
		myOccurence.countOccurrences(new int[]{2,3,4,5,6,7}, 5);
	}
	
	public int countOccurrences(int[] array, int x) 
	{
		int y = 0;
		for (int s : array) 
		{
			
			if (s == x) 
			{
				y++;
			}
		}
		return y;
	}

	public <T> int countOccurrences(Object[] array, Object x)
	{
		int y = 0;
		for (Object s : array) 
		{
			if (s.equals(x)) 
			{
				y++;
			}
		}
		return y;
	}
}
