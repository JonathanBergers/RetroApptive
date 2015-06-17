package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.activities.ItemDetailActivity;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Item;
import com.saxion.nl.retroapptive.model.ItemType;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.UserStory;

/**
 * Created by falco on 28-5-15.
 */
public class ListViewFragment extends Fragment {

    protected ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.list, container, false);
        listView = (ListView) rootView.findViewById(R.id.listView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        setOnclickListener();



    }


    protected void setOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Model.getInstance().setCurrentItem((Item)listView.getItemAtPosition(i));


                Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
                startActivity(intent);



            }



        });



    }



}
