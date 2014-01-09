import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class Data {
	
	public String fileName;
	public ArrayList<String> myFile = null;
	public ArrayList<Long> encryptedMyFile = null;
	
	
	public Data(){
		fileName = "src/file.txt";
		myFile = new ArrayList<String>();
		encryptedMyFile = new ArrayList<Long>();
	}
	
	public void readInFile(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = reader.readLine()) != null) {
			    // put in to myFile
				myFile.add(line);
			}
			System.out.println("read file end. Total line num = " + myFile.size());
		} catch (FileNotFoundException e) {
			System.out.println("[Error] readInFile, file not found!");
		} catch (IOException e) {
			System.out.println("[Error] readInFile, IOException!");
		}
	}
	
	
	public void encryptMyFile(){
		if (encryptedMyFile.size() != 0){
			System.out.println("[Data] Already encrypted!");
			return;
		}
		for (String line : this.myFile){
			this.encryptedMyFile.add(murmurHashString(line));
		}
		System.out.println("[Data] Got my file encrypted!");
	}
	
	public long murmurHashString(String str){
		// use murmurhash 64 
		return MurmurHash.hash64(str);
	}
	public long murmurHashBytes(byte[] data){
		int length = data.length;
		return MurmurHash.hash64(data, length);
	}
	
	public void shuffleLineOrder(){
		
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
}
