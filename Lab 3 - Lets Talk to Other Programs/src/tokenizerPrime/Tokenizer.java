package tokenizerPrime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import mainYourPrime.*;

public class Tokenizer {

	public static void main(String[] args) {
		// Define the file path where the CSV data is stored
		String path = "/Users/louisecorbally/Desktop/College/Enterprise Java/Labs/Lab 3 - Lets Talk to Other Programs/src/MovieDataset/metadata.csv";

		// Try to open and read the file using a Scanner wrapped around a BufferedReader
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
			// Set the delimiter to newline, so each iteration processes one line
			scanner.useDelimiter("\n");
			scanner.nextLine(); // Skip the first line (assumed to be headers)

			// Process each line of the file until there's no more data
			while (scanner.hasNext()) {
				System.out.println(argsParser(scanner.next())); // Parse and print the extracted movie data
			}
		} catch (FileNotFoundException e) {
			// Print stack trace if the file is not found
			e.printStackTrace();
		}
	}

	/**
	 * Parses a CSV row and extracts relevant movie information.
	 *
	 * @param str the input CSV row
	 * @return a map containing extracted movie attributes
	 */
	public static Map<String, Object> argsParser(String str) {
		Map<String, Object> mapArgs = new HashMap<>();

		// Split the input string using "," as the delimiter and remove leading/trailing spaces
		String[] e = str.trim().split(",");
		int i = 0;

		// Iterate through each element in the split array
		for (String s : e) {
			// Extract title: Located at index (length - 4)
			if (i == e.length - 4)
				mapArgs.put("title", s);

				// Extract rating: Located at index (length - 2), converted to a 5-point scale
			else if (i == e.length - 2) {
				try {
					mapArgs.put("Rating", Double.valueOf(s) / 10 * 5); // Convert rating scale
				} catch (NumberFormatException ex) {
					mapArgs.put("Rating", 0d); // Default to 0 if conversion fails
				}
			}

			// Extract genre by analyzing the full input string
			mapArgs.put("Genre", getGenre(str));
			i++;
		}

		return mapArgs;
	}

	/**
	 * Determines the movie genre based on keywords found in the input string.
	 *
	 * @param s the input string containing genre information
	 * @return the corresponding Genre class type or null if no match is found
	 */
	public static Class<? extends Genre> getGenre(String s) {
		if (s.contains("Comedy"))
			return Comedy.class;
		else if (s.contains("Action"))
			return Action.class;
		else if (s.contains("Drama"))
			return Drama.class;

		return null; // Return null if no genre matches
	}
}


