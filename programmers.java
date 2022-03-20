package practice;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.regex.Matcher;

public class programmers {

	public static void main(String[] args) {
		// s 문자열이 들어오면 숫자로 반환
		long start = System.nanoTime();
		Solution s = new Solution();
		System.out.println(s.solution("one4seveneight"));  // 1478
		System.out.println(s.solution("23four5six7"));		// 234567
		System.out.println(s.solution("2three45sixseven")); // 234567
		System.out.println(s.solution("123"));	// 123
		System.out.println(s.solution("sixtwotwofive"));
		long end = System.nanoTime();
		System.out.println("> 박예린 코딩 처리 시간 : " + (end-start) + "ns" );

		System.out.println("-------------------------------------");
		
		start = System.nanoTime();
		Solution_kim sk = new Solution_kim();
		System.out.println(sk.solution("one4seveneight"));
		System.out.println(sk.solution("23four5six7"));		// 234567
		System.out.println(sk.solution("2three45sixseven")); // 234567
		System.out.println(sk.solution("123"));	// 123
		System.out.println(sk.solution("sixtwotwofive"));
		end = System.nanoTime();
		System.out.println("> 김민님 코딩 처리 시간 : " + (end-start) + "ns" );

		System.out.println("-------------------------------------");
		
		start = System.nanoTime();
		Solution_kim2 sk2 = new Solution_kim2();
		System.out.println(sk2.solution("one4seveneight"));
		System.out.println(sk2.solution("23four5six7"));		// 234567
		System.out.println(sk2.solution("2three45sixseven")); // 234567
		System.out.println(sk2.solution("123"));	// 123
		System.out.println(sk2.solution("sixtwotwofive"));
		end = System.nanoTime();
		System.out.println("> 예린 추가 코딩 처리 시간 : " + (end-start) + "ns" );
		
		System.out.println("-------------------------------------");
		
		start = System.nanoTime();
		Solution1 sk3 = new Solution1();
		System.out.println(sk3.solution("one4seveneight"));
		System.out.println(sk3.solution("23four5six7"));		// 234567
		System.out.println(sk3.solution("2three45sixseven")); // 234567
		System.out.println(sk3.solution("123"));	// 123
		System.out.println(sk3.solution("sixtwotwofive"));
		end = System.nanoTime();
		System.out.println("> 민님 추가 코딩 처리 시간 : " + (end-start) + "ns" );
		
		System.out.println("-------------------------------------");
		
		start = System.nanoTime();
		Solution_baek sb = new Solution_baek();
		System.out.println(sb.solution("one4seveneight"));
		System.out.println(sb.solution("23four5six7"));		// 234567
		System.out.println(sb.solution("2three45sixseven")); // 234567
		System.out.println(sb.solution("123"));	// 123
		System.out.println(sb.solution("sixtwotwofive"));
		end = System.nanoTime();
		System.out.println("> 백경환님 코딩 처리 시간 : " + (end-start) + "ns" );

		
        
	} // main

} // class

class Solution1{
public int solution(String s) {
      
    int answer;
    String [] numArray = {"zero","one","two","three","four","five","six","seven","eight","nine"};
     int i = 0;
     int startIdx, endIdx;
     
     String s2 = String.join("", s.split("\\d"));       
     Pattern p = Pattern.compile("[a-z]+");    
     Matcher m = p.matcher(s2);           
     
     StringBuffer sb = new StringBuffer(s);  
     
  
        while( m.matches() ) { // while문을 돈다.
           if((startIdx = sb.indexOf(numArray[i])) != -1) {
              endIdx = startIdx + numArray[i].length();
              sb.replace(startIdx , endIdx ,i+"");
           } // if
           else i++;
           m = p.matcher(String.join("", sb.toString().split("\\d")));    // 남은 문자열 숫자로 자르고 matcher메서드사용
        } // while
        answer = Integer.parseInt(sb.toString()); 
        
     
     
     return answer;
}

 } // solution

class Solution_kim2 {
	
