package yourPrime;

import java.io.*;
import java.util.List;

public class Subscriber {

	// Instance variables for subscriber details and media collection
	private String userID;
	private String name;
	private String password;
	private MyMedia myMedia; // Stores the media associated with the subscriber
	private String path; // Default file path for saving and retrieving media

	// Constructor to initialize the subscriber with provided details
	public Subscriber(String userID, String name, String password, MyMedia myMedia) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.myMedia = myMedia;
		this.path = "/Users/louisecorbally/Desktop/College/Enterprise Java/Labs/Lab 4 - Functional Programming/yourPrime";
	}

	// Setter for user ID
	public void setUserID(String userId) {
		this.userID = userId;
	}

	// Getter for user ID
	public String getUserID() {
		return userID;
	}

	// Getter for password
	public String getPassword() {
		return password;
	}

	// Calculates and returns the total fees for all subscribed media items
	public double getFees() {
		return this.myMedia.calculateFees();
	}

	// Sorts the media list based on the given order type and returns the sorted list
	public List<?> sort(String orderType) {
		return myMedia.sort(orderType);
	}

	// Returns a string representation of the subscriber and their media subscriptions
	@Override
	public String toString() {
		return (this.name + " Acc. No: " + this.userID + " total charge is " +
				String.format("%.2f", getFees()) + " for:\n\n" + this.myMedia.toString());
	}

	// Getter for the media collection associated with the subscriber
	public MyMedia getMyMedia() {
		return this.myMedia;
	}

	// Saves the subscriber's media collection to a file using serialization
	public boolean saveMedia() {
		try (
				ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(path))
		) {
			objectOutput.writeObject(this.myMedia);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Handle file not found exception
		} catch (IOException e) {
			e.printStackTrace(); // Handle general IO exceptions
		}
		return false;
	}

	// Loads the subscriber's media collection from a file using deserialization
	public MyMedia getMedia() {
		try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(path))) {
			this.myMedia = (MyMedia) objectInput.readObject();
			return myMedia;
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Handle file not found exception
		} catch (IOException e) {
			e.printStackTrace(); // Handle general IO exceptions
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e); // Handle class not found exception
		}
		return null;
	}

	// Setter for password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter for subscriber's name
	public String getName() {
		return name;
	}
}
