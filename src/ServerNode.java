import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ServerNode extends Thread{
	
	public ArrayList<SocketFromServer> socketList = null;
	
	public ServerNode(){
		socketList = new ArrayList<SocketFromServer>();
		start();
	}
	
	public void run(){
		System.out.println("This is the server node.\nPlease input addresses, \"end\" to stop:");
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		
		try {
			while ((userInput = stdIn.readLine()) != null){
				if (userInput.equals("s")){
					// start to calculate pub and n
					System.out.println("[Server Node] Start calculating.");
					//start send pub and n to all node
					
					
				}else{
					String[] address = userInput.split(":");
					String ipNode = address[0]; // ip
					int portNode = Integer.parseInt(address[1]); //port
					socketList.add(new SocketFromServer(ipNode, portNode));
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
