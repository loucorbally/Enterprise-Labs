package NotSpotify;

/**
 * The Genre class is an abstract superclass that represents a music genre.
 * It holds a price for the song and defines an abstract method to calculate
 * the price based on the rating, which will be implemented by its subclasses.
 */
abstract class Genre {

	// The price of the song in the specified genre
	double price = 0.0;

	/**
	 * Abstract method to set the price of a song based on its rating.
	 * Each genre subclass (e.g., Pop, Rap, Rock) will implement this method
	 * to calculate the price according to its specific logic.
	 *
	 * @param rating The rating of the song (e.g., 1-5).
	 * @return The calculated price of the song based on the rating.
	 */
	abstract double setPrice(int rating);
}
