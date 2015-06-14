//package com.saxion.nl.retroapptive.activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.saxion.nl.retroapptive.R;
//import com.saxion.nl.retroapptive.communication.data.gatherer.IsisTask;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Actie;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ActionResult;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ActionResultItem;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.DomainObject;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Homepage;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ObjectMember;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Service;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.ServiceMember;
//import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Services;
//import com.saxion.nl.retroapptive.model.Model;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * Created by jonathan on 22-5-15.
// */
//public class CommunicationActivity extends UpdateAbleActivity{
//
//
//    private ListView listView;
//    private List<Link> links = new ArrayList<Link>();
//    private Link currentLink;
//
//    private IsisTask<?> isisTask;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_communication);
//
//        listView = (ListView) findViewById(R.id.listViewCommunication);
//        currentLink = Model.getInstance().getCurrentLink();
//        Log.d("LINK", currentLink.getHref());
//
//
//
//        //MainPage = service, so
//        getService();
//
//
//
//
//
//
//
//
//
//
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                currentLink = links.get(position);
//                currentLink.setHref(currentLink.getHref() + "/invoke/");
//                getActionResult();
//
//
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                return false;
//            }
//        });
//    }
//
//
//
//
//
//
//
//
//    @Override
//    public void update(Object o) {
//
//
//
//        if(o instanceof ActionResultItem){
//            ActionResultItem r = (ActionResultItem) o;
//
//            links = r.getValueAsList();
//            Log.d("RESULT", "ACTIONRES");
//
//        }
//        if(o instanceof ServiceMember){
//
//            ServiceMember r = (ServiceMember) o;
//            links = r.getLinks();
//            Log.d("RESULT", "SERVICEMEM");
//        }
//        if(o instanceof Service){
//
//            Service r = (Service) o;
//            for(String s : r.getMembers().keySet()){
//                links.addAll(r.getMembers().get(s).getLinks());
//
//
//
//            }//HACKS FOR DEMO
//            Homepage h = new Homepage();
//            h.setLinks(links);
//            Model.getInstance().setHomePage(h);
//            Intent intent = new Intent(this, FABActivity.class);
//            startActivity(intent);
//
//            Log.d("RESULT", "SERVICE");
//        }
//        if(o instanceof DomainObject){
//            DomainObject r = (DomainObject) o;
//            links = r.getLinks();
//            Log.d("YOOO", r.AsJson());
//        }
//        if(o instanceof Services){
//
//            Services r = (Services) o;
//            links = r.getLinks();
//
//        }
//
//        if(o instanceof ObjectMember){
//
//            ObjectMember r = (ObjectMember) o;
//            links = r.getLinks();
//        }
//
//        if(o instanceof ActionResult){
//
//
//            // als je op notyet completed drukt
//            ActionResult r = (ActionResult) o;
//            Map<String, ObjectMember> objectMembers = r.getResult().getMembers();
//            Model.getInstance().setTodoItemMembers(objectMembers);
//
//
//            Intent intent = new Intent(CommunicationActivity.this, NotesActivity.class);
//
//
//            links = r.getResult().getValueAsList();
//
//        }
//
//        if(o instanceof Actie){
//
//            Actie r = (Actie) o;
//            links = r.getLinks();
//        }
//
//
//        ArrayList<String> linkStrings = new ArrayList<String>();
//        for(Link link : links){
//
//
//
//
//            linkStrings.add(link.getHref());
//        }
//
//        if(listView.getAdapter() !=null){
//
//
//        }
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, linkStrings));
//
//
//
//
//
//    }
//    private void executeLink(){
//
//        isisTask.execute(currentLink);
//    }
//
//
//    private void getActionResultItem(){
//
//        isisTask = new IsisTask<ActionResultItem>(this, ActionResultItem.class);
//        executeLink();
//
//    }
//
//    private void getService(){
//        isisTask = new IsisTask<Service>(this, Service.class);
//        executeLink();
//    }
//
//    private void getDomainObject(){
//        isisTask = new IsisTask<DomainObject>(this, DomainObject.class);
//        executeLink();
//    }
//
//    private void getServices(){
//        isisTask = new IsisTask<Services>(this, Services.class);
//        executeLink();
//
//    }
//
//    private void getObjectMember(){
//        isisTask = new IsisTask<ObjectMember>(this, ObjectMember.class);
//        executeLink();
//
//
//    }
//    private void getServiceMember(){
//
//        isisTask = new IsisTask<ServiceMember>(this, ServiceMember.class);
//        executeLink();
//    }
//
//
//    private void getActionResult(){
//
//        isisTask = new IsisTask<ActionResult>(this, ActionResult.class);
//        executeLink();
//
//    }
//
//    private void getAction(){
//
//        isisTask = new IsisTask<Actie>(this, Actie.class);
//        executeLink();
//    }
//
//
//
//
//
//
//
//
//
//
//}
