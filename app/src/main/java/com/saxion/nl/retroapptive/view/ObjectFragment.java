package com.saxion.nl.retroapptive.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.ItemAdapter;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.List;

/**
 * Created by falco on 28-5-15.
 */
public final class ObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    public static Bundle args;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Log.d("INFLATE", "Oncreate");


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.list, container, false);
        args = getArguments();


        Log.d("INFLATE", "ONCREATEVIEW");
        Log.d("ARGS", args.getString(ARG_OBJECT));

        ListView listView = (ListView) rootView.findViewById(R.id.listView);


       // if(args.equals("Notities")){

            ItemAdapter<Notitie> itemAdapter = new ItemAdapter<>(container.getContext(), R.layout.fragment_list_item, Model.getInstance().notes);
            listView.setAdapter(itemAdapter);



//        }




        return rootView;
    }
}
