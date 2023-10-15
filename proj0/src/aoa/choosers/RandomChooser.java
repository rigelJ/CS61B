package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // check the wordLength
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
        int numWords = wordOfLength_List.size();
        int randomChosenWordNumber = StdRandom.uniform(numWords);
        this.chosenWord =  wordOfLength_List.get(randomChosenWordNumber);
    }

    @Override
    public int makeGuess(char letter) {
        char[] pattern_in_char =  this.pattern.toCharArray();
        int count =0;
        for(int i=0;i<chosenWord.length();i++) {
            if (letter == chosenWord.charAt(i)) {
                pattern_in_char[i] = letter;
                count += 1;
            }
        }
        this.pattern = new String(pattern_in_char);
        return count;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return this.pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return this.chosenWord;
    }
}
