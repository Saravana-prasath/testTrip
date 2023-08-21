/*Input:
 	Array 1: 2,4,5,6,7,9,10,13
 	Array 2: 2,3,4,5,6,7,8,9,11,15
	Output:
	Merged array: 2,3,4,5,6,7,8,9,10,11,13,15 
*/
package zohoPrograms;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MergeTwoArrays {

	public static void main(String[] args) {
		int [] array1 = {2,4,5,6,7,9,10,13};
		int [] array2 = {2,3,4,5,6,7,8,9,11,15};
		System.out.println(mergeTwoArrays(array1, array2));
	}

	private static Set<Integer> mergeTwoArrays(int[] arr1, int[] arr2) {
		int l = arr1.length + arr2.length;
		ArrayList<Integer> mergedArray = new ArrayList<>();
		for(int i=0; i<arr1.length; i++) {
			mergedArray.add(i, arr1[i]);
		}
		int k=0;
		for(int j=arr1.length; j<l; j++) {
			mergedArray.add(j, arr2[k]);
			k++;
		}
		Set<Integer> merge = new TreeSet<Integer>(mergedArray);
		return merge;
	}
}
