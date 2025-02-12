package NotSpotify;

import java.util.ArrayList;
import java.util.List;

public class NotSpotify {

	public static void main(String[] args) {

		// Create dummy songs to test the setPrice() method and toString() override method
		Song s1 = new Song("Nothing Else Matters", "Metallica", 5, new Rock());
		Song s2 = new Song("November Rain", "Guns N Roses", 3, new Rock());
		Song s3 = new Song("When I Come Around", "Green Day", 2, new Rock());
		Song s4 = new Song("Lose Yourself", "Eminem", 1, new Rap());
		Song s5 = new Song("99 Problems", "Jay-Z", 4, new Rap());
		Song s6 = new Song("Jesus Walks", "Ye", 5, new Rap());
		Song s7 = new Song("Hit Me Baby One More Time", "Britney Spears", 3, new Pop());
		Song s8 = new Song("The A Team", "Ed Sheeren", 4, new Pop());
		Song s9 = new Song("Blinding Lights", "The Weeknd", 1, new Pop());

		// TODO: Create a playlist for the Rock, Rap, and Pop genres
		List<Song> playList1 = new ArrayList<>();
		playList1.add(s1);  // Adding songs to the Rock playlist
		playList1.add(s2);
		playList1.add(s6);
		playList1.add(s9);
		playList1.add(s4);

		List<Song> playList2 = new ArrayList<>();
		playList2.add(s3);  // Adding songs to the Rap/Pop playlist
		playList2.add(s8);
		playList2.add(s7);
		playList2.add(s4);
		playList2.add(s5);



		getPlayList(playList1);  // Displays playlist 1 and its total price
		getPlayList(playList2);  // Displays playlist 2 and its total price
	}

	/**
	 * This method prints the details of each song in the playlist and calculates
	 * the total price of all songs in the list.
	 *
	 * @param list The playlist of songs.
	 */
	public static void getPlayList(List<Song> list) {
		// Initialize total price counter
		double total = 0.0;

		// Iterate through each song in the playlist
		for (Song s: list) {
			System.out.println(s.toString());  // Print the song details (title and artist)
			total += s.getPrice();  // Add the song's price to the total
		}

		// Print the total price for the playlist
		System.out.println("Total fees: " + total + "\n");
	}
}




