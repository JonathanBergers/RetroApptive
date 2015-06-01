package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.ROClient;






/* Author - Dimuthu Upeksha*/

public class LogInActivity extends Activity {

    private AutoCompleteTextView username;
    private EditText password;
    private Button loginButton;
    private ProgressBar loginProgressBar;
    private TextView loggingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        View mainView = findViewById(R.id.buttonLogin);
        View root = mainView.getRootView();
        root.setBackgroundColor(Color.BLUE);

        loginButton= (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(loginListener);
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {
            username = (AutoCompleteTextView) findViewById(R.id.textViewUsername);
            password = (EditText) findViewById(R.id.editTextPassWordh);

            loginProgressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
            loggingTextView = (TextView) findViewById(R.id.textViewLogging);

            //Set Visiblity
            username.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);

            loginProgressBar.setVisibility(View.VISIBLE);
            loggingTextView.setVisibility(View.VISIBLE);

            //ROClient.getInstance().setCredential(username.getText().toString(), password.getText().toString());

            //Intent intent = new Intent(LogInActivity.this, CommunicationActivity.class);
           // startActivity(intent);
        }
    };

}
