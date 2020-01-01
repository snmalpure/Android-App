package com.example.pictinsights;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton mSignupButton;
    AppCompatButton mLoginButton;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    TextInputLayout mEmailTextInput;
    TextInputLayout mPasswordTextInput;

    EditText mEmailEditText;
    EditText mPasswordEditText;

    String email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mEmailTextInput = (TextInputLayout) findViewById(R.id.emailTextInput);
        mPasswordTextInput = (TextInputLayout) findViewById(R.id.passwordTextInput);

        mEmailEditText = (EditText) findViewById(R.id.emailTextView);
        mPasswordEditText = (EditText) findViewById(R.id.passwordTextView);

        mSignupButton = (AppCompatButton) findViewById(R.id.not_a_member_button);

        mLoginButton = (AppCompatButton) findViewById(R.id.login_button);


        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmailEditText.getText().toString().trim();
                final String password = mPasswordEditText.getText().toString().trim();

                if(email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter email and password!", Toast.LENGTH_SHORT).show();
                }
                else {

                    final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"Authenticating",null,true);
                    progressDialog.getWindow().setGravity(Gravity.CENTER);

                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            if(email.equals("admin@pict.com") && password.equals("admin1"))
                            {
                                startActivity(new Intent(LoginActivity.this,DatabaseActivity.class));
                                Toast.makeText(LoginActivity.this, "Admin Log in successful!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else {

                                Intent i=(new Intent(LoginActivity.this, MainActivity.class));
                                email1 = mEmailEditText.getText().toString().trim();

                                i.putExtra("value1",email1);
                                startActivity(i);
                                finish();
                                Toast.makeText(LoginActivity.this, "User Log in successful!", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Log in failed!",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

            }}
        });
    }
}

