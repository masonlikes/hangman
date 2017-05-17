package com.example.mason.getword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText();
            }

        });
    }

    private void changeText(){
        setContentView(R.layout.activity_main);
        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        textViewToChange.setText("change");
    }
}
