package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Action;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

/**
 * Created by Jelle on 1-6-2015.
 */
public class ObjectActivity extends Activity {

	private EditText titleEditText, summaryEditText, categoryEditText;
	private Button save, cancel;
	private CheckBox isPositive;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_object);

		titleEditText = (EditText) findViewById(R.id.editTextNewObjectTitle);
		summaryEditText = (EditText) findViewById(R.id.editTextNewObjectSummary);
		categoryEditText = (EditText) findViewById(R.id.editTextNewObjectCategory);


		save = (Button) findViewById(R.id.buttonNewObjectSave);
		cancel = (Button) findViewById(R.id.buttonNewObjectCancel);

		isPositive = (CheckBox) findViewById(R.id.checkBoxNewObject);



		//setting de header to a new making a new item
		final int item = getIntent().getIntExtra("item", 0);
		if(item == 0){
			setTitle("Making a new note:");


		} else if(item == 1){
			setTitle("Making a new UserStory:");
			categoryEditText.setHint("Points");
			categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
			isPositive.setVisibility(View.INVISIBLE);


		} else {
			setTitle("Making a new Action:");
			categoryEditText.setHint("Priority");
			categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
			isPositive.setVisibility(View.INVISIBLE);

		}










		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//getting de values
				String title, summary, category;
				int p;
				boolean ispositive;

				title = titleEditText.getText().toString();
				summary = summaryEditText.getText().toString();


				if(item == 0){
					category = categoryEditText.getText().toString();
					ispositive = isPositive.isChecked();

					Notitie n = new Notitie(new Item(title, summary, 0 ));
					n.setCategory(category);
					n.setIsPositive(ispositive);
					Model.getInstance().addNote(n);



				} else if(item == 1) {
					p = Integer.parseInt(categoryEditText.getText().toString());
					UserStory u =  new UserStory(new Item(title, summary, 0));
					u.setPoints(p);
					Model.getInstance().addUserStory(u);

				} else {
					p = Integer.parseInt(categoryEditText.getText().toString());
					Action a = new Action(new Item(title, summary, 0));
					a.setPriority(p);
					Model.getInstance().addAction(a);


				}


				//new object van user story of note
				//new Object(title, description,category);
				//waardes title description, catergory


				//terug gaan naar de main view
				getIntent().addFlags(RESULT_OK);
				setResult(100, getIntent());
				finish();


				//Intent i = new Intent(ObjectActivity.this, MainActivity.class);
				//startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
