import java.util.ArrayList;


public class Msg {
	
	public String type = "";
	public ArrayList<String> encryptedBy = null;
	public ArrayList<Long> content = null;
	
	public Msg(){
		type = "";
		encryptedBy = new ArrayList<String>();
		content = new ArrayList<Long>();
	}
	
	public Msg(String t, ArrayList<String> by, ArrayList<Long> con ){
		type = t;
		encryptedBy = by;
		content = con;
	}
	
	public static Msg createMyNewFileMsg(){
		String t_tmp = "MsgNeedEncryption";
		ArrayList<String> by_tmp = new ArrayList<String>();
		by_tmp.add(TCPsocket.nodeName);
		ArrayList<Long> con_tmp = TCPsocket.myData.encryptedMyFile;
		return new Msg(t_tmp,by_tmp, con_tmp );
	}
}
