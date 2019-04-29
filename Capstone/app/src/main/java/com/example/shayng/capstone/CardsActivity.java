package com.example.shayng.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CardsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Event> events;
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

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String description = ds.child("description").getValue(String.class);
                    String location = ds.child("location").getValue(String.class);
                    String startTime = ds.child("startTime").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String endTime = ds.child("endTime").getValue(String.class);
                    //  Log.d("TAG", arrival + " / " + departure  + " / " + time);
                    //  list.add(time);


                    events.add(new Event(startTime,title,location,description));
                    adapter = new EventViewHolder(events);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        eventsRef.addValueEventListener(valueEventListener);


        mapsButton = findViewById(R.id.mapsButton);
        mapsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                startActivity(new Intent(CardsActivity.this, MapsActivity.class));
            }

        });
    }

    public void addCard(){
        //events.add(new Event())
    }
}



