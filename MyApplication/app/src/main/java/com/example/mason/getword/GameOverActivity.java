package com.example.mason.getword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

<<<<<<< HEAD
        Button play_again_button = (Button) findViewById(R.id.play_again_button);
        Button main_menu_button = (Button) findViewById(R.id.main_menu_button);

        play_again_button.setOnClickListener(new View.OnClickListener() {
=======
        TextView status = (TextView) findViewById(R.id.status);
        status.setText(getIntent().getBooleanExtra("victory", false)?getString(R.string.victory):getString(R.string.defeat));

        TextView word = (TextView) findViewById(R.id.word);
        word.setText(getIntent().getStringExtra("WORD"));

        Button playAgainBtn = (Button) findViewById(R.id.play_again_button);
        Button menuBtn = (Button) findViewById(R.id.menuBtn);
>>>>>>> 6b08f128abf5aa3f2a3a3ab05f54cba3ee6dbebf

        menuBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hangman.class);
//                intent.putExtra("PlayAgain", false);
                startActivity(intent);
            }
        });

        playAgainBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                playAgain();
            }

        });
        main_menu_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mainMenu();

            }

        });
    }

    private void playAgain(){
        Intent intent = new Intent(this, Hangman.class);
        intent.putExtra("PlayAgain", true);
        intent.putExtra("Difficulty", intent.getIntExtra("Difficulty", 1));
        startActivity(intent);
    }

    private void mainMenu(){
        Intent intent = new Intent(this, MainMenuActivity.class);

        startActivity(intent);
    }
}