	public int solution(String s) {
	
    	int answer;
    	String [] numArray = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        int i = 0;
        int startIdx, endIdx;
        
		String[] arr = s.split("\\d"); 	  		// 숫자로 잘라서 String 배열에 넣고
		String s2 = String.join("", arr); 		// 다시 문자열로 만들어서 s2에 넣기
        Pattern p = Pattern.compile("[a-z]+"); 	// 패턴 객체 생성
        Matcher m = p.matcher(s2);				// 패턴 객체와 일치하는지 체크하는 Matcher 객체 m
        
        StringBuffer sb = new StringBuffer(s);  
        
        if(m.matches()) { // 문자열 안에 영어가 있으면 true가 나오고
        	while(i != numArray.length ) { // while문을 돈다.
        		if((startIdx = sb.indexOf(numArray[i])) != -1) {
        			endIdx = startIdx + numArray[i].length();
        			sb.replace(startIdx , endIdx ,i+"");
        		} // if
        		else i++;
        	} // while
        	answer = Integer.parseInt(sb.toString()); // 돌아서 answer 변수에 int 형으로 변환
        } else {
        	answer = Integer.parseInt(sb.toString()); // 문자열 안에 영어가 없으면 false가 나와서 바로 int형으로 변환
        }
        
        return answer;

    } // solution
	
} // class

class Solution {
    public int solution(String s) {
        int answer = 0;
        String sAnswer = "";
        StringBuffer sb = new StringBuffer(s);
        String[] sEng = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] sNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        
        int num = 0;
        int idx;
        if(1 <= s.length() && s.length() <= 50) { // 1 ≤ s의 길이 ≤ 50
        	while(num != sEng.length ) { // 문자열 안에 중복되는 단어를 다 바꾸기 전까지 while문을 돌리기
        		idx = sb.indexOf(sEng[num]); // 찾고자 하는 단어의 시작 인덱스 값 저장
        		if( idx == -1) { // 찾는 단어가 없으면 -1 이 나오고
        			num++; // num 증가
        		} else{
        			sb.replace(idx, idx + sEng[num].length(), sNum[num]); // 찾고자 하는 단어를 숫자 문자열로 바꾸기(sNum 배열)
        		} // if-else
        	} // while
        	 answer = Integer.parseInt(sb.toString()); // sb 변수를 문자열로 바꾼 뒤 int 자료형으로 변환하여 answer 변수에 담기
        } // if
       
        return answer;
    } // solution
    
} // Solution

class Solution_baek {
	public static int solution(String s) {
    	String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    	int start, end;
		StringBuffer sb = new StringBuffer(s); 
		for (int i = 0; i < numbers.length; i++) { 
			while (sb.indexOf(numbers[i]) >= 0) { 
				start = sb.indexOf(numbers[i]); 
				end = start + numbers[i].length(); 
				sb.replace(start, end, ""+i); 
			}	
		}	
		int answer = Integer.parseInt(sb.toString());
		return answer;
	}
}

class Solution_kim {
    public int solution(String s) {
       // "one4seveneight" -> 1478 
       //  "23four5six7"      -> 234567
       //  "1zerotwozerozerozero3"  -> 1020003
       // 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열 s가 매개변수
       // s가 의미하는 원래 숫자를 return 
       String [] numArray = {"zero","one","two","three","four","five","six","seven","eight","nine"};
       StringBuffer sb = new StringBuffer(s);
       int startIdx, endIdx;
       
       for (int i = 0; i < numArray.length; i++) {
          if((startIdx = sb.indexOf(numArray[i])) != -1) {
          endIdx = startIdx + numArray[i].length();
          sb.replace(startIdx , endIdx ,(i--)+"");
          }
      }
       
        return Integer.parseInt(sb.toString());
    }
}

/*
숫자	영단어
0	zero
1	one
2	two
3	three
4	four
5	five
6	six
7	seven
8	eight
9	nine
/*
1 ≤ s의 길이 ≤ 50
s가 "zero" 또는 "0"으로 시작하는 경우는 주어지지 않습니다.
return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 s로 주어집니다.
*/