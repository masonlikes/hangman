package com.example.mason.getword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.id;
import static com.example.mason.getword.R.id.word_text;

public class Hangman extends AppCompatActivity {

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

            //see if they won
            Toast.makeText(getApplicationContext(), "CHECK WIN CONDITIONS", Toast.LENGTH_SHORT);

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
        while(i != -1){
            findViewById(letter_id_base+i).setVisibility(View.VISIBLE);
            i = wurd.indexOf(c, i+1);
        }
    }

    private boolean checkLetter(String str){
        return word_to_guess.getText().toString().toUpperCase().contains(str);
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

    private void changeText(){
        word_to_guess.setText(getRandomWord());
    }

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
