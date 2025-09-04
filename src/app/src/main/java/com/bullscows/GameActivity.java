package com.bullscows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bullscows.bullscows_app.R;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    private int count = 0;
    private int stringLength = 0;
    private ArrayList<String> number = new ArrayList<>(stringLength);
    private ArrayList<HistoryItem> itemArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stringLength = extras.getInt("len");
        }

        number = generateRandom(stringLength);
        ItemAdapter adapter = new ItemAdapter(this, itemArrayList);
        ListView historyListView = (ListView) findViewById(R.id.history_list);
        historyListView.setAdapter(adapter);
        final EditText inputField = (EditText) findViewById(R.id.number_entry);
        inputField.setHint("Enter a " + stringLength + "-digit number");
        Button check = (Button) findViewById(R.id.check_button);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                String fromInput = inputField.getText().toString();
                inputField.setText(null);
                int bulls = countBulls(fromInput);
                int cows = countCows(fromInput);
                HistoryItem itemToAdd = new HistoryItem(bulls, cows, count, fromInput);
                itemArrayList.add(itemToAdd);
                hideSoftKeyboard(GameActivity.this);
                if(bulls == stringLength){
                    Toast.makeText(GameActivity.this, "You guessed in " + count + " attempts", Toast.LENGTH_LONG).show();
                    itemArrayList.clear();
                    Intent i = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }
    public ArrayList<String> generateRandom(int length){
        ArrayList<String> generated = new ArrayList<>();
        Random rnd = new Random();
        ArrayList<Integer> ints = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            ints.add(i);
        }
        for(int i = 0; i < length; i++){
            int a = rnd.nextInt(ints.size());
            int b = ints.get(a);
            String stringToAdd = String.valueOf(b);
            generated.add(stringToAdd);
            Log.i("Generated", stringToAdd);
            ints.remove(a);
        }


        return generated;

    }

    public int countBulls(String toCompare){
        int c = 0;
        for(int i = 0; i < stringLength; i++){
            if(Character.toString(toCompare.charAt(i)).equals(number.get(i))){
                c++;
            }
        }
        return c;
    }

    public int countCows(String toCompare){
        int c = 0;
        for(int i = 0; i < stringLength; i++){
            for(int y = 0; y < stringLength; y++){
                if(Character.toString(toCompare.charAt(i)).equals(number.get(y))){
                    c++;
                }
            }
        }
        c -= countBulls(toCompare);
        return c;
    }

    public static void hideSoftKeyboard(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
