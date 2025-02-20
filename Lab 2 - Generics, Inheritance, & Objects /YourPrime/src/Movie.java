
public class Movie extends Media {
	
	// movie sub-class inherit from Media (superclass)
	// TODO complete all methods in this class, refer each method for details
	private String cast;
	private double duration;
	
	public Movie(String title, String cast, double duration, int year, int rating, Genre genre) {
		// TODO: assign all arguments to the class
		this.title = title;
		this.cast = cast;
		this.duration = duration;
		this.year = year;
		this.rating = rating;
		this.genre = genre;
	}
	
	public double getDuration() {
		// TODO: return duration
		return duration;
	}
	
	@Override
	public String toString() {
		// TODO: return the following string
		// title-of-the-movie with name-of-cast price at price
		return (title + " with " + cast + " price at " + price);
	}

}
