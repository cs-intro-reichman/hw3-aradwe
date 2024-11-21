/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Process both strings to lowercase and remove non-letters
		String processedStr1 = preProcess(str1);
		String processedStr2 = preProcess(str2);

		if (processedStr1.length() != processedStr2.length()) {
			return false;			
		}

		// Loop over the characters in the first string
		for(int i = 0; i < processedStr1.length(); i++){
			char currentLetter = processedStr1.charAt(i);
			
			// If currentLetter is in processedStr2, remove it
			int indexInStr2 = processedStr2.indexOf(currentLetter);
			if (indexInStr2 != -1) {
				// Remove the character from processedStr2 to avoid double-counting
				processedStr2 = processedStr2.substring(0, indexInStr2) + 
				processedStr2.substring(indexInStr2 + 1);
			}
			else{
				return false; // If the character isn't found, it's not an anagram
			}
		}
		return true; // If all characters from str1 are found in str2, it's an anagram
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Convert all the letters to lowercase
		str = str.toLowerCase();
		
		String processedStr = "";
		String abcLetters = "abcdefghijklmnopqrstuvwxyz";
		
		// Iterate through the abc to eliminate non-letters from the final output string
		for(int i = 0; i < str.length(); i++){
			char currentLetter = str.charAt(i);
			if (abcLetters.indexOf(str.charAt(i)) != -1) {
				processedStr += currentLetter;
			}
		}
		return processedStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		// Convert all the letters to lowercase and remove non-letters
		String processedInputStr = preProcess(str);
		
		String newRandomStr = "";
		int counter = processedInputStr.length();

		while (counter > 0) {
			// Generate a random index in the remaining letters range
			int randomIndex = (int)(Math.random() * counter);

			// Add the selected character to the result string
			newRandomStr += processedInputStr.charAt(randomIndex);

			// Remove the selected character from the input string
			processedInputStr = processedInputStr.substring(0, randomIndex) + 
			processedInputStr.substring(randomIndex + 1);

			 // Decrease the counter
			 counter--;	
		}	
		return newRandomStr;
	}
}
