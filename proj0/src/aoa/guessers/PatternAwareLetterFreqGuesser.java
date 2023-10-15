package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> PatternMatchList = PatternAwareLetterFreqGuesser.getFreqMapThatMatchesPattern(pattern,this.words);
        Map<Character,Integer> FreqMap = new HashMap<>();
        FreqMap = PatternAwareLetterFreqGuesser.getFrequencyMap(PatternMatchList);
        char nextGuessLetter = PatternAwareLetterFreqGuesser.getGuess(guesses,FreqMap);
        return nextGuessLetter;
    }

    //Return the words list that matches the patterns;
    public static List<String> getFreqMapThatMatchesPattern(String pattern,List<String> words){
        List<String> PatternMatchesList = new ArrayList<>();
        for(String s: words){
            if(s.length()!=pattern.length())
            {
                continue;
            }
            int flag = 0;
            for(int j=0;j<s.length();j++) {
                if (pattern.charAt(j) == '-') {
                    continue;
                }
                if (s.charAt(j) != pattern.charAt(j)) {
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
                PatternMatchesList.add(s);
            }
        }
        return  PatternMatchesList;
    }

    public static Map<Character, Integer> getFrequencyMap(List<String> wordlist) {
        Map<Character,Integer> FrequeMap = new HashMap<>();
        for(String i:wordlist){
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

    public static char getGuess(List<Character> guesses,Map<Character,Integer> Freqmap) {
        Map<Character,Integer> TreeMa = new TreeMap<>();
        TreeMa = Freqmap;
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
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("e--e", List.of('e')));
    }
}