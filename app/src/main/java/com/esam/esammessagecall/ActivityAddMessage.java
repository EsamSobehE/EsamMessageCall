package com.esam.esammessagecall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esam.esammessagecall.Date.MyTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ActivityAddMessage extends AppCompatActivity {
    private EditText etContacts;
    private EditText etPhoneNumber;
    private EditText etMessage;
    private EditText etDate;
    private EditText etTime;
    private Button btnSaveMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        etContacts= (EditText)findViewById(R.id.etContacts);
        etPhoneNumber= (EditText)findViewById(R.id.etPhoneNumber);
        etDate= (EditText)findViewById(R.id.etDate);
        etTime= (EditText)findViewById(R.id.etTime);
        btnSaveMessage= (Button)findViewById(R.id.btnSaveMessage);
    }
    private void dataHandler(){
        String stContacts=etContacts.getText().toString();
        if (stContacts.length()<3);
        etContacts.setError("Worng Contacts");
        String stPhoneNumber= etPhoneNumber.getText().toString();
        if (stPhoneNumber.length()<3);
        etPhoneNumber.setError("Worng PhoneNumber");
        String stMessage= etMessage.getText().toString();
        if (stMessage.length()<3);
        etMessage.setError("Wrong Message");
        String stDate= etDate.getText().toString();
        if (stDate.length()<3);
        etDate.setError("Worng Date");
        String stTime= etTime.getText().toString();
        if (stTime.length()<3);
        etTime.setError("Worng Time");
    }

    private void eventHandler(){
        btnSaveMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Date date = Calendar.getInstance().getTime();
                MyTask t = new MyTask();




                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                email = email.replace(".", "_");
                reference.child(email).child("MyTasks").push().setValue(t, new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        if (databaseError == null) {

                            Toast.makeText(getBaseContext(), "save ok", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ActivityAddMessage.this, TaskListActivity.class);
                            startActivity(i);

                        } else {

                            Toast.makeText(getBaseContext(), "save Err" + databaseError.getMessage(), Toast.LENGTH_LONG).show();

                            databaseError.toException().printStackTrace();
                        }
                    }
                });

            }
        });
    }


}
