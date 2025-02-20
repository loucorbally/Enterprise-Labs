// Abstract class representing a genre for the NotSpotify project
abstract class Genre {
	// The superclass for the NotSpotify project, intended to be extended by specific genre classes (e.g., Fantasy, Pop, etc.)

	// Instance variable to store the price for the genre
	double price = 0.0;  // Default price set to 0.0

	// Abstract method that must be implemented by any subclass of Genre
	// This method will set the price based on the rating passed to it
	abstract double setPrice(int rating);  // Subclasses will define the pricing logic
}
