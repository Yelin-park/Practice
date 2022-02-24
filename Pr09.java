package practice;

import java.util.Scanner;

public class Pr09 {

	public static void main(String[] args) {
		String password = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#%^&*][^$]{5,15}$";
		/*
		 * []                     : 범위 (범위에 해당하는 문자 하나)
		 * .                      : 문자 (공백, 한글, 숫자, 영문, 특수문자 등의 문자 하나)
		 * ?=.*[조건]         	  : 긍정형 전방탐색 (positive lookahead) -> 일치하는 문자가 있으면 값이 아닌 인덱스를 반환 -> 조건에 맞는 문자 포함 여부를 확인하는 것
		 *
		 * 1. ^                   : 시작
		 * 2. (?=.*[A-Za-z])      : 앞에 어떤문자(.)가 몇 개(*) 오든 [a-zA-Z]가 나오면 인덱스 0을 반환
		 * 3. (?=.*[0-9])         : 인덱스 0부터 시작해서 앞에 어떤문자(.)가 몇 개(*) 오든 [0-9]가 나오면 인덱스 0을 반환
		 * 4. (?=.*[!@#$%^&*])    : 인덱스 0부터 시작해서 앞에 어떤문자(.)가 몇 개(*) 오든 [0-9]가 나오면 인덱스 0을 반환
		 * 	  2 + 3 + 4 ==> 영문, 숫자, 특수문자가 하나 이상 포함되어 있음을 확인하는 구문
		 * 5. [a-zA-Z0-9!@#$%^&*] : 소문자, 대문자, 숫자, 특수문자 범위
		 * 6. [^$]                : $ 특수문자 제외
		 * 7. {5,15}              : 5 ~ 15 자리(조건에 맞는 문자 1개 이상, 범위에 해당하는 문자가 포함된 문자 5개 ~ 15개)
		 * 8. $ : 끝
		 * 
		 */
		String mypass;
		Scanner sc = new Scanner(System.in);
		
		int check = 0;
		do {
			if(check > 0) System.out.println("설정 조건을 만족하지 않습니다. 다시 입력하세요.");
			System.out.println("비밀번호를 설정하세요(5~15자리, 대소문자, 숫자, 특수문자($제외) 최소 1개 이상) > ");
			mypass = sc.next();
			check++;
		} while (!mypass.matches(password));
		
		System.out.println("비밀번호 설정이 완료되었습니다.");
				
	} // main

} // class
