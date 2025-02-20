package mainYourPrime;
import java.util.List;

public class Subscriber {
	
	// TODO complete the subscriber class, use the UML diagram in the task document
	private String userID;
	private String name;
	private String password;
	private MyMedia myMedia;
	
	public Subscriber(String userID, String name, String password, MyMedia myMedia) {
		// TODO assign all arguments to the instance variables
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.myMedia = myMedia;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public double getFees() {
		// TODO return fees for all subscribed items
		return this.myMedia.calculateFees();
		
	}
	
	public List<?> sort(String orderType) {
		// TODO return sorted list based on orderType argument
		return myMedia.sort(orderType);
	}
	
	@Override
	public String toString() {
		// TODO return a string with the following message:
		// name-of-subscriber Acc no: user-id total charge is the fees for show-all-subscribed-media
		return (this.name + " Acc. No: " + this.userID + " total charge is " + String.format("%.2f", getFees()) + " for:\n\n" + this.myMedia.toString());
	}
}
