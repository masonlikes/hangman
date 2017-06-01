package com.example.mason.getword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Hangman extends AppCompatActivity {

    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        /* Check if they want to play again */
        if(getIntent().getBooleanExtra("PlayAgain", false)){
            int difficulty = getIntent().getIntExtra("Difficulty", 1);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);

            i.putExtra("WORD", getRandomWord());
            i.putExtra("Difficulty", difficulty);
            startActivity(i);
        }

        Button start = (Button) findViewById(R.id.startbtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("WORD", getRandomWord());
                i.putExtra("Difficulty", 1);
                startActivity(i);
            }
        });


        Button easy = (Button) findViewById(R.id.easy_game);
        Button medium = (Button) findViewById(R.id.medium_game);
        Button hard = (Button) findViewById(R.id.hard_game);

        //add click listeners here
    }

    private String getRandomWord(int difficulty){
        return "";
    }

    private String getRandomWord(){
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


//        final TextView textViewToChange = (TextView) findViewById(R.id.word_text);
//        String randomWord = returnRandomWord();
//        textViewToChange.setText(randomWord);
        double randoWordDiff = WordDifficulty.getWordDiff(randomWord);

        return randomWord;
    }
}
