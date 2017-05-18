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

import java.util.ArrayList;
import java.util.List;

public class SelfDeletingButton extends AppCompatActivity {

    TextView used_letters;

    List<String> used;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        used_letters = new TextView(this);
        used_letters.setText(getApplicationContext().getString(R.string.used_letters, "None!"));
        used = new ArrayList<String>();

        setContentView(R.layout.activity_self_deleting_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;
        String[] TopLetters = { "A","B","C","D","E","F","G","H","I" };
        String[] MidLetters = { "J","K","L","M","N","O","P","Q","R" };
        String[] BotLetters = { "S","T","U","V","W","X","Y","Z" };

        LinearLayout TopRow = new LinearLayout(this);
        LinearLayout MidRow = new LinearLayout(this);
        LinearLayout BotRow = new LinearLayout(this);
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

        LinearLayout container = (LinearLayout)findViewById(R.id.container);

        container.addView(used_letters);

        container.addView(TopRow);
        container.addView(MidRow);
        container.addView(BotRow);
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
            v.setVisibility(View.GONE);
            Toast.makeText(v.getContext(), " (" + ((Button) v).getText() + ") Button destroyed!", Toast.LENGTH_SHORT).show();

            used.add(""+((Button) v).getText());
            used_letters.setText(getApplicationContext().getString(R.string.used_letters,TextUtils.join(", ", used)));
        }
    }

    private void updateHeight(View view) {
        int visibility = view.getVisibility();
        view.setVisibility(View.GONE);
        view.setVisibility(visibility);
    }

}
