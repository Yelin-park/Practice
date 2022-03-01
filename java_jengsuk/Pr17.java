package java_jengsuk;

public class Pr17 {

	public static void main(String[] args) {
		// 배열 섞기	
		int[] numArr = new int[10];
		
		for (int i = 0; i < numArr.length; i++) {
			numArr[i] = i;
			System.out.print(numArr[i]);
		}
		
		System.out.println();
		
		for (int i = 0; i < numArr.length; i++) {
			int n = (int)(Math.random()*10);
			int temp = numArr[0];
			numArr[0] = numArr[n];
			numArr[n] = temp;
		}
		
		for (int i = 0; i < numArr.length; i++) {
			System.out.print(numArr[i]);
		}

		
	} // main

} // class
