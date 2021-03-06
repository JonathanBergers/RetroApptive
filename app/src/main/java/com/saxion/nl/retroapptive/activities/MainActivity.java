package com.saxion.nl.retroapptive.activities;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.controller.CollectionPagerAdapter;
import com.saxion.nl.retroapptive.controller.sprintselector.Item;
import com.saxion.nl.retroapptive.model.Actie;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.model.Sprint;

public class MainActivity extends BaseActivity {

    private static final String HOST = "http://topicus.apps.gedge.nl/simpleapp/";

    public static MainActivity instance = null;

    public static Sprint currentSprint = null;

    private static int PAGE_NUM_NOTE = 0;
    private static int PAGE_NUM_ACTION = 1;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private boolean userStoriesSynced = false;
    private boolean actionsSynced = false;

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    FragmentManager fragmentManager;

    private static final int NEW_NOTE_REQUEST = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        getLayoutInflater().inflate(R.layout.activity_main, drawer);

        final ActionBar actionBar = getActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
                // hide the given tab
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
                // probably ignore this event
            }

        };

        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }

        fragmentManager = getFragmentManager();

        mCollectionPagerAdapter =
                new CollectionPagerAdapter(getFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(mCollectionPagerAdapter);

        //link.setHref("http://145.76.115.243:8080/restful/objects/TODO/1");
        //GetItemsTask getNoteTask = new GetItemsTask(ActionResult.class);

        //getNoteTask.execute(link);

        //mCollectionPagerAdapter.notifyDataSetChanged();

        //setting de plusbutton
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.fab);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mViewPager.getCurrentItem() == PAGE_NUM_NOTE){

                    // haalt local profile op
                    // maakt nieuwe lege notitie
                    Notitie notitie = new Notitie(currentSprint, null, null, Model.getInstance().getLocalProfile(), true, null);

                    Model.getInstance().setCurrentItem(notitie);


                }
                if(mViewPager.getCurrentItem() == PAGE_NUM_ACTION){

                    // haalt local profile op
                    // maakt nieuwe lege notitie
                    Actie actie = new Actie(currentSprint, null, null, Model.getInstance().getLocalProfile(), 5);

                    Model.getInstance().setCurrentItem(actie);


                }
                Intent i = new Intent(MainActivity.this, ItemDetailActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_REQUEST) {
            if (resultCode == RESULT_OK) {
                mCollectionPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getNotes() {

    }
    public CollectionPagerAdapter getmCollectionPagerAdapter(){
        return mCollectionPagerAdapter;
    }

}
