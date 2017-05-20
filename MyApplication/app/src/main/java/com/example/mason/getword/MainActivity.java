package com.example.mason.getword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selfDestructBtn = new Button(this);
        selfDestructBtn.setText("Push me!");
        selfDestructBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                Toast.makeText(v.getContext(), "Button destroyed!", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout container = (RelativeLayout)findViewById(R.id.container);
        container.addView(selfDestructBtn);


    }

    private void changeText(){
        final TextView textViewToChange = (TextView) findViewById(R.id.word_text);
        textViewToChange.setText(returnRandomWord());
    }

    private String returnRandomWord(){
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
}
