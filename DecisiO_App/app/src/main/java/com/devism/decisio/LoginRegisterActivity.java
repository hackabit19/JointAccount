package com.devism.decisio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
    }

    public void sendMessage (View view) {
        EditText text = (EditText) findViewById(R.id.email);
        String value = text.getText().toString();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String selectedtext = (String) radioButton.getText();
        if(selectedtext.contains("Register")) {
            Intent intent = new Intent(this, GenderDOBActivity.class);
            startActivity(intent);
        }
        else if(selectedtext.contains("Login")) {
            Intent intent = new Intent(this, DashActivity.class);
            startActivity(intent);
        }
    }
}
