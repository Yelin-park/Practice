package java_jengsuk;

public class InterfaceText3 {

	public static void main(String[] args) {
		A a = new A();
		a.methodA();
		
	} // main

} // class

class A{
	void methodA( ) {
		I i = InstanceManager.getInstance();
		i.methodB();
		System.out.println(i.toString()); // i 로 Object 클래스의 메서드 호출가능
	}
} // class A

interface I {
	public abstract void methodB();
} // interface I

class B implements I {

	@Override
	public void methodB() {
		System.out.println("methodB in B class");
	}
	
	public String toString() {return "class B";}
	
} // class B

class InstanceManager { // 관리하기 위한 용도의 클래스

	public static I getInstance() { // 리턴타입이 인터페이스
		return new B();
	}
	
} // class InstanceManager