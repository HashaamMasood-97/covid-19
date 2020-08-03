package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class inventory extends AppCompatActivity {
    TextView glooves, mask, waste, caps, sani, details;
    FirebaseUser user;
    String uid;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();


        database = FirebaseDatabase.getInstance().getReference().child("Ngo");

        glooves = (TextView) findViewById(R.id.glooves);
        mask = (TextView) findViewById(R.id.masks);
        waste = (TextView) findViewById(R.id.waste);
        caps = (TextView) findViewById(R.id.caps);
        sani = (TextView) findViewById(R.id.sani);
        details = (TextView) findViewById(R.id.details);


        this.setTitle("Inventory");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String mas=dataSnapshot.child(uid).child("mask").getValue(String.class);
                String san=dataSnapshot.child(uid).child("sani").getValue(String.class);
                String gloove=dataSnapshot.child(uid).child("glooves").getValue(String.class);
                String cap=dataSnapshot.child(uid).child("caps").getValue(String.class);
                String wast=dataSnapshot.child(uid).child("waste").getValue(String.class);
                String detail=dataSnapshot.child(uid).child("details").getValue(String.class);

                glooves.setText(gloove);
                mask.setText(mas);
                caps.setText(cap);
                sani.setText(san);
                waste.setText(wast);
                details.setText(detail);



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
}
