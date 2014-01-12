import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketForServer extends Thread{
	// this socket is used to exchange public keys and get n
	// this is a TCP server socket, wait for the server node to connect
	
	private Socket servSocket = null;
	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;
	
	public SocketForServer(){
		
		
		start();
	}
	
	public void run(){
		

	    System.out.println ("Waiting for Server node connection ....");
	    try { 
	    	servSocket = TCPsocket.listenSocket.accept(); 
			outputStream = new ObjectOutputStream(servSocket.getOutputStream());
			inputStream = new ObjectInputStream(servSocket.getInputStream());
	    } 
	    catch (IOException e) { 
	    	System.err.println("Accept server failed."); 
	    	System.exit(1); 
	    } 
	    
	    System.out.println ("[Server Node] Connected!");
	    try {
			TCPsocket.listenSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Close listen socket failed."); 
		}
	    
	    
	    // generate public key
	    // send to server
	    Msg myPubMsg = Msg.createMyPubKeyMsg(TCPsocket.myData.publicKey);
	    this.sendObjToServerNode(myPubMsg);
	    
	    this.waitingForServerMsgObj();
	    
	}
	

	

	private void waitingForServerMsgObj(){
		Msg msgIn = null;
		try {
			while((msgIn = (Msg) inputStream.readObject())!= null){
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendObjToServerNode(Msg obj){
			try {
				outputStream.writeObject(obj);
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
