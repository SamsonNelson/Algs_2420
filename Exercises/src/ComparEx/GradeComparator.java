package ComparEx;

import java.util.Comparator;

public class GradeComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
    	
//    	Student s1 = (Student) o1;
//        int n1 = s1.getGrade();
//        
//        Student s2 = (Student) o2;
//        int n2 = s2.getGrade();
//        
//		int result = Integer.compare(n1, n2);
//		return result;
		
		Student s1 = (Student) o1;
		Student s2 = (Student) o2;
//		descending order (ascending order would be:
//			s1.getGrade() - s2.getGrade())
		
		return s2.getGrade() - s1.getGrade();
    }

}
