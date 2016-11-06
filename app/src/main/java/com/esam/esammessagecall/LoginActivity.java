package com.esam.esammessagecall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnForgotMyPassword;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnForgotMyPassword=(Button)findViewById(R.id.btnForgetMyPassword);
        eventHandler();
        auth= FirebaseAuth.getInstance();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }



    /**
         * 1.getting data from the fprm (edyttext,checbox,radiobutton etc...)
         * 2.checking data (the email text is ok ,the password)
         * 3.dealing with the data
         */
    private void dataHandler()
    {
        boolean isok=true;
        //1.getting data
        String stEmail=etEmail.getText().toString();
        if (stEmail.length()==0){
            etEmail.setError("Wrong Email");
            isok=false;
        }
        String stPassword=etPassword.getText().toString();


        //2.checing
        if (stPassword.length()==0) {
            etPassword.setError("Wrong Email");
            isok=false;
        }
        if (isok){
            signIn(stEmail,stPassword);
        }

    }

    /**
     * putting event handliers (listeners)
     */
    private void eventHandler()
    {
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

              dataHandler();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);

            }
        });
        btnForgotMyPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }

    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent i=new Intent(LoginActivity.this,TaskListActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                     Intent intent=new Intent(LoginActivity.this,ActivityAddCall.class);
                       startActivity(intent);
                      finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if(user!=null)
            {
                //user is signed in
                Toast.makeText(LoginActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            }
            else
            {
                //user signed out
                Toast.makeText(LoginActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

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
}
