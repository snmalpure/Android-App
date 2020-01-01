package com.example.pictinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DatabaseActivity extends AppCompatActivity {

    String cell;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        spinner = (Spinner) findViewById(R.id.spinner5);

        AppCompatButton showbutton = (AppCompatButton)findViewById(R.id.showButton2);

        AppCompatButton update = (AppCompatButton) findViewById(R.id.updateButton);

        AppCompatButton logout = (AppCompatButton) findViewById(R.id.logoutbutton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cell_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        spinner.setAdapter(adapter);

        final FirebaseAuth fire1;
        fire1=FirebaseAuth.getInstance();

        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DatabaseActivity.this, DisplayStudentsListview.class);
                cell = spinner.getSelectedItem().toString();
                i.putExtra("value",cell);
                startActivity(i);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DatabaseActivity.this, AdminUpdate.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fire1.signOut();

                startActivity(new Intent(DatabaseActivity.this, LoginActivity.class));
                Toast.makeText(DatabaseActivity.this,"Admin is Logged Out",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
