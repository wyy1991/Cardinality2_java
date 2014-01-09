import java.net.*; 
import java.util.Collections;
import java.io.*; 


public class TCPsocket extends Thread{

	public static boolean prevConnected = false;
	public static boolean nextConnected = false;
	public static Data myData = null;
	public static SocketForNext next_socket = null;
	public static SocketForPrev prev_socket = null;
	public static String nodeName = "C";
	
	// both server socket for the previous node 
	// client socket for the next node
	public static void main(String[] args) throws IOException {
		
		// open up a new socket to communicate with next node
		next_socket = new SocketForNext();
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		
		// input previous nodes ip and port to connect
		System.out.println ("Enter IP and Port of previous node (X.X.X.X:XXXX): ");
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		userInput = stdIn.readLine();
		String[] address = userInput.split(":");
		String ipPrev = address[0]; // ip
		int portPrev = Integer.parseInt(address[1]); //port
		
		prev_socket = new SocketForPrev(ipPrev, portPrev);
		
		
		while (!((prevConnected == true) && (nextConnected == true))){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
		System.out.print("Both connected !");
		// from here connected
		// read in file line by line
		// E(m) encrypt line by line murmur encrypt
		// P(E(m)) shuffle

		myData = new Data();
		myData.readInFile();
		myData.hashMyFile();
		myData.encryptMyFile();
		myData.shuffleMyEncFile();
		
		
		
		// send to next node
		Msg mymsg = Msg.createMyNewFileMsg();
		next_socket.sendObjToNextNode(mymsg);
		
		stdIn.close();
	}
	
	
	
	
	
}
