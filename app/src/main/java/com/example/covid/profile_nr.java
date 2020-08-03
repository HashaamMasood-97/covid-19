package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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

public class profile_nr extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile_nr);
        li = (ListView) findViewById(R.id.listview3);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        itemlist =new ArrayList<>();
        image=(ImageView) findViewById(R.id.abc);


        database = FirebaseDatabase.getInstance().getReference().child("Ngo");

        this.setTitle("Profile");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                String email=dataSnapshot.child(uid).child("email").getValue(String.class);
                String phone=dataSnapshot.child(uid).child("phone").getValue(String.class);
                String  address=dataSnapshot.child(uid).child("address").getValue(String.class);
                String city=dataSnapshot.child(uid).child("city").getValue(String.class);
                String type=dataSnapshot.child(uid).child("type").getValue(String.class);
                String url=dataSnapshot.child(uid).child("url").getValue(String.class);
                Picasso.get()
                        .load(url)
                        .into(image);



                itemlist.add("Name: " +name);
                itemlist.add("Email: " +email);
                itemlist.add( "Phone: " +phone);
                itemlist.add( "Address: " +address);
                itemlist.add("City: " +city);
                itemlist.add("Type: " +type);





                adapter = new ArrayAdapter(profile_nr.this, android.R.layout.simple_list_item_1, itemlist);
                li.setAdapter(adapter);






            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
