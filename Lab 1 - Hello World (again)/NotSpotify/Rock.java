package NotSpotify;

/**
 * The Rock class extends the Genre class and represents the Rock genre of music.
 * It overrides the setPrice method to calculate the price of a song based on its rating for the Rock genre.
 */
public class Rock extends Genre {

	/**
	 * Sets the price of a song based on its rating for the Rock genre.
	 * The method is currently a placeholder and should be implemented based on desired logic.
	 *
	 * @param rating The rating of the song (e.g., 1-5).
	 * @return The price of the song for the Rock genre, calculated based on the rating.
	 */
	@Override
	double setPrice(int rating) {
		if (rating >= 4) {
			return 3.99;  // €2.99 for ratings 4 and above
		} else if (rating == 3) {
			return 2.99;  // €1.99 for a 3-star rating
		} else {
			return 0.99;
		}
	}
}

