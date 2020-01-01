package com.example.pictinsights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RoboconActivity extends AppCompatActivity {

    AppCompatButton mroboconExtraButton;
    ArrayList<String> myarraylist=new ArrayList<>();
    ListView mylistview;
    FirebaseDatabase database1;
    DatabaseReference datarefer;

    NoticeUpdate news1=new NoticeUpdate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robocon);

        mylistview=(ListView)findViewById(R.id.list3);
        mroboconExtraButton = (AppCompatButton) findViewById(R.id.roboconextraButton);

        /*final ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this,R.layout.activity_robocon_extra,R.id.textView17,myarraylist);

        database1= FirebaseDatabase.getInstance();
        datarefer= database1.getReference("Robocon News");

        mroboconExtraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datarefer.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        // String value=dataSnapshot.getValue(String.class);
                        // myarraylist.add(value);
                        news1=dataSnapshot.getValue(newsupdate.class);
                        myarraylist.add(news1.getNews().toString());
                        myadapter.notifyDataSetChanged();
                        startActivity(new Intent(RoboconActivity.this,RoboconListview.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });*/

        mroboconExtraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoboconActivity.this,RoboconExtra.class));
            }
        });

    }
}
