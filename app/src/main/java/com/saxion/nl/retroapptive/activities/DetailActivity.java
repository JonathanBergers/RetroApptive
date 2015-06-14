package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.saxion.nl.retroapptive.BaseActivity;
import com.saxion.nl.retroapptive.MainActivity;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserPicture;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by falco on 31-5-15.
 */
public class DetailActivity extends BaseActivity {

    public static final int NOTES_LIST = 0;
    public static final int USERSTORIES_LIST = 1;
    public static final int ACTIONS_LIST = 2;
    private static final int SELECT_SINGLE_PICTURE = 4;
    public static final String IMAGE_TYPE = "image/*";

    private ImageView selectedImagePreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Model model = Model.getInstance();


        if (getIntent().getIntExtra("list", 0) == NOTES_LIST) {

            setUpNote();


        } else if (getIntent().getIntExtra("list", 0) == USERSTORIES_LIST) {

            setUpUserStory();

        } else {

            // Action

        }
//setting de plusbutton

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
        Button delete = (Button) findViewById(R.id.button_DetailsDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<Notitie> notes = NotesListViewFragment.instance.getNotes();
                final Notitie note = notes.get(getIntent().getIntExtra("position", 0));
                int item = getIntent().getIntExtra("list", 0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Model.getInstance().deleteNote(note);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        MainActivity.instance.loadNotes(MainActivity.currentSprint);
                    }
                }).start();
               /* if(item == 0){
                    Model.getInstance().getNotes().remove(getIntent().getIntExtra("position", 0));
                } else if(item == 1){
                    Model.getInstance().getUserStories().remove(getIntent().getIntExtra("position", 0));
                } else {
                    Model.getInstance().getActions().remove(getIntent().getIntExtra("position", 0));
                }*/

                setResult(RESULT_OK);
                finish();
            }
        });




    }


    private void setUpNote() {

        getLayoutInflater().inflate(R.layout.activity_detail_note, drawer);

        TextView titel = (TextView) findViewById(R.id.textView_noteDetailsTitel);
        TextView project = (TextView) findViewById(R.id.textView_noteDetailsProject);
        TextView sprint = (TextView) findViewById(R.id.textView_noteDetailsSprint);
        TextView summary = (TextView) findViewById(R.id.textView_noteDetailsSummary);
        TextView category = (TextView) findViewById(R.id.textView_noteDetailsCategory);

        final List<Notitie> notes = NotesListViewFragment.instance.getNotes();


        titel.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getDescription());
        project.setText("Project: " + "niks");
        sprint.setText("Sprint: " + notes.get(getIntent().getIntExtra("position", 0)).getSprint().getSprintID());
        summary.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getSummary());
        category.setText("" + notes.get(getIntent().getIntExtra("position", 0)).getSubcategory());
        selectedImagePreview = (ImageView)findViewById(R.id.image_preview);

        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.fab);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, SELECT_SINGLE_PICTURE);

            }
        });

    }

    private void setUpUserStory() {

        setContentView(R.layout.activity_detail_user_story);
        TextView titel = (TextView) findViewById(R.id.textView_userStoryDetailsTitel);
        TextView project = (TextView) findViewById(R.id.textView_userStoryDetailsProject);
        TextView sprint = (TextView) findViewById(R.id.textView_userStoryDetailsSprint);
        TextView summary = (TextView) findViewById(R.id.textView_userStoryDetailsSummary);
        TextView points = (TextView) findViewById(R.id.textView_userStoryDetailsPoints);
        CheckBox isburned = (CheckBox) findViewById(R.id.checkBox_userStoryDetailsBurned);



            /*titel.setText(""+model.getUserStory(getIntent().getIntExtra("position", 0)).getDescription());
            project.setText("Project: "+ "niks");
            sprint.setText(""+model.getUserStory(getIntent().getIntExtra("position", 0)).getSprint().getSprintID());
            summary.setText("" + model.getUserStory(getIntent().getIntExtra("position", 0)).getSummary());
            points.setText("Points: "+ model.getUserStory(getIntent().getIntExtra("position", 0)).getPoints());

            isburned.setChecked(model.getUserStory(getIntent().getIntExtra("position", 0)).isBurned());
            */

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 101 && resultCode == RESULT_OK) {


            setResult(RESULT_OK);
            this.finish();

        }

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {

                Uri selectedImageUri = data.getData();
                try {

                    Bitmap yourBitmap =new UserPicture(selectedImageUri, getContentResolver()).getBitmap();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    final byte[] bArray = bos.toByteArray();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                final List<Notitie> notes = NotesListViewFragment.instance.getNotes();

                                Model.getInstance().addAttachment(notes.get(getIntent().getIntExtra("position", 0)), bArray);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

                    selectedImagePreview.setImageBitmap(yourBitmap);

                } catch (IOException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                }
                // original code
//                String selectedImagePath = getPath(selectedImageUri);
//                selectedImagePreview.setImageURI(selectedImageUri);
            }
        } else {
            // report failure
            Toast.makeText(getApplicationContext(), "failed to get intent data", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {

        // just some safety built in
        if (uri == null) {
            // perform some logging or show user feedback
            Toast.makeText(getApplicationContext(), "failed to get picture", Toast.LENGTH_LONG).show();
            Log.d(MainActivity.class.getSimpleName(), "Failed to parse image path from image URI " + uri);
            return null;
        }

        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here, thanks to the answer from @mad indicating this is needed for
        // working code based on images selected using other file managers
        return uri.getPath();
    }


//    /**
//     * helper to scale down image before display to prevent render errors:
//     * "Bitmap too large to be uploaded into a texture"
//     */
//    private void displayPicture(String imagePath, ImageView imageView) {
//
//        // from http://stackoverflow.com/questions/22633638/prevent-bitmap-too-large-to-be-uploaded-into-a-texture-android
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 4;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//        int height = bitmap.getHeight(), width = bitmap.getWidth();
//
//        if (height > 1280 && width > 960){
//            Bitmap imgbitmap = BitmapFactory.decodeFile(imagePath, options);
//            imageView.setImageBitmap(imgbitmap);
//        } else {
//            imageView.setImageBitmap(bitmap);
//        }
//    }




}
