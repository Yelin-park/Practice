package java_jengsuk;

import java.util.Arrays;

public class ArrayEx09 {

	public static void main(String[] args) {
		// 임의의 값으로 배열 채우기
		int[] code = {-4, -1, 3, 6, 11};
		int[] arr = new int[10];
		
		for (int i = 0; i < arr.length; i++) {
			int temp = (int)(Math.random() * code.length); // code 배열의 길이 => 5 이므로, 0 ~ 4까지의 숫자를 랜덤하게 생성
			arr[i] = code[temp]; // arr 배열 i값의 인덱스 순서대로 code[임의의 인덱스(0~4)] 값이 들어가도록 코딩
		}
		
		System.out.println(Arrays.toString(arr)); // 배열의 요소를 문자열로 가져오기

	} // main

} // class
