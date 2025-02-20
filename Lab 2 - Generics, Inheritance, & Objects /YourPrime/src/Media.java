public class Media {

	// Superclass for the media types available in the YourPrime streaming platform.
	// This class serves as a base for various types of media (e.g., Movie, Song, Book).

	// Instance variables to store the details about the media
	protected String title = "";  // The title of the media (e.g., movie name, book title)
	protected int year = 0;       // The release year of the media
	protected int rating = 0;     // The rating of the media (e.g., 1-5)
	protected double price = 0.0; // The price of the media, calculated based on genre and rating
	protected Genre genre;        // The genre of the media (e.g., Fantasy, Thriller, Pop, etc.)

	// Constructor - Initializes the media object with values for title, year, rating, and genre
	// (Constructor details are not provided, but typically, you'd pass these values when creating a new media object)

	// Method to get the price of the media
	public double getPrice() {
		// Call the setPrice method from the Genre class to calculate the price based on the rating
		price = genre.setPrice(rating);  // Set the price using the genre's setPrice logic
		return price;  // Return the calculated price
	}

	// Method to get the title of the media
	public String getTitle() {
		return title;  // Return the media title
	}

	// Method to get the rating of the media
	public int getRating() {
		return rating;  // Return the media rating
	}
}
