package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

import java.util.List;

/**
 * Created by falco on 31-5-15.
 */
public class DetailActivity extends Activity {

	public static final int NOTES_LIST = -1;
	public static final int USERSTORIES_LIST = -2;
	public static final int ACTIONS_LIST = -3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Model model = Model.getInstance();
		final List<Notitie> notes = model.getNotes();
		final List<UserStory> userStories = model.getUserStories();
		if (getIntent().getIntExtra("list", -3) == NOTES_LIST) {
			setContentView(R.layout.activity_detail_note);

			TextView titel = (TextView) findViewById(R.id.textView_noteDetailsTitel);
			TextView project = (TextView) findViewById(R.id.textView_noteDetailsProject);
			TextView sprint = (TextView) findViewById(R.id.textView_noteDetailsSprint);
			TextView summary = (TextView) findViewById(R.id.textView_noteDetailsSummary);
			TextView category = (TextView) findViewById(R.id.textView_noteDetailsCategory);

			Button edit = (Button) findViewById(R.id.button_noteDetailsEdit);

			titel.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getDescription());
			project.setText("Project: " + "niks");
			sprint.setText("Sprint: " + notes.get(getIntent().getIntExtra("position", 0)).getSprintNumber());
			summary.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getSummary());
			category.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getCategory());
		} else if (getIntent().getIntExtra("list", -3) == USERSTORIES_LIST) {
			setContentView(R.layout.activity_detail_user_story);
			TextView titel = (TextView) findViewById(R.id.textView_userStoryDetailsTitel);
			TextView project = (TextView) findViewById(R.id.textView_userStoryDetailsProject);
			TextView sprint = (TextView) findViewById(R.id.textView_userStoryDetailsSprint);
			TextView summary = (TextView) findViewById(R.id.textView_userStoryDetailsSummary);
			CheckBox isburned = (CheckBox) findViewById(R.id.checkBox_userStoryDetailsBurned);

			Button edit = (Button) findViewById(R.id.button_userStoryDetailsEdit);

			titel.setText("" + userStories.get(getIntent().getIntExtra("position", 0)).getDescription());
			project.setText("Project: " + "niks");
			sprint.setText("" + userStories.get(getIntent().getIntExtra("position", 0)).getSprintNumber());
			summary.setText("" + userStories.get(getIntent().getIntExtra("position", 0)).getSummary());

			isburned.setChecked(userStories.get(getIntent().getIntExtra("position", 0)).isBurned());
		}
	}
}
