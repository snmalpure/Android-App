package com.example.pictinsights;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminUpdate extends AppCompatActivity {
    Button btnsave;
    EditText notice;
    DatabaseReference acmdb;
    DatabaseReference ieeedb;
    DatabaseReference artdb;
    DatabaseReference robocondb;
    DatabaseReference edcdb;
    Spinner spinnerevents;
    Button btndelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        acmdb = FirebaseDatabase.getInstance().getReference("ACM News");
        ieeedb = FirebaseDatabase.getInstance().getReference("IEEE News");
        artdb = FirebaseDatabase.getInstance().getReference("Art Circle News");
        robocondb = FirebaseDatabase.getInstance().getReference("Robocon News");
        edcdb = FirebaseDatabase.getInstance().getReference("EDC News");

        btndelete=(Button)findViewById(R.id.butDel);
        btnsave = (Button) findViewById(R.id.but_news);

        notice = (EditText) findViewById(R.id.editText);

        spinnerevents = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerevents.setAdapter(adapter);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update();
            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();
            }
        });
    }


    public void update() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy,hh:mm:ss a");
        String date1= simpleDateFormat.format(calendar.getTime());

        String news1 = notice.getText().toString().trim();
        final String cell = spinnerevents.getSelectedItem().toString();

        if(cell.equals("ACM"))
        {
            if (!TextUtils.isEmpty(news1)) {
                String id = acmdb.push().getKey();

                NoticeUpdate newsu = new NoticeUpdate(news1,id,date1);
                acmdb.child(id).setValue(newsu);

                startActivity(new Intent(AdminUpdate.this, AcmListview.class));

                NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notice")
                        .setContentText("ACM Notice")
                        .setAutoCancel(true);

                Intent i = new Intent(this, AcmListview.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(AcmListview.class);

                stackBuilder.addNextIntent(i);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelid = "1";
                    NotificationChannel notificationChannel = new NotificationChannel(channelid,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(channelid);
                }

                notificationManager.notify(0,builder.build());
                Toast.makeText(this, "Notice Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminUpdate.this,AcmListview.class));

            } else {
                Toast.makeText(this, "You should enter a notice", Toast.LENGTH_LONG).show();
            }
        }

        else if(cell.equals("IEEE"))
        {
            if (!TextUtils.isEmpty(news1)) {

                String id = ieeedb.push().getKey();
                NoticeUpdate newsu = new NoticeUpdate(news1, id,date1);
                ieeedb.child(id).setValue(newsu);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notice")
                        .setContentText("IEEE Notice")
                        .setAutoCancel(true);

                Intent i=new Intent(this,IeeeListview.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(IeeeListview.class);

                stackBuilder.addNextIntent(i);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelid = "1";
                    NotificationChannel notificationChannel = new NotificationChannel(channelid,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(channelid);
                }

                notificationManager.notify(0,builder.build());
                Toast.makeText(this, "Notice Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminUpdate.this,IeeeListview.class));

            } else {
                Toast.makeText(this, "You should enter a notice", Toast.LENGTH_LONG).show();
            }
        }

        else if(cell.equals("Art Circle"))
        {
            if (!TextUtils.isEmpty(news1)) {

                String id = artdb.push().getKey();
                NoticeUpdate newsu = new NoticeUpdate(news1, id,date1);
                artdb.child(id).setValue(newsu);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notice")
                        .setContentText("Art Circle Notice")
                        .setAutoCancel(true);

                Intent i=new Intent(this,ArtCircleListview.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(ArtCircleListview.class);

                stackBuilder.addNextIntent(i);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelid = "1";
                    NotificationChannel notificationChannel = new NotificationChannel(channelid,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(channelid);
                }

                notificationManager.notify(0,builder.build());
                Toast.makeText(this, "Notice updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminUpdate.this,ArtCircleListview.class));

            } else {
                Toast.makeText(this, "You should enter a notice", Toast.LENGTH_LONG).show();
            }
        }

        else if(cell.equals("Robocon"))
        {
            if (!TextUtils.isEmpty(news1)) {

                String id = robocondb.push().getKey();
                NoticeUpdate newsu = new NoticeUpdate(news1, id,date1);
                robocondb.child(id).setValue(newsu);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notice")
                        .setContentText("Robocon Notice")
                        .setAutoCancel(true);

                Intent i=new Intent(this,RoboconListview.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(RoboconListview.class);

                stackBuilder.addNextIntent(i);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelid = "1";
                    NotificationChannel notificationChannel = new NotificationChannel(channelid,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(channelid);
                }

                notificationManager.notify(0,builder.build());
                Toast.makeText(this, "Notice Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminUpdate.this,RoboconListview.class));

            } else {
                Toast.makeText(this, "You should enter a notice", Toast.LENGTH_LONG).show();
            }
        }

        else if(cell.equals("EDC"))
        {
            if (!TextUtils.isEmpty(news1)) {

                String id = edcdb.push().getKey();
                NoticeUpdate newsu = new NoticeUpdate(news1, id,date1);
                edcdb.child(id).setValue(newsu);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notice")
                        .setContentText("EDC Notice")
                        .setAutoCancel(true);

                Intent i=new Intent(this,EdcListview.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(EdcListview.class);

                stackBuilder.addNextIntent(i);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelid = "1";
                    NotificationChannel notificationChannel = new NotificationChannel(channelid,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(channelid);
                }

                notificationManager.notify(0,builder.build());
                Toast.makeText(this, "Notice Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminUpdate.this,EdcListview.class));

            } else {
                Toast.makeText(this, "You should enter a notice", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void delete()
    {
        final String cell = spinnerevents.getSelectedItem().toString();

        if(cell.equals("ACM"))
        {
            acmdb = FirebaseDatabase.getInstance().getReference("ACM News");
            acmdb.removeValue();
            Toast.makeText(this,"NOTICE DELETED",Toast.LENGTH_SHORT).show();

        }
        else if(cell.equals("Robocon"))
        {
            robocondb = FirebaseDatabase.getInstance().getReference("Robocon News");
            robocondb.removeValue();
            Toast.makeText(this,"NOTICE DELETED",Toast.LENGTH_SHORT).show();
        }
        else if(cell.equals("IEEE"))
        {
            ieeedb = FirebaseDatabase.getInstance().getReference("IEEE News");
            ieeedb.removeValue();
            Toast.makeText(this,"NOTICE DELETED",Toast.LENGTH_SHORT).show();
        }
        else if(cell.equals("EDC"))
        {

            edcdb = FirebaseDatabase.getInstance().getReference("EDC News");
            edcdb.removeValue();
            Toast.makeText(this,"NOTICE DELETED",Toast.LENGTH_SHORT).show();

        }
        else if(cell.equals("Art Circle"))
        {

            artdb = FirebaseDatabase.getInstance().getReference("Art Circle News");
            artdb.removeValue();
            Toast.makeText(this,"NOTICE DELETED",Toast.LENGTH_SHORT).show();

        }

    }
}


