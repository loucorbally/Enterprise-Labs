package mainYourPrime;

public class Rap extends Genre {
	
	//TODO complete method override
	@Override
	double setPrice(int rating) {
		if (rating > 3) {
			return 4.99;
		} else if (rating == 3) {
			return 3.99;
		}
		return 1.99;
	}
}
