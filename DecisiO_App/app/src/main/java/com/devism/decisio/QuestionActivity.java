package com.devism.decisio;

//import com.devism.post;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionActivity extends AppCompatActivity {


    //PostRequest postRequest = new PostRequest();
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
        String url = "";
        if(selectedtext.contains("What to Listen?")) {
        //    url = "";
        }
        else if(selectedtext.contains("What to Eat?")) {
          //  url = "";
        }
        else if(selectedtext.contains("What to Wear?")) {
            //url = "";
        }
        else if(selectedtext.contains("What to Do?")) {
           // url = "";
        }
        else if(selectedtext.contains("Where to Go?")) {
            //url = "";
        }

    }
}
