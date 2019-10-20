package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
    }

    public void sendMessage(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
        int option = 1;
        if(selectedtext.contains("Cheerful")) option = 1;
        else if(selectedtext.contains("Gloomy")) option = 2;
        else if(selectedtext.contains("Humorous")) option = 3;
        else if(selectedtext.contains("Calm")) option = 4;
        else if(selectedtext.contains("Romantic")) option = 5;
        else if(selectedtext.contains("Angry")) option = 6;
        else if(selectedtext.contains("Tense")) option = 7;
        else if(selectedtext.contains("Lighthearted")) option = 8;
        Intent i = new Intent(this, QuestionActivity.class);
        startActivity(i);
    }
}
