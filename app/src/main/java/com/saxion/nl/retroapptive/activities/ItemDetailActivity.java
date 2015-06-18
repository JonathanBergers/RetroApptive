package com.saxion.nl.retroapptive.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Slider;
import com.gc.materialdesign.views.Switch;
import com.gc.materialdesign.widgets.SnackBar;
import com.jorgecastilloprz.expandablepanel.ExpandablePanelView;
import com.melnykov.fab.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.IsisNotitie;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.IsisSprint;
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

    private MaterialEditText editTextItemTitle, editTextItemSummary;
    private TextView textViewTitleCat, textViewProject, textViewSprint, textViewAccount, textViewTitlePoints, textViewBoolean;
    private Spinner spinnerCat;
    private Slider sliderPoints;
    private Switch switchViewItem;
    private ExpandablePanelView expandablePanelView;
    private ImageView imageViewArrow;

    private byte[] attachment;

    private Item currentItem;

    private boolean backClicked = false;





    private static final int SELECT_SINGLE_PICTURE = 4;
    public static final String IMAGE_TYPE = "image/*";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.item_details, drawer);

        initViews();





    }


    private void initViews(){







        editTextItemTitle = (MaterialEditText) findViewById(R.id.editTextItemTitle);
        editTextItemSummary = (MaterialEditText) findViewById(R.id.editTextItemSummary);

        textViewTitleCat = (TextView) findViewById(R.id.textViewCatTitle);
        textViewProject = (TextView) findViewById(R.id.textViewProject);
        textViewSprint = (TextView) findViewById(R.id.textViewSprint);
        textViewAccount = (TextView) findViewById(R.id.textViewAccount);
        textViewTitlePoints = (TextView) findViewById(R.id.textViewPointsTitle);
        textViewBoolean = (TextView) findViewById(R.id.textViewBoolean);
        switchViewItem = (Switch) findViewById(R.id.switchViewItem);
        expandablePanelView = (ExpandablePanelView) findViewById(R.id.expandableView);
        imageViewArrow = (ImageView) findViewById(R.id.imageViewArrow);





        sliderPoints = (Slider) findViewById(R.id.slider_item);



        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);

        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.fabItem);
        plusButton.setColorNormal(Color.YELLOW);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_picture)), SELECT_SINGLE_PICTURE);
            }
        });


        //TODO
//        if(currentItem.getAttachment() == null){
//
//
//        }

        imageViewArrow.setVisibility(View.INVISIBLE);



        setupCurrentItem();



    }




    private void setupCurrentItem(){

        currentItem = Model.getInstance().getCurrentItem();
        editTextItemTitle.setText(currentItem.getDescription());
        editTextItemSummary.setText(currentItem.getSummary());

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
        switchViewItem.setChecked(notitie.isPositive());

        if(switchViewItem.isCheck()){
            switchViewItem.setBackgroundColor(Color.GREEN);
        }else{
            switchViewItem.setBackgroundColor(Color.RED);

        }
        switchViewItem.setOncheckListener(new Switch.OnCheckListener() {
            @Override
            public void onCheck(Switch aSwitch, boolean b) {
                if (b) {
                    switchViewItem.setBackgroundColor(Color.GREEN);
                    textViewBoolean.setText("Positief");
                } else {
                    switchViewItem.setBackgroundColor(Color.RED);
                    textViewBoolean.setText("Negatief");
                }
            }
        });




    }


    private void setupAction(){


        Actie actie = (Actie) currentItem;

        textViewTitleCat.setVisibility(View.INVISIBLE);
        spinnerCat.setVisibility(View.INVISIBLE);
        switchViewItem.setVisibility(View.INVISIBLE);
        textViewBoolean.setVisibility(View.INVISIBLE);

        textViewTitlePoints.setText("Prioriteit");
        sliderPoints.setShowNumberIndicator(true);
        sliderPoints.setValue(actie.getPriority());
        sliderPoints.setMin(0);
        sliderPoints.setMax(10);




    }


    public void setupUserStory(){


    }


    @Override
    public void onBackPressed() {

if(backClicked){
    goBack();
}

        backClicked = true;

        if(currentItem instanceof Notitie){

            saveCreatedNote();

        }else if(currentItem instanceof Actie){
            saveCreatedAction();

        }else if(currentItem instanceof UserStory){
            setupUserStory();


        }





    }

    private void saveCreatedNote(){
        SnackBar snackbar = new SnackBar(ItemDetailActivity.this, "Wil je de notitie opslaan?", "Ja", new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            //TODO WERKT NIET< ISIS ITEM >???
                            final Notitie notitie =  Model.getInstance().createNote((IsisSprint)currentItem.getSprint(), editTextItemTitle.getText().toString(),
                                    editTextItemSummary.getText().toString(),switchViewItem.isCheck(),
                                    spinnerCat.getTransitionName());



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ItemDetailActivity.this.goBack();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                removeNote((IsisNotitie) Model.getInstance().getCreatedItem());
            }

        });
        snackbar.show();





    }


    private  void goBack(){
        super.onBackPressed();

    }
    private void saveCreatedAction(){
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    final Actie actie =  Model.getInstance().createNote(currentItem.getSprint(), editTextItemTitle.getText().toString(),
//                            editTextItemSummary.getText().toString(),switchViewItem.isCheck(),
//                            spinnerCat.getTransitionName());
//
//                    Model.getInstance().deleteNote((Notitie)currentItem);
//                    Model.getInstance().setCreatedItem(notitie);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            SnackBar snackbar = new SnackBar(ItemDetailActivity.this, "Notitie opgeslagen", "Ongedaan maken", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    removeNote((Notitie) Model.getInstance().getCreatedItem());
//
//                                }
//                            });
//                            snackbar.show();
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }


    private void removeNote(final Notitie notitie){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {



                    Model.getInstance().deleteNote(notitie);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void removeAction(final Actie actie){
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//
//
//                    //TODO
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

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
                    final byte[] bArray = bos.toByteArray();
                    attachment = bArray;



                    expandablePanelView.setBackground(new BitmapDrawable(yourBitmap));

                    imageViewArrow.setVisibility(View.VISIBLE);


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
