package practice;

public class Pr05_07 {

	public static void main(String[] args) {
		// 주민등록번호로 출생지역 파악하기
		// 출력결과 : 출생지역은 "서울특별시"입니다.
		String rrn = days10.Ex05_02.getRRN();
		
		String birthPlace = rrn.substring(8, 10);
		
		String 서울특별시 = "0[0-8]"; 	 			// 00~08
		String 부산광역시 = "09|1[0-2]"; 			// 09~12
		String 인천광역시 = "1[3-5]";    			// 13~15
		String 경기도 = "1[6-9]|2[0-5]"; 			// 16~25
		String 강원도 = "2[6-9]|3[0-4]"; 			// 26~34
		String 충청북도 = "3[5-9]"; 	 			// 35~39
		String 대전광역시 = "4[0-1]";   			// 40~41
		String 충청남도 = "4[2-3]|4[5-7]";      	// 42~47
		String 충청남도세종특별자치시 = "44"; 		// 44, 96  -> 44 : 충청남도랑 중복
		String 세종특별자치시 = "96";				// 44, 96  ->      충청남도랑 중복
		String 전라북도 = "4[8-9]|5[0-4]"; 			// 48~54
		String 전라남도 = "5[7-9]|6[0-6]"; 			// 55~66
		String 광주광역시 = "55|56";      			// 55, 56 -> 전라남도랑 중복
		String 대구광역시 = "6[7-9]|76";  			// 67~69, 76
		String 경상북도 = "7[0-5]|7[7-9]|8[0-1]";   // 70~75, 77~81
		String 경상남도 = "8[2-4]|8[6-9]|9[0-2]";   // 82~84, 86~89, 90~92
		String 경상남도울산광역시 = "90";			// 85, 90  -> 90 : 경상남도랑 중복
		String 울산광역시 = "85"; 					// 85, 90  -> 90 : 경상남도랑 중복
		String 제주특별자치도 = "9[3-5]"; 			// 93~95

		String bp ="";
		if(birthPlace.matches(서울특별시)) 							bp = "서울특별시";
		else if(birthPlace.matches(부산광역시)) 					bp = "부산광역시";
		else if(birthPlace.matches(인천광역시)) 					bp = "인천광역시";
		else if(birthPlace.matches(경기도)) 						bp = "경기도";
		else if(birthPlace.matches(강원도))							bp = "강원도";
		else if(birthPlace.matches(충청북도)) 						bp = "충청북도";
		else if(birthPlace.matches(대전광역시)) 					bp = "대전광역시";
		else if(birthPlace.matches(충청남도)) 						bp = "충청남도";
		else if(birthPlace.matches(충청남도세종특별자치시)) 		bp = "충청남도 세종특별자치시";
		else if(birthPlace.matches(세종특별자치시)) 				bp = "세종특별자치시";
		else if(birthPlace.matches(전라북도)) 						bp = "전라북도";
		else if(birthPlace.matches(전라남도)) 						bp = "전라남도";
		else if(birthPlace.matches(광주광역시)) 					bp = "전라남도 광주광역시";
		else if(birthPlace.matches(대구광역시)) 					bp = "대구광역시";
		else if(birthPlace.matches(경상북도)) 						bp = "경상북도";
		else if(birthPlace.matches(경상남도)) 						bp = "경상남도";
		else if(birthPlace.matches(경상남도울산광역시)) 			bp = "경상남도 울산광역시";
		else if(birthPlace.matches(울산광역시)) 					bp = "울산광역시";
		else if(birthPlace.matches(제주특별자치도))					bp = "제주특별자치도";
		
		System.out.printf("출생지역은 \"%s\"입니다.", bp);
		
	} // main

} // class
