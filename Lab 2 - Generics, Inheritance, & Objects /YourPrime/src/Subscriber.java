import java.util.List;

public class Subscriber {
	private String userID;  // Variable to store the user's ID
	private String name;  // Variable to store the name of the subscriber
	private MyMedia myMedia;  // Variable to store the media associated with the subscriber

	// Constructor to initialize the Subscriber object with a userID, name, and associated media (myMedia)

	public Subscriber(String userID, String name, MyMedia myMedia) {
		this.userID = userID;  // Assign the passed userID to the instance variable userID
		this.name = name;  // Assign the passed name to the instance variable name
		this.myMedia = myMedia;  // Assign the passed myMedia to the instance variable myMedia


	}

	// Method to calculate and return the fees for the media the subscriber has
	public double getFees() {
		return myMedia.calculateFees();  // Call the calculateFees method of myMedia to get the total fees

	}

	// Method to sort the media items based on the provided orderType (e.g., by date, name, etc.)
	public List<?> sort(String orderType) {
		return myMedia.sort(orderType);  // Call the sort method of myMedia to sort based on the orderType

	}

	// Override the toString method to return a string representation of the subscriber and their media
	@Override
	public String toString() {
		return (name + " Acc no:" + userID + " total charge is " + getFees() + " for " + myMedia.toString());

	}
}
