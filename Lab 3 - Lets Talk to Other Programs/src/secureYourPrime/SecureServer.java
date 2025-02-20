package secureYourPrime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecureServer {
	private Map<String, String> userDb = new HashMap<>(); // Stores user credentials (username -> password)
	private Map<String, Double> feesDb = new HashMap<>(); // Stores user fees (username -> outstanding fees)
	private SecretKey secretKey; // Secret key for encryption and decryption
	private Cipher cipher; // Cipher instance for handling encryption operations

	public static void main(String[] args) throws NoSuchAlgorithmException,
			InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException {

		// Create an instance of SecureServer
		SecureServer server = new SecureServer();
		try {
			server.go(); // Start the server
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void go() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeySpecException {

		// Create a server socket listening on port 4242
		try (ServerSocket serverSock = new ServerSocket(4242)) {
			while (true) {
				// Accept incoming client connection
				Socket s = serverSock.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
				PrintWriter writer = new PrintWriter(s.getOutputStream());

				// TODO: Process authentication using the encrypted message received from the client.
				// The message needs to be decoded and passed to the authenticate() method.

				// TODO: Create a new thread to handle encrypted file transfer from the client.
				// Since the server may handle multiple client requests simultaneously, this should
				// be implemented as a multithreaded operation.

				// Set up decryption settings using AES algorithm
				setSecurity("AES/ECB/PKCS5Padding");

				// Read the encrypted message from the client and decode it from Base64
				byte[] cipherText;
				cipherText = cipher.doFinal(Base64.getDecoder().decode(input.readLine()));

				// Convert decrypted byte array to string and authenticate the user
				String message = authenticate(new String(cipherText));
				writer.println(message); // Send authentication response to the client
				writer.flush();

				// Start a new thread to handle file reception from the client
				Thread tfThread = new Thread(new FileReceiver(s, input, secretKey, cipher));
				tfThread.start();
			}
		}
	}

	private void setSecurity(String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidKeyException {
		// TODO: Decode and then decipher the message for secure communication
		this.secretKey = KeyGenerator.getKeyFromPassword(); // Generate a secret key for decryption
		this.cipher = Cipher.getInstance(algorithm); // Initialize the cipher with the given algorithm
		this.cipher.init(Cipher.DECRYPT_MODE, secretKey); // Set cipher to decryption mode using the secret key
	}

	private String authenticate(String input) {
		// Split the decrypted authentication message into parts
		String[] s = input.split("-");

		// Check if the message follows the expected format ("LOGIN-username-password")
		if (s[0].equals("LOGIN")) {
			// Validate the user's credentials
			if (checkUser(s[1], s[2])) {
				// Check if the user has any outstanding fees
				if (!checkAccount(s[1])) {
					return "Outstanding fees - you are barred"; // Deny access if fees are due
				} else {
					return "Access granted"; // Grant access if no outstanding fees
				}
			}
		}
		return "Wrong credentials"; // Return error if login fails
	}

	private boolean checkUser(String user, String password) {
		// Populate the user database with a test user
		userDb.put("TestUser", "password");

		// Validate the username and password against the stored values
		if (userDb.containsKey(user) && password.equals(userDb.get(user)))
			return true;
		return false;
	}

	private boolean checkAccount(String user) {
		// Populate the fees database with a test user account balance
		feesDb.put("TestUser", 0d);

		// Check if the user has any outstanding fees
		if (feesDb.get(user) >= 0) {
			return true; // Return true if fees are cleared
		}
		return false; // Return false if user has outstanding fees
	}
}
