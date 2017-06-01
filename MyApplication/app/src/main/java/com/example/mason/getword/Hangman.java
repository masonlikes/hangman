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

<<<<<<< HEAD
    final static int letter_id_base = 10000;
    final static int keyboard_id_base = 20000;

    boolean firstTime = true;
=======
>>>>>>> 6b08f128abf5aa3f2a3a3ab05f54cba3ee6dbebf
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        if(firstTime) {
            firstTime = false;
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }

        makeBoth();
    }

    private void makeBoth(){
        makeWordToGuess();
        generateKeyboard();
    }


    private void makeWordToGuess(){
        wordToGuess = getRandomWord();
        setContentView(R.layout.activity_self_deleting_button);
        word_to_guess = new TextView(this);
        word_to_guess.setText(wordToGuess);
        container = (LinearLayout)findViewById(R.id.container);

        container.addView(word_to_guess);

        makeLetterLabels(wordToGuess);
    }

    private void makeLetterLabels(String str){
        TextView letter = null;
        LinearLayout layout = new LinearLayout(this);
        layout.setId(' '+letter_id_base);
        for(int i = 0; i < str.length(); i++){
            String upperStr = str.toUpperCase();
            letter = new TextView(this);
            letter.setText(str.charAt(i)+"");
//            letter.setId(upperStr.charAt(i));
            letter.setId(letter_id_base+i);
            letter.setVisibility(View.INVISIBLE);
            layout.addView(letter);
        }
        container.addView(layout);
    }

    public void generateKeyboard(){
        used_letters = new TextView(this);
        used_letters.setText(getApplicationContext().getString(R.string.used_letters, "None!"));
        used = new ArrayList<String>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
=======
        setContentView(R.layout.main_menu);
>>>>>>> 6b08f128abf5aa3f2a3a3ab05f54cba3ee6dbebf


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
