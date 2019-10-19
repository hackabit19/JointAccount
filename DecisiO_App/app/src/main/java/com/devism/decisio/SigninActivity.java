package com.mk9866.myloginsignupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        btnSignIn = (Button) findViewById(R.id.button2);
        tvSignUp = (TextView) findViewById(R.id.textView);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(SigninActivity.this, "LoggedIn Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(SigninActivity.this, "Please Log In!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    emailId.setError("Please Enter Email Id!");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("please Enter Password");
                    password.requestFocus();
                } else if (pwd.isEmpty() && email.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(pwd.isEmpty() && email.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SigninActivity.this, "LogIn Error,Plese LogIn Again!", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SigninActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);


    }
}