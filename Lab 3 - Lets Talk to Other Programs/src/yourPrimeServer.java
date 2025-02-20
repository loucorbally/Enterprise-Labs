import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class yourPrimeServer {
	private Map<String, String> userDb = new HashMap<>();
	private Map<String, Double> feesDb = new HashMap<>();

	public static void main(String[] args) {
		yourPrimeServer server = new yourPrimeServer();
		try {
			server.go();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO complete the go() method on the server, you need to authenticate your user (given credentials)
	// from the client, and only permit access if there is no outstanding fees. Use the Map variables to 
	// verify user and check outstanding fees status. Return the correct message to the client.
	//
	public void go() throws IOException {
		ServerSocket serverSocket = new ServerSocket(4242);
		while (true) {
			Socket s = serverSocket.accept();
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String message = authenticate(input.readLine());

			PrintWriter writer = new PrintWriter(s.getOutputStream());
			writer.println(message);
			writer.close();
		}

	}

	// TODO Complete authenticate using the utility boolean methods available here. You need to return the following 
	// message to the client:
	// 1 = Access granted -> if everything is ok
	// 2 = Wrong credentials -> if username or / and password is / are invalid
	// 3 = Outstanding fees - you are barred -> if account is overdue
	//
	private String authenticate(String input) {
		String[] s = input.split("-");
		if (s[0].equals("LOGIN")) {
			if (checkUser(s[1], s[2])) {
				if (!checkAccount(s[1]))
					return "Outstanding fees - You are barred from logging in";
				else {
					return "Access Granted";}
				}
				return "Wrong credentials";
			}
			return "Wrong credentials";
		}

		private boolean checkUser (String user, String password){
			userDb.put("TestUser", "password");
			if (userDb.containsKey(user) && password.equals(userDb.get(user)))
				return true;
			return false;
		}

		private boolean checkAccount (String user){
			feesDb.put("TestUser", 0d);
			if (feesDb.get(user) >= 0) {
				return true;
			}
			return false;
		}
	}


