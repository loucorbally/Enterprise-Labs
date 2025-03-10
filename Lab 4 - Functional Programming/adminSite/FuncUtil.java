package adminSite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import yourPrime.Subscriber;

public class FuncUtil {

	private Map<String, Subscriber> userDb = new HashMap<>();

	public FuncUtil(Map<String, Subscriber> userDb) {
		this.userDb = userDb;
	}

	public Map<String, Subscriber> getUserDb() {
		return userDb;
	}

	// TODO check if the subscriber user id exists in the system. If not, create a new user id using supplier interface, and
	// add user into the map userDb. You should also use the built-in interface in map to check for existing user.
	//
	public void addSubscriber(Subscriber subscriber) {

	}

	public Integer generateId() {
		return new Random().nextInt(80000);
	}

	// TODO update the subscriber password, you need to modify the userDb map accordingly.
	//
	public boolean modifyPassword(String userId, String newPassword) {

	}

	// TODO delete subscriber given the userId, to ensure deletion, return the boolean value
	// to indicate that the user no longer exists
	//
	public boolean deleteSubscriber(String userId) {

	}

	// TODO search user using the predicate interface - you can use the userId or password as keyword
	// return the list of subscriber matching any of the keyword.
	//
	public List<Subscriber> searchSubscriber(String keyword) {

	}

	// TODO Calculate the total overdue fees for all subscribers (i.e., overdue fees are
	// any positive fees in the subscriber account - use the functional interface to filter
	// the values
	//
	public double calculateOverdueFees() {

	}

	// TODO Use the predicate and function interfaces to display the subscribers with outstanding fees
	// the message should display: John with outstanding amount of X
	//
	public void printAllSubscribers() {
	
	}
}
