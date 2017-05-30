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

        Button button = (Button) findViewById(R.id.play_again_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                playAgain();

            }

        });
    }

    private void playAgain(){
        Intent intent = new Intent(this, Hangman.class);

        startActivity(intent);
    }
}
