import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Network {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		int N, TC;
		String n, tc;
		String input;
		String[] DeviceArr = new String[2];
		 			
		n  = JOptionPane.showInputDialog("What is the number of WI-FI Connections ?");
		tc = JOptionPane.showInputDialog("What is the number of devices clients want to connect ?");
		
		N = Integer.parseInt(n);	
		TC = Integer.parseInt(tc);
		

		Router router = new Router(N);

		for (int i = 0; i < TC; i++) {	 //Storing device info in the Arraylist
			
			input = JOptionPane.showInputDialog("Enter type and name of device" + (i+1));
			DeviceArr = input.split(" ");

			Device device = new Device(router, DeviceArr[0], DeviceArr[1]);
			router.devices.add(device);
		}

		for (int i = 0; i < TC; i++) {
			Thread thread = new Thread(router.devices.get(i));
			thread.start();
		}
		
		scanner.close();
	}

}
