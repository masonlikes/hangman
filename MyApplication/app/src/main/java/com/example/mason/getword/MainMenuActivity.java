package com.example.mason.getword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button easyButton = (Button) findViewById(R.id.easy_button);
        Button mediumButton = (Button) findViewById(R.id.medium_button);
        Button hardButton = (Button) findViewById(R.id.hard_button);

        easyButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                startGame();
            }

        });
        mediumButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                startGame();
            }

        });
        hardButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                startGame();
            }

        });
    }

    private void startGame(){

    }
}
