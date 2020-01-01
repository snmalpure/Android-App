package com.example.pictinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class ArtCircleActivity extends AppCompatActivity {
    AppCompatButton artbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artcircle);

        artbutton = (AppCompatButton) findViewById(R.id.artextraButton);

        artbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(ArtCircleActivity.this,ArtCircleExtra.class));
    }
});
    }
}