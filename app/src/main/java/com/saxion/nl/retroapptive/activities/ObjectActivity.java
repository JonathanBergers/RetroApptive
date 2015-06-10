package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.saxion.nl.retroapptive.BaseActivity;
import com.saxion.nl.retroapptive.MainActivity;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Action;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jelle on 1-6-2015.
 */
public class ObjectActivity extends BaseActivity {

    private EditText titleEditText, summaryEditText, categoryEditText;
    private Button save, cancel, takePhoto;
    private CheckBox isPositive;
    private Uri imageUri;
    private ImageView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_new_object, drawer);

        titleEditText = (EditText) findViewById(R.id.editTextNewObjectTitle);
        summaryEditText = (EditText) findViewById(R.id.editTextNewObjectSummary);
        categoryEditText = (EditText) findViewById(R.id.editTextNewObjectCategory);


        save = (Button) findViewById(R.id.buttonNewObjectSave);
        cancel = (Button) findViewById(R.id.buttonNewObjectCancel);
        takePhoto = (Button) findViewById(R.id.buttonNewPhoto);

        isPositive = (CheckBox) findViewById(R.id.checkBoxNewObject);

        final int item = getIntent().getIntExtra("item", 0);


        if (getIntent().getBooleanExtra("edit", false)) {
            final int currentItemPos = getIntent().getIntExtra("position", 0);


            if (item == 0) {
                setTitle("Editing note:");

				/*titleEditText.setText(Model.getInstance().getNote(currentItemPos).getDescription());
                summaryEditText.setText(Model.getInstance().getNote(currentItemPos).getSummary());
				categoryEditText.setText(Model.getInstance().getNote(currentItemPos).getSubcategory());
				isPositive.setChecked(Model.getInstance().getNote(currentItemPos).isPositive());*/


            } else if (item == 1) {
                setTitle("Editing UserStory:");
                categoryEditText.setHint("Points");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setText("Burned");

				/*titleEditText.setText(Model.getInstance().getUserStory(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getUserStory(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getUserStory(currentItemPos).getPoints());
				isPositive.setChecked(Model.getInstance().getUserStory(currentItemPos).isBurned());*/


            } else {
                setTitle("Editing Action:");
                categoryEditText.setHint("Priority");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setVisibility(View.INVISIBLE);

				/*titleEditText.setText(Model.getInstance().getAction(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getAction(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getAction(currentItemPos).getPriority());*/


            }

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title, summary, category;
                    int p;
                    boolean ispositive;

                    title = titleEditText.getText().toString();
                    summary = summaryEditText.getText().toString();

                    if (item == 0) {
                        category = categoryEditText.getText().toString();
                        ispositive = isPositive.isChecked();


                        //Model.getInstance().getNote(currentItemPos).setDescription(title);
                        //Model.getInstance().getNote(currentItemPos).setSummary(summary);
                        //Model.getInstance().getNote(currentItemPos).setCategory(category);
                        //Model.getInstance().getNote(currentItemPos).setIsPositive(ispositive);


                    } else if (item == 1) {
                        p = Integer.parseInt(categoryEditText.getText().toString());
                        ispositive = isPositive.isChecked();

                        //Model.getInstance().getUserStory(currentItemPos).setDescription(title);
                        //Model.getInstance().getUserStory(currentItemPos).setSummary(summary);
                        //Model.getInstance().getUserStory(currentItemPos).setPoints(p);
                        //Model.getInstance().getUserStory(currentItemPos).setIsBurned(ispositive);


                    } else {
                        p = Integer.parseInt(categoryEditText.getText().toString());

                        //Model.getInstance().getAction(currentItemPos).setDescription(title);
                        //Model.getInstance().getAction(currentItemPos).setSummary(summary);
                        //Model.getInstance().getAction(currentItemPos).setPriority(p);


                    }

                    setResult(RESULT_OK, getIntent());
                    finish();


                }
            });


        } else {
            //setting data

            if (item == 0) {
                setTitle("Making a new note:");
            } else if (item == 1) {
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
                    final String title, summary, category;
                    int p;
                    final boolean ispositive;

                    title = titleEditText.getText().toString();
                    summary = summaryEditText.getText().toString();


                    if (item == 0) {
                        category = categoryEditText.getText().toString();
                        ispositive = isPositive.isChecked();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Model.getInstance().createNote(MainActivity.currentSprint, title, summary, ispositive, category);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                MainActivity.instance.loadNotes(MainActivity.currentSprint);
                            }
                        }).start();

                        //Notitie n = new Notitie(new Item(title, summary, 0 ));
                        //n.setCategory(category);
                        //n.setIsPositive(ispositive);
                        //Model.getInstance().addNote(n);


                    } else if (item == 1) {
                        p = Integer.parseInt(categoryEditText.getText().toString());
                        //UserStory u =  new UserStory(new Item(title, summary, 0));
                        //u.setPoints(p);
                        //Model.getInstance().addUserStory(u);

                    } else {
                        p = Integer.parseInt(categoryEditText.getText().toString());
                        //Action a = new Action(new Item(title, summary, 0));
                        //	a.setPriority(p);
                        //Model.getInstance().addAction(a);


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

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                String currentTime = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE)+","+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+"."+c.get(Calendar.SECOND)+".jpg";

                Intent photoIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), currentTime);
                Log.d("DEBUG",currentTime);
                imageUri = Uri.fromFile(photo);
                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(photoIntent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DEBUG", "Deze wordt aangeroepen");

        if(resultCode == Activity.RESULT_OK){
            ContentResolver cr = getContentResolver();
            Uri selectedImage = imageUri;
            cr.notifyChange(selectedImage,null);

            photoView = (ImageView)findViewById(R.id.imageViewPhoto);
            Log.d("DEBUG","Zo ver komt die ook");
            Bitmap bitmapPhoto;

            try{
                bitmapPhoto = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                Log.d("DEBUG","Zelfs hier komt die");
                photoView.setImageBitmap(bitmapPhoto);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
