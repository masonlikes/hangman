package com.example.mason.getword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rand = new Random();
    private int guesses = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.word_button);
        setButtons();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //change the top text
                changeText();
            }
        });
    }

    private void changeText(){
       // setContentView(R.layout.activity_main);
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

    private boolean checkLetter(String str){
        final TextView wordText = (TextView) findViewById(R.id.word_text);

        return wordText.getText().toString().toUpperCase().contains(str);
    }

    private void changeText(String str){
        final TextView textViewToChange = (TextView) findViewById(R.id.answer_text);
        String answer = null;
        if(checkLetter(str)){
            answer = "Yes";
        }else {
            guesses--;
            answer = "No";
        }
        textViewToChange.setText(answer + ". "  + guesses + " guesses left.");
    }

    private void setButtons(){
        Button a_button = (Button) findViewById(R.id.a_button);
        Button b_button = (Button) findViewById(R.id.b_button);
        Button c_button = (Button) findViewById(R.id.c_button);
        Button d_button = (Button) findViewById(R.id.d_button);
        Button e_button = (Button) findViewById(R.id.e_button);
        Button f_button = (Button) findViewById(R.id.f_button);
        Button g_button = (Button) findViewById(R.id.g_button);
        Button h_button = (Button) findViewById(R.id.h_button);
        Button i_button = (Button) findViewById(R.id.i_button);
        Button j_button = (Button) findViewById(R.id.j_button);
        Button k_button = (Button) findViewById(R.id.k_button);
        Button l_button = (Button) findViewById(R.id.l_button);
        Button m_button = (Button) findViewById(R.id.m_button);
        Button n_button = (Button) findViewById(R.id.n_button);
        Button o_button = (Button) findViewById(R.id.o_button);
        Button p_button = (Button) findViewById(R.id.p_button);
        Button q_button = (Button) findViewById(R.id.q_button);
        Button r_button = (Button) findViewById(R.id.r_button);
        Button s_button = (Button) findViewById(R.id.s_button);
        Button t_button = (Button) findViewById(R.id.t_button);
        Button u_button = (Button) findViewById(R.id.u_button);
        Button v_button = (Button) findViewById(R.id.v_button);
        Button w_button = (Button) findViewById(R.id.w_button);
        Button x_button = (Button) findViewById(R.id.x_button);
        Button y_button = (Button) findViewById(R.id.y_button);
        Button z_button = (Button) findViewById(R.id.z_button);

        a_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("A");
            }

        });
        b_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("B");
            }

        });
        c_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("C");
            }

        });
        d_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("D");
            }

        });
        e_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("E");
            }

        });
        f_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("F");
            }

        });
        g_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("G");
            }

        });
        h_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("H");
            }

        });
        i_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("I");
            }

        });
        j_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("J");
            }

        });
        k_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("K");
            }

        });
        l_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("L");
            }

        });
        m_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("M");
            }

        });
        n_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("N");
            }

        });
        o_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("O");
            }

        });
        p_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("P");
            }

        });
        q_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("Q");
            }

        });
        r_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("R");
            }

        });
        s_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("S");
            }

        });
        t_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("T");
            }

        });
        u_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("U");
            }

        });
        v_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("V");
            }

        });
        w_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("W");
            }

        });
        x_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("X");
            }

        });
        y_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("Y");
            }

        });
        z_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //change the top text
                changeText("Z");
            }

        });
    }
}
