package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class all_hos_info extends AppCompatActivity implements OnMapReadyCallback{
    DatabaseReference ref;
    TextView name, email, city, address, phone, type, doctors, vent, bed, icu, glooves, mask, waste, caps, sani, date, time  ;
    ImageView image;
    FirebaseUser user;
    String uid;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hos_info);

         this.setTitle("Details");

        name = (TextView) (findViewById(R.id.name));
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.contact);
        address = (TextView) findViewById(R.id.address);
        city = (TextView) findViewById(R.id.city);
        doctors = (TextView) findViewById(R.id.doctor);
        vent = (TextView) findViewById(R.id.vent);
        bed = (TextView) findViewById(R.id.bed);
        icu = (TextView) findViewById(R.id.icu);
        type = (TextView) findViewById(R.id.type);
        glooves = (TextView) findViewById(R.id.glooves);
        mask = (TextView) findViewById(R.id.masks);
        waste = (TextView) findViewById(R.id.waste);
        caps = (TextView) findViewById(R.id.head);
        sani = (TextView) findViewById(R.id.sanitizers);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        image=(ImageView)findViewById(R.id.img);



        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("hospital").child(key);


       /*
        waste.setText(getIntent().getStringExtra("waste"));
*/
        name.setText(getIntent().getStringExtra("name"));
        glooves.setText(getIntent().getStringExtra("glooves"));
        mask.setText(getIntent().getStringExtra("masks"));
        caps.setText(getIntent().getStringExtra("caps"));
        sani.setText(getIntent().getStringExtra("sani"));
        String message=getIntent().getStringExtra("url");

        Picasso.get()
                .load(message)
                .into(image);
        email.setText(getIntent().getStringExtra("email"));

        city.setText(getIntent().getStringExtra("city"));
        phone.setText(getIntent().getStringExtra("phone"));
        type.setText(getIntent().getStringExtra("type"));
        address.setText(getIntent().getStringExtra("address"));

        bed.setText(getIntent().getStringExtra("bed"));
        icu.setText(getIntent().getStringExtra("icu"));
        doctors.setText(getIntent().getStringExtra("doctors"));
        vent.setText(getIntent().getStringExtra("vent"));
        time.setText(getIntent().getStringExtra("time"));
        date.setText(getIntent().getStringExtra("date"));




        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;
        map.setMinZoomPreference(12.0f);
        map.setMaxZoomPreference(15.0f);
        String key = getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("hospital").child(key);
        String longitutde = getIntent().getStringExtra("longitude");
          String latitude = getIntent().getStringExtra("latitude");
        String name = getIntent().getStringExtra("name");
        double lon=Double.parseDouble(longitutde);
        double lat=Double.parseDouble(latitude);
        LatLng location=new LatLng(lat, lon);
        map.addMarker(new MarkerOptions().position(location).title(name));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}