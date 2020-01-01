package com.example.pictinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class AcmExtraActivity extends AppCompatActivity {

    AppCompatButton mAcmExtraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acm_extra);

        mAcmExtraButton = (AppCompatButton) findViewById(R.id.acmextraButton2);

        mAcmExtraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AcmExtraActivity.this, AcmListview.class));
            }
        });
    }
}

