package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.ROClient;






/* Author - Dimuthu Upeksha*/

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(loginListener);
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText username = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.password);
            ROClient.getInstance().setCredential(username.getText().toString(), password.getText().toString());

            //Intent intent = new Intent(LogInActivity.this, CommunicationActivity.class);
           // startActivity(intent);
        }
    };

}
