/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComparEx;

/**
 *
 * @author dhunter3
 */
import java.util.Comparator;

public class NameComparator implements Comparator {
	
	MainComparatorExample name = new MainComparatorExample();
	
	Student student = new Student();
	
    @Override
    public int compare(Object o1, Object o2) {
    	
        Student s1 = (Student) o1;
        String n1 = s1.getName();
        
        Student s2 = (Student) o2;
        String n2 = s2.getName();
        
		int result = n1.compareTo(n2);
		return result;
    }
    


}
