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

    //ListView li;
    //ArrayAdapter<String> adapter;
    FirebaseUser user;
   // List<String> itemlist;
    String uid;
    DatabaseReference database;
    ImageView image;
    TextView name, email, city, address, phone, type, doctors, vent, bed, icu, glooves, mask, waste, caps, sani, date, time  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_hr);
       // li = (ListView) findViewById(R.id.listview);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
      //  itemlist =new ArrayList<>();
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
      //  waste = (TextView) findViewById(R.id.waste);
        caps = (TextView) findViewById(R.id.head);
        sani = (TextView) findViewById(R.id.sanitizers);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        image=(ImageView)findViewById(R.id.img);


        this.setTitle("Profile");
        database = FirebaseDatabase.getInstance().getReference().child("hospitals");



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nam=dataSnapshot.child(uid).child("name").getValue(String.class);
                String emai=dataSnapshot.child(uid).child("email").getValue(String.class);
                String phon=dataSnapshot.child(uid).child("phone").getValue(String.class);
                String  addres=dataSnapshot.child(uid).child("address").getValue(String.class);
                String cit=dataSnapshot.child(uid).child("city").getValue(String.class);
                String typ=dataSnapshot.child(uid).child("type").getValue(String.class);
                String  doctor=dataSnapshot.child(uid).child("doctors").getValue(String.class);
                String ven=dataSnapshot.child(uid).child("vent").getValue(String.class);
                String be=dataSnapshot.child(uid).child("bed").getValue(String.class);
                String ic=dataSnapshot.child(uid).child("icu").getValue(String.class);
                String mas=dataSnapshot.child(uid).child("mask").getValue(String.class);
                String san=dataSnapshot.child(uid).child("sani").getValue(String.class);
                String gloove=dataSnapshot.child(uid).child("glooves").getValue(String.class);
                String cap=dataSnapshot.child(uid).child("caps").getValue(String.class);
                String wast=dataSnapshot.child(uid).child("waste").getValue(String.class);
                String tim=dataSnapshot.child(uid).child("time").getValue(String.class);
                String dat=dataSnapshot.child(uid).child("date").getValue(String.class);
                String imga=dataSnapshot.child(uid).child("url").getValue(String.class);


                Picasso.get()
                        .load(imga)
                        .into(image);

                name.setText(nam);
                email.setText(emai);
                city.setText(cit);
                phone.setText(phon);
                type.setText(typ);
                address.setText(addres);
                bed.setText(be);
                icu.setText(ic);
                doctors.setText(doctor);
                vent.setText(ven);
                time.setText(tim);
                date.setText(dat);
                glooves.setText(gloove);
                mask.setText(mas);
                caps.setText(cap);
                sani.setText(san);








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
               /* itemlist.add("                              Profile");
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

       */




            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
