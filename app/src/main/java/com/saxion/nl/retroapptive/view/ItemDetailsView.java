package com.saxion.nl.retroapptive.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Action;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.User;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

/**
 * Created by jonathan on 17-6-15.
 */
public class ItemDetailsView extends LinearLayout {


    private MaterialEditText editTextItem1, editTextItem2, editTextItem3, editTextItem4;
    private TextView textViewTitleCat;
    private Spinner spinnerCat;
    private Button buttonBurned;

    private Item currentItem;



    public ItemDetailsView(Context context) {
        super(context);
        initViews(context);


    }

    public ItemDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public ItemDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public ItemDetailsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater.from(context));
        inflater.inflate(R.layout.item_details, this);


        Log.d("ITEMVIEW", "INITVIEWS");



        editTextItem1 = (MaterialEditText) findViewById(R.id.editTextItem1);
        editTextItem2 = (MaterialEditText) findViewById(R.id.editTextItem2);
        editTextItem3 = (MaterialEditText) findViewById(R.id.editTextItem3);
        editTextItem4 = (MaterialEditText) findViewById(R.id.editTextItem4);
        textViewTitleCat = (TextView) findViewById(R.id.textViewCatTitle);


        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);
        buttonBurned = (Button) findViewById(R.id.buttonItem);

        editTextItem1.setFloatingLabel(2);
        editTextItem1.setFloatingLabelText("JOOO");
        editTextItem1.setBaseColor(Color.BLUE);
        editTextItem1.setFloatingLabelTextSize(30);
        AddFloatingActionButton ad = new AddFloatingActionButton(context);
        ad.setVisibility(VISIBLE);



    }




    private void setupCurrentItem(){

        currentItem = Model.getInstance().getCurrentItem();
        editTextItem1.setText(currentItem.getDescription());
        editTextItem2.setText(currentItem.getSummary());


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
        if(notitie.getSubcategory() == null){

            textViewTitleCat.setVisibility(INVISIBLE);
            spinnerCat.setVisibility(INVISIBLE);
        }else{

            //TODO category ophalen

        }

        editTextItem3.setVisibility(INVISIBLE);
        editTextItem4.setVisibility(INVISIBLE);




    }


    private void setupAction(){


    }


    public void setupUserStory(){


    }

















}
