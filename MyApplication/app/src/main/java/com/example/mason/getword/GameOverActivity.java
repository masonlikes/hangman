package com.example.mason.getword;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game_over);

        TextView status = (TextView) findViewById(R.id.status);
        status.setText(getIntent().getBooleanExtra("victory", false)?getString(R.string.victory):getString(R.string.defeat));

        TextView word = (TextView) findViewById(R.id.word);
//        word.setText();
        String actual = getIntent().getStringExtra("WORD");
        word.setText(getString(R.string.word, actual));
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
        int diff = getIntent().getIntExtra("diff", -1);
        Log.i("Diff", ""+diff);
        intent.putExtra("diff", diff);
        startActivity(intent);
    }
}