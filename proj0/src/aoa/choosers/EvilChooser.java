package aoa.choosers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import org.apache.commons.collections.FastArrayList;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        if(wordLength<1) {
            throw new IllegalArgumentException("wordlength is less than one");
        }
        List<String> wordOfLength_List =  FileUtils.readWordsOfLength(dictionaryFile,wordLength);
        if(wordOfLength_List.isEmpty()){
            throw new IllegalStateException("can not find the word");
        }

        //get the initial word pattern
        char[] pattern_in_chars = new char[wordLength];
        for(int i=0;i<wordLength;i++){
            pattern_in_chars[i] = '-';
        }
        this.pattern = new String(pattern_in_chars);

        //get random words in list
        this.wordPool = FileUtils.readWords(dictionaryFile);
    }


    @Override
    public int makeGuess(char letter) {

        //create the chosenMap
        Map<String, Integer> chosenMap = new TreeMap<>();
        for (String word : wordPool) {
            if(word.length()!=this.pattern.length()){
                continue;
            }
            String wordPattern = this.getEachPattern(word, letter);
            if (chosenMap.containsKey(wordPattern)) {
                chosenMap.replace(wordPattern, chosenMap.get(wordPattern) + 1);
            } else {
                chosenMap.put(wordPattern, 1);
            }
        }

        // find the maxValue in Map
        int maxValue = 0;
        String max = "null";


        for (String s : chosenMap.keySet()) {
            if (chosenMap.get(s) > maxValue) {
                max = s;
                maxValue = chosenMap.get(s);
            }
            else if (chosenMap.get(s) == maxValue) {
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)==max.charAt(i)){
                        continue;
                    }
                    else if(s.charAt(i)<max.charAt(i)){
                        max = s;
                        maxValue = chosenMap.get(s);
                        break;
                    }
                    else if (max.charAt(i)<s.charAt(i)) {
                        break;
                    }
                }
            }
        }

        //update the wordpool
        Iterator<String> it = wordPool.iterator();
        while (it.hasNext()) {
            String i = it.next();
            if(i.length()!=this.pattern.length()){
                it.remove();
                continue;
            }
            String word_pattern2 = this.getEachPattern(i,letter);
            if (word_pattern2.equals(max)) {
                continue;
            }
            else{
                it.remove();
            }
        }

        //update the pattern
        this.pattern = max;

        //Get the times that letter occur;
        int count = 0;
        for(int x=0;x<this.pattern.length();x++){
            if(this.pattern.charAt(x)==letter){
                count +=1;
            }
        }

        return count;
    }

    public String getEachPattern(String word,char letter){
        char[] pattern_in_char = this.pattern.toCharArray();
        int count =0;
        for(int i=0;i<word.length();i++) {
            if (letter == word.charAt(i)) {
                pattern_in_char[i] = letter;
                count += 1;
            }
        }
        return new String(pattern_in_char);
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        int chosen_word_number = wordPool.size();
        int randomChosenWordNumber = StdRandom.uniform(chosen_word_number);
        return wordPool.get(randomChosenWordNumber);
    }
}
