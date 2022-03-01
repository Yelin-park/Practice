package java_jengsuk;

public class ArrayEx14 {

	public static void main(String[] args) {
		String src = "ABCDEF";
		
		char[] chArr = src.toCharArray(); // String -> char 배열 변환
		System.out.println(chArr);
		
		System.out.println("-------------------------------------------------------");
		
		char[] chArr2 = { 'A', 'B', 'C' };
		String str = new String(chArr2);  // char배열 -> String 변환
		System.out.println(str);
		
	} // main

} // class
