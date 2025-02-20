import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import mainYourPrime.MyMedia;
import mainYourPrime.Subscriber;

public class yourPrimeClient {
	public static void main(String[] args) {
		Subscriber testUser = new Subscriber("TestUser", "test", "password", new MyMedia(null, null, null));
		yourPrimeClient primeClient = new yourPrimeClient();

		try {
			primeClient.login(testUser);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// TODO complete to login function - user will contact server add request access to myMedia library
	// The server will authenticate user's login information, verify, and perform additional check on outstanding fees
	//
	public void login(Subscriber user) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 4242);

		String protectedData = user.getUserID() + user.getPassword();
		PrintWriter writer = new PrintWriter(s.getOutputStream());
		writer.println("Login: " + protectedData);
		writer.flush();

		InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
		BufferedReader reader = new BufferedReader(streamReader);
		String message = reader.readLine();
		System.out.println(message);

		reader.close();
		s.close();
	}
}
