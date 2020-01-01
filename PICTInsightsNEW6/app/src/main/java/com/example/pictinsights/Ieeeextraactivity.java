package com.example.pictinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class Ieeeextraactivity extends AppCompatActivity {
    AppCompatButton IEEEExtraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ieeeextraactivity);

        IEEEExtraButton=(AppCompatButton) findViewById(R.id.ieeextraButton2);

        IEEEExtraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Ieeeextraactivity.this,IeeeListview.class));
            }
        });
    }
}

