package com.example.shayng.capstone;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.*;

import android.widget.SearchView;
import android.widget.Toast;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ExampleDialog.ExampleDialogListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private SearchView searchBar;
    //private Button button;
    private FloatingActionButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Event");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventsRef = rootRef.child("Event");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String description = ds.child("description").getValue(String.class);
                    String location = ds.child("location").getValue(String.class);
                    String startTime = ds.child("startTime").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String endTime = ds.child("endTime").getValue(String.class);

                    applyMarker(title,startTime, endTime, location,false, description);
                  //  Log.d("TAG", arrival + " / " + departure  + " / " + time);
                  //  list.add(time);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        eventsRef.addValueEventListener(valueEventListener);


        button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                eventDialog();

            }
        });
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setIconified(false);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setIndoorEnabled(false);
        LatLng CI = new LatLng(34.162849, -119.044044);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CI));
        CameraUpdateFactory.zoomIn();
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(30.0f);
        LatLng neBound = new LatLng(34.168748, -119.032949);
        LatLng swBound = new LatLng(34.157731, -119.055160);
        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(swBound,neBound));
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-130, 10);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Event Name: Shayn's Birthday.\n" + "Location: Bell Tower\n"+"Time: 12/05/18 at 6:00pm"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        //googleMap.getMyLocation()
       // CameraPosition cameraPosition =
    }


    public void eventDialog(){

        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");


    }


    @Override
    public void applyMarker(String title, String date, String endDate, String place, boolean addData, String d){
        LatLng CI = new LatLng(34.162849, -119.044044);

        String eventString = title;

        //eventString = "<p>Location</p>" + "\n" + "<p>Time</p>";

        if(place.equals("Bell Tower")) CI = new LatLng(34.1611, -119.04312);
        else if(place.equals("Broome Library")) CI = new LatLng(34.162619, -119.041338);
        else if(place.equals("Madera Hall")) CI = new LatLng(34.162849, -119.044044);
        else if(place.equals("Aliso Hall")) CI = new LatLng(34.161306, -119.045317);
        else if(place.equals("Anacapa Village")) CI = new LatLng(34.159380, -119.044884);
        else if(place.equals("Arroyo Hall")) CI = new LatLng(34.160392, -119.044991);
        else if(place.equals("Bell Tower East")) CI = new LatLng(34.161271, -119.042083);
        else if(place.equals("Bell Tower West")) CI = new LatLng(34.160716, -119.044272);
        else if(place.equals("Central Mall")) CI = new LatLng(34.162034, -119.043525);
        else if(place.equals("Chaparral Hall")) CI = new LatLng(34.162032, -119.045642);
        else if(place.equals("Del Norte Hall")) CI = new LatLng(34.163169, -119.044043);
        else if(place.equals("El Dorado Hall")) CI = new LatLng(34.164196, -119.047112);
        else if(place.equals("Grand Salon")) CI = new LatLng(34.163837, -119.043636);
        else if(place.equals("Ironwood Hall")) CI = new LatLng(34.162496, -119.046543);
        else if(place.equals("Islands Cafe")) CI = new LatLng(34.160290, -119.041959);
        else if(place.equals("Lighthouse Cafe")) CI = new LatLng(34.161443, -119.044499);
        else if(place.equals("Lindero Hall")) CI = new LatLng(34.159530, -119.041412);
        else if(place.equals("South Quad")) CI = new LatLng(34.160031, -119.042781);
        else if(place.equals("Manzanita Hall")) CI = new LatLng(34.162703, -119.045072);
        else if(place.equals("Martin V. Smith Center")) CI = new LatLng(34.163951, -119.043227);
        else if(place.equals("Modoc Hall")) CI = new LatLng(34.163900, -119.048249);
        else if(place.equals("Napa Hall")) CI = new LatLng(34.163817, -119.045432);
        else if(place.equals("North Field")) CI = new LatLng(34.167523, -119.044615);
        else if(place.equals("North Quad")) CI = new LatLng(34.163762, -119.044377);
        else if(place.equals("Ojai Hall")) CI = new LatLng(34.161681, -119.042588);
        else if(place.equals("Petit Salon")) CI = new LatLng(34.163592, -119.043529);
        else if(place.equals("Pizza 3.14")) CI = new LatLng(34.163304, -119.039495);
        else if(place.equals("Placer Hall")) CI = new LatLng(34.163408, -119.043039);
        else if(place.equals("Potrero Field")) CI = new LatLng(34.159963, -119.047577);
        else if(place.equals("Qumpling")) CI = new LatLng(34.162795, -119.039316);
        else if(place.equals("Santa Cruz Village")) CI = new LatLng(34.159627, -119.043825);
        else if(place.equals("Santa Rosa Village")) CI = new LatLng(34.158806, -119.042687);
        else if(place.equals("Solano Hall")) CI = new LatLng(34.163301, -119.045340);
        else if(place.equals("Student Union Building")) CI = new LatLng(34.161334, -119.044087);
        else if(place.equals("Topanga Hall")) CI = new LatLng(34.159992, -119.041634);
        else if(place.equals("Tortillas Grill")) CI = new LatLng(34.163000, -119.039426);
        else if(place.equals("Town Center")) CI = new LatLng(34.163130, -119.039261);
        else if(place.equals("Town Center Market")) CI = new LatLng(34.163018, -119.039088);
        else if(place.equals("Trinity Hall")) CI = new LatLng(34.159280, -119.042474);
        else if(place.equals("University Hall")) CI = new LatLng(34.162621, -119.043165);
        else if(place.equals("Sage Hall")) CI = new LatLng(34.164067, -119.042300);
        else if(place.equals("Sierra Hall")) CI = new LatLng(34.162250, -119.044508);
        else if(place.equals("Yuba Hall")) CI = new LatLng(34.164019, -119.041081);
        else if(place.equals("Malibu Hall")) CI = new LatLng(34.161260, -119.040836);
        else CI = new LatLng(34.16034, -119.042781);


        Event event = new Event(date,title ,place, d);

        String everything = "\nLocation: " + place +
                            "\nTime: " + date +
                            "\nDescription: " + d;

        mMap.addMarker(new MarkerOptions().position(CI).icon(BitmapDescriptorFactory.fromResource(R.drawable.cutest_dolphin))
                .title(eventString).snippet(everything));

        if(addData) {
            mDatabase.push().setValue(event);
            Toast.makeText(MapsActivity.this,"New Event Created!", Toast.LENGTH_SHORT).show();
        }
        //mDatabase.push().setValue(event);


    }

}
