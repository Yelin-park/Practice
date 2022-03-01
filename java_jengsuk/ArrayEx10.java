package java_jengsuk;

public class ArrayEx10 {

	public static void main(String[] args) {
		// 배열의 버블정렬
		int[] numArr = new int[10];
		
		for (int i = 0; i < numArr.length; i++) {
			System.out.print(numArr[i] = (int)(Math.random() * 10));
		} // i for
		
		System.out.println();
		
		for (int i = 0; i < numArr.length-1; i++) {
			boolean changed = false; // 자리 바꿈이 발생했는지 체크 > 매 반복문마다 changed를 false로 초기화
			
			for (int j = 0; j < numArr.length-1-i; j++) {
				if(numArr[j] > numArr[j+1]) { // 다음 인덱스에 있는 값이 작으면 자리를 바꾼다
					int temp = numArr[j];
					numArr[j] = numArr[j+1];
					numArr[j+1] = temp;
					changed = true; // 자리를 바꿨으니 true로 
				} // if
			
			} // j for
			
			if(!changed) break; // 자리 바꿈이 없으면 반복문을 종료
			
		} // i for
		
		for (int k = 0; k < numArr.length; k++) { // k for문 코드를 두번째 i for문이 끝나기 전에 넣으면 정렬이 되는 순서 확인 가능
			System.out.print(numArr[k]);
			
		}// k for

	} // main

} // class
