package com.esam.esammessagecall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

public class TaskListActivity extends AppCompatActivity {
    private EditText etText;
    private Button btnAddCall;
    private ListView etListview;
    private EditText editText;
    private Button btnAddMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        btnAddCall = (Button) findViewById(R.id.btnAddCall);
        etListview = (ListView) findViewById(R.id.etListview);
         btnAddMessage = (Button) findViewById(R.id.btnAddMessage);
        eventHandler();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }




    private void dataHandler() {
        //1.getting data
        String stText = etText.getText().toString();


        //2.checing
        if (stText.length() == 0)
            etText.setError("Wrong Email");

    }

    private void eventHandler() {
        btnAddCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaskListActivity.this,ActivityAddCall.class);
                startActivity(i);

            }
        });
        btnAddMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(TaskListActivity.this,ActivityAddMessage.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmLogOut:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(TaskListActivity.this, LoginActivity.class);
                startActivity(i);

                break;
        }
        return true;

    }



}

