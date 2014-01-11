
public class ServerNode extends Thread{
	
	public ServerNode(){
		start();
	}
	
	public void run(){
		System.out.println("This is the server node.\nPlease input addresses, \"end\" to stop:");
	}
	
}
