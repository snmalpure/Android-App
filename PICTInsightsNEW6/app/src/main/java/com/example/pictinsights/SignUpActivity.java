package com.example.pictinsights;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    TextInputLayout mNameTextInput;
    TextInputLayout mEmailTextInput;
    TextInputLayout mPasswordTextInput;

    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mPasswordEditText;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    DatabaseReference databaseReference;

    AppCompatButton mAppCompatButton;

    private Spinner spinnerYear;
    private Spinner spinnerBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        mNameTextInput = (TextInputLayout) findViewById(R.id.nameTextInput);
        mEmailTextInput = (TextInputLayout) findViewById(R.id.emailTextInput);
        mPasswordTextInput = (TextInputLayout) findViewById(R.id.passwordTextInput);

        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        mAppCompatButton = (AppCompatButton) findViewById(R.id.registerButton);

        spinnerYear =(Spinner) findViewById(R.id.spinner);
        spinnerBranch = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year_array,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.cell_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        spinnerYear.setAdapter(adapter);
        spinnerBranch.setAdapter(adapter2);


        mAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final StudentDetails studentDetails = new StudentDetails();

                final String name = mNameEditText.getText().toString().trim();
                final String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                final String year = spinnerYear.getSelectedItem().toString();
                final String cell = spinnerBranch.getSelectedItem().toString();


                if(name.equals("") || email.equals("") || password.equals(""))
                {
                    Toast.makeText(SignUpActivity.this,"Enter all the fields!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final ProgressDialog progressDialog = ProgressDialog.show(SignUpActivity.this,"Signing Up",null,true);
                    progressDialog.getWindow().setGravity(Gravity.CENTER);

                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(SignUpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            studentDetails.setStudentName(name);
                            studentDetails.setStudentEmail(email);
                            studentDetails.setStudentYear(year);
                            studentDetails.setStudentCell(cell);

                            String studentID = databaseReference.push().getKey();
                            databaseReference.child(studentID).setValue(studentDetails);

                            progressDialog.dismiss();

                            String email1 = mEmailEditText.getText().toString().trim();

                            Intent i = (new Intent(SignUpActivity.this,MainActivity.class));
                            i.putExtra("value1",email1);
                            startActivity(i);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Sign Up failed!",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }}
        });


    }
}
