package practice;

public class Pr12 {

	public static void main(String[] args) {
	      // 6번 문제( 마름모 )
	      // __*__     	 i 1   2   1    i * 2 - 1 = j 
	      // _***_   	 i 2   1   3      
	      // *****   	 i 3   0   5     
	      // _***_   	 i 4   1   3     
	      // __*__       i 5   2   1     
		
	      // *****   	  
	      // _***_   	 i 1   1   3      i * 2 + 1
	      // __*__       i 2   2   1     
		
		//  2 * 3 - i
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3-i; j++) {
				System.out.print("-");
			}
			for (int j = 1; j <= i * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("-");
			}
			for (int j = 3; j >= i * 2 - 1; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
	} // main

} // class
