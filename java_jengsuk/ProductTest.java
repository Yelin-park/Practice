package java_jengsuk;

public class ProductTest {

	public static void main(String[] args) {
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		
		System.out.println("p1의 제품번호는" + p1.serialNo + "입니다.");
		System.out.println("p2의 제품번호는" + p2.serialNo + "입니다.");
		System.out.println("p3의 제품번호는" + p3.serialNo + "입니다.");

	} // main

} // class

class Product {
	static int count = 0;
	int serialNo;
	
	{
		++count;
		serialNo = count;
	} // 인스턴스 초기화 블럭
	
	public Product() {} // 기본생성자
	
} // Product class