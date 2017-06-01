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

        TextView status = (TextView) findViewById(R.id.status);
        status.setText(getIntent().getBooleanExtra("victory", false)?getString(R.string.victory):getString(R.string.defeat));

        TextView word = (TextView) findViewById(R.id.word);
        word.setText(getIntent().getStringExtra("WORD"));

        Button playAgainBtn = (Button) findViewById(R.id.play_again_button);
        Button menuBtn = (Button) findViewById(R.id.menuBtn);

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
    }

    private void playAgain(){
        Intent intent = new Intent(this, Hangman.class);
        intent.putExtra("PlayAgain", true);
        intent.putExtra("Difficulty", intent.getIntExtra("Difficulty", 1));
        startActivity(intent);
    }
}
