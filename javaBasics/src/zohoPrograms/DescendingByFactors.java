package zohoPrograms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DescendingByFactors {

	public static void main(String[] args) {
		int [] numbers = {6, 7, 9, 2, 5, 9};
		System.out.println("Input: "+ Arrays.toString(numbers));
		System.out.println("Output: "+ sortNumbersByFactors(numbers));
	}

	public static Set<Integer> sortNumbersByFactors(int[] input) {
		Map<Integer, Integer> findFactors = new LinkedHashMap<Integer, Integer>();
		for(int i=0; i<input.length; i++) {
			int factCount = 0;
			for(int j=1; j<=input[i]; j++) {
				if(input[i]%j==0) {
					factCount++;
				}
			}
			findFactors.put(input[i], factCount);
		}
		// Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
               new LinkedList<Map.Entry<Integer, Integer> >(findFactors.entrySet());
        
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o2,
                               Map.Entry<Integer, Integer> o1)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
		
     // put data from sorted list to hashmap
        Map<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        
        return temp.keySet();
    }

}
