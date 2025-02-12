
public class PhraseOMatic {
	public static void main(String[] args) {

		// TODO 1. Ask user to provide the number of phrases (use System.in)

		// make three sets of words to choose from
		String[] wordListOne = {"24/7", "multi-Tier", "30,000 foot", "B-to-B", "win-win", "front-end",
				"web-based", "pervasive", "smart", "six-sigma", "critical-path", "dynamic"};
		String[] wordListTwo = {"empowered", "value-added", "customer-centric", "sticky", "networked",
				"cooperative", "oriented", "centric", "distributed", "clustered", "branded", "outside-the-box",
				"positioned", "focused", "leveraged", "aligned", "targeted", "shared", "cooperative", "accelerated"};
		String[] wordListThree = {"solution", "architecture", "space", "core competency", "tipping-point", "mission",
				"process", "strategy", "mindshare", "portal", "vision", "paradigm"};

		// TODO 2. Change the existing procedure to generate phrases into a method called generatePhrase(String[] s1 ...)
		// find out how many words are in each list
		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;

		// generate three random numbers
		int randOne = (int)(Math.random() * oneLength);
		int randTwo = (int)(Math.random() * twoLength);
		int randThree = (int)(Math.random() * threeLength);

		// you can now build a phrase
		String phrase = wordListOne[randOne] + " " + wordListTwo[randTwo] + " " + wordListThree[randThree];

		// TODO 3. Modify your main method to call generatePhrase() n times (given by the user)
		// print out the phrase
		System.out.println("What we need is a " + phrase);
	}
}
