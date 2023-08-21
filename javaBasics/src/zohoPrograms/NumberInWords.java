package zohoPrograms;

import java.util.Scanner;

public class NumberInWords {

	private static final String [] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
	        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	
	private static final String[] tens = {
	        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
	    };
	
	public static void main(String[] args) {
		System.out.print("Enter the Number: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String words = convertIntoWords(num);
		System.out.println("The number is: "+ num);
		System.out.println("Numbers in words: "+ words);
		
	}

	private static String convertIntoWords(int num) {
		
		if(num<0 || num>999) {
			return "Number is out of range";
		}	
		if(num==0) {
			return "zero";
		}
		if(num<20) {
			return units[num];
		}
		if(num<100) {
			return tens[num/10] + (num%10 !=0 ?" "+ units[num%10]:"");
		}
		return units[num/100]+ " hundred" + (num%100!=0?" and "+ convertIntoWords(num%100):"");
	}

}
