package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> PatternMatchList = PAGALetterFreqGuesser.getFreqMapThatMatchesPattern(pattern,this.words,guesses);
        Map<Character,Integer> FreqMap = new HashMap<>();
        FreqMap = PatternAwareLetterFreqGuesser.getFrequencyMap(PatternMatchList);
        char nextGuessLetter = PatternAwareLetterFreqGuesser.getGuess(guesses,FreqMap);
        return nextGuessLetter;
    }

    public static List<String> getFreqMapThatMatchesPattern(String pattern,List<String> words,List<Character> guess){
        List<String> PatternMatchesList = new ArrayList<>();
        List<Character> guesslist = new ArrayList<>();
        for(Character c:guess){
            guesslist.add(c);
        }

        for(int k=0;k<pattern.length();k++){
            if(guesslist.contains(pattern.charAt(k))){
                Character removec = pattern.charAt(k);
                guesslist.remove(removec);
            }
        }
        for(String s: words){
            if(s.length()!=pattern.length())
            {
                continue;
            }
            int flag = 0;
            for(int j=0;j<s.length();j++) {
                if (pattern.charAt(j) == '-') {
                    if(guesslist.contains(s.charAt(j))){
                        flag = 1;
                        break;
                    }
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



    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
