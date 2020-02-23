import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class main {

    /**
     * Checks if string is unique using int (bit vector)
     * Runtime: O(N)
     * No additional data structure is used here
     * @param input the input string
     * @return true if the input string is unique, false otherwise
     */
    private static boolean isUniqueUsingInt(String input) {
        int checker = 0;

        for (int i=0; i<input.length(); i++) {
            int bitAtIndex = input.charAt(i) - 'a';
            if ((checker & 1 << bitAtIndex) > 0) {
                return false;
            }
            checker |= 1 << bitAtIndex;
        }
        return true;
    }

    /**
     * Checks if string is unique using set
     * Runtime: O(N)
     * @param input the input string
     * @return true if the input string is unique, false otherwise
     */
    private static boolean isUniqueUsingSet(String input) {
        Set<Character> set = new HashSet<>();
        for (int i=0; i<input.length(); i++) {
            if (set.contains(input.charAt(i))) {
                return false;
            }
            set.add(input.charAt(i));
        }
        return true;
    }

    /**
     * Checks to find if first string is a permutation of the second string
     * Uses sorting (Dual-Pivot quick sort)
     * Runtime: O(nlogn)
     * @param firstInput the first input string
     * @param secondInput the second input string
     * @return true if first string is a permutation of the second string, false otherwise
     */
    private static boolean isPermutationOf(String firstInput, String secondInput) {
        if(firstInput.length() != secondInput.length()) {
            return false;
        }
        char[] firstInputCharArray = firstInput.toCharArray();
        char[] secondInputCharArray = secondInput.toCharArray();
        Arrays.sort(firstInputCharArray);
        Arrays.sort(secondInputCharArray);

        return Arrays.equals(firstInputCharArray, secondInputCharArray);
    }

    /**
     * Checks to find if first string is a permutation of the second string,
     * using ASCII values
     * Runtime: O(n)
     * @param firstInput the first input string
     * @param secondInput the second input string
     * @return true if first string is a permutation of the second string, false otherwise
     */
    private static boolean isPermutationOfUsingAsciiValues(String firstInput, String secondInput) {
        if(firstInput.length() != secondInput.length()) {
            return false;
        }

        int[] charCount = new int[256];

        for (int i=0;  i<firstInput.length(); i++) {
            charCount[firstInput.charAt(i)]++;
            charCount[secondInput.charAt(i)]--;
        }

        for (int i=0; i<charCount.length; i++){
            if(charCount[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to find if first string is a permutation of the second string,
     * using hashmaps
     * Runtime: O(n)
     * @param firstInput the first input string
     * @param secondInput the second input string
     * @return true if first string is a permutation of the second string, false otherwise
     */
    private static boolean isPermutationOfUsingHashmap(String firstInput, String secondInput) {
        if(firstInput.length() != secondInput.length()) {
            return false;
        }

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (char charFromfirstInput : firstInput.toCharArray()) {
            if (!characterIntegerHashMap.containsKey(charFromfirstInput)) {
                characterIntegerHashMap.put(charFromfirstInput, 1);
            } else {
                characterIntegerHashMap.put(charFromfirstInput, characterIntegerHashMap.get(charFromfirstInput)+1);
            }
        }

        for(char charFromSecondInput : secondInput.toCharArray()) {
            if(characterIntegerHashMap.containsKey(charFromSecondInput)) {
                characterIntegerHashMap.put(charFromSecondInput, characterIntegerHashMap.get(charFromSecondInput)-1);
            } else {
                // does not contain the key, i.e. the current character in the second input string,
                // which means that the first input and the second input have different characters
                // Hence, input strings do not match.
                return false;
            }
        }

        for(Character character : characterIntegerHashMap.keySet()) {
            if (characterIntegerHashMap.get(character) != 0) {
                // the map has this 'char' whose value is not 0
                // Hence, input strings do not match.
                return false;
            }
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "tesla";
        System.out.println("Is input unique? - " + isUniqueUsingInt(input));
        System.out.println("Is input unique? - " + isUniqueUsingSet(input));

        String firstInput = "aba";
        String secondInput = "aab";
        System.out.println(firstInput + " is a permutation of " + secondInput + ": " + isPermutationOf(firstInput, secondInput));
        System.out.println(firstInput + " is a permutation of " + secondInput + ": " + isPermutationOfUsingAsciiValues(firstInput, secondInput));
        System.out.println(firstInput + " is a permutation of " + secondInput + ": " + isPermutationOfUsingHashmap(firstInput, secondInput));
    }
}
