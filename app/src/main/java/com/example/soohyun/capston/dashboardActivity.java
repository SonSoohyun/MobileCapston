package com.example.soohyun.capston;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.Iterator;

/**
 * Created by sooHyun on 2017-05-20.
 */

public class dashboardActivity extends Activity {
    private DatabaseReference mDatabase;
    private String email;
    ValueEventListener checkRegister = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
            while(child.hasNext()){
                if(email.equals(child.next().getKey())){
                    Toast.makeText(getApplicationContext(), "존재하는 아이디 입니다.", Toast.LENGTH_LONG).show();
                    mDatabase.removeEventListener(this);
                    return;
                }
            }
            mDatabase.child("email").setValue(email);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        Intent intent = getIntent();
       email = intent.getStringExtra("email");

        TextView showemail = (TextView)findViewById(R.id.showEmail);
        showemail.setText(email);
        mDatabase.addListenerForSingleValueEvent(checkRegister);


        ImageButton historyBtn = (ImageButton) findViewById(R.id.histroyBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_act = new Intent(dashboardActivity.this, historyActivity.class);
                startActivity(intent_act);
            }
        });

        ImageButton peopleBtn = (ImageButton) findViewById(R.id.peopleBtn);
        peopleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_act = new Intent(dashboardActivity.this, peopleActivity.class);
                startActivity(intent_act);
            }
        });


        ImageButton rankingBtn = (ImageButton) findViewById(R.id.rankingBtn);
        rankingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_act = new Intent(dashboardActivity.this, rankingActivity.class);
                startActivity(intent_act);
            }
        });

        ProgressBar proBar = (ProgressBar)findViewById(R.id.progressBar);
        proBar.setOnClickListener(new ProgressBar.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent_act = new Intent(dashboardActivity.this, gpsCalculateActivity.class);
                startActivity(intent_act);
            }
        });
    }

}
