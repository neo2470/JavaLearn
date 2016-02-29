package thread;

public class UnsynBankTest {
	
	public static void main(String[] args) {
		Bank bank = new Bank(NACCOUNTS, INIT_BALANCE);
		
		for(int i=0; i<NACCOUNTS; ++i) {
			new Thread(new TransferRunnable(bank, i, INIT_BALANCE)).start();
		}
	}
	
	private static final int NACCOUNTS = 10;
	private static final double INIT_BALANCE = 1000;
}

class Bank {
	
	public Bank(int n, double initBalance) {
		accounts = new double[n];
		
		for(int i=0; i<n; ++i) {
			accounts[i] = initBalance;
		}
	}
	
	public void transfer(int from, int to, double amount) {
		if(amount > accounts[from]) {
			System.err.println("账户 " + from + " 余额不足");
			return ;
		}
		
		accounts[from] -= amount;
		accounts[to] += amount;
		System.out.printf("从 账户%d 转 %10.2f 至 账户%d，银行总资产 %10.2f\n", from, amount, to, getTotalBalance());
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
}

class TransferRunnable implements Runnable {
	
	public TransferRunnable(Bank bank, int from, double maxAmount) {
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
	
	private Bank bank;
	private int from;
	private double maxAmount;
	private final int DELAY = 10;
}
