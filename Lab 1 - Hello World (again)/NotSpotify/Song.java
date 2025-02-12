package NotSpotify;

/**
 * The Song class represents a musical song with a title, artist, rating, and genre.
 * It provides methods to calculate the song's price based on its rating and genre,
 * and to return a string representation of the song.
 */
public class Song {

    private String title;  // The title of the song
    private String artist; // The artist of the song
    private int rating;    // The rating of the song (e.g., 1-5)
    private Genre genre;   // The genre of the song

    /**
     * Constructor to initialize a Song object with a title, artist, rating, and genre.
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param rating The rating of the song (e.g., 1-5).
     * @param genre The genre of the song.
     */
    public Song(String title, String artist, int rating, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.rating = rating;
        setGenre(genre);  // Set the genre using the setter method
    }

    /**
     * Calculates the price of the song based on its rating and genre.
     * The price is determined by the genre's price calculation logic.
     *
     * @return The price of the song based on its rating and genre.
     */
    public double getPrice() {
        return genre.setPrice(rating);  // Calculate price based on genre and rating
    }

    /**
     * Returns a string representation of the song, including its title and artist.
     *
     * @return A string describing the song (e.g., "Song title by Artist").
     */
    @Override
    public String toString() {
        return (title + " by " + artist);  // Return song title and artist as a string
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre The genre to be set for the song.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
