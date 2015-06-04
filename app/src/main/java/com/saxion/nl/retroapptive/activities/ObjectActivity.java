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

import com.saxion.nl.retroapptive.DrawerActivity;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Action;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

/**
 * Created by Jelle on 1-6-2015.
 */
public class ObjectActivity extends DrawerActivity {

	private EditText titleEditText, summaryEditText, categoryEditText;
	private Button save, cancel;
	private CheckBox isPositive;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLayoutInflater().inflate(R.layout.activity_new_object, frameLayout);

		titleEditText = (EditText) findViewById(R.id.editTextNewObjectTitle);
		summaryEditText = (EditText) findViewById(R.id.editTextNewObjectSummary);
		categoryEditText = (EditText) findViewById(R.id.editTextNewObjectCategory);


		save = (Button) findViewById(R.id.buttonNewObjectSave);
		cancel = (Button) findViewById(R.id.buttonNewObjectCancel);

		isPositive = (CheckBox) findViewById(R.id.checkBoxNewObject);

		final int item = getIntent().getIntExtra("item", 0);


		if(getIntent().getBooleanExtra("edit", false)){
			final int currentItemPos = getIntent().getIntExtra("position", 0);



			if(item == 0){
				setTitle("Editing note:");

				titleEditText.setText(Model.getInstance().getNote(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getNote(currentItemPos).getSummary());
				categoryEditText.setText(Model.getInstance().getNote(currentItemPos).getCategory());
				isPositive.setChecked(Model.getInstance().getNote(currentItemPos).isPositive());


			} else if(item == 1){
				setTitle("Editing UserStory:");
				categoryEditText.setHint("Points");
				categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
				isPositive.setText("Burned");

				titleEditText.setText(Model.getInstance().getUserStory(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getUserStory(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getUserStory(currentItemPos).getPoints());
				isPositive.setChecked(Model.getInstance().getUserStory(currentItemPos).isBurned());





			} else {
				setTitle("Editing Action:");
				categoryEditText.setHint("Priority");
				categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
				isPositive.setVisibility(View.INVISIBLE);

				titleEditText.setText(Model.getInstance().getAction(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getAction(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getAction(currentItemPos).getPriority());



			}

			save.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					String title, summary, category;
					int p;
					boolean ispositive;

					title = titleEditText.getText().toString();
					summary = summaryEditText.getText().toString();

					if(item == 0){
						category = categoryEditText.getText().toString();
						ispositive = isPositive.isChecked();


						Model.getInstance().getNote(currentItemPos).setDescription(title);
						Model.getInstance().getNote(currentItemPos).setSummary(summary);
						Model.getInstance().getNote(currentItemPos).setCategory(category);
						Model.getInstance().getNote(currentItemPos).setIsPositive(ispositive);



					} else if(item == 1) {
						p = Integer.parseInt(categoryEditText.getText().toString());
						ispositive = isPositive.isChecked();

						Model.getInstance().getUserStory(currentItemPos).setDescription(title);
						Model.getInstance().getUserStory(currentItemPos).setSummary(summary);
						Model.getInstance().getUserStory(currentItemPos).setPoints(p);
						Model.getInstance().getUserStory(currentItemPos).setIsBurned(ispositive);


					} else {
						p = Integer.parseInt(categoryEditText.getText().toString());

						Model.getInstance().getAction(currentItemPos).setDescription(title);
						Model.getInstance().getAction(currentItemPos).setSummary(summary);
						Model.getInstance().getAction(currentItemPos).setPriority(p);




					}

					setResult(RESULT_OK, getIntent());
					finish();



				}
			});






		} else {
			//setting data

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

					setResult(RESULT_OK, getIntent());
					finish();


					//Intent i = new Intent(ObjectActivity.this, MainActivity.class);
					//startActivity(i);
				}
			});


		}

		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				setResult(RESULT_CANCELED, getIntent());
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}


}
