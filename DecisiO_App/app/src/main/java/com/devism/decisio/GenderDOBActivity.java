package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GenderDOBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_dob);
    }

    public void sendMessage (View view) {
        EditText text = (EditText) findViewById(R.id.age);
        String value = text.getText().toString();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        int gender = 0;
        if(selectedtext.contains("Male")) gender = 1;
        else if(selectedtext.contains("Female")) gender = 0;
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
    }
}
