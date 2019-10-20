package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
    }

    public void sendMessage(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        int option = 1;
        if(selectedtext.contains("Want to Answer?")) option = 1;
        else if(selectedtext.contains("Want to Question?")) option = 2;
        if(option == 2) {
            Intent intent = new Intent(this, MoodActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, AnswerActivity.class);
            startActivity(intent);
        }
    }
}
