package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saxion.nl.retroapptive.MainActivity;
import com.saxion.nl.retroapptive.R;

/**
 * Created by Jelle on 1-6-2015.
 */
public class ObjectActivity extends Activity {

	private EditText titleEditText, decriptionEditText, categoryEditText;
	private Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_new_object);

		save = (Button) findViewById(R.id.buttonSave);

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//getting de values
				String title, description, category;

				title = "" + titleEditText.getText();
				description = "" + decriptionEditText.getText();
				category = "" + categoryEditText.getText();

				//new object van user story of note
				//new Object(title, description,category);
				//waardes title description, catergory

				//terug gaan naar de main view
				Intent i = new Intent(ObjectActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
	}
}
