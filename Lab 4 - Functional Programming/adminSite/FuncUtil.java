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

	// HashMap to store subscribers with their user ID as the key
	private Map<String, Subscriber> userDb = new HashMap<>();

	// Constructor to initialize FuncUtil with an existing user database
	public FuncUtil(Map<String, Subscriber> userDb) {
		this.userDb = userDb;
	}

	// Getter method to retrieve the user database
	public Map<String, Subscriber> getUserDb() {
		return userDb;
	}

	// Adds a subscriber to the user database if the user ID does not already exist.
	// Uses the Supplier functional interface to generate a new unique user ID.
	public void addSubscriber(Subscriber subscriber) {
		Supplier<String> userID = () -> generateId().toString(); // Generates a new ID
		String id = userID.get(); // Retrieves the generated ID
		subscriber.setUserID(id); // Assigns the new ID to the subscriber
		userDb.putIfAbsent(id, subscriber); // Adds the subscriber to the database if the ID is not already present
	}

	// Generates a random user ID between 0 and 79,999
	public Integer generateId() {
		return new Random().nextInt(80000);
	}

	// Modifies the password of a subscriber with the given user ID.
	// Returns true if the password was successfully updated, otherwise false.
	public boolean modifyPassword(String userId, String newPassword) {
		Subscriber subscriber = userDb.get(userId); // Retrieves the subscriber from the database
		subscriber.setPassword(newPassword); // Updates the password
		userDb.replace(userId, subscriber); // Updates the subscriber in the map

		// Checks if the password was successfully updated
		if (userDb.get(userId).getPassword().equals(newPassword))
			return true;
		else
			return false;
	}

	// Deletes a subscriber from the database using the user ID.
	// Returns true if the subscriber was successfully deleted, otherwise false.
	public boolean deleteSubscriber(String userId) {
		userDb.remove(userId); // Removes the subscriber from the map

		// Checks if the subscriber no longer exists in the database
		if (userDb.get(userId) == null)
			return true;
		else
			return false;
	}

	// Searches for a subscriber based on a given keyword (user ID or name).
	// Uses the Predicate interface to match the keyword.
	// Returns a list of subscribers that match the search criteria.
	public List<Subscriber> searchSubscriber(String keyword) {
		Predicate<String> searchUserID = userId -> userId.equals(keyword); // Predicate to match user ID
		Predicate<String> searchName = name -> name.contains(keyword); // Predicate to match name

		List<Subscriber> results = new ArrayList<>();

		// Iterates over the user database and adds matching subscribers to the results list
		userDb.forEach((_, v) -> {
			if (searchUserID.test(v.getUserID()) || searchName.test(v.getName()))
				results.add(v);
		});

		return results;
	}

	// Calculates the total overdue fees for all subscribers.
	// Overdue fees are any positive fees in the subscriber's account.
	// Uses the Predicate functional interface to filter positive fee values.
	public double calculateOverdueFees() {
		Predicate<Double> outstanding = n -> n > 0d; // Predicate to check if fees are positive
		double total = 0d;

		// Iterates over all subscribers and sums up the overdue fees
		for (Subscriber s : userDb.values()) {
			if (outstanding.test(s.getFees()))
				total += s.getFees();
		}

		return total;
	}

	// Prints all subscribers who have outstanding fees.
	// Uses Predicate to check for outstanding fees and Function to format the output message.
	public void printAllSubscribers() {
		Predicate<Double> outstanding = n -> n > 0d; // Predicate to check outstanding fees
		Function<Subscriber, String> details = p -> p.getName() + " with outstanding amount = " + String.format("%.2f", p.getFees());

		// Iterates through the user database and prints details of subscribers with outstanding fees
		userDb.forEach((_, v) -> {
			if (outstanding.test(v.getFees()))
				System.out.println(details.apply(v));
		});
	}
}
