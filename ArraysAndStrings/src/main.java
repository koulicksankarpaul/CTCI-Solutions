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

    public static void main(String[] args) {
        String input = "test";
        System.out.println("Is input unique? - " + isUniqueUsingInt(input));
        System.out.println("Is input unique? - " + isUniqueUsingSet(input));
    }
}
