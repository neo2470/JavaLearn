package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadTest {

	public static void main(String[] args) {
		
		new MyThread().start();
		
		new Thread(new MyThread1()).start();
		
		MyThread2 myThread2 = new MyThread2();
		ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
		Future<Integer> mFuture = mExecutorService.submit(myThread2);
		
		try {
			System.out.println(mFuture.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class MyThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("By extending Thread");
	}
}

class MyThread1 implements Runnable {

	@Override
	public void run() {
		System.out.println("By implementing Runnable");
	}
}

class MyThread2 implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("By implementing Callable");
		return 1990;
	}
}