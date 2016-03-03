package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UnsynBankTest2 {
	
	public static void main(String[] args) {
		Bank2 bank = new Bank2(NACCOUNTS, INIT_BALANCE);
		
		for(int i=0; i<NACCOUNTS; ++i) {
			new Thread(new TransferRunnable2(bank, i, INIT_BALANCE)).start();
		}
	}
	
	private static final int NACCOUNTS = 10;
	private static final double INIT_BALANCE = 1000;
}

class Bank2 {
	
	public Bank2(int n, double initBalance) {
		accounts = new double[n];
		for(int i=0; i<n; ++i) {
			accounts[i] = initBalance;
		}
		
		mLock = new ReentrantLock();
		sufficientFunds = mLock.newCondition();
	}
	
	public void transfer(int from, int to, double amount) {
		
		// 临界区
//		mLock.lock();
		
		if(mLock.tryLock()) {
			try {
				while(amount > accounts[from]) {
					System.err.println("账户 " + from + " 余额不足");
					sufficientFunds.await();
				}
				
				accounts[from] -= amount;
				accounts[to] += amount;
				System.out.printf("从 账户%d 转 %-5.2f 至 账户%d (余额 %-7.2f)，银行总资产 %-10.2f\n", from, amount, to, accounts[to], getTotalBalance());
				
				sufficientFunds.signalAll();
			} catch (InterruptedException e) {
				
			}finally {
				mLock.unlock();
			}
		}
	}
	
	public double getTotalBalance() {
		double sum = 0;
		for(double a : accounts) {
			sum += a;
		}
		return sum;
	}
	
	public int size() {
		return accounts.length;
	}
	
	private final double[] accounts;
	private final ReentrantLock mLock;
	private final Condition sufficientFunds;
}

class TransferRunnable2 implements Runnable {
	
	public TransferRunnable2(Bank2 bank, int from, double maxAmount) {
		this.bank = bank;
		this.from = from;
		this.maxAmount = maxAmount;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int to = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(from, to, amount);
				
				Thread.sleep((int) (DELAY * Math.random()));
			}
		} catch (InterruptedException e) {
		}
	}
	
	private Bank2 bank;
	private int from;
	private double maxAmount;
	private final int DELAY = 10;
}
