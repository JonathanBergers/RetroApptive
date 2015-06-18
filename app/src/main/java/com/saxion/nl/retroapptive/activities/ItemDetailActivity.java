package com.saxion.nl.retroapptive.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.Slider;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Action;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Profiel;
import com.saxion.nl.retroapptive.model.UserPicture;
import com.saxion.nl.retroapptive.model.UserStory;
import com.saxion.nl.retroapptive.view.NotesListViewFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by falco on 31-5-15.
 */
public class ItemDetailActivity extends BaseActivity {

    private MaterialEditText editTextItem1, editTextItem2;
    private TextView textViewTitleCat, textViewProject, textViewSprint, textViewAccount, textViewTitlePoints;
    private Spinner spinnerCat;
    private Button buttonBurned;
    private Slider sliderPoints;


    private Item currentItem;





    private static final int SELECT_SINGLE_PICTURE = 4;
    public static final String IMAGE_TYPE = "image/*";

    private ImageView selectedImagePreview;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.item_details, drawer);

        initViews();





    }


    private void initViews(){







        editTextItem1 = (MaterialEditText) findViewById(R.id.editTextItem1);
        editTextItem2 = (MaterialEditText) findViewById(R.id.editTextItem2);

        textViewTitleCat = (TextView) findViewById(R.id.textViewCatTitle);
        textViewProject = (TextView) findViewById(R.id.textViewProject);
        textViewSprint = (TextView) findViewById(R.id.textViewSprint);
        textViewAccount = (TextView) findViewById(R.id.textViewAccount);
        textViewTitlePoints = (TextView) findViewById(R.id.textViewPointsTitle);



        sliderPoints = (Slider) findViewById(R.id.slider_item);



        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);

        ButtonFloat floatingActionButton = (ButtonFloat) findViewById(R.id.buttonFloat);




        setupCurrentItem();



    }




    private void setupCurrentItem(){

        currentItem = Model.getInstance().getCurrentItem();
        editTextItem1.setText(currentItem.getDescription());
        editTextItem2.setText(currentItem.getSummary());

        if(currentItem.getProfile().getProfileType() == Profiel.ProfielType.SCRUM_MASTER){
            textViewAccount.setCompoundDrawables(getDrawable(R.drawable.account_scrum), null, null, null);
        }


        textViewAccount.setText("  " + currentItem.getProfile().getName() + " " + currentItem.getProfile().getSurname());
        textViewProject.setText("  " +currentItem.getSprint().getProject().getName());
        textViewSprint.setText("  " + currentItem.getSprint().getSprintID());

        if(currentItem instanceof Notitie){

            setupNote();
            return;
        }

        if(currentItem instanceof Actie){
            setupAction();
            return;
        }

        if(currentItem instanceof UserStory){
            setupUserStory();
            return;

        }




    }



    private void setupNote(){

        Notitie notitie = (Notitie) currentItem;


        //setting up UI
        //if( no cat ){

            textViewTitleCat.setVisibility(View.INVISIBLE);
            spinnerCat.setVisibility(View.INVISIBLE);
        //}else{

            //TODO category ophalen

        //}

        sliderPoints.setVisibility(View.INVISIBLE);
        textViewTitlePoints.setVisibility(View.INVISIBLE);


        //setting up data





    }


    private void setupAction(){


        Actie actie = (Actie) currentItem;

        textViewTitleCat.setVisibility(View.INVISIBLE);
        spinnerCat.setVisibility(View.INVISIBLE);


        textViewTitlePoints.setText("Prioriteit");
        sliderPoints.setShowNumberIndicator(true);
        sliderPoints.setValue(actie.getPriority());




    }


    public void setupUserStory(){


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
