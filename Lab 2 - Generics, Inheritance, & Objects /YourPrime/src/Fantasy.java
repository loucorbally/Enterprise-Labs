
public class Fantasy extends Genre {

	// Override the setPrice method from the Genre class
	@Override
	double setPrice(int rating) {

		// If the rating is greater than 3, return a price of 1.99
		if (rating > 3) {
			return 1.99;
		}
		// If the rating is exactly 3, return a price of 0.99
		else if (rating == 3) {
			return 0.99;
		}
		// If the rating is less than 3, return a price of 0.00
		return 0.00;
	}
}

