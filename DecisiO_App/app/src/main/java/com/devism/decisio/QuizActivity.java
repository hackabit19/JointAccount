package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void sendMessage(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        int option = 1;
        if(selectedtext.contains("6 Hours")) option = 1;
        else if(selectedtext.contains("12 Hours")) option = 2;
        else if(selectedtext.contains("24 Hours")) option = 3;
        else if(selectedtext.contains("48 Hours")) option = 4;
        else if(selectedtext.contains("1 Week")) option = 5;
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
    }

    private void getTimeFromAndroid() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        int rtd = 1;
        if (timeOfDay >= 0 && timeOfDay < 12) rtd = 1;
        else if (timeOfDay >= 12 && timeOfDay < 16) rtd = 2;
        else if (timeOfDay >= 16 && timeOfDay < 21) rtd = 3;
        else if (timeOfDay >= 21 && timeOfDay < 24) rtd = 4;
    }
}
