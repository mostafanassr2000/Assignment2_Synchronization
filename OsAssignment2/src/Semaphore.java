import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Semaphore {
	/*Attributes*/
	private int N; 	//Initial value
	private int maxNumOfConnections;
	private FileWriter logs;
	private String logOutput;
	String[] devicesInPerformance;
	
	/*Constructor*/
	Semaphore(int N){
		this.N = N;
		//this.logs = logs;
		this.maxNumOfConnections = N;
		devicesInPerformance = new String[N];
	}

	/*Methods*/
	public synchronized void occupy(Device device)	throws InterruptedException, IOException{ 
		N--;
		
		if (N < 0) {
			
			logOutput = "(" + device.getName() + ")" + " (" + device.getType() + ")" + " arrived and waiting";
			writeToLogs(logOutput);		
			
			wait();	//Device is waiting.
			
		} else {
			logOutput = "(" + device.getName() + ")" + " (" + device.getType() + ")" + " arrived";
			writeToLogs(logOutput);		
		}
		
		//Adjusting connection numbers
		for(int i = 0; i < maxNumOfConnections; i++) {
			
			if(devicesInPerformance[i] == null) {
				device.setConnection(i+1);	
				devicesInPerformance[i] = device.getName();
				break;
			}
		}
	}

	public synchronized void release(Device device) {
		N++;
		
		if (N <= 0) {
			notify();			
		}
		
		//Adjusting connection numbers
		for(int i = 0; i < maxNumOfConnections; i++) {
			if(devicesInPerformance[i] == device.getName()) {
				devicesInPerformance[i] = null;
				break;
			}
		}
	}
	
	public void writeToLogs(String logOutput) throws IOException {
		System.out.println(logOutput);
		JOptionPane.showMessageDialog(new JFrame(), logOutput);
		logs = new FileWriter("Logs.txt", true);
		logs.write(logOutput + System.lineSeparator());
		logs.close();
	}

}
