import java.net.*; 
import java.io.*; 


public class TCPsocket extends Thread{
	// both server socket for the previous node 
	// client socket for the next node
	public static void main(String[] args) throws IOException {
		// open up a new socket to communicate with next node
		SocketForNext next_socket = new SocketForNext();
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
		
		SocketForPrev prev_socket = new SocketForPrev(ipPrev, portPrev);
	}
	
	
}
