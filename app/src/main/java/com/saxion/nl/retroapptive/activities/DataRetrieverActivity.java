package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.saxion.nl.retroapptive.MainActivity;
import com.saxion.nl.retroapptive.R;
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
import com.saxion.nl.retroapptive.communication.login.LoginCredentials;
import com.saxion.nl.retroapptive.model.Model;
import com.saxion.nl.retroapptive.model.Notitie;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class DataRetrieverActivity extends Activity {


    ArrayList<DomainObject> domainObjects = new ArrayList<>();
    ArrayList<Notitie> notities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retriever);
        //ApacheIsisDataGatherer.getInstance().setHost("http://145.76.115.243:8080/restful/");

        LoginCredentials loginCredentials = new LoginCredentials("todoapp-admin", "pass");



        ROClient.getInstance().setCredential("todoapp-admin", "pass");
        ROClient.getInstance().setHost("http://10.0.1.23:8080/restful");
        Link link = new Link();
        link.setHref("http://10.0.1.23:8080/restful/services/ToDoItems/actions/collectNotes/invoke");
        link.setMethod("GET");
        //link.setHref("http://145.76.115.243:8080/restful/objects/TODO/1");
        GetItemsTask getItemsTask = new GetItemsTask(ActionResult.class);

        getItemsTask.execute(link);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_retriever, menu);
        return true;
    }



    //START MAIN ACTIVITY

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("MAIN","Started");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
















//haalt de item lijst op
class GetItemsTask extends IsisTask<ActionResult>{
    List<Link> links;

//haalt de individuele items op
    class GetItemTask extends IsisTask<DomainObject>{
        public GetItemTask(Class<DomainObject> typeClass) {
            super(typeClass);


        }



        // post execute van ITEM
        @Override
        protected void onPostExecute(DomainObject domainObject) {
            Log.d("POST", domainObject.getTitle());
            domainObjects.add(domainObject);
            Model.getInstance().notesTestStrings.add(domainObject.getTitle());


            //TODO iets waardoor domainobjecten uit elkaar worden gehouden
            IsisConverter converter = new IsisConverter();
            Model.getInstance().notes.add(converter.getNotitieFromDomainObject(domainObject));


            //Recursion ;D
            if(!links.isEmpty()){

                Link linkToObject = links.remove(0);
                Log.d("TRY", linkToObject.getHref());
                GetItemTask getItemTask = new GetItemTask(DomainObject.class);
                getItemTask.execute(linkToObject);

            }




        }

    }
    public GetItemsTask(Class<ActionResult> typeClass) {
        super(typeClass);
    }


    //post execute van ITEMSS
    @Override
    protected void onPostExecute(ActionResult actionResult) {




        links = actionResult.getResult().getValueAsList();

        if(links == null){

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
            error = CONNECTION_ERROR;
            e.printStackTrace();
        } catch (InvalidCredentialException e) {
            error = INVALID_CREDENTIAL;
            e.printStackTrace();
        } catch (UnknownErrorException e) {
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
