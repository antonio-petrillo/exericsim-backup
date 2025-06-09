public class PigLatinTranslator {

    public String translate(String word) {
		var words = word.split(" ");
		for (int i = 0; i < words.length; i++) {
			words[i] = toPigLatin(words[i]);
		}

		return String.join(" ", words);
	}

    private static String toPigLatin(String word) {
		var clusters = getConsonantCluster(word);

		// RULE 1
		if (!word.startsWith("y") && isVowel(word.charAt(0))
			|| word.startsWith("xr")
			|| word.startsWith("yt") ) {
			return word + "ay";
		}

		// NONE APPLY
		if (clusters[1].isEmpty()) {
			return word;
		}

		// RULE 3
		if (clusters[0].endsWith("q") && clusters[1].startsWith("u")) {
			return clusters[1].substring(1) + clusters[0].substring(0, clusters[0].length() - 1) + "quay";
		}

		// RULE 4
		if (clusters[0].startsWith("y")) {
			return clusters[1] + clusters[0] + "ay";
		}

		// RULE 4
		if (clusters[0].isEmpty() && clusters[1].startsWith("y")) {
			return clusters[1].substring(1) + "yay";
		}

		// RULE 2
		return clusters[1] + clusters[0] + "ay";
    }


	private static boolean isVowel(char character) {
		return character == 'a'
			|| character == 'e'
			|| character == 'i'
			|| character == 'o'
			|| character == 'u'
			|| character == 'y';
	}

	private static String[] getConsonantCluster(String word) {
		int i = 0;
		for (; i < word.length(); i++) {
			if (isVowel(word.charAt(i))) {
				break;
			}
		}
		String[] clusters = new String[2];
		clusters[0] = !word.isEmpty() ? word.substring(0, i) : "";
		clusters[1] = !word.isEmpty() ? word.substring(i) : "";
		return clusters;
	}
}
