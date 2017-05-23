package com.example.mason.getword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelfDeletingButton extends AppCompatActivity {

    Random rand = new Random();
    TextView used_letters;
    String wordToGuess;
    TextView word_to_guess;
    List<String> used;
    LinearLayout container, TopRow, MidRow, BotRow;
    Button resetbtn;
    Button get_word_button;

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
        for(int i = 0; i < str.length(); i++){
            letter = new TextView(this);
            letter.setText(str.charAt(i)+"");
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
            setupKeyboardButton(btn);
            TopRow.addView(btn);
        }
        for(i = 0; i < MidLetters.length; i++){
            btn = new Button(this, null, android.R.attr.buttonStyleSmall);
            btn.setText(MidLetters[i]);
            setupKeyboardButton(btn);
            MidRow.addView(btn);
        }
        for(i = 0; i < BotLetters.length; i++){
            btn = new Button(this, null, android.R.attr.buttonStyleSmall);
            btn.setText(BotLetters[i]);
            setupKeyboardButton(btn);
            BotRow.addView(btn);
        }

        container = (LinearLayout)findViewById(R.id.container);

        container.addView(used_letters);

        container.addView(TopRow);
        container.addView(MidRow);
        container.addView(BotRow);

        resetbtn = new Button(this);
        resetbtn.setText(R.string.reset);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBoth();
            }
        });
        container.addView(resetbtn);
        get_word_button = new Button(this);
        get_word_button.setText(R.string.get_new_word);
        get_word_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {makeBoth();
            }
        });
        container.addView(get_word_button);
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
                v.setVisibility(View.GONE);
                Toast.makeText(v.getContext(), " (" + btnLetter + ") Button destroyed!", Toast.LENGTH_SHORT).show();

                used.add("" + ((Button) v).getText());
                used_letters.setText(getApplicationContext().getString(R.string.used_letters, TextUtils.join(", ", used)));
            }
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

}
