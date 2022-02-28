package practice;

public class Pr14 {

	public static void main(String[] args) {
		// 거듭제곱(power)
		
		double result = power(2, -3);
		System.out.println(result);
		
		result = recursivePower(2, -3);
		System.out.println(result);
		
	} // main

	// [과제] 재귀 호출 함수
	private static double recursivePower(double a, double n) {
		if(n == 0) return 1;
		if(n > 0) return a * recursivePower(a, n-1);
		else return (1 / a) * recursivePower(a, n+1);
	}

	private static double power(int a, int n) {
		double result = 1;
		int k = Math.abs(n);
		for (int i = 1; i <= k; i++) {
			result *= a;
		}
		return n < 0 ? 1/result : result;
	}
	
	


} // class
