package com.example.soohyun.capston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sooHyun on 2017-05-22.
 */


public class gpsCalculateActivity extends Activity {

    private FirebaseDatabase mFirebseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ArrayAdapter<String> mAdapter;
    GPSTracker gps = new GPSTracker();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpscalculate);
        gps.startGPS(getApplicationContext(), gpsCalculateActivity.this);


        ImageButton imagebtn = (ImageButton) findViewById(R.id.completebtn);
        imagebtn.setOnClickListener(new ProgressBar.OnClickListener(){
            @Override

            public void onClick(View view){
                Intent intent_act = new Intent(gpsCalculateActivity.this, dashboardActivity.class);
                Double totalDistance = gps.getDistance();
               intent_act.putExtra("totalDistance", totalDistance);
                startActivity(intent_act);

            }
        });






    }

    private void initGPS(gpsCalculation gps) {
        if(gps == null){}
    }

}
