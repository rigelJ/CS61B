package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character,Integer> FrequeMap = new HashMap<>();
        for(String i:this.words){
            for(int j=0;j<i.length();j++) {
                if (FrequeMap.containsKey(i.charAt(j))) {
                    FrequeMap.replace(i.charAt(j), FrequeMap.get(i.charAt(j))+1);
                }
                else {
                    FrequeMap.put(i.charAt(j), 1);
                }
            }
        }
        return FrequeMap;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        Map<Character,Integer> TreeMa = new TreeMap<>();
        TreeMa = this.getFrequencyMap();
        int max = 0;
        char maxkey = 'a';
        for( Character c:TreeMa.keySet()) {
            if (guesses.contains(c)) {
                continue;
            }
            if (TreeMa.get(c) > max) {
                max = TreeMa.get(c);
                maxkey = c;
            }
        }
        return maxkey;
        }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());
        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
