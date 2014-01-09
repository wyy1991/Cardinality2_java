import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Data {
	
	public String fileName;
	public ArrayList<String> myFile = null;
	public ArrayList<Long> hashFile = null;
	public ArrayList<Long> encryptedMyFile = null;
	public HashMap<String, ArrayList<Long> > finalSet= null;
	
	
	public Data(){
		String fname =  TCPsocket.nodeName + ".txt";
		this.fileName = this.getClass().getResource(fname).getFile();
		System.out.println("file name:"+this.fileName);
		myFile = new ArrayList<String>();
		hashFile = new ArrayList<Long>();
		encryptedMyFile = new ArrayList<Long>();
		this.finalSet = new HashMap<String, ArrayList<Long> >();
	}
	
	public void insertFinalSet(String key, ArrayList<Long> content){
		this.finalSet.put(key, content);
		System.out.println("[Data]Stored data " + key + ".");
	}
	
	public void readInFile(){
		System.out.println("[Data] start read in file...");
		System.out.println("[Data]" + this.fileName);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = reader.readLine()) != null) {
			    // put in to myFile
				myFile.add(line);
			}
			System.out.println("[Data]read file success !");
		} catch (FileNotFoundException e) {
			System.out.println("[Error] readInFile, file not found!");
		} catch (IOException e) {
			System.out.println("[Error] readInFile, IOException!");
		}
	}
	
	
	
	public void hashMyFile(){
		if (this.hashFile.size() != 0){
			System.out.println("[Data] Already hashed!");
			return;
		}
		for (String line : this.myFile){
			this.hashFile.add(murmurHashString(line));
		}
		System.out.println("[Data] Got my file hashed!");
	}
	
	
	
	
	public void encryptMyFile(){
		if (this.encryptedMyFile.size() != 0 ){
			System.out.println("[Data] Already encrypted!");
			return;
		}
		this.encryptedMyFile = this.encryptFile(this.hashFile);
		System.out.println("[Data] Got my file encrypted!");
		System.out.println("[Test]last line:" + this.encryptedMyFile.get(this.encryptedMyFile.size()-1));
	}
	
	
	public void shuffleMyEncFile(){
		Collections.shuffle(this.encryptedMyFile);
	}
	
	public long murmurHashString(String str){
		// use murmurhash 64 
		return MurmurHash.hash64(str);
	}
	public long murmurHashBytes(byte[] data){
		int length = data.length;
		return MurmurHash.hash64(data, length);
	}
	
	
	
	public byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(8);
	    buffer.putLong(x);
	    return buffer.array();
	}

	public long bytesToLong(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(8);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getLong();
	}
	
	public static Long encrypt(long val){
		// todo add PohligHellMan encryption
		return val;
	}
	public static ArrayList<Long> shuffle(ArrayList<Long> file){
		Collections.shuffle(file);
		return file;
	}
	
	public static ArrayList<Long> encryptFile(ArrayList<Long> file){
		ArrayList<Long> encFile  = new ArrayList<Long>();
		for (Long val : file){
			encFile.add(encrypt(val));
		}
		return encFile;
	}
}
