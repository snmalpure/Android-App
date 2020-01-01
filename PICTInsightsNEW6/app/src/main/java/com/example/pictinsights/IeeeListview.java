package com.example.pictinsights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IeeeListview extends AppCompatActivity {
    ArrayList<String> myarraylist=new ArrayList<>();
    ListView mylistview;
    FirebaseDatabase database1;
    DatabaseReference datarefer;
    String news2,date,notice;
    SearchView searchView;
    NoticeUpdate news1=new NoticeUpdate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ieee_listview);

        mylistview=(ListView)findViewById(R.id.list3);

        searchView = (SearchView) findViewById(R.id.searchbar);

        final ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this,R.layout.activity_ieee_update,R.id.textView20,myarraylist);

        database1= FirebaseDatabase.getInstance();
        datarefer= database1.getReference("IEEE News");
        datarefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    news1=ds.getValue(NoticeUpdate.class);
                    news2=news1.getNews().toString();
                    date=news1.getDate() + "\n\n ";
                    notice = date + news2;

                    myarraylist.add(notice);
                }
                mylistview.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myadapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(!searchView.isIconified()) {
            searchView.setIconified(true);
        }
        else {
            super.onBackPressed();
        }
    }
}


