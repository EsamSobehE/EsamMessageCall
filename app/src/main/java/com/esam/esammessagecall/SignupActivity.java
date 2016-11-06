package com.esam.esammessagecall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity
{
    private EditText etEmail;
    private EditText etFullNAme;
    private EditText etPassw1;
    private EditText etRePassw2;
    private Button btnSignUp;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etFullNAme=(EditText)findViewById(R.id.etFulNAme);
        etPassw1=(EditText)findViewById(R.id.etPassw1);
        etRePassw2=(EditText)findViewById(R.id.etRePassw2);
        btnSignUp=(Button)findViewById(R.id.btnSignIn);
        eventHandler();
        auth= FirebaseAuth.getInstance();

    }

    private void dataHandler()
    {
        String stEmail = etEmail.getText().toString();
        boolean isok=true;
        if (stEmail.length()==0) {
            etEmail.setError("Worng Email");
            isok=false;
        }
        String stFullName=etFullNAme.getText().toString();
        if (stFullName.length()==0) {
            etFullNAme.setError("Wrong FullName");
            isok = false;
        }
        String stPassw1=etPassw1.getText().toString();
        if (stPassw1.length()==0) {
            etPassw1.setError("Wrong Passw1");
            isok = false;
        }
        String stRePassw2=etRePassw2.getText().toString();
        if (stRePassw2.length()==0) {
            etRePassw2.setError("Wrong RePasww2");
            isok = false;
        }
        if(isok)
         creatAcount(stEmail,stPassw1);


    }

    private void eventHandler()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // Intent i=new Intent(SignupActivity.this,TaskListActivity.class);
               // startActivity(i);
                dataHandler();

            }
        });
    }

    // (יצירת ואתחול המאזין מידית עם הגדרת התכונה )
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if(user!=null)
            {
                //user is signed in
                Toast.makeText(SignupActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            }
            else
            {
                //user signed out
                Toast.makeText(SignupActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null)
            auth.removeAuthStateListener(authStateListener);
    }



    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignupActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignupActivity.this,TaskListActivity.class);
                    startActivity(i);
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }






}
