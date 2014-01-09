import java.net.*; 
import java.util.Collections;
import java.io.*; 


public class TCPsocket extends Thread{

	public static boolean prevConnected = false;
	public static boolean nextConnected = false;
	public static Data myData = null;
	public static SocketForNext next_socket = null;
	public static SocketForPrev prev_socket = null;
	public static String nodeName = "0";
	
	// both server socket for the previous node 
	// client socket for the next node
	public static void main(String[] args) throws IOException {


		
		
		
		/*// test murmur hash
		System.out.println( myData.murmurHashString("Hello my name is irene !"));
		System.out.println( myData.murmurHashBytes(myData.longToBytes(myData.murmurHashString("Hello my name is irene !"))));
		
		byte[] content = myData.longToBytes(12345);
		MurmurHash.hash32(content, content.length );
		
		System.out.println(MurmurHash.hash32(myData.longToBytes(MurmurHash.hash32(content, content.length, 1 )), myData.longToBytes(MurmurHash.hash32(content, content.length, 1 )).length, 2));
		System.out.println(MurmurHash.hash32(myData.longToBytes(MurmurHash.hash32(content, content.length, 2 )), myData.longToBytes(MurmurHash.hash32(content, content.length, 2 )).length, 1));
		*/
		
		
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
		
		
		while (prevConnected != true || nextConnected != true){
			;
		}
		// from here connected
		// read in file line by line
		// E(m) encrypt line by line murmur encrypt
		// P(E(m)) shuffle
		myData = new Data();
		myData.readInFile();
		myData.encryptMyFile();
		myData.shuffleMyEncFile();
		
		
		// send to next node
		Msg mymsg = Msg.createMyNewFileMsg();
		next_socket.sendObjToNextNode(mymsg);
		
		stdIn.close();
	}
	
	
	
	
	
}
