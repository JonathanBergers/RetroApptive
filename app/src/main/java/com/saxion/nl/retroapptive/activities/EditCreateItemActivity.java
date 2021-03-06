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

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.model.Model;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Jelle on 1-6-2015.
 */
public class EditCreateItemActivity extends BaseActivity {

    private EditText titleEditText, summaryEditText, categoryEditText;
    private Button save, cancel, takePhoto;
    private CheckBox isPositive;
    private Uri imageUri;
    private ImageView photoView;



    private int itemType;


    private final static int NOTE = 0;
    private final static int USER_STORY = 1;
    private final static int ACTION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.item_details, drawer);




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

    private void createItem() {

        switch (itemType){
            case NOTE:
                break;
            case USER_STORY:
                break;
            case ACTION:
                break;
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


                if (itemType == 0) {
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


                } else if (itemType == 1) {
                    p = Integer.parseInt(categoryEditText.getText().toString());
                    //UserStory u =  new UserStory(new Item(title, summary, 0));
                    //u.setPoints(p);
                    //Model.getInstance().addUserStory(u);

                } else {
                    p = Integer.parseInt(categoryEditText.getText().toString());
                    //Actie a = new Actie(new Item(title, summary, 0));
                    //	a.setPriority(p);
                    //Model.getInstance().addAction(a);


                }


                //new object van user story of note
                //new Object(title, description,category);
                //waardes title description, catergory


                //terug gaan naar de main view

                setResult(RESULT_OK, getIntent());
                finish();


                //Intent i = new Intent(EditCreateItemActivity.this, MainActivity.class);
                //startActivity(i);
            }
        });

    }


    private void createUIEdit(){

        switch(itemType){
            case NOTE:
                setTitle("Pas notitie aan:");
//
//				titleEditText.setText(Model.getInstance().getNote(currentItemPos).getDescription());
//                summaryEditText.setText(Model.getInstance().getNote(currentItemPos).getSummary());
//				categoryEditText.setText(Model.getInstance().getNote(currentItemPos).getSubcategory());
//				isPositive.setChecked(Model.getInstance().getNote(currentItemPos).isPositive());
                break;
            case USER_STORY:
                setTitle("Pas user story aan");
                categoryEditText.setHint("Points");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setText("Burned");

				/*titleEditText.setText(Model.getInstance().getUserStory(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getUserStory(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getUserStory(currentItemPos).getPoints());
				isPositive.setChecked(Model.getInstance().getUserStory(currentItemPos).isBurned());*/
                break;
            case ACTION:
                setTitle("Pas actie aan");
                categoryEditText.setHint("Priority");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setVisibility(View.INVISIBLE);

				/*titleEditText.setText(Model.getInstance().getAction(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getAction(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getAction(currentItemPos).getPriority());*/
                break;
        }


    }


    private void createUICreate(){


        if (itemType == NOTE) {
            setTitle("Making a new note:");
            return;
        }


        if (itemType == USER_STORY) {
            setTitle("Making a new UserStory:");
            categoryEditText.setHint("Points");
            categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            isPositive.setVisibility(View.INVISIBLE);
            return;

        }

        if(itemType == ACTION) {
            setTitle("Making a new Actie:");
            categoryEditText.setHint("Priority");
            categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            isPositive.setVisibility(View.INVISIBLE);
            return;
        }

    }

    private void editItem() {
        final int currentItemPos = getIntent().getIntExtra("position", 0);

        switch(itemType){
            case NOTE:
                setTitle("Pas notitie aan:");

				/*titleEditText.setText(Model.getInstance().getNote(currentItemPos).getDescription());
                summaryEditText.setText(Model.getInstance().getNote(currentItemPos).getSummary());
				categoryEditText.setText(Model.getInstance().getNote(currentItemPos).getSubcategory());
				isPositive.setChecked(Model.getInstance().getNote(currentItemPos).isPositive());*/;
                break;
            case USER_STORY:
                setTitle("Pas user story aan");
                categoryEditText.setHint("Points");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setText("Burned");

				/*titleEditText.setText(Model.getInstance().getUserStory(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getUserStory(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getUserStory(currentItemPos).getPoints());
				isPositive.setChecked(Model.getInstance().getUserStory(currentItemPos).isBurned());*/
                break;
            case ACTION:
                setTitle("Pas actie aan");
                categoryEditText.setHint("Priority");
                categoryEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                isPositive.setVisibility(View.INVISIBLE);

				/*titleEditText.setText(Model.getInstance().getAction(currentItemPos).getDescription());
				summaryEditText.setText(Model.getInstance().getAction(currentItemPos).getSummary());
				categoryEditText.setText("" + Model.getInstance().getAction(currentItemPos).getPriority());*/
                break;
        }
        
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title, summary, category;
                int p;
                boolean ispositive;

                title = titleEditText.getText().toString();
                summary = summaryEditText.getText().toString();

                if (itemType == 0) {
                    category = categoryEditText.getText().toString();
                    ispositive = isPositive.isChecked();


                    //Model.getInstance().getNote(currentItemPos).setDescription(title);
                    //Model.getInstance().getNote(currentItemPos).setSummary(summary);
                    //Model.getInstance().getNote(currentItemPos).setCategory(category);
                    //Model.getInstance().getNote(currentItemPos).setIsPositive(ispositive);


                } else if (itemType == 1) {
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DEBUG", "Deze wordt aangeroepen");

        if(resultCode == Activity.RESULT_OK){
            ContentResolver cr = getContentResolver();
            Uri selectedImage = imageUri;
            cr.notifyChange(selectedImage,null);

            //photoView = (ImageView)findViewById(R.id.imageViewPhoto);
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
