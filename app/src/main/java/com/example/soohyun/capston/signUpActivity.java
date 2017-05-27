package com.example.soohyun.capston;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by sooHyun on 2017-05-26.
 */

public class signUpActivity extends Activity {
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth mAuth;
    Context mContext;
    private Button signupBtn;
    private Button cancelBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        //쓸 변수 전부 초기화
       emailText = (EditText) findViewById(R.id.signupID);
        passwordText = (EditText) findViewById(R.id.signupPassword);
        mContext = this;
        mAuth = FirebaseAuth.getInstance();
       signupBtn = (Button) findViewById(R.id.signupBtn);
        cancelBtn = (Button) findViewById(R.id.signupCancel);

        //가입할 때 눌렀을 때 동작 등록.
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(emailText.getText().toString(), passwordText.getText().toString());
            }
        });

        //취소 버튼 누르면 그냥 종료하는 리스너 등록.
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    void registerUser(String email, String password){
        if(checkValid(email, password) == 0){
            Toast.makeText(mContext, "It is not valid so return",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            Toast.makeText(mContext, "email : " + email+"password : "+password,
                    Toast.LENGTH_SHORT).show();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(mContext, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(mContext, "Sign up is success.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signUpActivity.this, loginActivity.class));

                                finish();
                            }

                            // [START_EXCLUDE]

                            // [END_EXCLUDE]
                        }
                    });
            // [END create_user_with_email]
        }
    }

    private int checkValid(String id, String password) {
        if(!isValidID(id)){
            Log.e(TAG, "createAccount: email is not valid ");
            Toast.makeText(signUpActivity.this, "Email is not valid",
                    Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (!isValidPassword(password)){
            Log.e(TAG, "createAccount: password is not valid ");
            Toast.makeText(mContext, "Password is not valid",
                    Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }

    //아이디는 이메일 양식에 맞게
    private boolean isValidID(String target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    //6자리 이상, 한글 미포함, 숫자, 영어 포함
    private boolean isValidPassword(String target) {
        Pattern p = Pattern.compile("(^.*(?=.{6,100})(?=.*[0-9])(?=.*[a-zA-Z]).*$)");

        Matcher m = p.matcher(target);
        if (m.find() && !target.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")){
            return true;
        }else{
            return false;
        }
    }

}
