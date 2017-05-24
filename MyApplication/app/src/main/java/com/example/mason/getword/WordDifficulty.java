package com.example.mason.getword;

import java.util.LinkedHashSet;
import java.util.Set;

public class WordDifficulty {
    private final static String ZipfsLaw = "etaoinshrdlcumwfgypbvkjxqz";

    private static int getCharOccurrences(String word, char c){
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = word.indexOf(c,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex++;
            }
        }
        return count;
    }

    public static double getWordDiff(String word){
        word = word.replace(" ", "").toLowerCase();
        char[] letters = word.toCharArray();
        //creating a truncated word so I don't have duplicate letters for the loop
        Set<Character> charSet = new LinkedHashSet<Character>();
        for(char c : letters){
            charSet.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for(char c : charSet){
            sb.append(c);
        }
        String truncatedWord = sb.toString();
        double wordDiff = 0.0f;
        for(char c : truncatedWord.toCharArray()){
            //letter difficulty = ((Word Length / Letter occurrences) * (letter's Zipfs Law Rank)) * % of alphabet not used
            double lettDiff = (((float)word.length() / (float)getCharOccurrences(word, c)) * (1+ZipfsLaw.indexOf(c))) * ((double)(ZipfsLaw.length() - truncatedWord.length())/(double)ZipfsLaw.length());
            //word difficulty += letter difficulty
            wordDiff += lettDiff;
        }

        //wordDiff = Sum of all Letter difficulties * (% of unique letters in word)
        wordDiff = wordDiff * ((double)truncatedWord.length()/(double)word.length());

        wordDiff = Math.floor(wordDiff);

        return wordDiff;
    }
}
