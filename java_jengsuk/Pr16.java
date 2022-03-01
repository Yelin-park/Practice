package java_jengsuk;

import java.util.Arrays;

/**
 * @author Yelin
 * @date 2022. 2. 28. - 오후 11:56:47
 * @subject Ch5. 배열
 * @content 1) Arrays.toString() 메서드
 * 				기능 : 배열의 참조변수 값을 String (ex. [1요소 , 2요소, 3요소..])으로 가져오는 기능
 * 
 * 			2) char[] : 배열은 syso로 출력하면 각 요소가 구분자없이 그대로 출력
 * 
 * 			3) System.arraycopy() 메서드
 * 				기능 : 배열을 복사하는 기능
 * 				매개변수 : 옮길 배열명, 인덱스, 옮겨지는 배열명, 인덱스, 옮길 배열의 길이(배열명.length)
 */
public class Pr16 {

	public static void main(String[] args) {
		// 1) Arrays.toString()
		int[] sArr = {10, 20, 30, 40, 50};
		System.out.println(Arrays.toString(sArr));
		
		// 2) char[] 배열
		char[] c = {'a', 'b', 'c', 'd'};
		System.out.println(c);

		// 3) System.arraycopy()
		char[] abc = {'a', 'b', 'c', 'd'};
		char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		char[] Arr = new char[abc.length + num.length]; // 배열 abc와 num을 붙여서 하나의 배열(Arr) 만들기
		System.arraycopy(abc, 0, Arr, 0, abc.length);
		System.arraycopy(num, 0, Arr, 0, num.length);
		System.out.println(Arr);
		
		System.arraycopy(abc, 0, num, 6, 2); // num 배열의 인덱스 6위치에 abc 배열의 0부터 시작해서 2개 복사
		System.out.println(num);
		
	} // main

} // class
