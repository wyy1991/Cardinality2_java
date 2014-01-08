import java.net.*; 
import java.io.*; 

public class SocketForNext extends Thread{
	
	private Socket nextSocket = null;
	private int port = 10000;
	
	public SocketForNext(){
		this.nextSocket = null;
		this.port = 10000;
		start();
		
	}
	
	public void run(){
		ServerSocket serverSocket = null; 
		boolean portNumNotFound = true;
		while (portNumNotFound){
			try { 
				this.port += 1;
				serverSocket = new ServerSocket(port); 
				portNumNotFound = false;
		    } 
		    catch (IOException e){ 
		         System.err.println("Could not listen on port:" + port); 
		         portNumNotFound = true;
		    } 
		}
		//System.out.println("IP:Host = " + getMyIP() + ":" + this.port);
		System.out.println("IP:Host = " + "127.0.0.1" + ":" + this.port);
	    System.out.println ("Waiting for connection.....");

	    try { 
	    	nextSocket = serverSocket.accept(); 
	    } 
	    catch (IOException e) { 
	    	System.err.println("Accept failed."); 
	    	System.exit(1); 
	    } 
	    
	    System.out.println ("Connection to next node successful!");

	}
	
	public String getMyIP(){
		try{
			String addr = InetAddress.getLocalHost().getHostAddress();
			
			return addr;
		}catch (UnknownHostException e) {
			return null;
		}
	}
}
