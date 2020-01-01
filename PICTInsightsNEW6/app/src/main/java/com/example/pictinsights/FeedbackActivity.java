package com.example.pictinsights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    AppCompatButton butstar;
    RatingBar ratestar;
    String myrate;
    DatabaseReference datastar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        butstar = (AppCompatButton)findViewById(R.id.submitbutton);

        ratestar=(RatingBar)findViewById(R.id.ratingBar);

        datastar= FirebaseDatabase.getInstance().getReference("star");

        ratestar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int star=(int)v;
                String message=null;
                myrate=String.valueOf(ratingBar.getRating());

                switch (star)
                {
                    case 0:
                        message="Sorry to hear that :(";
                        break;
                    case 1:
                        message="Sorry to hear that :(";
                        break;
                    case 2:
                        message="We are open to suggestions!";
                        break;
                    case 3:
                        message="Good enough!";
                        break;
                    case 4:
                        message="Great! Thank You";
                        break;
                    case 5:
                        message="AWESOME! :)";
                        break;
                }
                Toast.makeText(FeedbackActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
        butstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Toast.makeText(FeedbackActivity.this,"Your Rating is: "+String.valueOf(myrate),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void update()
    {
        String star1=String.valueOf(myrate);

        if(!TextUtils.isEmpty(star1))
        {
            String id=datastar.push().getKey();
            FeedbackUpdate feedu = new FeedbackUpdate (star1,id);
            datastar.child(id).setValue(feedu);
            Toast.makeText(this,"Feedback Received. Thank You!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"You should give rating",Toast.LENGTH_LONG).show();
        }
    }

}

