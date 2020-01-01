package com.example.pictinsights;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database1;
    DatabaseReference datarefer;

    AppCompatButton acmButton;
    AppCompatButton ieeeButton;
    AppCompatButton edcButton1;
    AppCompatButton artbutton1;
    AppCompatButton roboButton1;

    TextView username;
    String email2,email3,name;

    StudentDetails st1 = new StudentDetails();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        acmButton = (AppCompatButton) findViewById(R.id.acmButton);
        ieeeButton = (AppCompatButton) findViewById(R.id.ieeeButton);
        edcButton1= (AppCompatButton) findViewById(R.id.edcButton);
        artbutton1= (AppCompatButton) findViewById(R.id.artButton);
        roboButton1=(AppCompatButton) findViewById(R.id.roboconButton);

        acmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,AcmActivity.class));
            }
        });

        ieeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,IeeeActivity.class));
            }
        });

        edcButton1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                startActivity(new Intent ( MainActivity.this,Edcmain.class));
            }
        });

        artbutton1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArtCircleActivity.class));

            }
        });

        roboButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RoboconActivity.class));
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView=navigationView.getHeaderView(0);
        username = (TextView)headerView.findViewById(R.id.nav_header_name);
        email2 = getIntent().getExtras().getString("value1");

        database1= FirebaseDatabase.getInstance();
        datarefer= database1.getReference("Users");

        datarefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    st1=ds.getValue(StudentDetails.class);
                    email3=st1.getStudentEmail().toString();
                    name=st1.getStudentName().toString();
                    if(email2.equalsIgnoreCase(email3))
                    {
                        username.append(name.toString() +"!");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FirebaseAuth fire1;
        fire1=FirebaseAuth.getInstance();
        int id = item.getItemId();

        if (id == R.id.nav_gallery)
        {
            startActivity(new Intent(MainActivity.this,GalleryActivity.class));
            Toast.makeText(MainActivity.this,"Gallery",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_out)
        {
            fire1.signOut();

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(MainActivity.this,"User is Logged Out",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_rate) {
            startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}