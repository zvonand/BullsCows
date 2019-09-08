package com.bullscows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button checkButton = (Button) findViewById(R.id.play_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textField = (EditText) findViewById(R.id.length_entry);
                String length = textField.getText().toString();
                int wordLength;
                if(length.matches("")){
                    wordLength = 4;
                }else{
                    wordLength = Integer.parseInt(length);
                }
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                i.putExtra("len",wordLength);
                startActivity(i);
            }
        });

    }


}
