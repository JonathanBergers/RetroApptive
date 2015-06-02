package com.saxion.nl.retroapptive;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.saxion.nl.retroapptive.activities.LogInActivity;
import com.saxion.nl.retroapptive.activities.ObjectActivity;
import com.saxion.nl.retroapptive.communication.converter.IsisConverter;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.ROClient;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.RORequest;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.ConnectionException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.InvalidCredentialException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.UnknownErrorException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ActionResult;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.JsonRepr;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
import com.saxion.nl.retroapptive.controller.CollectionPagerAdapter;
import com.saxion.nl.retroapptive.controller.ItemAdapter;
import com.saxion.nl.retroapptive.controller.NoteAdapter;
import com.saxion.nl.retroapptive.model.Notitie;
import com.saxion.nl.retroapptive.view.ListViewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private static final String HOST = "http://192.168.2.10:8080";

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		ROClient.getInstance().setCredential("todoapp-admin", "pass");
		ROClient.getInstance().setHost(HOST + "/restful");

		Link link = new Link();
		link.setHref(HOST + "/restful/services/ToDoItems/actions/collectNotes/invoke");
		link.setMethod("GET");
		//link.setHref("http://145.76.115.243:8080/restful/objects/TODO/1");
		GetItemsTask getNoteTask = new GetItemsTask(ActionResult.class);

		getNoteTask.execute(link);

		//mCollectionPagerAdapter.notifyDataSetChanged();

		//setting de plusbutton
		FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.fab);
		plusButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ObjectActivity.class);
				i.putExtra("item", (mViewPager.getCurrentItem()));
				Log.d("Item", ""+mViewPager.getCurrentItem());
				startActivityForResult(i, 100);
			}
		});

	}

	//TODO OPHALEN VAN USER STORIES bij oncreate.

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == RESULT_OK){
			mCollectionPagerAdapter.notifyDataSetChanged();

		}

	}

	// kan volgens mij weg
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		//FragmentManager fragmentManager = getFragmentManager();
		//fragmentManager.beginTransaction()
		// .replace(R.id.container, new ListViewFragment())
		// .commit();
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

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	//haalt de item lijst op
	class GetItemsTask extends IsisTask<ActionResult> {

		//haalt de individuele items op

		ArrayList<DomainObject> domainObjects = new ArrayList<>();
		ArrayList<Notitie> notities = new ArrayList<>();

		List<Link> links;

		class GetItemTask extends IsisTask<DomainObject> {

			public GetItemTask(Class<DomainObject> typeClass) {
				super(typeClass);

			}

//            private int getType(DomainObject domainObject){
//
//
//            }

			// post execute van ITEM
			@Override
			protected void onPostExecute(DomainObject domainObject) {

				Log.d("POST", domainObject.getTitle());

				IsisConverter.getInstance().convertObject(domainObject);

				//Recursion ;D
				if (!links.isEmpty()) {

					Link linkToObject = links.remove(0);
					Log.d("TRY", linkToObject.getHref());
					GetItemTask getItemTask = new GetItemTask(DomainObject.class);
					getItemTask.execute(linkToObject);

				} else if (!userStoriesSynced) {
					Log.d("USERSTORY", "SYNCING");
					userStoriesSynced = true;
					Link link2 = new Link();
					link2.setHref(HOST + "/restful/services/ToDoItems/actions/collectUserStories/invoke");
					link2.setMethod("GET");
					//link.setHref("http://145.76.115.243:8080/restful/objects/TODO/1");
					GetItemsTask getUserStoriesTask = new GetItemsTask(ActionResult.class);
					getUserStoriesTask.execute(link2);
				} else if (!actionsSynced) {
					Log.d("Actions", "SYNCING");
					actionsSynced = true;
					Link link3 = new Link();
					link3.setHref(HOST + "/restful/services/ToDoItems/actions/collectActions/invoke");
					link3.setMethod("GET");
					GetItemsTask getActionsTask = new GetItemsTask(ActionResult.class);
					getActionsTask.execute(link3);
				}

				mCollectionPagerAdapter.notifyDataSetChanged();

			}

		}

		public GetItemsTask(Class<ActionResult> typeClass) {
			super(typeClass);
		}

		//post execute van ITEMSS
		@Override
		protected void onPostExecute(ActionResult actionResult) {

			if(actionResult == null){
				Log.d("NIKS", "result is null");
				return;
			}

			links = actionResult.getResult().getValueAsList();

			if (links.isEmpty()) {
				Log.d("NIKS", "links is leeg");
				return;
			}

			Link firstLink = links.remove(0);
			Log.d("TRY", firstLink.getHref());
			GetItemTask firstGetItemTask = new GetItemTask(DomainObject.class);
			firstGetItemTask.execute(firstLink);

		}
	}

	class IsisTask<T extends JsonRepr> extends AsyncTask<Link, Void, T> {

		private Class<T> typeClass;

		public IsisTask(Class<T> typeClass) {

			this.typeClass = typeClass;

		}

		int error = 0;
		private static final int INVALID_CREDENTIAL = -1;
		private static final int CONNECTION_ERROR = -2;
		private static final int UNKNOWN_ERROR = -3;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected T doInBackground(Link... params) {
			Link elementLink = params[0];
			ROClient client = ROClient.getInstance();
			RORequest request = client.RORequestTo(elementLink.getHref());
			try {
				Log.d("TRY", elementLink.getHref());
				T result = client.executeT(typeClass, elementLink.getMethod(), request, null);
				Log.d("TRY", "WORK");
				return result;
			} catch (ConnectionException e) {
				Log.d("TRY", "connectionexc");

				error = CONNECTION_ERROR;
				e.printStackTrace();
			} catch (InvalidCredentialException e) {
				Log.d("TRY", "INVALID");
				error = INVALID_CREDENTIAL;
				e.printStackTrace();
				Intent intent = new Intent(MainActivity.this, LogInActivity.class);
				MainActivity.this.startActivity(intent);
			} catch (UnknownErrorException e) {
				Log.d("TRY", "unknown");

				error = UNKNOWN_ERROR;
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.d("TRY", "FAIL");
			return null;

		}

	}

}
