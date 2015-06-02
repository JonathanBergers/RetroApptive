package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;

/**
 * Created by falco on 31-5-15.
 */
public class DetailActivity extends Activity {

    public static final int NOTES_LIST = 0;
    public static final int USERSTORIES_LIST = 1;
    public static final int ACTIONS_LIST = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Model model = Model.getInstance();



        if(getIntent().getIntExtra("list", 0)== NOTES_LIST){
            setContentView(R.layout.activity_detail_note);

            TextView titel = (TextView) findViewById(R.id.textView_noteDetailsTitel);
            TextView project = (TextView) findViewById(R.id.textView_noteDetailsProject);
            TextView sprint = (TextView) findViewById(R.id.textView_noteDetailsSprint);
            TextView summary = (TextView) findViewById(R.id.textView_noteDetailsSummary);
            TextView category = (TextView) findViewById(R.id.textView_noteDetailsCategory);



            titel.setText(""+model.getNote(getIntent().getIntExtra("position", 0)).getDescription());
            project.setText("Project: " + "niks");
            sprint.setText("Sprint: "+model.getNote(getIntent().getIntExtra("position", 0)).getSprintNumber());
            summary.setText(""+ model.getNote(getIntent().getIntExtra("position", 0)).getSummary());
            category.setText(""+model.getNote(getIntent().getIntExtra("position", 0)).getCategory());


        } else if(getIntent().getIntExtra("list", 0)== USERSTORIES_LIST){
            setContentView(R.layout.activity_detail_user_story);
            TextView titel = (TextView) findViewById(R.id.textView_userStoryDetailsTitel);
            TextView project = (TextView) findViewById(R.id.textView_userStoryDetailsProject);
            TextView sprint = (TextView) findViewById(R.id.textView_userStoryDetailsSprint);
            TextView summary = (TextView) findViewById(R.id.textView_userStoryDetailsSummary);
            TextView points = (TextView) findViewById(R.id.textView_userStoryDetailsPoints);
            CheckBox isburned = (CheckBox) findViewById(R.id.checkBox_userStoryDetailsBurned);



            titel.setText(""+model.getUserStory(getIntent().getIntExtra("position", 0)).getDescription());
            project.setText("Project: "+ "niks");
            sprint.setText(""+model.getUserStory(getIntent().getIntExtra("position", 0)).getSprintNumber());
            summary.setText("" + model.getUserStory(getIntent().getIntExtra("position", 0)).getSummary());
            points.setText("Points: "+ model.getUserStory(getIntent().getIntExtra("position", 0)).getPoints());

            isburned.setChecked(model.getUserStory(getIntent().getIntExtra("position", 0)).isBurned());






        } else {

        }

        Button edit = (Button) findViewById(R.id.button_DetailsEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, ObjectActivity.class);
                i.putExtra("item", (getIntent().getIntExtra("list", 0)));
                i.putExtra("edit", true);
                i.putExtra("position", getIntent().getIntExtra("position", 0));
                startActivityForResult(i, 101);
            }
        });






    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 101 && resultCode == RESULT_OK){

            setResult(RESULT_OK);
           this.finish();

        }


    }
}
