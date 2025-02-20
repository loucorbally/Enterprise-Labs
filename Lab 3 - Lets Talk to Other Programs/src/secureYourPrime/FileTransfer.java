package secureYourPrime;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

public class FileTransfer implements Runnable {
	PrintWriter writer;
	Cipher cipher;
	
	public FileTransfer(PrintWriter writer, Cipher cipher) {
		this.writer = writer;
		this.cipher = cipher;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String path = "/Users/eir/eclipse-workspace/YourPrime/src/secretFile.txt";
			writeFile(path);
			
			// transfer file via socket 
			FileInputStream fileInputStream = new FileInputStream(path);
			PrintWriter printwriter = writer;
			int c;
	        while ((c = fileInputStream.read()) != -1) {
	        		printwriter.println(c);
	        		printwriter.flush();
	        }
	        fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeFile(String path) throws IOException {
		String secretMessage = "I love CT5168, this is the best module in the program";
		FileOutputStream fileOutputStream;

		// write file using file writer and cipher
		fileOutputStream = new FileOutputStream(path);
		CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
		cipherOutputStream.write(secretMessage.getBytes());
		cipherOutputStream.close();
		fileOutputStream.close();
	}
}
