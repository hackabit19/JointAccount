package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void sendMessage(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        int option = 1;
        if(selectedtext.contains("What to Listen?")) {

        }
        else if(selectedtext.contains("What to Eat?")) {

        }
        else if(selectedtext.contains("What to Wear?")) {

        }
        else if(selectedtext.contains("What to Do?")) {

        }
        else if(selectedtext.contains("Where to Go?")) {

        }
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
