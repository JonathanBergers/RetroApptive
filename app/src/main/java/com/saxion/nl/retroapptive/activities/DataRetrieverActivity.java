package com.saxion.nl.retroapptive.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.saxion.nl.retroapptive.R;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.ROClient;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.RORequest;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.ConnectionException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.InvalidCredentialException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.UnknownErrorException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ActionResult;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.JsonRepr;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
import com.saxion.nl.retroapptive.model.Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class DataRetrieverActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retriever);

        GetItemsTask getItemsTask = new GetItemsTask(ActionResult.class);

        ROClient.getInstance().setCredential("todoapp-admin", "pass");
        Link link = new Link();
        link.setHref("http://145.76.115.243:8080/services/ToDoItems/actions/notYetComplete/invoke");
        link.setMethod("GET");
        getItemsTask.execute(link);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_retriever, menu);
        return true;
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













}
class GetItemsTask extends IsisTask<ActionResult>{

    boolean objectRecieved = false;
    ArrayList<DomainObject> domainObjects = new ArrayList<>();
    public GetItemsTask(Class<ActionResult> typeClass) {
        super(typeClass);
    }



    @Override
    protected void onPostExecute(ActionResult actionResult) {


        List<Link> links = actionResult.getResult().getLinks();





        if(links == null){

            return;
        }








            class GetItemTask extends IsisTask<DomainObject>{


                public GetItemTask(Class<DomainObject> typeClass) {
                    super(typeClass);


                }
                @Override
                protected void onPostExecute(DomainObject domainObject) {
                    domainObjects.add(domainObject);
                    objectRecieved = true;
                    System.out.println(domainObject.getTitle());

                }




            }

        Link firstLink = links.remove(0);
        GetItemTask firstGetItemTask = new GetItemTask(DomainObject.class);
        firstGetItemTask.doInBackground(firstLink);


        while(links.isEmpty() == false  && objectRecieved == true ) {

            Link linkToObject = links.remove(0);
            GetItemTask getItemTask = new GetItemTask(DomainObject.class);
            getItemTask.doInBackground(linkToObject);
            objectRecieved = false;

        }







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