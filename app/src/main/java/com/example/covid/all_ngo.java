package com.example.covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class all_ngo extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ngo);

        this.setTitle("NGO/Private Donor");

        lv=(ListView) findViewById(R.id.LV);
        Query query = FirebaseDatabase.getInstance().getReference().child("Ngo");
        FirebaseListOptions<ngoData> options= new FirebaseListOptions.Builder<ngoData>()
                .setLayout(R.layout.allngodata)
                .setQuery(query, ngoData.class)
                .build();


        adapter2= new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView name= v.findViewById(R.id.name);
                TextView email= v.findViewById(R.id.email);
                TextView phone= v.findViewById(R.id.phone);
                ImageView image= v.findViewById(R.id.img);




                ngoData user= (ngoData) model;

                name.setText("Name: " + user.getName().toString());

                phone.setText("Phone: " +user.getPhone().toString());
                email.setText("City: " + user.getCity().toString());
                String message= user.getUrl().toString();

                Picasso.get()
                        .load(message)
                        .into(image);





            }
        };
        lv.setAdapter(adapter2);

        //lv.setStackFromBottom(true);

        //  // ADD THIS LINE TO REVERSE ORDER!
        // adapter2.notifyDataSetChanged();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                Intent Deletepdate=  new Intent(all_ngo.this, all_ngo_info.class);
                ngoData user= (ngoData) adapterView.getItemAtPosition(position);
                Deletepdate.putExtra("email", user.getEmail());
                Deletepdate.putExtra("key", user.getuid());
                Deletepdate.putExtra("name", user.getName());
                Deletepdate.putExtra("phone", user.getPhone());
                Deletepdate.putExtra("address", user.getAddress());
                Deletepdate.putExtra("city", user.getCity());
                Deletepdate.putExtra("type", user.getType());
                Deletepdate.putExtra("glooves", user.getGlooves());
                Deletepdate.putExtra("masks", user.getMask());
                Deletepdate.putExtra("waste", user.getWaste());
                Deletepdate.putExtra("sani", user.getSani());
                Deletepdate.putExtra("caps", user.getCaps());
                Deletepdate.putExtra("Extra", user.getDetails());
                Deletepdate.putExtra("url", user.getUrl());





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

