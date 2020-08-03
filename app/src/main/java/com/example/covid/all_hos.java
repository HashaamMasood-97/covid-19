package com.example.covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class all_hos extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hos);
        this.setTitle("Hospitals");

        lv=(ListView) findViewById(R.id.LV);
        Query query = FirebaseDatabase.getInstance().getReference().child("hospitals");
        FirebaseListOptions<hospitalData> options= new FirebaseListOptions.Builder<hospitalData>()
                .setLayout(R.layout.allhosdata)
                .setQuery(query, hospitalData.class)
                .build();


        adapter2= new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView name= v.findViewById(R.id.name);
                TextView email= v.findViewById(R.id.email);
                TextView phone= v.findViewById(R.id.phone);
                ImageView image= v.findViewById(R.id.img);


                hospitalData user= (hospitalData) model;
                    name.setText("Name: " + user.getName().toString());
                    phone.setText("Phone: " + user.getPhone().toString());
                    email.setText("City: " + user.getCity().toString());
                    String message= user.getUrl().toString();

                Picasso.get()
                        .load(message)
                        .into(image);
            }
        };
        lv.setAdapter(adapter2);





      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                Intent Deletepdate=  new Intent(all_hos.this, all_hos_info.class);
                hospitalData user= (hospitalData) adapterView.getItemAtPosition(position);
                Deletepdate.putExtra("email", user.getEmail());
                Deletepdate.putExtra("key", user.getUid());
                Deletepdate.putExtra("name", user.getName());
                Deletepdate.putExtra("phone", user.getPhone());
                Deletepdate.putExtra("address", user.getAddress());
                Deletepdate.putExtra("city", user.getCity());
                Deletepdate.putExtra("type", user.getType());
                Deletepdate.putExtra("doctors", user.getDoctors());
                Deletepdate.putExtra("bed", user.getBed());
                Deletepdate.putExtra("vent", user.getVent());
                Deletepdate.putExtra("icu", user.getIcu());
                Deletepdate.putExtra("glooves", user.getGlooves());
                Deletepdate.putExtra("masks", user.getMask());
                Deletepdate.putExtra("waste", user.getWaste());
                Deletepdate.putExtra("sani", user.getSani());
                Deletepdate.putExtra("caps", user.getCaps());
                Deletepdate.putExtra("date", user.getDate());
                Deletepdate.putExtra("time", user.getTime());
                Deletepdate.putExtra("url", user.getUrl());
                Deletepdate.putExtra("longitude", user.getLongitude());
                Deletepdate.putExtra("latitude", user.getLatitude());






                startActivity(Deletepdate);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }



}

