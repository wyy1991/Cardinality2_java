import java.net.*; 
import java.io.*; 

public class SocketForPrev extends Thread {
	
	private Socket prevSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
	
	
	private String prevNodeIP = "127.0.0.1";
	private int prevNodePort = 0;
	
	public SocketForPrev(String ip, int port){
		this.prevNodeIP = ip;
		this.prevNodePort = port;
		start();
	}
	
	public void sendToPrevNode(String msg){
		 out.println(msg);
		 System.out.println("[To Prev Node]" + msg);
	}
	
	public String readFromPrevNode(){
		String inLine = null;
		try {
			inLine = in.readLine();
			System.out.println("[From Prev Node]" + inLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inLine;
	}
	
	public void closeSocekt(){
		try {
			in.close();
			out.close();
			prevSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
	        System.out.println ("Attemping to connect to host " +
			this.prevNodeIP + " on port " + this.prevNodePort + ".");

	        try {
	            this.prevSocket = new Socket(this.prevNodeIP, this.prevNodePort);
	            out = new PrintWriter(this.prevSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(this.prevSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: " + this.prevNodeIP);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for "
	                               + "the connection to: " + this.prevNodeIP);
	            System.exit(1);
	        }
	        System.out.println("[Previous node] connected!");
	        TCPsocket.prevConnected = true;
	        
	        // start waiting for prev node 
	        waitingForPrevNodeMsg();

	}
	
	public void pendingMsgFromPrev(String rawMsg){
		// check message
		System.out.println("[From Prev]" + rawMsg);
	}
	
	public void waitingForPrevNodeMsg(){
		String inputLine = null;
		System.out.println("Waiting for prev node to send msg ...");
		try {
			while ((inputLine = in.readLine()) != null){ 
				pendingMsgFromPrev(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
