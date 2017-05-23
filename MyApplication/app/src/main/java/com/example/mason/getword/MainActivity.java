package com.example.mason.getword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Random rand = new Random();

    final static String ZipfsLaw = "etaoinshrdlcumwfgypbvkjxqz";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selfDestructBtn = new Button(this);
        selfDestructBtn.setText("Push me!");
        selfDestructBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                Toast.makeText(v.getContext(), "Button destroyed!", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout container = (RelativeLayout)findViewById(R.id.container);
        container.addView(selfDestructBtn);


    }

    private void changeText(){
        final TextView textViewToChange = (TextView) findViewById(R.id.word_text);
        textViewToChange.setText(returnRandomWord());
    }

    private String returnRandomWord(){
        String randomWord = "";
        try {

            InputStream is = getAssets().open("Words.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String fileContent = new String(buffer, "UTF-8");
            String[] contentArray = fileContent.split(",");
            randomWord = contentArray[rand.nextInt(contentArray.length)];

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return randomWord;
    }
}
