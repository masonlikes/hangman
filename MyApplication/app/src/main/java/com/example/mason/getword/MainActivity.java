package com.example.mason.getword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    final static int letter_id_base = 10000;
    final static int keyboard_id_base = 20000;

    Random rand = new Random();
    TextView used_letters;
    String wordToGuess;
    TextView word_to_guess;
    List<String> used;
    LinearLayout container, TopRow, MidRow, BotRow;
    Button reset_button;
    Button hint_button;

//    private String word = null;

    final String[]
            TopLetters = { "Q","W","E","R","T","Y","U","I","O","P" },
            MidLetters = { "A","S","D","F","G","H","J","K","L" },
            BotLetters = { "Z","X","C","V","B","N","M" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeBoth();
    }

    private void makeBoth(){
        makeWordToGuess();
        generateKeyboard();
    }

    private void makeWordToGuess(){
//        wordToGuess = getRandomWord();
//        setContentView(R.layout.game_view);
//        word_to_guess = new TextView(this);
//        word_to_guess.setText(wordToGuess);
//        container = (LinearLayout)findViewById(R.id.container);
//
//        container.addView(word_to_guess);

        wordToGuess = getIntent().getStringExtra("WORD");
        setContentView(R.layout.game_view);
        word_to_guess = new TextView(this);
        word_to_guess.setText(wordToGuess);
//        word_to_guess.setText(word);
        container = (LinearLayout)findViewById(R.id.container);
        //container.addView(word_to_guess);

        makeLetterLabels(wordToGuess);
    }

    private void makeLetterLabels(String str){
        TextView letter = null;
        LinearLayout layout = new LinearLayout(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;
        layout.setLayoutParams(lp);

//        LinearLayout layout = (LinearLayout)findViewById(R.id.LetterContainer);
        layout.setId(' '+letter_id_base);
        for(int i = 0; i < str.length(); i++){
//            String upperStr = str.toUpperCase();
//            letter.setText(str.charAt(i)+"");
//            letter.setId(upperStr.charAt(i));
            letter = new TextView(this);
            letter.setTextSize(30);
            letter.setId(letter_id_base+i);
            letter.setText("_");
            letter.setPadding(10, 10, 10, 10);

            layout.addView(letter);
//            letter.setText(str.charAt(i)+"");
//            letter.setId(upperStr.charAt(i));
//            letter.setVisibility(View.INVISIBLE);
        }
        container.addView(layout);
    }

    public void generateKeyboard(){
        used_letters = new TextView(this);
        used_letters.setText(getApplicationContext().getString(R.string.used_letters, "None!"));
        used_letters.setTextSize(22);
        used = new ArrayList<String>();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;

        TopRow = new LinearLayout(this);
        MidRow = new LinearLayout(this);
        BotRow = new LinearLayout(this);
        TopRow.setOrientation(LinearLayout.HORIZONTAL);
        MidRow.setOrientation(LinearLayout.HORIZONTAL);
        BotRow.setOrientation(LinearLayout.HORIZONTAL);
        TopRow.setLayoutParams(lp);
        MidRow.setLayoutParams(lp);
        BotRow.setLayoutParams(lp);
        TopRow.setGravity(Gravity.CENTER);
        MidRow.setGravity(Gravity.CENTER);
        BotRow.setGravity(Gravity.CENTER);

        Button btn;
        int i;
        for(i = 0; i < TopLetters.length; i++){
            btn = new Button(this, null, android.R.attr.buttonStyleSmall);
            btn.setText(TopLetters[i]);
            btn.setId(TopLetters[i].charAt(0)+keyboard_id_base);
            setupKeyboardButton(btn);
            TopRow.addView(btn);
        }
        for(i = 0; i < MidLetters.length; i++){
            btn = new Button(this, null, android.R.attr.buttonStyleSmall);
            btn.setText(MidLetters[i]);
            btn.setId(MidLetters[i].charAt(0)+keyboard_id_base);
            setupKeyboardButton(btn);
            MidRow.addView(btn);
        }
        for(i = 0; i < BotLetters.length; i++){
            btn = new Button(this, null, android.R.attr.buttonStyleSmall);
            btn.setText(BotLetters[i]);
            btn.setId(BotLetters[i].charAt(0)+keyboard_id_base);
            setupKeyboardButton(btn);
            BotRow.addView(btn);
        }

        container = (LinearLayout)findViewById(R.id.container);

        container.addView(used_letters);

        container.addView(TopRow);
        container.addView(MidRow);
        container.addView(BotRow);

        LinearLayout ll = new LinearLayout(this);

        reset_button = new Button(this);
        hint_button = new Button(this);

        reset_button.setText(R.string.reset);
        hint_button.setText(R.string.hint);

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBoth();
                resetBody();
            }
        });
        hint_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currWord = letterLabelsToString();
                boolean equal = wordToGuess.equals(currWord);
                if(!equal) {
                    giveHint(v);
                }else{
                    Toast.makeText(v.getContext(), " No more hints available ", Toast.LENGTH_SHORT).show();
                }

                if(gameIsOver()){
                    gameOverScreen();
                }
            }
        });

        ll.addView(reset_button);
        ll.addView(hint_button);
        ll.setGravity(Gravity.CENTER);
        container.addView(ll);
    }

    public String letterLabelsToString(){
        String word = "";
        LinearLayout ll = (LinearLayout) container.findViewById(' '+letter_id_base);
        TextView holder = null;
        for(int i = 0; i < ll.getChildCount(); i++){
            holder = (TextView)ll.getChildAt(i);
            if(holder.getVisibility() != View.INVISIBLE) {
                word += holder.getText().toString();
            }
        }
        return word;
    }

    private void giveHint(View v){
        char letter;
        String strLetter;
        do {
            letter = wordToGuess.charAt(rand.nextInt(wordToGuess.length()));
            strLetter = letter+"";
        }while (used.contains(strLetter.toUpperCase()));
        Button holder = (Button) container.findViewById(strLetter.toUpperCase().charAt(0)+keyboard_id_base);
        makeLetterVisible(strLetter.toUpperCase());
        fadeLetters(holder);
    }

    public void setupKeyboardButton(Button btn){
        btn.setOnClickListener(new KeyboardListener());
        btn.setHeight(17);
        btn.setWidth(25);
        btn.setTextSize(10);
        btn.setMaxLines(1);
        btn.setMaxHeight(5);
        btn.setPadding(0, 0, 0, 0);
        btn.setMinHeight(0);
        btn.setMinWidth(0);
        btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
    }

    private class KeyboardListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            CharSequence btnLetter = ((Button) v).getText();
            if(!checkLetter(btnLetter.toString())) {
                revealBodyPart();
            }
            else{
                makeLetterVisible(((Button) v).getText().toString());
            }
            fadeLetters(v);

            if(letterLabelsToString().equalsIgnoreCase(wordToGuess)){
                Log.i("MyApp", "GAME OVER");
                Toast.makeText(getBaseContext(), "GAME OVER!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                i.putExtra("Difficulty", getIntent().getIntExtra("Difficulty", 1));
                i.putExtra("victory", true);
                startActivity(i);
            }

            if(gameIsOver()){
                gameOverScreen();
            }
        }
    }

    private void fadeLetters(View v){
        used.add(((Button) v).getText().toString());
        used_letters.setText(getApplicationContext().getString(R.string.used_letters, TextUtils.join(", ", used)));
        v.setEnabled(false);
    }

    private void gameOverScreen(){
        Intent intent = new Intent(this, GameOverActivity.class);

        startActivity(intent);
    }

    private boolean gameIsOver(){
        return wordToGuess.equals(letterLabelsToString()) || hangmanCounter == 7;
    }

    private void makeLetterVisible(String c){
        String wurd = word_to_guess.getText().toString().toUpperCase();
        int i = wurd.indexOf(c);
        String letter = "";
        if(i > -1){
            letter = (""+wurd.charAt(i)+"").toUpperCase();
            while(i != -1){
                TextView view = (TextView)findViewById(letter_id_base+i);
                view.setText(letter);
                i = wurd.indexOf(c, i+1);
            }
        }
    }

    private boolean checkLetter(String str){
        return word_to_guess.getText().toString().toUpperCase().contains(str);
    }



