package com.example.mason.getword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button play_again_button = (Button) findViewById(R.id.play_again_button);
        Button main_menu_button = (Button) findViewById(R.id.main_menu_button);

        play_again_button.setOnClickListener(new View.OnClickListener() {

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

        startActivity(intent);
    }

    private void mainMenu(){
        Intent intent = new Intent(this, MainMenuActivity.class);

        startActivity(intent);
    }
}
