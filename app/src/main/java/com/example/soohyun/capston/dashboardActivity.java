package com.example.soohyun.capston;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;

/**
 * Created by sooHyun on 2017-05-20.
 */

public class dashboardActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
