package math;

public class BitTricks {

	/**
	 * 乘以2{n*2}
	 * @param n
	 * @return
	 */
	public static int mul2(int n) {
		return n << 1;
	}

	/**
	 * 除以2{n/2}
	 * @param n
	 * @return
	 */
	public static int div2(int n) {
		return n >> 1;
	}

	/**
	 * n乘以2的m次方{n*2^m}
	 * @param n
	 * @param m
	 * @return
	 */
	public static int mul2Power(int n, int m) {
		return n << m;
	}
	
	/**
	 * n除以2的m次方{n/2^m}
	 * @param n
	 * @param m
	 * @return
	 */
	public static int div2Power(int n, int m) {
		return n >> m;
	}

	/**
	 * 判断一个数是否为奇数
	 * @param n
	 * @return
	 */
	public static boolean isOdd(int n) {
		return 1 == (n & 1);
	}

	/**
	 * 不用临时变量交换2个数
	 */
	public static void swap() {
		int a = 1;
		int b = 2;
		System.out.println("Before : " + a + ", " + b);
		
		a ^= b;
		b ^= a;
		a ^= b;
		System.out.println("After : " + a + ", " + b);
	}

	public static void main(String[] args) {
		System.out.println("mul2(2) : " + mul2(2));
		System.out.println("div2(2) : " + div2(2));
		System.out.println("mul2Power(2, 2) : " + mul2Power(2, 2));
		System.out.println("div2Power(512, 2) : " + div2Power(512, 2));
		System.out.println("isOdd(465864524) : " + isOdd(465864524));
		System.out.println("isOdd(156460153) : " + isOdd(156460153));
		swap();
	}
}
