package thread;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CounterWithUITest extends Frame implements Runnable, ActionListener, WindowListener {
	
	public CounterWithUITest() {
		button = new Button("開始");
		button.addActionListener(this);
		label = new Label("0", Label.CENTER);
		label.setFont(new Font("", 1, 100));
		
		setLayout(new BorderLayout());
		add(button, "South");
		add(label, "Center");
		addWindowListener(this);
		
		counter = new Thread(this);
		
		setTitle("計數器");
		setSize(320, 480);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CounterWithUITest();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		++click;
		
		if (1 == (click & 1)) {
			counter.start();
			button.setLabel("結束");
		} else {
			isRunning = false;
			button.setLabel("開始");
			if(2 == click) {
				button.setEnabled(false);
			}
		}
	}

	@Override
	public void run() {
		while(isRunning) {
			try {
				++count;
				Thread.sleep(1000);
				label.setText(count+"");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	private int count;
	private int click;
	
	private Label label;
	private Button button;
	private Thread counter;
	private boolean isRunning = true;
}
