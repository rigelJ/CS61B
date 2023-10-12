import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /**
     * Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> NumMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            NumMap.put(c, i + 1);
        }
        return NumMap;
    }

    /**
     * Returns a map from the integers in the list to their squares. For example, if the input list
     * is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> Nummap = new HashMap<>();
        for (Integer i : nums) {
            Double dpow = Math.pow(i, 2);
            Integer ipow = dpow.intValue();
            Nummap.put(i, ipow);
        }
        return Nummap;
    }

    /**
     * Returns a map of the counts of all words that appear in a list of words.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> Wordmap = new HashMap<>();
        for (String s : words) {
            if (Wordmap.containsKey(s)) {
                Wordmap.replace(s, Wordmap.get(s) + 1);
            }
            else{
            Wordmap.put(s, 1);
            }
        }
        return Wordmap;
    }
}

