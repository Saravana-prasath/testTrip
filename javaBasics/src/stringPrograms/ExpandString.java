package stringPrograms;

public class ExpandString {

	public static void main(String[] args) {

		String str = "a2b3";
		String output = newExpandedString(str);
		System.out.println("Output: " + output);

	}

	public static String newExpandedString(String inputString) {
		StringBuffer expand = new StringBuffer();
		int index = 0;
		
		 while(index<inputString.length()) {
			 
			 char currentChar = inputString.charAt(index);
			 index++;
			 int count = 0;
			 
			 while(index < inputString.length() && Character.isDigit(inputString.charAt(index))) {
				 count = count*10 + inputString.charAt(index)-'0';
				 index++;
			 }
			 
			 for(int i=1; i<=count; i++) {
				 expand.append(currentChar);
			 }
		 }
		return expand.toString();
	}

}
