package java_jengsuk;

public class ArrayEx11 {

	public static void main(String[] args) {
		int[] numArr = new int[10];
		int[] counter = new int[10];
		
		for (int i = 0; i < counter.length; i++) {
			numArr[i] = (int)(Math.random() * 10);
			System.out.print(numArr[i]);
		} // i for
		
		System.out.println(); // 개행
		 
		for (int i = 0; i < counter.length; i++) {
			counter[numArr[i]]++;
			// counter[numArr[0]]; > ex) numArr[0]의 값은 4
			// counter[4]++;       >     counter[4]의 값을 1 증가 시킨다. (인덱스의 요소에 있는 값을 증가 시킴)
		} // i for
		
		for (int i = 0; i < counter.length; i++) {
			System.out.println(i + "의 개수 : " + counter[i]);
		} // i for
		
 	} // main

} // class
