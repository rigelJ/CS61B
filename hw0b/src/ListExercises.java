import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        if (L.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < L.size(); i++) {
            sum = sum + L.get(i);
        }
        return sum;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> NotSingle = new ArrayList<>();
        for (Integer i : L) {
            if (i % 2 == 0) {
                NotSingle.add(i);
            }
        }
        return NotSingle;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> inout = new ArrayList<Integer>();
        for (Integer i : L1) {
            for(Integer j:L2){
                if(i==j){
                    inout.add(i);
                }

            }
        }
        return inout;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> L, char c) {
        int count = 0;
        for (String i : L) {
            for (int j = 0; j < i.length(); j++) {
                if (c == i.charAt(j)) {
                    count += 1;
                }
            }
        }
        return count;
    }
}