//    private void changeText(){
//        Intent i = new Intent(getBaseContext(), Hangman.class);
//        i.putExtra("WORD", getRandomWord());
//
//
//        /* take the above code and it's related methods and put them into the main menu activity */
//        word = getIntent().getStringExtra("WORD");
//        word_to_guess.setText(word);
//    }

    private void updateHeight(View view) {
        int visibility = view.getVisibility();
        view.setVisibility(View.GONE);
        view.setVisibility(visibility);
    }

    ImageView image;
    int hangmanCounter = 1;
    private void resetBody(){
        hangmanCounter = 1;
        image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.hangman1);
    }

    private void revealBodyPart(){
        image = (ImageView) findViewById(R.id.imageView1);
        if(hangmanCounter == 1){
            image.setImageResource(R.drawable.hangman2);
            hangmanCounter++;
        }
        else if(hangmanCounter == 2){
            image.setImageResource(R.drawable.hangman3);
            hangmanCounter++;
        }
        else if(hangmanCounter == 3){
            image.setImageResource(R.drawable.hangman4);
            hangmanCounter++;
        }
        else if(hangmanCounter == 4){
            image.setImageResource(R.drawable.hangman5);
            hangmanCounter++;
        }
        else if(hangmanCounter == 5){
            image.setImageResource(R.drawable.hangman6);
            hangmanCounter++;
        }
        else {
            image.setImageResource(R.drawable.hangman7);
            hangmanCounter++;
        }
    }
}
