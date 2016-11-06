package com.esam.esammessagecall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddCall extends AppCompatActivity {
    private EditText etContacts;
    private EditText etPhoneNumber;
    private EditText etDate;
    private EditText etTime;
    private Button btnSaveCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_call);
        etContacts= (EditText)findViewById(R.id.etContacts);
        etPhoneNumber= (EditText)findViewById(R.id.etPhoneNumber);
        etDate= (EditText)findViewById(R.id.etDate);
        etTime= (EditText)findViewById(R.id.etTime);
        btnSaveCall= (Button)findViewById(R.id.btnSaveCall);
    }
    private void dataHandler(){
        String stContacts=etContacts.getText().toString();
        if (stContacts.length()<3);
        etContacts.setError("Worng Contacts");
        String stPhoneNumber= etPhoneNumber.getText().toString();
        if (stPhoneNumber.length()<3);
        etPhoneNumber.setError("Worng PhoneNumber");
        String stDate= etDate.getText().toString();
        if (stDate.length()<3);
        etDate.setError("Worng Date");
        String stTime= etTime.getText().toString();
        if (stTime.length()<3);
        etTime.setError("Worng Time");

    }
    private void eventHandler(){
        btnSaveCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
