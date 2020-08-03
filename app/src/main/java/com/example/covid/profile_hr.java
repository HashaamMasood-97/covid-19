package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class profile_hr extends AppCompatActivity {

    ListView li;
    ArrayAdapter<String> adapter;
    FirebaseUser user;
    List<String> itemlist;
    String uid;
    DatabaseReference database;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_hr);
        li = (ListView) findViewById(R.id.listview);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        itemlist =new ArrayList<>();
        image=(ImageView) findViewById(R.id.abc);


        this.setTitle("Profile");
        database = FirebaseDatabase.getInstance().getReference().child("hospitals");



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                String email=dataSnapshot.child(uid).child("email").getValue(String.class);
                String phone=dataSnapshot.child(uid).child("phone").getValue(String.class);
                String  address=dataSnapshot.child(uid).child("address").getValue(String.class);
                String city=dataSnapshot.child(uid).child("city").getValue(String.class);
                String type=dataSnapshot.child(uid).child("type").getValue(String.class);
                String  doctors=dataSnapshot.child(uid).child("doctors").getValue(String.class);
                String vent=dataSnapshot.child(uid).child("vent").getValue(String.class);
                String bed=dataSnapshot.child(uid).child("bed").getValue(String.class);
                String icu=dataSnapshot.child(uid).child("icu").getValue(String.class);
                String mask=dataSnapshot.child(uid).child("mask").getValue(String.class);
                String sani=dataSnapshot.child(uid).child("sani").getValue(String.class);
                String glooves=dataSnapshot.child(uid).child("glooves").getValue(String.class);
                String caps=dataSnapshot.child(uid).child("caps").getValue(String.class);
                String waste=dataSnapshot.child(uid).child("waste").getValue(String.class);
                String time=dataSnapshot.child(uid).child("time").getValue(String.class);
                String date=dataSnapshot.child(uid).child("date").getValue(String.class);
                String imga=dataSnapshot.child(uid).child("url").getValue(String.class);


                Picasso.get()
                        .load(imga)
                        .into(image);


              /*  itemlist.add("Name: " +name + "\n" + "Email: "+ email  +"\n" +  "Phone: " +phone + "\n" +
                        "Address: " +address + "\n" + "City: " +city + "\n" + "Type: " +type + "\n"
                        + "Medical Equipments" + "\n"
                        + "Available Doctors: " + doctors + "\n" +
                        "Available Beds: " +bed + "\n" +
                        "Available ICU Rooms: " +icu + "\n" +
                        "Available Ventilators: " + vent + "\n" +
                        "PPE Status (In Stock)" +  "\n"   +
                        "Glooves: " +glooves + "\n" +"Masks: " +mask + "\n" +
                        "Sanitizers: " +sani + "\n" +
                        "Head Caps: " +caps + "\n" + "Waste Bags: " + waste
                        + "\n" + "Expected Run Out Time: " + time

                );
*/
                itemlist.add("                              Profile");
                itemlist.add("Name: " +name + "\n" + "Email: "+ email  +"\n" +  "Phone: " +phone + "\n" +
                        "Address: " +address + "\n" + "City: " +city + "\n" + "Type: " +type );
                itemlist.add("             Medical Equipments/ Supplies");
                itemlist.add( "Available Doctors: " + doctors + "\n" +
                        "Available Beds: " +bed + "\n" +
                        "Available ICU Rooms: " +icu + "\n" +
                        "Available Ventilators: " + vent);
                itemlist.add("                         PPE In Stock");
                itemlist.add( "Glooves: " +glooves + "\n" +"Masks: " +mask + "\n" +
                        "Sanitizers: " +sani + "\n" +
                        "Head Caps: " +caps + "\n" + "Waste Bags: " + waste );
                itemlist.add("Expected Run Out Time of PPE: " + time);
                itemlist.add("Updated At: " + date);

                adapter = new ArrayAdapter(profile_hr.this, R.layout.hos_pro, R.id.Info1, itemlist);
                li.setAdapter(adapter);






            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
