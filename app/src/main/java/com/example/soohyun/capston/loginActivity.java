package com.example.soohyun.capston;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText input_email, input_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        //사용자 계정이 이미 있다면(로그인 없이 바로 대시보드로 넘기자
        if(mAuth.getCurrentUser() != null){

        }
        input_email = (EditText)findViewById(R.id.input_id);
        input_pwd = (EditText)findViewById(R.id.input_password);

        TextView signUpTextview = (TextView)findViewById(R.id.signuptextview);
        signUpTextview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent_signup = new Intent(loginActivity.this, signUpActivity.class );
                startActivity(intent_signup);

            }
        });

        ImageButton loginBtn = (ImageButton) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                final String password = input_pwd.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "Login failed",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent intent = new Intent(loginActivity.this, dashboardActivity.class);
                            intent.putExtra("email", input_email.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    }

                });
            }
        });
    }

}
