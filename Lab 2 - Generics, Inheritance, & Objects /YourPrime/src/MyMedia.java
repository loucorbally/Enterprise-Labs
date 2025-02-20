import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyMedia {
	// Lists to store different types of media items (Movies, Books, Songs)
	private List<Movie> movieList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<Song> songList = new ArrayList<>();

	// Constructor to initialize the MyMedia object with lists of Movies, Books, and Songs
	public MyMedia(List<Movie> listM, List<Book> listB, List<Song> listS) {
		this.movieList = listM;  // Assign the passed list of movies to movieList
		this.bookList = listB;   // Assign the passed list of books to bookList
		this.songList = listS;   // Assign the passed list of songs to songList
	}

	// Method to calculate the total fees for all media (Movies, Books, Songs)
	public double calculateFees() {
		double fees = 0.0;  // Initialize total fees to 0.0

		// Add the price of each movie to the total fees
		for (Movie m : movieList) {
			fees += m.getPrice();
		}

		// Add the price of each book to the total fees
		for (Book b : bookList) {
			fees += b.getPrice();
		}

		// Add the price of each song to the total fees
		for (Song s : songList) {
			fees += s.getPrice();
		}

		return fees;  // Return the total calculated fees
	}

	// Override toString method to provide a string representation of all media items
	@Override
	public String toString() {
		String str = "";  // Initialize an empty string

		// Append the string representation of each movie in the movieList
		for (Movie m : movieList) {
			str += m.toString() + "\n";
		}

		// Append the string representation of each book in the bookList
		for (Book b : bookList) {
			str += b.toString() + "\n";
		}

		// Append the string representation of each song in the songList
		for (Song s : songList) {
			str += s.toString() + "\n";
		}

		return str;  // Return the concatenated string representing all media items
	}

	// Method to sort the media items based on a specified order type (price, title, rating, pages, name)
	public List<?> sort(String orderType) {
		// This method is used by subscriber objects to sort the media

		// Create a combined list (master) containing all media (Movies, Books, Songs)
		List<Media> master = new ArrayList<>();
		master.addAll(movieList);  // Add all movies to the master list
		master.addAll(bookList);   // Add all books to the master list
		master.addAll(songList);   // Add all songs to the master list

		// Define different comparators for sorting based on price, title, rating, pages, and name
		Comparator<Media> comparebyPrice = Comparator.comparing(Media::getPrice);
		Comparator<Media> comparebyTitle = Comparator.comparing(Media::getTitle);
		Comparator<Media> comparebyRating = Comparator.comparing(Media::getRating);
		Comparator<Book> comparebyPages = Comparator.comparing(Book::getNoPages);
		Comparator<Movie> comparebyName = Comparator.comparing(Movie::getDuration);

		// Sorting logic based on the orderType argument
		if (orderType.equalsIgnoreCase("price")) {
			master.sort(comparebyPrice);  // Sort the master list by price
		} else if (orderType.equalsIgnoreCase("title")) {
			master.sort(comparebyTitle);  // Sort the master list by title
		} else if (orderType.equalsIgnoreCase("rating")) {
			master.sort(comparebyRating);  // Sort the master list by rating
		} else if (orderType.equalsIgnoreCase("pages")) {
			bookList.sort(comparebyPages);  // Sort only the bookList by number of pages
			return bookList;  // Return the sorted bookList
		} else if (orderType.equalsIgnoreCase("name")) {
			movieList.sort(comparebyName);  // Sort only the movieList by name (duration)
			return movieList;  // Return the sorted movieList
		}

		return master;  // Return the sorted master list (contains Movies, Books, and Songs)
	}
}

