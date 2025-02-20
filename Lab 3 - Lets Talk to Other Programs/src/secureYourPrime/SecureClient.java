package secureYourPrime;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import mainYourPrime.MyMedia;
import mainYourPrime.Subscriber;

public class SecureClient {
	private SecretKey secretKey;
	private Cipher cipher;

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeySpecException {

		// Create a test user for login
		Subscriber testUser = new Subscriber("TestUser", "test", "password", new MyMedia(null, null, null));
		SecureClient primeClient = new SecureClient();

		try {
			// Attempt to log in with the test user
			primeClient.login(testUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Subscriber user) throws UnknownHostException, IOException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException {

		// Establish a connection to the server at localhost on port 4242
		Socket socket = new Socket("127.0.0.1", 4242);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());

		// Send login request to the server
		System.out.println("Requesting access to the server\n");
		writer.println(getMessage(user.getUserID(), user.getPassword())); // Send encrypted credentials
		writer.flush();

		// Read the server's response
		InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(streamReader);
		String message = reader.readLine();
		System.out.println(message + "\n");

		// If access is granted, proceed with file encryption and transmission
		if (message.equals("Access granted")) {
			String path = "/Users/louisecorbally/Desktop/College/Enterprise Java/Labs/Lab 3 - Lets Talk to Other Programs/src/secretFile.txt";
			System.out.println("Creating secret file, and transmitting the file over socket\n");
			writeFile(path);  // Encrypt and write the secret message to a file
			transmitFile(socket, path); // Send the encrypted file over the network
			System.out.println("Transmission completed\n");
		}

		// Close the reader and socket connection
		reader.close();
		socket.close();
	}

	public void setCipher(String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException {
		// Initialize encryption cipher using a secret key
		this.secretKey = KeyGenerator.getKeyFromPassword();
		this.cipher = Cipher.getInstance(algorithm);
		this.cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	}

	// Encrypts the login credentials and returns an encrypted string
	public String getMessage(String user, String password) throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		setCipher("AES/ECB/PKCS5Padding"); // Initialize cipher with AES encryption mode
		String data = "LOGIN-" + user + "-" + password; // Create login request format
		byte[] cipherText;
		cipherText = cipher.doFinal(data.getBytes("UTF-8")); // Encrypt the data
		String encryptedString = Base64.getEncoder().encodeToString(cipherText); // Convert to Base64 for transmission
		return encryptedString;
	}

	// Encrypts and writes a secret message to a file
	public void writeFile(String path) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException {

		setCipher("AES/CBC/PKCS5Padding"); // Set encryption mode with CBC and padding
		byte[] iv = cipher.getIV(); // Retrieve initialization vector

		String secretMessage = "Shh... This is a secret message";
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
		fileOutputStream.write(iv); // Write IV to file for decryption later
		cipherOutputStream.write(secretMessage.getBytes()); // Encrypt and write message to file
		cipherOutputStream.close();
	}

	// Sends an encrypted file over a network connection
	public void transmitFile(Socket socket, String path) throws IOException {
		DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
		int bytes = 0;
		File file = new File(path);
		FileInputStream fileInput = new FileInputStream(file);
		dataOutput.writeLong(file.length()); // Send file length for processing on receiver side

		byte[] buffer = new byte[16 * 1024]; // Buffer for file transfer
		while ((bytes = fileInput.read(buffer)) != -1) {
			dataOutput.write(buffer, 0, bytes); // Write data to output stream
			dataOutput.flush();
		}
		fileInput.close();
	}
}



