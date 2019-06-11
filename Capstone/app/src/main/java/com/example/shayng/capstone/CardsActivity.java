package com.example.shayng.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
//import android.widget.SearchView;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.SearchView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class CardsActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    private EventViewHolder adapter; // could be EventViewHolder not recyclerview.adapter RecyclerView.Adapter
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView; // was static
    private SearchView searchView;
    private ArrayList<Event> events;
    private ArrayList<Event> eventsFilter;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private FloatingActionButton mapsButton;
    private DatabaseReference mDatabase;
    //private ImageButton favButton = findViewById(R.id.favButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        events = new ArrayList<>();
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recList.setLayoutManager(llm);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Event");

//bruce miller s.miller@lansconnect.com
        //write papa a letter

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventsRef = rootRef.child("Event");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Calendar now = Calendar.getInstance();
                events.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String description = ds.child("description").getValue(String.class);
                    String location = ds.child("location").getValue(String.class);
                    String startTime = ds.child("startTime").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String endTime = ds.child("endTime").getValue(String.class);

                    if(now.getTimeInMillis()<Long.parseLong(endTime)) {
                        applyMarker(title, startTime, endTime, location, false, description);
                    }
                    //events.add(new Event(title,startTime,endTime,location,description));
                    //adapter = new EventViewHolder(events);
                    //recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        eventsRef.addValueEventListener(valueEventListener);

        FloatingActionButton addFab = findViewById(R.id.cardfloatingActionButton);
       addFab.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view)
           {
               beventDialog();

           }
       });

        mapsButton = findViewById(R.id.mapsButton);
        mapsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                startActivity(new Intent(CardsActivity.this, MapsActivity.class));
            }

        });

    }

    public void beventDialog(){

        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "xexample dialog");

    }


@Override

public void applyMarker(String title, String startDate, String endDate, String place, boolean addData, String description)
{
    events.add(new Event(title,startDate,endDate,place,description));
    adapter = new EventViewHolder(events);
    recyclerView.setAdapter(adapter);

    if(addData) {
        mDatabase.push().setValue(new Event(title, startDate, endDate, place, description));
        Toast.makeText(CardsActivity.this, "New Event Created!", Toast.LENGTH_SHORT).show();
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

       searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               // recyclerView.getFilter
                //adapter = new EventViewHolder(events);
               adapter.getFilter().filter(s);
               recyclerView.setAdapter(adapter);

                return false;
            }
        });
        return true;//super.onCreateOptionsMenu(menu);
    }
}



