package zohoPrograms;

public class PatternProgram1 {

	public static void main(String[] args) {
		
		//String input = "12345";
		String input = "geeksforgeeks";
		int length = input.length();
		if(length%2==0) {
			System.out.println("The string length must be the odd value");
		}else {
			
			for(int i=0; i<length; i++) {
				for(int j=0; j<=length-1; j++) {
					if(i==j) {
						System.out.print(input.charAt(i)+" ");
					}
					else if(j==(length-1-i)) {
						System.out.print(input.charAt(length-1-i)+" ");
					}else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}

	}

}
