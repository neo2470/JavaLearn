package thread;

public class CounterTest {

	public static void main(String[] args) {
		MyCounterThread counter = new MyCounterThread();
		counter.start();
	}
}

class MyCounterThread extends Thread {

	@Override
	public void run() {
		super.run();
		while(10 > count) {
			try {
				Thread.sleep(1000);
				++count;
				System.out.println(count+"");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void start() {
		super.start();
		System.out.println("启动计数器...");
	}
	
	private int count;	
}
