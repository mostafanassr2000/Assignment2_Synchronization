import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Router {
	/*Attributes*/
	private int N;
	private Semaphore S;
	private FileWriter logs;
	private String logOutput;
	ArrayList<Device> devices = new ArrayList<Device>();

	/*Constructor*/
	Router(int N) {
		this.N = N;
		S = new Semaphore(N);
	}

	/*Methods*/
	void addDevice(Device device) {
		devices.add(device);
	}

	public synchronized void connect(Device device) throws InterruptedException, IOException {
		S.occupy(device);

		logOutput = "Connection " + device.getConnection() + ": " + device.getName() + " Occupied";
		writeToLogs(logOutput);			
	}

	public void perform(Device device) throws InterruptedException, IOException {
		logOutput = "Connection " + device.getConnection() + ": " + device.getName() + " performs online activity";
		writeToLogs(logOutput);		
		
		Thread.sleep(500);	//Sleeps for 0.5s
	}

	public void logout(Device device) throws IOException {
		logOutput = "Connection " + device.getConnection() + ": " + device.getName() + " Logged out";
		writeToLogs(logOutput);		
		
		S.release(device);
	}
	
	public void writeToLogs(String logOutput) throws IOException {
		System.out.println(logOutput);
		JOptionPane.showMessageDialog(new JFrame(), logOutput);
		logs = new FileWriter("Logs.txt", true);
		logs.write(logOutput + System.lineSeparator());
		logs.close();
	}
	
}
