package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.login.LoginCredentials;
import com.saxion.nl.retroapptive.model.Model;

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
        //root.setBackgroundColor(Color.BLUE);

        loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(loginListener);
    }

    private View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {
            username = (AutoCompleteTextView) findViewById(R.id.textViewUsername);
            password = (EditText) findViewById(R.id.editTextPassWordh);

            //ROClient.getInstance().setCredential(username.getText().toString(), password.getText().toString());

            loginProgressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
            loggingTextView = (TextView) findViewById(R.id.textViewLogging);

            //Set Visiblity
            username.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);

            loginProgressBar.setVisibility(View.VISIBLE);
            loggingTextView.setVisibility(View.VISIBLE);

            final LoginCredentials loginCredentials = new LoginCredentials(username.getText().toString(), password.getText().toString());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int loginCode = Model.getInstance().login(loginCredentials);

                    runOnUiThread(new Thread(
                            new Runnable() {
                                @Override
                                public void run() {
//                                    username.setVisibility(View.VISIBLE);
//                                    password.setVisibility(View.VISIBLE);
//                                    loginButton.setVisibility(View.VISIBLE);
//
//                                    loginProgressBar.setVisibility(View.GONE);
//                                    loggingTextView.setVisibility(View.GONE);
                                }
                            }
                    ));

                    if (loginCode == 200) {
                        //ROClient.getInstance().setCredential(username.getText().toString(), password.getText().toString());

                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {

                        runOnUiThread(new Thread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(LogInActivity.this, "Incorrect login, error code: " + loginCode, Toast.LENGTH_LONG).show();
                                            }
                                        }
                                )
                        );
                    }
                }
            }).start();
        }
    };

}
