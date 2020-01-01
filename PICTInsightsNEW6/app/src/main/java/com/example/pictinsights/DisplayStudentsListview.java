package com.example.pictinsights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayStudentsListview extends AppCompatActivity {

    ArrayList<String> myarraylist=new ArrayList<>();
    ListView mylistview;
    FirebaseDatabase database1;
    DatabaseReference datarefer;
    String cell1,cell2,name,year,email,count2;

    StudentDetails st1=new StudentDetails();
    int count1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students_listview);

        mylistview=(ListView)findViewById(R.id.list7);

        cell1=getIntent().getExtras().getString("value");

        final ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this,R.layout.activity_display_students,R.id.textView38,myarraylist);

        database1= FirebaseDatabase.getInstance();
        datarefer= database1.getReference("Users");

        datarefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    st1=ds.getValue(StudentDetails.class);
                    cell2=st1.getStudentCell().toString();

                    if(cell1.equalsIgnoreCase(cell2))
                    {
                        name=st1.getStudentName().toString();
                        count1++;
                        count2=String.valueOf(count1);
                        year = st1.getStudentYear().toString();
                        email = st1.getStudentEmail();

                        myarraylist.add(count2.concat(".").concat(" ").concat(name).concat("  ").concat(year).concat("  ").concat(email));
                    }
                }

                mylistview.setAdapter(myadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

}