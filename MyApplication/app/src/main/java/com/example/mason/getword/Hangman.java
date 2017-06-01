
package com.example.mason.getword;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman extends AppCompatActivity {

    Random rand = new Random();

    final static int MEDIUM = 150, HARD = 300;

    List<String> EasyWords = new ArrayList<>();
    List<String> MediumWords = new ArrayList<>();
    List<String> HardWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main_menu);

        loadWords();

        /* Check if they want to play again */
        if(getIntent().getBooleanExtra("PlayAgain", false)){
            int difficulty = getIntent().getIntExtra("diff", -1);
            Log.i("diff", ""+difficulty);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("WORD", getRandomWord(difficulty));
            i.putExtra("diff", difficulty);
            startActivity(i);
        }

        Button start = (Button) findViewById(R.id.startbtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("WORD", getRandomWord(getIntent().getIntExtra("diff", -1)));
                i.putExtra("diff", -1);
                startActivity(i);
            }
        });


        Button easy = (Button) findViewById(R.id.easy_game);
        easy.setOnClickListener(new DifficultyButtonClickListener(1));
        Button medium = (Button) findViewById(R.id.medium_game);
        medium.setOnClickListener(new DifficultyButtonClickListener(2));
        Button hard = (Button) findViewById(R.id.hard_game);
        hard.setOnClickListener(new DifficultyButtonClickListener(3));

        //add click listeners here
    }

    private class DifficultyButtonClickListener implements Button.OnClickListener{
        private final int d;
        public DifficultyButtonClickListener(int d){
            this.d = d;
        }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("WORD", getRandomWord(d));
            i.putExtra("diff", d);
            startActivity(i);
        }
    }

    private String getRandomWord(int difficulty){
        switch (difficulty){
            case 1:
                return EasyWords.get(rand.nextInt(EasyWords.size()));
            case 2:
                return MediumWords.get(rand.nextInt(MediumWords.size()));
            case 3:
                return HardWords.get(rand.nextInt(HardWords.size()));
            default:
                return getRandomWord();

        }
    }

    private void loadWords(){
        try{
            InputStream is = getAssets().open("Words.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String fileContent = new String(buffer, "UTF-8");
            String[] contentArray = fileContent.split(",");

            for (String word: contentArray) {
                double diff = WordDifficulty.getWordDiff(word);
                Log.i("Word", word+"");
                Log.i("Diff", diff+"");
                if(diff < MEDIUM){
                    EasyWords.add(word);
                } else if(diff >= MEDIUM && diff < HARD) {
                    MediumWords.add(word);
                } else if(diff >= HARD){
                    HardWords.add(word);
                } else {
                    throw new IllegalStateException();
                }
            }
            Log.i("Easy", EasyWords.size()+"");
            Log.i("Med", MediumWords.size()+"");
            Log.i("Hard", HardWords.size()+"");
        } catch (Exception e){
            e.printStackTrace();
        }
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
