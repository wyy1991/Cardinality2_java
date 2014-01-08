import java.net.*; 
import java.io.*; 

public class SocketForPrev extends Thread {
	
	public String prevNodeIP = "127.0.0.1";
	public int prevNodePort = 0;
	
	public SocketForPrev(String ip, int port){
		this.prevNodeIP = ip;
		this.prevNodePort = port;
		start();
	}
	
	public void run(){

	        System.out.println ("Attemping to connect to host " +
			this.prevNodeIP + " on port " + this.prevNodePort + ".");

	        Socket echoSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;

	        try {
	            // echoSocket = new Socket("taranis", 7);
	            echoSocket = new Socket(this.prevNodeIP, this.prevNodePort);
	            out = new PrintWriter(echoSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(
	                                        echoSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: " + this.prevNodeIP);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for "
	                               + "the connection to: " + this.prevNodeIP);
	            System.exit(1);
	        }
	        System.out.println("Connected to previous node.");

	}
}
